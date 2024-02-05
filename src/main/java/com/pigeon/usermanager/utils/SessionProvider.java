package com.pigeon.usermanager.utils;

import com.pigeon.usermanager.service.impl.TokenServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

@Component
@RequiredArgsConstructor
public class SessionProvider {

    public HttpSession getSession() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        return ((ServletRequestAttributes) attributes).getRequest().getSession();
    }
}
