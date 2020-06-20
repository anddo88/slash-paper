package sp.domain.shared;

import java.util.Set;

public class DomainException extends Exception {

  private final Set<DomainError> errorCodes;

  public DomainException(String debugMessage, DomainError... errorCodes) {
    super(debugMessage);
    this.errorCodes = Set.of(errorCodes);
  }

  public DomainException(String debugMessage, Throwable ex, DomainError... errorCodes) {
    super(debugMessage, ex);
    this.errorCodes = Set.of(errorCodes);
  }

  public Set<DomainError> errorCodes() {
    return this.errorCodes;
  }

  @Override
  public String toString() {
    return "DomainException {errorCodes= " + errorCodes + '}';
  }
}
