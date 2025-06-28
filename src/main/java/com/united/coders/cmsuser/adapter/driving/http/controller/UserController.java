package com.united.coders.cmsuser.adapter.driving.http.controller;

import com.united.coders.cmsuser.adapter.driving.http.dto.request.UserRequestDto;
import com.united.coders.cmsuser.adapter.driving.http.dto.response.UserResponseDto;
import com.united.coders.cmsuser.adapter.driving.http.handlers.IUserHandler;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.united.coders.cmsuser.configuration.Contants.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final IUserHandler userHandler;

    @GetMapping
    @SecurityRequirement(name = "jwt")
    public ResponseEntity<List<UserResponseDto>> getUsers() {
        return new ResponseEntity<>(userHandler.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "jwt")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(userHandler.getUser(id), HttpStatus.OK);
    }

    @PostMapping
    @SecurityRequirement(name = "jwt")
    public ResponseEntity<Map<String, String>> createUser(@RequestBody UserRequestDto userRequestDto) {
        userHandler.createUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(RESPONSE_MESSAGE_KEY, USER_CREATED_MESSAGE));
    }

    @PutMapping("/{id}")
    @SecurityRequirement(name = "jwt")
    public ResponseEntity<Map<String, String>> updateteUser(@RequestBody UserRequestDto userRequestDto, @PathVariable Long id) {
        userHandler.updateUser(userRequestDto, id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Collections.singletonMap(RESPONSE_MESSAGE_KEY, USER_UPDATED_MESSAGE));
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "jwt")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable Long id) {
        userHandler.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(Collections.singletonMap(RESPONSE_MESSAGE_KEY, USER_DELETED_MESSAGE));
    }
}
