package api;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.sql.DataSource;
import java.sql.SQLException;

import static api.MetamodelVersionService.getLastVersion;

public class MetamodelAPI {
    private final ClassLoader classLoader;
    private final String rootPath;
    @Getter
    private final String version;

    private MetamodelAPI(Builder builder) {
        this.rootPath = builder.path;
        this.version = builder.version;
        this.classLoader = new MetamodelVersionClassLoaderService(this.version).getClassLoader();
    }

    @NoArgsConstructor
    public static class Builder {
        private String path;
        private String version;

        public Builder path(String s) {
            this.path = s;
            return this;
        }

        public MetamodelAPI buildLastVersion() {
            this.version = getLastVersion(this.getClass().getClassLoader());
            return new MetamodelAPI(this);
        }

        public MetamodelAPI buildSnapshotVersion() {
            this.version = "SNAPSHOT";
            return new MetamodelAPI(this);
        }

        public MetamodelAPI buildWithCurrentVersion(String version) {
            this.version = version;
            return new MetamodelAPI(this);
        }
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
