package com.pigeon.usermanager.service.impl;

import com.pigeon.usermanager.mapper.UserMapper;
import com.pigeon.usermanager.model.dto.UserDto;
import com.pigeon.usermanager.model.entity.BlacklistEntity;
import com.pigeon.usermanager.model.entity.UserEntity;
import com.pigeon.usermanager.repository.BlacklistRepository;
import com.pigeon.usermanager.service.BlackListService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlackListServiceImpl implements BlackListService {

    private final Integer PAGE_SIZE = 20;

    private final UserServiceImpl userService;
    private final BlacklistRepository blacklistRepository;
    private final UserMapper userMapper;

    @Override
    public void addUser(Long id) {
        UserEntity ownerUser = userService.getUserFromSession();
        UserEntity blockedUser = userService.getUserById(id);
        BlacklistEntity blacklist = BlacklistEntity.builder()
                .owner(ownerUser)
                .user(blockedUser)
                .build();
        blacklistRepository.save(blacklist);
    }

    @Override
    public void deleteUser(Long id) {
        UserEntity ownerUser = userService.getUserFromSession();
        UserEntity blockedUser = userService.getUserById(id);
        blacklistRepository.deleteBlacklistEntityByOwnerAndUser(ownerUser, blockedUser);
    }

    @Override
    public Page<UserDto> getUsersFromBlacklist(Pageable pageable) {
        UserEntity ownerUser = userService.getUserFromSession();
        Page<BlacklistEntity> blacklist = blacklistRepository.findAllByOwner(ownerUser, pageable);

        return blacklist.map((entity) -> userMapper.toDto(entity.getUser()));
    }
}
