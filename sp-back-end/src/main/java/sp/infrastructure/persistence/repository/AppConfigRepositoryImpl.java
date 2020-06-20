package sp.infrastructure.persistence.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sp.domain.model.config.AppConfig;
import sp.domain.model.config.AppConfigRepository;
import sp.infrastructure.config.ConfigProperties;

@Repository
public class AppConfigRepositoryImpl implements AppConfigRepository {

  private final ConfigProperties configProperties;

  @Autowired
  public AppConfigRepositoryImpl(ConfigProperties configProperties) {
    this.configProperties = configProperties;
  }

  @Override
  public AppConfig findAll() {
    return AppConfig.read(
        this.configProperties.isInitializingDataRequired(),
        this.configProperties.getRsaPrivateKey(),
        this.configProperties.getJwtSigningKey(),
        this.configProperties.getJwtExpirationTime());
  }
}
