package sp.application.service;

import sp.application.shared.ApplicationException;
import sp.domain.model.user.User;

public interface UserManagementService {

  String generateToken(User user) throws ApplicationException;

  Integer verifyTokenAndExtractUserIdFrom(String token) throws ApplicationException;
}
