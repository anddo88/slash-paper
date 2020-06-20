package sp.infrastructure.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "SP_ORGANIZATION_NESTED_SETS")
public class OrgNestedSet {

  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.TABLE,
      generator = "ORGANIZATION_NESTED_SETS_SEQ_GENERATOR")
  @TableGenerator(
      name = "ORGANIZATION_NESTED_SETS_SEQ_GENERATOR", table = "SEQ_GENERATOR",
      pkColumnName = "SEQ_NAME", pkColumnValue = "ORGANIZATION_NESTED_SETS_SEQ_PK",
      valueColumnName = "SEQ_VALUE", initialValue = 1, allocationSize = 1
  )
  private Integer id;

  @Column(name = "LEVEL")
  private Integer level;

  @Column(name = "LFT")
  private Integer left;

  @Column(name = "RGT")
  private Integer right;

  @Column(name = "NODE_NUMBER")
  private Integer nodeNumber;

  @Column(name = "NODE_COUNT")
  private Integer nodeCount;

  @Column(name = "SORT_PATH")
  private byte[] sortPath;

  @ManyToOne
  @JoinColumn(name = "ORG_ID", referencedColumnName = "ID")
  private OrgEntity orgEntity;

  public Integer getId() {
    return id;
  }

  public OrgNestedSet setId(Integer id) {
    this.id = id;
    return this;
  }

  public Integer getLevel() {
    return level;
  }

  public OrgNestedSet setLevel(Integer level) {
    this.level = level;
    return this;
  }

  public Integer getLeft() {
    return left;
  }

  public OrgNestedSet setLeft(Integer left) {
    this.left = left;
    return this;
  }

  public Integer getRight() {
    return right;
  }

  public OrgNestedSet setRight(Integer right) {
    this.right = right;
    return this;
  }

  public Integer getNodeNumber() {
    return nodeNumber;
  }

  public OrgNestedSet setNodeNumber(Integer nodeNumber) {
    this.nodeNumber = nodeNumber;
    return this;
  }

  public Integer getNodeCount() {
    return nodeCount;
  }

  public OrgNestedSet setNodeCount(Integer nodeCount) {
    this.nodeCount = nodeCount;
    return this;
  }

  public byte[] getSortPath() {
    return sortPath;
  }

  public OrgNestedSet setSortPath(byte[] sortPath) {
    this.sortPath = sortPath;
    return this;
  }

  public OrgEntity getOrgEntity() {
    return orgEntity;
  }

  public OrgNestedSet setOrgEntity(OrgEntity orgEntity) {
    this.orgEntity = orgEntity;
    return this;
  }
}
