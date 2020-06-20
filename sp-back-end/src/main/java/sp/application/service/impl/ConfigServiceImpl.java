package sp.application.service.impl;

import sp.application.service.ConfigService;
import sp.domain.model.config.AppConfig;
import sp.domain.model.config.AppConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigServiceImpl implements ConfigService {

    private final AppConfigRepository appConfigRepository;

    @Autowired
    ConfigServiceImpl(AppConfigRepository appConfigRepository) {
        this.appConfigRepository = appConfigRepository;
    }

    @Override
    public AppConfig retrieveAppConfig() {
        return this.appConfigRepository.findAll();
    }
}
