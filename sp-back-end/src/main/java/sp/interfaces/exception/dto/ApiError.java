package sp.interfaces.exception.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

public class ApiError {
  private HttpStatus status;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
  private LocalDateTime timestamp;

  private Map<String, String> errorCodes;

  public ApiError(HttpStatus status, Map<String, String> errorCodes) {
    this.status = status;
    this.timestamp = LocalDateTime.now();
    this.errorCodes = errorCodes;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public ApiError setStatus(HttpStatus status) {
    this.status = status;
    return this;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public ApiError setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
    return this;
  }

  public Map<String, String> getErrorCodes() {
    return errorCodes;
  }

  public ApiError setErrorCodes(Map<String, String> errorCodes) {
    this.errorCodes = errorCodes;
    return this;
  }
}
