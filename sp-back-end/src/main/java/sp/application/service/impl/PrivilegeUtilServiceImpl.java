package sp.application.service.impl;

import sp.domain.model.privilege.Privilege;
import sp.domain.service.UtilService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PrivilegeUtilServiceImpl implements UtilService {


  @Override
  public <T> boolean equals(Set<T> firstSet, Set<T> secondSet) {
    return firstSet.equals(secondSet);
  }

  @Override
  public Set<Privilege> differentPrivilegesBetween(Set<Privilege> allPrivileges, Set<Privilege> storedPrivileges) {
    allPrivileges.removeAll(storedPrivileges);
    return allPrivileges;
  }
}
