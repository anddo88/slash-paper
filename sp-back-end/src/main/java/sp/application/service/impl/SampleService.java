package sp.application.service.impl;

import sp.infrastructure.persistence.entity.ProfileEntity;
import sp.infrastructure.persistence.repository.jpa.ProfileRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SampleService {
    private final ProfileRepositoryJpa profileRepositoryJpa;

    @Autowired
    public SampleService(ProfileRepositoryJpa profileRepositoryJpa) {
        this.profileRepositoryJpa = profileRepositoryJpa;
    }

    @Transactional(readOnly = true)
    public void storeDefaultData() {
        Optional<ProfileEntity> x = this.profileRepositoryJpa.findTopBy();
        System.out.println("**********************************");
        if (x.isPresent()) System.out.println(x.get().getPrivilegeEntitySet().size());
        else System.out.println("null");
        System.out.println("**********************************");
    }
}
