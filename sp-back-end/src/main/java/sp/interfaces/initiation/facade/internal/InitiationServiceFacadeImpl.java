package sp.interfaces.initiation.facade.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sp.application.service.InitiationService;
import sp.interfaces.initiation.facade.InitiationServiceFacade;

@Service
public class InitiationServiceFacadeImpl implements InitiationServiceFacade {

  private final InitiationService initiationService;

  @Autowired
  public InitiationServiceFacadeImpl(InitiationService initiationService) {
    this.initiationService = initiationService;
  }

  @Override
  public void storeDefaultData() {
    this.initiationService.storeDefaultData();
  }
}
