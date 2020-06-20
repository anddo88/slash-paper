package sp.interfaces.appconfig.facade.internal.assembler;

import sp.domain.model.config.AppConfig;
import sp.interfaces.appconfig.facade.dto.AppConfigDTO;

public class AppConfigDTOAssembler {
  public AppConfigDTO toDTO(AppConfig appConfig) {
    return new AppConfigDTO();
  }
}
