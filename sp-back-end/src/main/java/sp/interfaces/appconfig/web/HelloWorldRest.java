package sp.interfaces.appconfig.web;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sp.application.service.InitiationService;
import sp.application.service.impl.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.security.Principal;

@Controller
public class HelloWorldRest {

  private final InitiationService initiationService;
  private final SampleService sampleService;

  @Autowired
  public HelloWorldRest(InitiationService initiationService, SampleService sampleService) {
    this.initiationService = initiationService;
    this.sampleService = sampleService;
  }

  @GetMapping(path = "/api/hello")
  ResponseEntity<String> hello(Principal principal,
                               Authentication authentication) {
    System.out.println(principal.getName());
    System.out.println(authentication.getAuthorities());
    return new ResponseEntity<>("Hello World", HttpStatus.OK);
  }
}
