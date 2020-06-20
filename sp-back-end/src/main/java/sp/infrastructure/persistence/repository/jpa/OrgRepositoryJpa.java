package sp.infrastructure.persistence.repository.jpa;

import sp.infrastructure.persistence.entity.OrgEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrgRepositoryJpa extends JpaRepository<OrgEntity, Integer> {
  List<OrgEntity> queryByParent(OrgEntity orgEntity);
}
