package com.pigeon.usermanager.listener;

import com.pigeon.usermanager.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.security.Principal;

import static java.util.Objects.requireNonNull;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserOnlineListener {

    private final UserService userService;

    @EventListener
    public void userIsConnected(SessionConnectEvent event) {
        Principal principal = requireNonNull(event.getUser());
        boolean isChanged = userService.updateOnlineStatus(principal, true);
        if (isChanged) log.info("User {} is online now", principal.getName());
    }

    @EventListener
    public void userIsDisconnected(SessionDisconnectEvent event) {
        Principal principal = requireNonNull(event.getUser());
        boolean isChanged = userService.updateOnlineStatus(principal, false);
        if (isChanged) log.info("User {} is offline now", principal.getName());
    }
}
