package com.pigeon.usermanager.service;

import com.pigeon.usermanager.model.dto.UserOnlineDto;
import com.pigeon.usermanager.model.entity.UserEntity;

/**
 * Service for working with user online status
 */
public interface UserOnlineService {

    /**
     * Create default user online status
     *
     * @param user User for initialization online status
     */
    void create(UserEntity user);

    /**
     * Update online status
     *
     * @param user User for update status
     * @param isOnline Online flag
     * @return Status been changed
     */
    boolean update(UserEntity user, boolean isOnline);

    /**
     * Get online status
     *
     * @param user {@link UserEntity}
     * @return {@link UserOnlineDto}
     */
    UserOnlineDto get(UserEntity user);
}
