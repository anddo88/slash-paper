package sp.application.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sp.application.service.InitiationService;
import sp.domain.model.config.AppConfigRepository;
import sp.domain.model.privilege.Privilege;
import sp.domain.model.privilege.PrivilegeRepository;
import sp.domain.model.profile.Profile;
import sp.domain.model.profile.ProfileRepository;
import sp.domain.model.user.Password;
import sp.domain.model.user.PasswordState;
import sp.domain.model.user.User;
import sp.domain.model.user.UserRepository;
import sp.domain.service.UtilService;

import java.util.Set;

@Service
public class InitiationServiceImpl implements InitiationService {

  private final UserRepository userRepository;
  private final ProfileRepository profileRepository;
  private final AppConfigRepository appConfigRepository;
  private final PrivilegeRepository privilegeRepository;
  private final UtilService privilegeUtilService;

  @Autowired
  public InitiationServiceImpl(
      UserRepository userRepository,
      ProfileRepository profileRepository,
      AppConfigRepository appConfigRepository,
      PrivilegeRepository privilegeRepository,
      UtilService privilegeUtilService) {
    this.userRepository = userRepository;
    this.profileRepository = profileRepository;
    this.appConfigRepository = appConfigRepository;
    this.privilegeRepository = privilegeRepository;
    this.privilegeUtilService = privilegeUtilService;
  }

  @Override
  @Transactional(readOnly = true)
  public void storeDefaultData() {
    if (checkIfInitializationIsRequired()) {
      initializePrivileges();
      initializeProfilesAndUsers();
    }
  }

  private void initializeProfilesAndUsers() {
    if (this.profileRepository.findAny().isEmpty() && this.userRepository.findAny().isEmpty()) {
      Profile defaultProfile =
          this.profileRepository.save(
              new Profile("admin").assignPrivileges(this.privilegeRepository.findAll()));
      this.userRepository.save(
          new User("admin", new Password("{noop}admin", PasswordState.RAW), true).assignProfiles(Set.of(defaultProfile)));
    }
  }

  private void initializePrivileges() {
    Set<Privilege> storedPrivileges = this.privilegeRepository.findAllStored();
    Set<Privilege> allPrivileges = this.privilegeRepository.findAll();
    if (!privilegeUtilService.equals(allPrivileges, storedPrivileges)) {
      Set<Privilege> notStoredPrivileges =
          privilegeUtilService.differentPrivilegesBetween(allPrivileges, storedPrivileges);
      this.privilegeRepository.save(notStoredPrivileges);
    }
  }

  private boolean checkIfInitializationIsRequired() {
    return this.appConfigRepository.findAll().initializingDataRequired();
  }
}
