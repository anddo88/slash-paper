package sp.interfaces.usermanagement.facade.internal.assembler;

import sp.domain.model.user.Password;
import sp.domain.model.user.PasswordState;
import sp.domain.model.user.User;
import sp.interfaces.usermanagement.facade.dto.LoginRequest;
import sp.interfaces.usermanagement.facade.dto.LoginResponse;

public class LoginDTOAssembler {
  public User fromDTO(LoginRequest loginRequest) {
    return new User(
        loginRequest.getUsername(),
        new Password(loginRequest.getPassword(), PasswordState.BASE64_ENCRYPTED));
  }


  public LoginResponse toDTO(String token) {
    return new LoginResponse(token);
  }
}
