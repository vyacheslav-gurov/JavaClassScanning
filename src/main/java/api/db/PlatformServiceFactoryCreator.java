package api.db;

import api.PlatformServiceFactory;
import api.settings.PlatformDatabaseConnection;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.dbutils.QueryRunner;

import java.util.Properties;

public class PlatformServiceFactoryCreator {

    public PlatformServiceFactory create(PlatformDatabaseConnection connection, String schema, boolean lockOpen) throws ClassNotFoundException {
        Properties props = connection.toProperties();
        HikariConfig config = new HikariConfig(props);
        HikariDataSource dataSource = new HikariDataSource(config);
        QueryRunner queryRunner = new QueryRunner(dataSource);
        return new DbPlatformServiceFactory(queryRunner, schema);
    }

    public PlatformServiceFactory create(PlatformDatabaseConnection connection, String schema) throws ClassNotFoundException {
        return create(connection, schema, true);
    }
}