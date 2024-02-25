package com.pigeon.usermanager.service.impl;

import com.pigeon.usermanager.exception.enums.http.UserErrorCode;
import com.pigeon.usermanager.exception.http.UserServiceException;
import com.pigeon.usermanager.model.dto.UserDto;
import com.pigeon.usermanager.model.entity.BlacklistEntity;
import com.pigeon.usermanager.model.entity.UserEntity;
import com.pigeon.usermanager.repository.BlacklistRepository;
import com.pigeon.usermanager.service.BlackListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BlackListServiceImpl implements BlackListService {

    private final UserServiceImpl userService;
    private final BlacklistRepository blacklistRepository;

    @Override
    public void addUser(Long id) {
        UserEntity ownerUser = userService.getCurrentUser();
        UserEntity blockedUser = userService.getUserById(id);
        BlacklistEntity blacklist = BlacklistEntity.builder()
                .owner(ownerUser)
                .user(blockedUser)
                .build();
        try {
            blacklistRepository.save(blacklist);
        } catch (Exception e) {
            throw new UserServiceException(UserErrorCode.USER_IN_BLACKLIST_ALREADY_EXIST);
        }
    }

    @Override
    public void deleteUser(Long id) {
        UserEntity ownerUser = userService.getCurrentUser();
        UserEntity blockedUser = userService.getUserById(id);
        blacklistRepository.deleteBlacklistEntityByOwnerAndUser(ownerUser, blockedUser);
    }

    @Override
    public Page<UserDto> getUsersFromBlacklist(Pageable pageable) {
        UserEntity owner = userService.getCurrentUser();
        Page<UserDto> users = blacklistRepository.findAllByOwner(owner, pageable)
                .map(BlacklistEntity::getUser)
                .map(userService::getDtoByEntity);
        log.info("Returned blacklist item count: {}, for user: {}, request: {}",
                users.stream().count(), owner.getLogin(), pageable);
        return users;
    }
}
