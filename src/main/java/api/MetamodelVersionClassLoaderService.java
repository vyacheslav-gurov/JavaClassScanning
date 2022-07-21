package api;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.net.URL;
import java.util.List;

@RequiredArgsConstructor
class MetamodelVersionClassLoaderService {
    private final String version;

    @SneakyThrows
    protected ClassLoader getClassLoader() {
        ClassLoader thisClassLoader = this.getClass().getClassLoader();
        URL resource = thisClassLoader.getResource("metamodel-impl/" + getMajorVersionName());

        FixedLocatedArchiveLoader loader = new FixedLocatedArchiveLoader(
                new URL[]{resource}, thisClassLoader
        );
        loader.addAppliedPackages(".");
        List.of(
                "java.",
                "org.apache.commons.dbutils",
                "ru.sber.cb.diam.metamodel.services.",
                "com.fasterxml.jackson",
                "jdk."
        ).forEach(loader::addNotAppliedPackages);

        return loader;
    }

    private String getMajorVersionName() {
        final String snapshot = "SNAPSHOT";
        if (version.contains(snapshot)) {
            return snapshot;
        }
        String[] split = version.split("\\.");
        if (split.length < 2) {
            return split[0];
        }
        return split[0] + split[1];

    }
}
