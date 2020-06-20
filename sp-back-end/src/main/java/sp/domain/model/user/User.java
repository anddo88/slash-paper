package sp.domain.model.user;

import sp.domain.model.profile.Profile;
import sp.domain.service.CryptoService;
import sp.domain.shared.DomainException;

import java.util.Collections;
import java.util.Set;

public class User {

  private Integer id;
  private String name;
  private Password password;
  private boolean enabled;
  private Set<Profile> profiles = Collections.emptySet();

  public User(String name, Password password, boolean enabled) {
    this(name, password);
    this.enabled = enabled;
  }

  public User(Integer id, String name, Password password, boolean enabled) {
    this(name, password, enabled);
    this.id = id;
  }

  public User(String name, Password password) {
    this.name = name;
    this.password = password;
  }

  public Integer id() {
    return id;
  }

  public User specifyAnId(Integer id) {
    this.id = id;
    return this;
  }

  public String name() {
    return name;
  }

  public User setName(String name) {
    this.name = name;
    return this;
  }

  public Password password() {
    return password;
  }

  public User assignPassword(Password password) {
    this.password = password;
    return this;
  }

  public boolean enabled() {
    return enabled;
  }

  public User makeEnabled(boolean enabled) {
    this.enabled = enabled;
    return this;
  }

  public Set<Profile> profiles() {
    return profiles;
  }

  public User assignProfiles(Set<Profile> profiles) {
    this.profiles = profiles;
    return this;
  }

  public String generateToken(
      CryptoService cryptoService, String jwtSigningKey, String jwtExpirationTime)
      throws DomainException {
    return cryptoService.generateJwtTokenForUser(this.id, jwtSigningKey, jwtExpirationTime);
  }
}
