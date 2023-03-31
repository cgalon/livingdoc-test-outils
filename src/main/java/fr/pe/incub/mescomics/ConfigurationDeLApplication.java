package fr.pe.incub.mescomics;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
@EntityScan(basePackageClasses = {Application.class})
@EnableJpaRepositories("fr.pe.incub.mescomics")
public class ConfigurationDeLApplication {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:testdb");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }

    @Bean
    public EntityManager entityManager() {
        return entityManagerFactory().getObject().createEntityManager();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("fr.pe.incub.mescomics");
        em.setJpaVendorAdapter(jpaVendorAdapter());
        return em;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        JpaVendorAdapter jva = new HibernateJpaVendorAdapter();
        return jva;
    }

    @Bean
    public OpenAPI configureLesMetadataDeLaDocumentation() {
        return new OpenAPI()
                .info(new Info()
                        .title("Gestion d'une collection de comics")
                        .description("Application d'exemple de génération de documentation")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Christophe Galon")
                                .email("cgalon@free.fr")
                                .url("https://github.com/cgalon")));
    }

}
