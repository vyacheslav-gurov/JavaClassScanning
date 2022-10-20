package api;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;
import java.util.Properties;

public class PlatformServiceFactoryCreator {

    private final ClassLoader classLoader;
    @Getter
    private final DataSource dataSource;
    private final String schema;

    protected PlatformServiceFactoryCreator(ClassLoader classLoader, PlatformDatabaseConnection connection) {
        this.classLoader = classLoader;
        Properties props = connection.toProperties();
        props.remove("dataSourceClassName");
        props.put("driverClassName", "oracle.jdbc.driver.OracleDriver");
        this.schema = connection.getSchema();
        HikariConfig config = new HikariConfig(props);
        if (connection.getMaxConnections() != null) {
            config.setMaximumPoolSize(connection.getMaxConnections());
        }
        String initSql = LockOpenScript.get(this.schema);
        if (connection.getConnectionInitSql() != null) {
            initSql = connection.getConnectionInitSql();
        }
        config.setConnectionInitSql(initSql);
        config.setConnectionTimeout(600000);
        config.setMaxLifetime(1800000);
        config.setMinimumIdle(20);
        config.setValidationTimeout(3000);
        config.setIdleTimeout(60000);
        config.addDataSourceProperty("v$session.program", String.format(connection.getModuleName(), connection.getInitId()));
        config.setJdbcUrl(connection.getUrl());
        this.dataSource = new HikariDataSource(config);
    }

    public PlatformServiceFactory create() throws ClassNotFoundException {
        QueryRunner queryRunner = new QueryRunner(dataSource);
        return new DbPlatformServiceFactory(queryRunner, this.schema, classLoader);
    }

}