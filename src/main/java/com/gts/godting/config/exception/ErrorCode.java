package com.gts.godting.config.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.CONFLICT;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // 400 BAD_REQUEST
    INVALID_REFRESH_TOKEN(BAD_REQUEST, "리프레시 토큰이 유효하지 않습니다."),
    MISMATCH_REFRESH_TOKEN(BAD_REQUEST, "리프레시 토큰 정보가 일치하지 않습니다."),

    // 401 UNAUTHORIZED
    MEMBER_NOT_FOUND(NOT_FOUND, "유저 정보를 찾을 수 없습니다."),
    REFRESH_TOKEN_NOT_FOUND(NOT_FOUND, "로그아웃 된 사용자입니다."),

    // 409 CONFLICT
    DUPLICATE_RESOURCE(CONFLICT, "데이터가 이미 존재합니다."),
    ;

    private final HttpStatus httpStatus;
    private final String detail;
}