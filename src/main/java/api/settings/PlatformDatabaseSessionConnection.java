package api.settings;

import lombok.Getter;
import ru.sber.cb.diam.metamodel.impl.db.PlatformSessionService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class PlatformDatabaseSessionConnection implements AutoCloseable {

    @Getter
    private final Connection connection;
    private final PlatformSessionService platformSessionService;

    public PlatformDatabaseSessionConnection(DataSource dataSource, String schema) throws SQLException {
        this.connection = dataSource.getConnection();
        this.platformSessionService = new PlatformSessionService(schema);
    }

    public void open() throws SQLException {
        this.platformSessionService.executeOpen(this.connection);
    }

    @Override
    public void close() throws SQLException {
        this.platformSessionService.executeClose(this.connection);
        this.connection.close();
    }
}
