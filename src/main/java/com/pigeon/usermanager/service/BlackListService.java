package com.pigeon.usermanager.service;

import com.pigeon.usermanager.model.dto.UserDto;
import com.pigeon.usermanager.model.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service for working with a blacklist
 */
public interface BlackListService {

    /**
     * Add user to blacklist
     *
     * @param id User identifier
     */
    void addUser(Long id);

    /**
     * Delete user from blacklist
     *
     * @param id User identifier
     */
    void deleteUser(Long id);

    /**
     * Delete user from blacklist
     *
     * @param page Current page
     * @param owner Owner user
     * @return {@link List<UserEntity>}
     */
    Page<UserDto> getUsersFromBlacklist(UserEntity owner, Pageable page);
}
