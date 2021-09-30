package com.methodie.prvizadatak.spring;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@org.springframework.context.annotation.Configuration
@EnableJpaRepositories(basePackages = {"com.methodie.prvizadatak.business.database"},
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef= "transactionManager")
public class Configuration {
}
