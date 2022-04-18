package com.gts.godting.config.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // 400 BAD_REQUEST
    INVALID_REFRESH_TOKEN(BAD_REQUEST, "리프레시 토큰이 유효하지 않습니다."),
    MISMATCH_REFRESH_TOKEN(BAD_REQUEST, "리프레시 토큰 정보가 일치하지 않습니다."),
    FILE_UPLOAD_FAILED(BAD_REQUEST, "파일 업로드에 실패하였습니다."),

    // 401 UNAUTHORIZED
    INVALID_AUTH_TOKEN(UNAUTHORIZED, "권한 정보가 없는 토큰입니다"),
    UNAUTHORIZED_MEMBER(UNAUTHORIZED, "현재 내 계정 정보가 존재하지 않습니다"),

    // 404 NOT_FOUND
    MEMBER_NOT_FOUND(NOT_FOUND, "유저 정보를 찾을 수 없습니다."),
    REFRESH_TOKEN_NOT_FOUND(NOT_FOUND, "로그아웃 된 사용자입니다."),

    // 404 NOT_FOUND, OAuth2 Provider Error
    OAUTH2_PROVIDER_NOT_FOUNT(NOT_FOUND, "잘못된 프로바이더 입니다."),

    // 409 CONFLICT, Database Error
    DUPLICATE_RESOURCE(CONFLICT, "데이터가 이미 존재합니다."),

    // 410 GONE
    FILE_DELETED(GONE, "파일이 사용자에 의해 삭제되었습니다."),

    // 500 INTERNAL_SERVER_ERROR
    SERVER_FILE_UPLOAD_FAILED(INTERNAL_SERVER_ERROR, "서버 오류로 파일 업로드에 실패하였습니다.")

    ;

    private final HttpStatus httpStatus;
    private final String detail;
}
