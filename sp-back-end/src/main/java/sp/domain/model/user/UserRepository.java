package sp.domain.model.user;

import java.util.Optional;

public interface UserRepository {

  Optional<User> findByUserNameWithProfiles(String userName);
  Optional<User> findByIdNameWithProfiles(Integer id);

  User save(User user);

  Optional<User> findAny();

  Optional<User> findByUserName(String username);

    Optional<User> findById(Integer id);
}
