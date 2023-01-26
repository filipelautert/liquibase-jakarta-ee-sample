package org.liquibase.jakartaeesample.liquibase;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import liquibase.integration.jakarta.cdi.CDILiquibaseConfig;
import liquibase.integration.jakarta.cdi.annotations.LiquibaseType;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.resource.ResourceAccessor;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * A Simple CDI Producer to configure the CDI Liquibase integration
 */
@ApplicationScoped
public class LiquibaseProducer {

    @Resource(lookup = "java:jboss/datasources/ExampleDS")
    private DataSource myDataSource;

    @Produces
    @LiquibaseType
    public CDILiquibaseConfig createConfig() {
        CDILiquibaseConfig config = new CDILiquibaseConfig();
        config.setChangeLog("liquibase/db.changelog.xml");
        return config;
    }

    @Produces
    @LiquibaseType
    public DataSource createDataSource() throws SQLException {
        return myDataSource;
    }

    @Produces
    @LiquibaseType
    public ResourceAccessor create() {
        return new ClassLoaderResourceAccessor(getClass().getClassLoader());
    }

}