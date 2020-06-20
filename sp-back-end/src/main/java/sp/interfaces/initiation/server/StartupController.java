package sp.interfaces.initiation.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import sp.interfaces.initiation.facade.InitiationServiceFacade;

@Configuration
public class StartupController implements ApplicationListener<ApplicationReadyEvent> {

  private final InitiationServiceFacade initiationServiceFacade;

  @Autowired
  public StartupController(InitiationServiceFacade initiationServiceFacade) {
    this.initiationServiceFacade = initiationServiceFacade;
  }

  @Override
  public void onApplicationEvent(ApplicationReadyEvent event) {
    this.initiationServiceFacade.storeDefaultData();
  }
}
