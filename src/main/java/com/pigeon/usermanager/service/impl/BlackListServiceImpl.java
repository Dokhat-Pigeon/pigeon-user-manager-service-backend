package com.pigeon.usermanager.service.impl;

import com.pigeon.usermanager.model.entity.BlacklistEntity;
import com.pigeon.usermanager.model.entity.UserEntity;
import com.pigeon.usermanager.repository.BlacklistRepository;
import com.pigeon.usermanager.service.BlackListService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BlackListServiceImpl implements BlackListService {

    private final Integer PAGE_SIZE = 20;

    private final TokenServiceImpl tokenService;
    private final UserServiceImpl userService;
    private final BlacklistRepository blacklistRepository;

    @Override
    public void addUser(Long id) {
        UserEntity ownerUser = tokenService.getUserFromSession();
        UserEntity blockedUser = userService.getUserById(id);
        BlacklistEntity blacklist = BlacklistEntity.builder()
                .owner(ownerUser)
                .user(blockedUser)
                .build();
        blacklistRepository.save(blacklist);
    }

    @Override
    public void deleteUser(Long id) {
        UserEntity ownerUser = tokenService.getUserFromSession();
        UserEntity blockedUser = userService.getUserById(id);
        blacklistRepository.deleteBlacklistEntityByOwnerAndUser(ownerUser, blockedUser);
    }

    @Override
    public List<UserEntity> getUsersFromBlacklist(Integer page) {
        UserEntity ownerUser = tokenService.getUserFromSession();
        Pageable pageable = (Pageable) PageRequest.of(page, PAGE_SIZE);
        List<BlacklistEntity> blacklist = blacklistRepository.findAllByOwner(ownerUser, pageable);

        return blacklist.stream()
                .map(BlacklistEntity::getUser)
                .toList();

    }
}
