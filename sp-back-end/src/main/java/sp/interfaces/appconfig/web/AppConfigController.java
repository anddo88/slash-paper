package sp.interfaces.appconfig.web;

import org.springframework.security.access.prepost.PreAuthorize;
import sp.infrastructure.utility.APIController;
import sp.interfaces.appconfig.facade.AppConfigServiceFacade;
import sp.interfaces.appconfig.facade.dto.AppConfigDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

@APIController
public class AppConfigController {

  private final AppConfigServiceFacade appConfigServiceFacade;

  @Autowired
  public AppConfigController(AppConfigServiceFacade appConfigServiceFacade) {
    this.appConfigServiceFacade = appConfigServiceFacade;
  }

  @GetMapping("/app-config")
  @PreAuthorize("isAnonymous() or isAuthenticated()")
  public AppConfigDTO list() {
    return this.appConfigServiceFacade.getAppConfig();
  }
}
