package com.pigeon.usermanager.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOnlineDto {

    private Boolean isOnline;

    private ZonedDateTime lastOnlineDate;
}
