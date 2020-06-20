package sp.infrastructure.persistence.repository.jpa;

import sp.infrastructure.persistence.entity.UserEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryJpa extends JpaRepository<UserEntity, Integer>, QueryByExampleExecutor<UserEntity> {
  Optional<UserEntity> findByUserName(String userName);
  Optional<UserEntity> findTopBy();
}
