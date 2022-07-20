package api;

import javax.sql.DataSource;
import java.sql.SQLException;

import static api.MetamodelVersionService.getCurrentVersion;

public class MetamodelAPI {
    private final ClassLoader classLoader;
    private final String rootPath;

    public MetamodelAPI(String path) {
        this.classLoader = new MetamodelVersionClassLoaderService(getCurrentVersion(path)).getClassLoader();
        this.rootPath = path;
    }

    public PlatformServiceFactoryCreator getDatabasePlatformServiceFactoryCreator() {
        return new PlatformServiceFactoryCreator(classLoader);
    }

    public FsPlatformServiceFactory getFileSystemPlatformServiceFactory() {
        return new FsPlatformServiceFactory(rootPath, classLoader);
    }

    public PlatformDatabaseSessionConnection getPlatformDatabaseSessionConnection(DataSource dataSource, String schema) throws SQLException {
        return new PlatformDatabaseSessionConnection(dataSource, schema, classLoader);
    }
}
