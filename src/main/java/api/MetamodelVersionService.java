package api;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;

class MetamodelVersionService {

    private final static String DEFAULT = "2022.3";

    protected static String getCurrentVersion(String path) {
        File file = Path.of(path).resolve("schemas").toFile();
        String[] directories = file.list((current, name) -> new File(current, name).isDirectory());
        return Arrays.stream(directories != null ? directories : new String[0]).findFirst().orElse(DEFAULT);
    }
}
