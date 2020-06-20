package sp.infrastructure.persistence.entity;

import java.util.Set;
import javax.persistence.CascadeType;
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
@Table(name = "SP_PROFILE")
public class ProfileEntity {

  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.TABLE, generator = "PROFILE_SEQ_GENERATOR")
  @TableGenerator(
      name = "PROFILE_SEQ_GENERATOR", table = "SEQ_GENERATOR", pkColumnName = "SEQ_NAME",
      pkColumnValue = "PROFILE_SEQ_PK", valueColumnName = "SEQ_VALUE", initialValue = 1,
      allocationSize = 1
  )
  private Integer id;

  @Column(name = "PROFILE_NAME")
  private String name;

  @ManyToMany
  @JoinTable(
      name = "SP_PROFILE_PRIVILEGE",
      joinColumns = @JoinColumn(name = "PROFILE_ID", referencedColumnName = "ID"),
      inverseJoinColumns = @JoinColumn(name = "OP_ID", referencedColumnName = "ID")
  )
  private Set<PrivilegeEntity> privilegeEntitySet;

  public Integer getId() {
    return this.id;
  }

  public ProfileEntity setId(Integer id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public ProfileEntity setName(String name) {
    this.name = name;
    return this;
  }

  public Set<PrivilegeEntity> getPrivilegeEntitySet() {
    return privilegeEntitySet;
  }

  public ProfileEntity setPrivilegeEntitySet(Set<PrivilegeEntity> privilegeEntitySet) {
    this.privilegeEntitySet = privilegeEntitySet;
    return this;
  }
}
