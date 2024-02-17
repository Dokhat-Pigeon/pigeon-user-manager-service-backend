package com.pigeon.usermanager.service.impl;

import com.pigeon.usermanager.model.dto.UserDto;
import com.pigeon.usermanager.model.entity.BlacklistEntity;
import com.pigeon.usermanager.model.entity.UserEntity;
import com.pigeon.usermanager.repository.BlacklistRepository;
import com.pigeon.usermanager.service.BlackListService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlackListServiceImpl implements BlackListService {

    private final UserServiceImpl userService;
    private final BlacklistRepository blacklistRepository;
    private final UserOnlineServiceImpl userOnlineService;

    @Override
    public void addUser(Long id) {
        UserEntity ownerUser = userService.getCurrentUser();
        UserEntity blockedUser = userService.getUserById(id);
        BlacklistEntity blacklist = BlacklistEntity.builder()
                .owner(ownerUser)
                .user(blockedUser)
                .build();
        blacklistRepository.save(blacklist);
    }

    @Override
    public void deleteUser(Long id) {
        UserEntity ownerUser = userService.getCurrentUser();
        UserEntity blockedUser = userService.getUserById(id);
        blacklistRepository.deleteBlacklistEntityByOwnerAndUser(ownerUser, blockedUser);
    }

    @Override
    public Page<UserDto> getUsersFromBlacklist(UserEntity owner, Pageable pageable) {
        Page<BlacklistEntity> blacklist = blacklistRepository.findAllByOwner(owner, pageable);

        return blacklist.map((entity) -> userService.getDtoByEntity(entity.getUser()));
    }
}
