package uz.imirsaburov.manage.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import uz.imirsaburov.manage.shop.base.BaseRepositoryImpl;


@EnableJpaRepositories(
        repositoryBaseClass = BaseRepositoryImpl.class)
@SpringBootApplication
public class ShopApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ShopApplication.class, args);
    }

}
