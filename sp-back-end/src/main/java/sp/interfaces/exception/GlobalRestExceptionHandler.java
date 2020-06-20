package sp.interfaces.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import sp.application.shared.ApplicationException;
import sp.domain.shared.DomainError;
import sp.interfaces.exception.dto.ApiError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public final class GlobalRestExceptionHandler extends ResponseEntityExceptionHandler {

  private final Logger logger = LoggerFactory.getLogger(GlobalRestExceptionHandler.class);

  @ExceptionHandler(value = ApplicationException.class)
  protected ResponseEntity<Object> handleApplicationException(
      ApplicationException applicationException,
      HandlerMethod handlerMethod,
      WebRequest webRequest,
      HttpServletRequest servletRequest,
      HttpServletResponse servletResponse,
      HttpMethod httpMethod) {

    logger.error(applicationException.getMessage(), applicationException);

    Map<String, String> errorCodesPairs = new HashMap<>();
    applicationException
        .errorCodes()
        .forEach(
            errorCode ->
                errorCodesPairs.put(
                    String.format("e%05d", errorCode.getErrorCode()), errorCode.getErrorMessage()));

    if (applicationException.errorCodes().contains(DomainError.BAD_CREDENTIALS))
      return handleExceptionInternal(
          applicationException,
          new ApiError(HttpStatus.UNAUTHORIZED, errorCodesPairs),
          new HttpHeaders(),
          HttpStatus.UNAUTHORIZED,
          webRequest);
    else
      return handleExceptionInternal(
          applicationException,
          new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, errorCodesPairs),
          new HttpHeaders(),
          HttpStatus.INTERNAL_SERVER_ERROR,
          webRequest);
  }

  @ExceptionHandler(value = Exception.class)
  protected ResponseEntity<Object> handleAnyException(
      Exception ex,
      HandlerMethod handlerMethod,
      WebRequest webRequest,
      HttpServletRequest servletRequest,
      HttpServletResponse servletResponse,
      HttpMethod httpMethod) {

    logger.error(ex.getMessage(), ex);

    Map<String, String> errorCodesPairs = new HashMap<>();
    errorCodesPairs.put(
        String.format("e%05d", DomainError.UNEXPECTED_ERROR.getErrorCode()),
        DomainError.UNEXPECTED_ERROR.getErrorMessage());

    return handleExceptionInternal(
        ex,
        new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, errorCodesPairs),
        new HttpHeaders(),
        HttpStatus.INTERNAL_SERVER_ERROR,
        webRequest);
  }
}
