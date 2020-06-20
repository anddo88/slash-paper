package sp.interfaces.usermanagement.facade.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sp.application.service.UserManagementService;
import sp.application.shared.ApplicationException;
import sp.domain.model.user.User;
import sp.interfaces.usermanagement.facade.UserManagementFacade;
import sp.interfaces.usermanagement.facade.dto.LoginRequest;
import sp.interfaces.usermanagement.facade.dto.LoginResponse;
import sp.interfaces.usermanagement.facade.internal.assembler.LoginDTOAssembler;

@Component
public class UserManagementFacadeImpl implements UserManagementFacade {

  private final UserManagementService userManagementService;

  @Autowired
  public UserManagementFacadeImpl(UserManagementService userManagementService) {
    this.userManagementService = userManagementService;
  }

  @Override
  public LoginResponse generateToken(LoginRequest loginRequest) throws ApplicationException {
    LoginDTOAssembler assembler = new LoginDTOAssembler();
    User enteredUser = assembler.fromDTO(loginRequest);
    String token = userManagementService.generateToken(enteredUser);
    return assembler.toDTO(token);
  }

  @Override
  public Integer validateAndExtractUserIdFromToken(String token) throws ApplicationException {
    return userManagementService.verifyTokenAndExtractUserIdFrom(token);
  }
}
