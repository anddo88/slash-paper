package sp.infrastructure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import sp.domain.model.user.User;
import sp.domain.service.AuthService;
import sp.domain.shared.DomainError;
import sp.domain.shared.DomainException;

@Service
public class AuthServiceImpl implements AuthService {

  private final AuthenticationManager authenticationManager;

  @Autowired
  public AuthServiceImpl(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  @Override
  public void authenticateUsingNameAndPassword(User user) throws DomainException {
    try {
      this.authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(user.name(), user.password().value()));
    } catch (BadCredentialsException ex) {
      throw new DomainException("Bad credentials Entered", ex, DomainError.BAD_CREDENTIALS);
    }
  }
}
