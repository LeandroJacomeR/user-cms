package com.united.coders.cmsuser.adapter.driving.http.handlers.impl;

import com.united.coders.cmsuser.adapter.driving.http.dto.request.LoginRequestDto;
import com.united.coders.cmsuser.adapter.driving.http.dto.request.UserRequestDto;
import com.united.coders.cmsuser.adapter.driving.http.dto.response.JwtResponseDto;
import com.united.coders.cmsuser.adapter.driving.http.dto.response.RegisterResponseDto;
import com.united.coders.cmsuser.adapter.driving.http.handlers.IAuthHandler;
import com.united.coders.cmsuser.adapter.driving.http.handlers.IUserHandler;
import com.united.coders.cmsuser.adapter.driving.http.mapper.IUserRequestMapper;
import com.united.coders.cmsuser.configuration.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
@RequiredArgsConstructor
public class AuthHandlerImpl implements IAuthHandler {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final IUserHandler userHandler;

    @Qualifier("IUserRequestMapper")
    private final IUserRequestMapper userRequestMapper;

    @Override
    public JwtResponseDto login(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        //UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return new JwtResponseDto(jwt);
    }

    @Override
    public RegisterResponseDto register(UserRequestDto userRequestDto) {
        userHandler.createUser(userRequestDto);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userRequestDto.getEmail(),
                        userRequestDto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        return new RegisterResponseDto(userRequestMapper.toUserResponseDto(userRequestDto), jwt);
    }

    @Override
    public JwtResponseDto refresh(JwtResponseDto jwtResponseDto) throws ParseException {
        String token = jwtProvider.refreshToken(jwtResponseDto);
        return new JwtResponseDto(token);
    }
}
