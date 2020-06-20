package sp.infrastructure.persistence.repository;

import sp.domain.model.profile.Profile;
import sp.domain.model.profile.ProfileRepository;
import sp.infrastructure.persistence.converter.PrivilegeConverter;
import sp.infrastructure.persistence.converter.ProfileConverter;
import sp.infrastructure.persistence.entity.ProfileEntity;
import sp.infrastructure.persistence.repository.jpa.PrivilegeRepositoryJpa;
import sp.infrastructure.persistence.repository.jpa.ProfileRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class ProfileRepositoryImpl implements ProfileRepository {

  private final ProfileRepositoryJpa profileRepositoryJpa;
  private final PrivilegeRepositoryJpa privilegeRepositoryJpa;
  private final ProfileConverter profileConverter;
  private final PrivilegeConverter privilegeConverter;

  @Autowired
  public ProfileRepositoryImpl(
      ProfileRepositoryJpa profileRepositoryJpa,
      PrivilegeRepositoryJpa privilegeRepositoryJpa,
      ProfileConverter profileConverter,
      PrivilegeConverter privilegeConverter) {
    this.profileRepositoryJpa = profileRepositoryJpa;
    this.privilegeRepositoryJpa = privilegeRepositoryJpa;
    this.profileConverter = profileConverter;
    this.privilegeConverter = privilegeConverter;
  }

  @Override
  public Optional<Profile> findAny() {
    Optional<ProfileEntity> profileEntityOptional = this.profileRepositoryJpa.findTopBy();
    if(profileEntityOptional.isPresent())
      return Optional.of(this.profileConverter.toDomain(profileEntityOptional.get()));
    else
      return Optional.empty();
  }

  @Override
  public Profile save(Profile profile) {
    ProfileEntity savedProfileEntity =
        this.profileRepositoryJpa.save(this.profileConverter.toPersistenceEntity(profile));
    return this.profileConverter.toDomain(savedProfileEntity);
  }
}
