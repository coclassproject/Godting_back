package com.gts.godting.config.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomExcepition extends RuntimeException {

    private final ErrorCode errorCode;

}
