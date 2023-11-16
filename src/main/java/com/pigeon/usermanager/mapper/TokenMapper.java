package com.pigeon.usermanager.mapper;

import com.pigeon.usermanager.model.cache.TokenCache;
import com.pigeon.usermanager.model.dto.TokenDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TokenMapper {

    TokenCache toCache(TokenDto token);

    TokenDto toDto(TokenCache token);

}
