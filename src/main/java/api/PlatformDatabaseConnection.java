package api;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Properties;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class PlatformDatabaseConnection extends PlatformConnection {
    private String username;
    private String pass;
    private Integer maxConnections;
    private String schema;

    @Override
    public String toString() {
        return super.toString();
    }

    public Properties toProperties() {
        Properties props = new Properties();
        try {
            props.setProperty("dataSourceClassName", Class.forName(this.getDataSourceClassName()).getCanonicalName());
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Class with name " + this.getDataSourceClassName() + " not found ", e);
        }
        props.setProperty("dataSource.url", this.getUrl());
        props.setProperty("dataSource.user", this.getUsername());
        props.setProperty("dataSource.password", this.getPass());
        return props;
    }
}