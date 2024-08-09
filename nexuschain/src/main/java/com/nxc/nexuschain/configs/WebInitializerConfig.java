package com.nxc.nexuschain.configs;

import com.nxc.nexuschain.services.InitializerService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WebInitializerConfig {
    private final InitializerService service;

    @PostConstruct
    public void initialize() {
        this.service.createAdminUser();
    }
}
