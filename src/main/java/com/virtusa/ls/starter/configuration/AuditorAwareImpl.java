package com.virtusa.ls.starter.configuration;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuditorAwareImpl implements AuditorAware<UUID> {

    @Override
    public Optional<UUID> getCurrentAuditor() {
        return Optional.ofNullable((UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

}
