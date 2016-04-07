package todo.backend.rest.exception;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class ExceptionResolver {

    private final Logger log = LoggerFactory.getLogger(ExceptionResolver.class);

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public @ResponseBody ErrorResponse validationException(HttpServletRequest request, MethodArgumentNotValidException exception) {
        if (log.isErrorEnabled()) {
            log.error(exception.getMessage(), exception);
        }
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors().stream().map(e -> new FieldError(e.getObjectName(), e.getField(), e.getCode())).collect(Collectors.toList());
        return new ErrorResponse(fieldErrors, exception.getMessage());
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public @ResponseBody ErrorResponse serverError(HttpServletRequest request, Throwable exception) {
        if (log.isErrorEnabled()) {
            log.error(exception.getMessage(), exception);
        }
        return new ErrorResponse(exception.getClass().getSimpleName(), exception.getMessage());
    }
}
