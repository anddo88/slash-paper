package sp.infrastructure.persistence.entity;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "SP_USER")
public class UserEntity {

  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.TABLE, generator = "USER_SEQ_GENERATOR")
  @TableGenerator(
      name = "USER_SEQ_GENERATOR", table = "SEQ_GENERATOR", pkColumnName = "SEQ_NAME",
      pkColumnValue = "USER_SEQ_PK", valueColumnName = "SEQ_VALUE", initialValue = 1,
      allocationSize = 1
  )
  private Integer id;

  @Column(name = "USER_NAME")
  private String userName;

  @Column(name = "PASSWORD")
  private String password;

  @Column(name = "ACTIVE")
  private boolean enabled;

  @ManyToMany
  @JoinTable(
      name = "SP_USER_PROFILE",
      joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"),
      inverseJoinColumns = @JoinColumn(name = "PROFILE_ID", referencedColumnName = "ID"))
  private Set<ProfileEntity> profileEntitySet;

  public Integer getId() {
    return id;
  }

  public UserEntity setId(Integer id) {
    this.id = id;
    return this;
  }

  public String getUserName() {
    return userName;
  }

  public UserEntity setUserName(String userName) {
    this.userName = userName;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public UserEntity setPassword(String password) {
    this.password = password;
    return this;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public UserEntity setEnabled(boolean enabled) {
    this.enabled = enabled;
    return this;
  }

  public Set<ProfileEntity> getProfileEntitySet() {
    return profileEntitySet;
  }

  public UserEntity setProfileEntitySet(Set<ProfileEntity> profileEntitySet) {
    this.profileEntitySet = profileEntitySet;
    return this;
  }
}
