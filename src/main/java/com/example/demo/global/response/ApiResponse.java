package com.example.demo.global.response;


import com.example.demo.global.exception.ErrorCode;

import java.util.Map;

public record ApiResponse<T>(
        boolean success, // 성공/실패
        String code, // OK/Error
        String message,
        T data, // 보내고자 하는 데이터
        Map<String, String> errors // 검증 실패 시
) {
    // 성공 - 데이터 있는 경우
    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(true, "OK", "요청이 성공했습니다.", data, null);
    }
    // 성공 - 데이터가 없는 경우
    public static  ApiResponse<Void> ok() {
        return new ApiResponse<>(true, "OK", "요청이 성공했습니다.", null, null);
    }

    // 실패 - 필드 에러 없음
    public static  ApiResponse<Void> error(ErrorCode errorCode) {
        return new ApiResponse<>(false,errorCode.name(), errorCode.getMessage(), null,null);
    }

    // 실패 - 필드 에러 있음
    public static  ApiResponse<Void> error(ErrorCode errorCode, Map<String,String> errors) {
        return new ApiResponse<>(false,errorCode.name(), errorCode.getMessage(), null,errors);
    }
}
