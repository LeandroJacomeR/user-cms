package com.united.coders.cmsuser.configuration;

import com.united.coders.cmsuser.adapter.driven.jpa.postgres.exceptions.NoDataFoundException;
import com.united.coders.cmsuser.adapter.driven.jpa.postgres.exceptions.RoleNotAllowedForCreationException;
import com.united.coders.cmsuser.adapter.driven.jpa.postgres.exceptions.UserAlreadyExistsException;
import com.united.coders.cmsuser.adapter.driven.jpa.postgres.exceptions.UserNotFoundException;
import com.united.coders.cmsuser.domain.exception.FormatEmailException;
import com.united.coders.cmsuser.domain.exception.FormatPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.united.coders.cmsuser.configuration.Contants.*;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errorMessages = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            if (error instanceof FieldError) {
                FieldError fieldError = (FieldError) error;
                errorMessages.add(fieldError.getField() + ": " + fieldError.getDefaultMessage());
            } else {
                errorMessages.add(error.getDefaultMessage());
            }
        }
        return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundException(NoDataFoundException noDataFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, NO_DATA_FOUND_MESSAGE));
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleUserAlreadyExistsException(UserAlreadyExistsException userAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, USER_ALREADY_EXISTS_MESSAGE));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFoundException(UserNotFoundException userNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, USER_NOT_FOUND_MESSAGE));
    }

    @ExceptionHandler(RoleNotAllowedForCreationException.class)
    public ResponseEntity<Map<String, String>> handleRoleNotAllowedForCreationException(RoleNotAllowedForCreationException roleNotAllowedForCreationException) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, ROLE_NOT_ALLOWED_MESSAGE));
    }

    @ExceptionHandler(FormatEmailException.class)
    public ResponseEntity<Map<String, String>> handleFormatEmailException(FormatEmailException formatEmailException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, EMAIL_INVALID_FORMAT_MESSAGE));
    }

    @ExceptionHandler(FormatPasswordException.class)
    public ResponseEntity<Map<String, String>> handleFormatPasswordException(FormatPasswordException formatPasswordException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, PASSWORD_INVALID_FORMAT_MESSAGE));
    }
}
