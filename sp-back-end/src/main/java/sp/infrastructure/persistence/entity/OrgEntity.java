package sp.infrastructure.persistence.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "SP_ORGANIZATION")
public class OrgEntity {

  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.TABLE, generator = "ORGANIZATION_SEQ_GENERATOR")
  @TableGenerator(
      name = "ORGANIZATION_SEQ_GENERATOR", table = "SEQ_GENERATOR", pkColumnName = "SEQ_NAME",
      pkColumnValue = "ORGANIZATION_SEQ_PK", valueColumnName = "SEQ_VALUE", initialValue = 1,
      allocationSize = 1
  )
  private Integer id;

  @Column(name = "NAME")
  private String name;

  @ManyToOne
  @JoinColumn(name = "PARENT_ID", referencedColumnName = "ID")
  private OrgEntity parent;

  @OneToMany(mappedBy = "orgEntity")
  private List<OrgNestedSet> orgNestedSetList;

  public Integer getId() {
    return id;
  }

  public OrgEntity setId(Integer id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public OrgEntity setName(String name) {
    this.name = name;
    return this;
  }

  public OrgEntity getParent() {
    return parent;
  }

  public OrgEntity setParent(OrgEntity parent) {
    this.parent = parent;
    return this;
  }

  public List<OrgNestedSet> getOrgNestedSetList() {
    return orgNestedSetList;
  }

  public OrgEntity setOrgNestedSetList(List<OrgNestedSet> orgNestedSetList) {
    this.orgNestedSetList = orgNestedSetList;
    return this;
  }
}
