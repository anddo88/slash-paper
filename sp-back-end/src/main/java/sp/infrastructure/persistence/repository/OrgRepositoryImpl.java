package sp.infrastructure.persistence.repository;

import sp.domain.model.organization.Organization;
import sp.domain.model.organization.OrganizationRepository;
import sp.infrastructure.persistence.converter.DomainEntityConverter;
import sp.infrastructure.persistence.converter.OrgConverter;
import sp.infrastructure.persistence.entity.OrgEntity;
import sp.infrastructure.persistence.entity.OrgNestedSet;
import sp.infrastructure.persistence.repository.jpa.OrgNestedSetRepositoryJpa;
import sp.infrastructure.persistence.repository.jpa.OrgRepositoryJpa;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class OrgRepositoryImpl implements OrganizationRepository {

  private final OrgRepositoryJpa orgRepositoryJpa;
  private final OrgConverter orgConverter;
  private OrgNestedSetRepositoryJpa orgNestedSetRepositoryJpa;

  @Autowired
  public OrgRepositoryImpl(OrgRepositoryJpa orgRepositoryJpa, OrgNestedSetRepositoryJpa orgNestedSetRepositoryJpa,
                           OrgConverter orgConverter) {
    this.orgRepositoryJpa = orgRepositoryJpa;
    this.orgConverter = orgConverter;
    this.orgNestedSetRepositoryJpa = orgNestedSetRepositoryJpa;
  }

  @Override
  public void findRootOrganization() {
    List<OrgEntity> orgEntityList = this.orgRepositoryJpa.queryByParent(null);
    List<OrgNestedSet> orgNestedSetList = new ArrayList<>();
    int nodeNumberCounter = 1;
    for (OrgEntity orgEntity : orgEntityList) {
      ByteBuffer bb = ByteBuffer.allocate(4);
      bb.putInt(orgEntity.getId());
      OrgNestedSet orgnestedSet = new OrgNestedSet().setLevel(1).setOrgEntity(orgEntity).setSortPath(bb.array()).setNodeNumber(nodeNumberCounter);
      orgNestedSetList.add(orgnestedSet);
      iterateOverChilds(orgEntity, orgNestedSetList, orgnestedSet, nodeNumberCounter);
    }

    this.orgNestedSetRepositoryJpa.saveAll(orgNestedSetList);
  }

  private void iterateOverChilds(OrgEntity orgEntity, List<OrgNestedSet> orgNestedSetList, OrgNestedSet orgnestedSet, int nodeNumberCounter) {
    List<OrgEntity> orgEntityChildren = this.orgRepositoryJpa.queryByParent(orgEntity);
    if (orgEntityChildren != null) {
      for (OrgEntity orgEntityChild : orgEntityChildren) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
          outputStream.write(orgnestedSet.getSortPath());
          ByteBuffer bb = ByteBuffer.allocate(4);
          bb.putInt(orgEntityChild.getId());
          outputStream.write(bb.array());
        } catch (IOException e) {
          e.printStackTrace();
        }
        OrgNestedSet orgNestedSetChild = new OrgNestedSet().setLevel(orgnestedSet.getLevel() + 1).setOrgEntity(orgEntityChild).setSortPath(outputStream.toByteArray()).setNodeNumber(++nodeNumberCounter);
        orgNestedSetList.add(orgNestedSetChild);
        iterateOverChilds(orgEntityChild, orgNestedSetList, orgNestedSetChild, nodeNumberCounter);
      }
    }
  }
}
