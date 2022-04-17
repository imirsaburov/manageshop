package uz.imirsaburov.manage.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import uz.imirsaburov.manage.shop.base.BaseRepositoryImpl;
import uz.imirsaburov.manage.shop.util.CurrentUserUtils;

import java.util.Optional;

@EnableJpaAuditing
@Configuration
public class JpaAuditingConfig {

    @Bean
    public AuditorAware<Long> auditorAware() {
        return Optional::empty;
    }
}
