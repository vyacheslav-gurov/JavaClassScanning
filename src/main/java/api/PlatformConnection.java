package api;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public abstract class PlatformConnection {
    private String id;
    private String title;
    private String url;
    private String dataSourceClassName;

    @Override
    public String toString() {
        return this.title;
    }

}