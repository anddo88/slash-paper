package sp.interfaces.usermanagement.facade.dto;

public class LoginRequest {
  private String username;
  private String password;

  public String getPassword() {
    return password;
  }

  public LoginRequest setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getUsername() {
    return username;
  }

  public LoginRequest setUsername(String username) {
    this.username = username;
    return this;
  }
}
