package sp.interfaces.appconfig.facade.internal;

import sp.application.service.ConfigService;
import sp.interfaces.appconfig.facade.AppConfigServiceFacade;
import sp.interfaces.appconfig.facade.dto.AppConfigDTO;
import sp.interfaces.appconfig.facade.internal.assembler.AppConfigDTOAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppConfigServiceFacadeImpl implements AppConfigServiceFacade {

  private final ConfigService configService;

  @Autowired
  public AppConfigServiceFacadeImpl(ConfigService configService) {
    this.configService = configService;
  }

  @Override
  public AppConfigDTO getAppConfig() {
    AppConfigDTOAssembler assembler = new AppConfigDTOAssembler();
    return assembler.toDTO(this.configService.retrieveAppConfig());
  }
}
