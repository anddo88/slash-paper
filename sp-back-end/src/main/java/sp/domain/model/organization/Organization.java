package sp.domain.model.organization;

public class Organization {

  private Integer id;
  private String name;

  public Organization(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

  public Integer id() {
    return id;
  }

  public Organization setId(Integer id) {
    this.id = id;
    return this;
  }

  public String name() {
    return name;
  }

  public Organization setName(String name) {
    this.name = name;
    return this;
  }
}
