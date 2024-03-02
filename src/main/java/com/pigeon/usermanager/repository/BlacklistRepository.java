package com.pigeon.usermanager.repository;

import com.pigeon.usermanager.model.entity.BlacklistEntity;
import com.pigeon.usermanager.model.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface BlacklistRepository extends CrudRepository<BlacklistEntity, Long> {

    void deleteBlacklistEntityByOwnerAndUser(UserEntity owner, UserEntity user);

    Page<BlacklistEntity> findAllByOwner(UserEntity owner, Pageable pageable);
}
