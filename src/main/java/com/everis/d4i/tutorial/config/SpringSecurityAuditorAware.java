package com.everis.d4i.tutorial.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;


//@Component
//public class SpringSecurityAuditorAware implements AuditorAware<String> {
//
//    @Override
//    public Optional<String> getCurrentAuditor() {
//        return Optional.of(SecurityUtils.getCurrentUserLogin().orElse(Constants.SYSTEM));
//    }
//}