package sp.infrastructure.persistence.repository;

import sp.domain.model.user.User;
import sp.domain.model.user.UserRepository;
import sp.infrastructure.persistence.converter.UserConverter;
import sp.infrastructure.persistence.entity.UserEntity;
import sp.infrastructure.persistence.repository.jpa.UserRepositoryJpa;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

  private final UserRepositoryJpa userRepositoryJpa;
  private final UserConverter userConverter;

  @Autowired
  public UserRepositoryImpl(UserRepositoryJpa userRepositoryJpa, UserConverter userConverter) {
    this.userRepositoryJpa = userRepositoryJpa;
    this.userConverter = userConverter;
  }

  @Override
  public Optional<User> findByUserNameWithProfiles(String userName) {
    Optional<UserEntity> optionalUsersEntity = this.userRepositoryJpa.findByUserName(userName);
    return optionalUsersEntity.map(this.userConverter::toDomainWithProfiles);
  }

  @Override
  public Optional<User> findByIdNameWithProfiles(Integer id) {
    Optional<UserEntity> optionalUsersEntity = this.userRepositoryJpa.findById(id);
    return optionalUsersEntity.map(this.userConverter::toDomainWithProfiles);
  }

  @Override
  public Optional<User> findByUserName(String username) {
    Optional<UserEntity> optionalUsersEntity = this.userRepositoryJpa.findByUserName(username);
    return optionalUsersEntity.map(this.userConverter::toDomain);
  }

  @Override
  public Optional<User> findById(Integer id) {
    Optional<UserEntity> optionalUsersEntity = this.userRepositoryJpa.findById(id);
    return optionalUsersEntity.map(this.userConverter::toDomain);
  }

  @Override
  public User save(User user) {
    UserEntity userEntity = this.userConverter.toPersistenceEntity(user);
    UserEntity savedUserEntity = this.userRepositoryJpa.save(userEntity);
    return this.userConverter.toDomain(savedUserEntity);
  }

  @Override
  public Optional<User> findAny() {
    Optional<UserEntity> optionalUsersEntity = this.userRepositoryJpa.findTopBy();
    return optionalUsersEntity.map(this.userConverter::toDomain);
  }


}
