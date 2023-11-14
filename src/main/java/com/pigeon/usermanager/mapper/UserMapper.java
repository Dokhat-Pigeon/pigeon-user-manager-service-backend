package com.pigeon.usermanager.mapper;

import com.pigeon.usermanager.model.cache.RegistrationCache;
import com.pigeon.usermanager.model.dto.RegistrationDto;
import com.pigeon.usermanager.model.entity.UserEntity;
import com.pigeon.usermanager.model.enums.UserStatus;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.UUID;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    RegistrationCache toCache(RegistrationDto registration, UUID recordId, UserStatus status, Long timeToLive);

    UserEntity toEntity(RegistrationCache registration);
}
