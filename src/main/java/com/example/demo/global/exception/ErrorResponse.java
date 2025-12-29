package com.example.demo.global.exception;

import java.util.Map;

public record ErrorResponse(
        String code,
        String message,
        Map<String, String> fieldErrors
) {
    //                             총 한개의 매개변수를 받아야 됨.
    public static ErrorResponse of(ErrorCode errorCode) {
        return new ErrorResponse(errorCode.name(), errorCode.getMessage(), null);
    }
//                                 총 두개의 매개변수를 받아야 됨.
    public static ErrorResponse of(ErrorCode errorCode, Map<String,String> fieldErrors) {
        return new ErrorResponse(errorCode.name(), errorCode.getMessage(), fieldErrors);
    }
}
