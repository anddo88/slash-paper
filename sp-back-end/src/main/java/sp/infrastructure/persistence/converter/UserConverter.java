package sp.infrastructure.persistence.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sp.domain.model.user.Password;
import sp.domain.model.user.PasswordState;
import sp.domain.model.user.User;
import sp.infrastructure.persistence.entity.UserEntity;

@Component
public class UserConverter {

  private final ProfileConverter profileConverter;

  @Autowired
  public UserConverter(ProfileConverter profileConverter) {
    this.profileConverter = profileConverter;
  }

  public UserEntity toPersistenceEntity(User user) {
    return new UserEntity()
        .setId(user.id())
        .setPassword(user.password().value())
        .setUserName(user.name())
        .setEnabled(user.enabled())
        .setProfileEntitySet(
            user.profiles() == null
                ? null
                : this.profileConverter.toPersistenceEntitySet(user.profiles()));
  }

  public User toDomain(UserEntity userEntity) {
    return new User(
        userEntity.getId(),
        userEntity.getUserName(),
        new Password(userEntity.getPassword(), PasswordState.HASHED),
        userEntity.isEnabled());
  }

  public User toDomainWithProfiles(UserEntity userEntity) {
    return new User(
            userEntity.getId(),
            userEntity.getUserName(),
            new Password(userEntity.getPassword(), PasswordState.HASHED),
            userEntity.isEnabled())
        .assignProfiles(
            userEntity.getProfileEntitySet() == null
                ? null
                : this.profileConverter.toDomainSet(userEntity.getProfileEntitySet()));
  }
}
