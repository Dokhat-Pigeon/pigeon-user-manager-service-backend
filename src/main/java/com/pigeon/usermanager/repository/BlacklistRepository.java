package com.pigeon.usermanager.repository;

import com.pigeon.usermanager.model.entity.BlacklistEntity;
import com.pigeon.usermanager.model.entity.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface BlacklistRepository extends PagingAndSortingRepository<BlacklistEntity, Long> {

    Optional<BlacklistEntity> findByOwnerAndUser(UserEntity owner, UserEntity user);

    void deleteBlacklistEntityByOwnerAndUser(UserEntity owner, UserEntity user);

    List<BlacklistEntity> findAllByOwner(UserEntity owner, Pageable pageable);
}
