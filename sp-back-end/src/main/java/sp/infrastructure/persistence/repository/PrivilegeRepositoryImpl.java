package sp.infrastructure.persistence.repository;

import sp.domain.model.privilege.Privilege;
import sp.domain.model.privilege.PrivilegeConstant;
import sp.domain.model.privilege.PrivilegeRepository;
import sp.infrastructure.persistence.converter.PrivilegeConverter;
import sp.infrastructure.persistence.entity.PrivilegeEntity;
import sp.infrastructure.persistence.repository.jpa.PrivilegeRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class PrivilegeRepositoryImpl implements PrivilegeRepository {
  private final PrivilegeRepositoryJpa privilegeRepositoryJpa;
  private final PrivilegeConverter privilegeConverter;

  @Autowired
  public PrivilegeRepositoryImpl(
      PrivilegeRepositoryJpa privilegeRepositoryJpa, PrivilegeConverter privilegeConverter) {
    this.privilegeRepositoryJpa = privilegeRepositoryJpa;
    this.privilegeConverter = privilegeConverter;
  }

  @Override
  public Set<Privilege> findAllStored() {
    return this.privilegeConverter.toDomainSet(this.privilegeRepositoryJpa.findAllBy());
  }

  public Set<Privilege> findAll() {
    return Set.of(PrivilegeConstant.values())
        .parallelStream()
        .map(Privilege::new)
        .collect(Collectors.toSet());
  }

  public Set<Privilege> save(Set<Privilege> privileges) {
    Set<PrivilegeEntity> privilegeEntitySet = this.privilegeConverter.toPrivilegeEntitySet(privileges);
    List<PrivilegeEntity> saved = this.privilegeRepositoryJpa.saveAll(privilegeEntitySet);
    return this.privilegeConverter.toDomainSet(saved);
  }
}
