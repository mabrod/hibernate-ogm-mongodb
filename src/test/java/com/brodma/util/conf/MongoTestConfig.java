package com.brodma.util.conf;

import com.brodma.conf.MongoProperty;
import org.hibernate.ogm.cfg.OgmProperties;
import org.hibernate.ogm.datastore.mongodb.MongoDBProperties;
import org.hibernate.ogm.jpa.HibernateOgmPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;

@TestConfiguration
@Profile("test")
public class MongoTestConfig {

    private Map<String, String> mongoProperties = new HashMap();

    @Autowired
    public MongoTestConfig(MongoProperty mongoProperty) {
        mongoProperties.put(OgmProperties.ENABLED, mongoProperty.getEnabled());
        mongoProperties.put(OgmProperties.DATASTORE_PROVIDER, mongoProperty.getProvider());
        mongoProperties.put(OgmProperties.HOST, mongoProperty.getHost());
        mongoProperties.put(OgmProperties.DATABASE, mongoProperty.getDatabase());
        mongoProperties.put(OgmProperties.USERNAME, mongoProperty.getUsername());
        mongoProperties.put(OgmProperties.PASSWORD, mongoProperty.getPassword());
        mongoProperties.put(OgmProperties.GRID_DIALECT, mongoProperty.getGridDialect());
        mongoProperties.put(OgmProperties.CREATE_DATABASE, mongoProperty.getCreateDatabase());
        mongoProperties.put(OgmProperties.ERROR_HANDLER, mongoProperty.getErrorHandler());
        mongoProperties.put(MongoDBProperties.WRITE_CONCERN, mongoProperty.getWriteconcern());
        mongoProperties.put(MongoDBProperties.READ_PREFERENCE, mongoProperty.getReadPreference());
        mongoProperties.put(MongoDBProperties.ASSOCIATION_DOCUMENT_STORAGE, mongoProperty.getAssociationDocumentStorageType());
        mongoProperties.put(MongoDBProperties.ASSOCIATIONS_STORE, mongoProperty.getAssociationStorageType());
        mongoProperties.put(MongoDBProperties.MAP_STORAGE, mongoProperty.getMapStorageType());
        mongoProperties.put("packagesToScan", mongoProperty.getPackagesToScan());
        mongoProperties.put("puName", mongoProperty.getPuName());
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan(mongoProperties.get("packagesToScan"));
        entityManagerFactoryBean.setPersistenceProviderClass(HibernateOgmPersistence.class);
        entityManagerFactoryBean.setPersistenceUnitName(mongoProperties.get("puName"));
        entityManagerFactoryBean.setJpaDialect(new HibernateJpaDialect());
        entityManagerFactoryBean.setJpaPropertyMap(mongoProperties);
        return entityManagerFactoryBean;
    }

    @Bean
    @Primary
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) throws Throwable {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        transactionManager.setPersistenceUnitName(mongoProperties.get("puName"));
        return transactionManager;
    }
}
