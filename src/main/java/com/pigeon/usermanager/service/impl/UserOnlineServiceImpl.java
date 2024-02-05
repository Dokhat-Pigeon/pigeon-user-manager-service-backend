package com.pigeon.usermanager.service.impl;

import com.pigeon.usermanager.exception.runtime.UserOnlineServiceException;
import com.pigeon.usermanager.mapper.UserOnlineMapper;
import com.pigeon.usermanager.model.cache.UserOnlineCache;
import com.pigeon.usermanager.model.dto.UserOnlineDto;
import com.pigeon.usermanager.model.entity.UserEntity;
import com.pigeon.usermanager.model.enums.WsTopic;
import com.pigeon.usermanager.repository.cache.UserOnlineCacheRepository;
import com.pigeon.usermanager.service.UserOnlineService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

import static com.pigeon.usermanager.exception.enums.runtime.UserOnlineErrorCode.USER_ONLINE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserOnlineServiceImpl implements UserOnlineService {

    private final UserOnlineMapper userOnlineMapper;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final UserOnlineCacheRepository userOnlineCacheRepository;

    @Override
    public void create(UserEntity user) {
        UserOnlineCache userOnline = UserOnlineCache.builder()
                .id(user.getId())
                .isOnline(false)
                .lastOnlineDate(ZonedDateTime.now())
                .build();
        userOnlineCacheRepository.save(userOnline);
    }

    @Override
    public void update(UserEntity user, boolean isOnline) {
        UserOnlineCache onlineCache = this.getUserOnline(user);
        onlineCache.setOnline(isOnline);
        onlineCache.setLastOnlineDate(ZonedDateTime.now());
        userOnlineCacheRepository.save(onlineCache);
        this.send(onlineCache, user.getLogin());
    }

    private void send(UserOnlineCache onlineCache, String login) {
        UserOnlineDto dto = userOnlineMapper.toDto(onlineCache);
        simpMessagingTemplate.convertAndSend(WsTopic.ONLINE_OTHER.getPath(login), dto);
    }

    private UserOnlineCache getUserOnline(UserEntity user) {
        return userOnlineCacheRepository.findById(user.getId())
                .orElseThrow(() -> new UserOnlineServiceException(USER_ONLINE_NOT_FOUND, user.getLogin()));
    }
}
