package sp.interfaces.usermanagement.facade;

import sp.application.shared.ApplicationException;
import sp.interfaces.usermanagement.facade.dto.LoginRequest;
import sp.interfaces.usermanagement.facade.dto.LoginResponse;

public interface UserManagementFacade {

    LoginResponse generateToken(LoginRequest authRequest) throws ApplicationException;

    Integer validateAndExtractUserIdFromToken(String token) throws ApplicationException;

}
