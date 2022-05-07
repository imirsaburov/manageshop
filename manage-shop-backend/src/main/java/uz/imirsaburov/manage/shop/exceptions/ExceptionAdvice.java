package uz.imirsaburov.manage.shop.exceptions;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.imirsaburov.manage.shop.dto.exception.ErrorResponseDTO;
import uz.imirsaburov.manage.shop.enums.ExceptionEnum;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionAdvice {

    private final MessageSource messageSource;

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<?> ex(Throwable throwable, HttpServletRequest request) {
        ExceptionEnum internalServerError = ExceptionEnum.INTERNAL_SERVER_ERROR;

        ErrorResponseDTO responseDTO = new ErrorResponseDTO();
        responseDTO.setStatus(internalServerError.getHttpStatus().value());
        responseDTO.setTimestamp(System.currentTimeMillis());
        responseDTO.setMessage(messageSource.getMessage(internalServerError.getLocaleCode(), new String[]{throwable.getMessage()}, LocaleContextHolder.getLocale()));
        responseDTO.setErrorCode(internalServerError.getErrorCode());
        responseDTO.setPath(request.getRequestURI());
        return ResponseEntity.status(internalServerError.getHttpStatus()).body(responseDTO);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<?> ex(BindException throwable, HttpServletRequest request) {
        ExceptionEnum javaxValidation = ExceptionEnum.VALIDATION;

        ErrorResponseDTO responseDTO = new ErrorResponseDTO();
        responseDTO.setStatus(javaxValidation.getHttpStatus().value());
        responseDTO.setTimestamp(System.currentTimeMillis());
        responseDTO.setMessage(throwable.getFieldError().getDefaultMessage());
        responseDTO.setErrorCode(javaxValidation.getErrorCode());
        responseDTO.setPath(request.getRequestURI());

        return ResponseEntity.status(javaxValidation.getHttpStatus()).body(responseDTO);
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<?> ex(AppException throwable, HttpServletRequest request) {

        ExceptionEnum exceptionEnum = throwable.getExceptionEnum();

        ErrorResponseDTO responseDTO = new ErrorResponseDTO();
        responseDTO.setStatus(exceptionEnum.getHttpStatus().value());
        responseDTO.setTimestamp(System.currentTimeMillis());
        responseDTO.setMessage(messageSource.getMessage(exceptionEnum.getLocaleCode(), throwable.getParams(), LocaleContextHolder.getLocale()));
        responseDTO.setErrorCode(exceptionEnum.getErrorCode());
        responseDTO.setPath(request.getRequestURI());

        return ResponseEntity.status(exceptionEnum.getHttpStatus()).body(responseDTO);
    }
}
