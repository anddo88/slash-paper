package sp.infrastructure.persistence.repository.jpa;

import sp.infrastructure.persistence.entity.OrgNestedSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrgNestedSetRepositoryJpa extends JpaRepository<OrgNestedSet, Integer> {
}
