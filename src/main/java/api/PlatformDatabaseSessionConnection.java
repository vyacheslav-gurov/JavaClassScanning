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
        invokeClassMethodTypeParams(platformSessionService, "executeOpen", TypeParam.builder().type(Connection.class).value(this.connection).build());
    }

    @Override
    public void close() throws SQLException {
        invokeClassMethodTypeParams(platformSessionService, "executeClose", TypeParam.builder().type(Connection.class).value(this.connection).build());
        this.connection.close();
    }
}
