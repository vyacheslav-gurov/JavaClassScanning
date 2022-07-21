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
        ClassLoader classLoader = this.getClass().getClassLoader();
        URL versionFolded = MetamodelVersionService.getVersionFolded(classLoader, version);
        FixedLocatedArchiveLoader loader = new FixedLocatedArchiveLoader(
                new URL[]{versionFolded}, classLoader
        );
        loader.addAppliedPackages(".");
        List.of(
                "java.",
                "org.apache.commons.dbutils",
                "ru.sber.cb.diam.metamodel.services.",
                "com.fasterxml.jackson",
                "jdk.",
                "com.zaxxer.hikari.",
                "javax."
        ).forEach(loader::addNotAppliedPackages);

        return loader;
    }
}
