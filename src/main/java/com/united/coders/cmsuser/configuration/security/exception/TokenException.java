package com.united.coders.cmsuser.configuration.security.exception;

import org.springframework.security.core.AuthenticationException;

import static com.united.coders.cmsuser.configuration.Contants.TOKEN_RESPONSE_ERROR_MESSAGE;

public class TokenException extends AuthenticationException {
    public TokenException() {
        super(TOKEN_RESPONSE_ERROR_MESSAGE);
    }
}
