package com.pigeon.usermanager.mapper;

import com.pigeon.usermanager.model.cache.UserOnlineCache;
import com.pigeon.usermanager.model.dto.UserOnlineDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserOnlineMapper {

    UserOnlineDto toDto(UserOnlineCache userOnline);
}
