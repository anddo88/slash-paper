package sp.domain.service;

import sp.domain.model.user.User;
import sp.domain.shared.DomainException;

public interface AuthService {
  void authenticateUsingNameAndPassword(User user) throws DomainException;
}
