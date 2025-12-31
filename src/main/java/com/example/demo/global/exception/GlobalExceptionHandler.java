package com.example.demo.global.exception;

import com.example.demo.global.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice// 이 어노테이션 덕분에 예외가 터지면 이 클래스가 가로채서 처리할수 있게 해줌.
public class GlobalExceptionHandler {

    // 우리가 만든 CustomException이 발생하면 잡는다.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidation(MethodArgumentNotValidException e) {
        // 에러가 발생한 필드와 메시지를 맵에 담는다.
        Map<String, String> errors = new LinkedHashMap<>();
        for (FieldError fe : e.getBindingResult().getFieldErrors()) { // 에러 리스트를 출력해줌.
            errors.putIfAbsent(fe.getField(), fe.getDefaultMessage());
            // 예: "username" -> "아이디는 필수 입력 값입니다."
        } /*
        putIfAbsent 는 왜 쓸까?
        예)--------------------------------------------------------
        @NotBlank(message = "제목은 필수입니다.")
        @Size(min = 5, message = "제목은 5글자 이상이어야 합니다.")
        private String title;
        -----------------------------------------------------------
        이것처럼 예외가 2개 이상인건 putIfAbsent 이걸 사용해야 둘다 예외가 터져도 첫번째 저장된 예외를 사용자에게 보여줌.
        * 만약 put을 사용하게 되면 마지막 예외를 보여줘서 헷갈리게 만들수 있다.
        */

        ErrorCode errorCode = ErrorCode.VALIDATION_ERROR;
        return ResponseEntity // ResponseEntity 타입 (사용자에게 json 형태로 보이게 해줌.)
                .status(errorCode.getStatus()) // 현재 상태코드 예) 400 등등
                .body(ApiResponse.error(errorCode, errors)); // 사용자에게 보여질 body 부분. 매개변수로 두개를 넣는다.
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse<Void>> handleCustom(CustomException e) {
        ErrorCode errorCode = e.getErrorCode();
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(ApiResponse.error(errorCode));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(Exception e) {
        ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(ApiResponse.error(errorCode));
    }

}
