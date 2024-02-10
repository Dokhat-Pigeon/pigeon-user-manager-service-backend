package com.pigeon.usermanager.interceptor;

import com.pigeon.usermanager.model.enums.WsTopic;
import com.pigeon.usermanager.security.JwtAuthentication;
import com.pigeon.usermanager.security.JwtParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.core.DestinationResolutionException;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthenticationChannelInterceptor implements ChannelInterceptor {

    private final JwtParser jwtParser;

    @Override
    public Message<?> preSend(@NonNull Message<?> message, @NonNull MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        if (accessor != null && accessor.getCommand() != null) {
            switch (accessor.getCommand()) {
                case CONNECT -> this.connectAuthenticate(accessor);
                case SUBSCRIBE -> this.subscribeAuthenticate(accessor);
            }
        }
        return message;
    }

    private void connectAuthenticate(StompHeaderAccessor accessor) {
        List<String> authHeaders = accessor.getNativeHeader(HttpHeaders.AUTHORIZATION);
        if (authHeaders != null && !authHeaders.isEmpty()) {
            String token = authHeaders.get(0).replace("Bearer ", "");
            Authentication authentication = jwtParser.parse(token);
            accessor.setUser(authentication);
        } else {
            throw new AccessDeniedException("Authorization header is not found");
        }
    }

    private void subscribeAuthenticate(StompHeaderAccessor accessor) {
        String destination = accessor.getDestination();
        if (accessor.getUser() instanceof JwtAuthentication authentication) {
            Optional<WsTopic> wsTopic = WsTopic.byPattern(destination);
            if (wsTopic.isEmpty()) throw new DestinationResolutionException("Not found destination");
            boolean isAccess = wsTopic
                    .filter(WsTopic::isSecure)
                    .map(topic -> topic.getPath(authentication.getUsername()))
                    .filter(path -> path.equals(destination))
                    .isPresent();
            if (!isAccess) throw new AccessDeniedException("Forbidden to this destination");
        }
    }
}
