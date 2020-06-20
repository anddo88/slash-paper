package sp.application.shared;

import sp.domain.shared.DomainException;
import sp.domain.shared.DomainError;

import java.util.HashSet;
import java.util.Set;

public class ApplicationException extends Exception {

  private final Set<DomainError> errorCodes;

  public ApplicationException(Exception exception) {
    super(exception);
    if (exception instanceof DomainException)
      this.errorCodes = ((DomainException) exception).errorCodes();
    else {
      this.errorCodes = new HashSet<>();
      this.errorCodes.add(DomainError.UNEXPECTED_ERROR);
    }
  }

  public boolean hasErrorCodes() {
    return (this.errorCodes.size() > 0);
  }

  public Set<DomainError> errorCodes() {
    return this.errorCodes;
  }
}
