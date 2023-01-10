package gutsandgun.kite_user.config;

import com.sun.tools.jconsole.JConsoleContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource({ "classpath:application.yml" })
@EnableJpaRepositories(
        basePackages = "gutsandgun.kite_user.repository.read", // Master Repository 경로
        entityManagerFactoryRef = "ReadEntityManager",
        transactionManagerRef = "ReadTransactionManager"
)
public class ReadDBConfig {
    @Autowired
    private Environment env;

    @Autowired
    private JpaProperties jpaProperties;
    @Autowired
    private HibernateProperties hibernateProperties;

    @Bean
    public LocalContainerEntityManagerFactoryBean ReadEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(ReadDataSource());

        //Entity 패키지 경로
        em.setPackagesToScan("gutsandgun.kite_user.entity.read");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        //Hibernate 설정
        Map<String, Object> properties = hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings());
        //Hibernate 설정
//        HashMap<String, Object> properties = new HashMap<>();
//        properties.put("hibernate.hbm2ddl.auto",env.getProperty("spring.jpa.hibernate.ddl-auto"));
//        properties.put("hibernate.dialect",env.getProperty("spring.jpa.properties.hibernate.dialect"));
//        properties.put("hibernate.dialect.storage_engine",env.getProperty("spring.jpa.properties.hibernate.storage_engine"));
//        properties.put("hibernate.format_sql",env.getProperty("spring.jpa.properties.hibernate.format_sql"));
//        properties.put("hibernate.show-sql",env.getProperty("spring.jpa.properties.hibernate.show-sql"));
//        properties.put("hibernate.generate-ddl",env.getProperty("spring.jpa.properties.hibernate.generate-ddl"));
//        properties.put("hibernate.naming.physical-strategy", env.getProperty("spring.jpa.hibernate.naming.physical-strategy"));
//        properties.put("hibernate.naming.implicit_naming_strategy", env.getProperty("spring.jpa.hibernate.naming.implicit-strategy"));
        System.out.println(properties);
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean
    @ConfigurationProperties(prefix="spring.readdb")
    public DataSource ReadDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public PlatformTransactionManager ReadTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(ReadEntityManager().getObject());
        return transactionManager;
    }
}
