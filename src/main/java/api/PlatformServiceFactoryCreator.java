package api;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;
import java.util.Properties;

public class PlatformServiceFactoryCreator {

    private final ClassLoader classLoader;

    protected PlatformServiceFactoryCreator(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Deprecated
    public PlatformServiceFactory create(PlatformDatabaseConnection connection, String schema) throws ClassNotFoundException {
        Properties props = connection.toProperties();
        HikariConfig config = new HikariConfig(props);
        HikariDataSource dataSource = new HikariDataSource(config);
        return create(dataSource, schema);
    }

    public PlatformServiceFactory create(DataSource dataSource, String schema) throws ClassNotFoundException {
        QueryRunner queryRunner = new QueryRunner(dataSource);
        return new DbPlatformServiceFactory(queryRunner, schema, classLoader);
    }

}