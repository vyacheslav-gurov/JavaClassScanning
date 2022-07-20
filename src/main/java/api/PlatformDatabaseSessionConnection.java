package api;

import lombok.Getter;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class PlatformDatabaseSessionConnection extends ReflectionPlatformServiceFactory implements AutoCloseable {

    @Getter
    private final Connection connection;
    private final Object platformSessionService;

    protected PlatformDatabaseSessionConnection(DataSource dataSource, String schema, ClassLoader classLoader) throws SQLException {
        super(classLoader);
        this.connection = dataSource.getConnection();
        this.platformSessionService = getServiceInstanceFromNameAndParams(PlatformSessionService, schema);
    }

    public void open() throws SQLException {
        invokeClassMethod(platformSessionService, "executeOpen", this.connection);
    }

    @Override
    public void close() throws SQLException {
        invokeClassMethod(platformSessionService, "executeClose", this.connection);
        this.connection.close();
    }
}
