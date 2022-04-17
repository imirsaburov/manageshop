package uz.imirsaburov.manage.shop.exceptions;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.imirsaburov.manage.shop.dto.exception.ErrorResponseDTO;
import uz.imirsaburov.manage.shop.enums.LocaleEnum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionAdvice {

    private final MessageSource messageSource;

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<?> ex(Throwable throwable, HttpServletRequest request) {
        ErrorResponseDTO responseDTO = new ErrorResponseDTO();
        responseDTO.setStatus(500);
        responseDTO.setTimeStamp(System.currentTimeMillis());
        responseDTO.setMessage(throwable.getLocalizedMessage());
        responseDTO.setError(throwable.getClass().getName());
        responseDTO.setPath(request.getRequestURI());

        return ResponseEntity.status(500)
                .body(responseDTO);
    }

    @ExceptionHandler(HttpStatusCodeException.class)
    public ResponseEntity<?> ex(HttpStatusCodeException throwable, HttpServletRequest request) {
        System.out.println(LocaleContextHolder.getLocale());
        ErrorResponseDTO responseDTO = new ErrorResponseDTO();
        responseDTO.setStatus(500);
        responseDTO.setTimeStamp(System.currentTimeMillis());
        responseDTO.setMessage(messageSource.getMessage(throwable.getLocaleCode(),throwable.getParams(),LocaleContextHolder.getLocale()));
        responseDTO.setError(throwable.getClass().getSimpleName());
        responseDTO.setPath(request.getRequestURI());

        return ResponseEntity.status(500)
                .body(responseDTO);
    }
}
