package sp.interfaces.usermanagement.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import sp.application.shared.ApplicationException;
import sp.infrastructure.utility.APIController;
import sp.interfaces.usermanagement.facade.UserManagementFacade;
import sp.interfaces.usermanagement.facade.dto.LoginRequest;
import sp.interfaces.usermanagement.facade.dto.LoginResponse;

@APIController
public class UserManagementController {

    private final UserManagementFacade userManagementFacade;

    @Autowired
    public UserManagementController(UserManagementFacade userManagementFacade) {
        this.userManagementFacade = userManagementFacade;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest authRequest) throws ApplicationException {
        return this.userManagementFacade.generateToken(authRequest);
    }

}
