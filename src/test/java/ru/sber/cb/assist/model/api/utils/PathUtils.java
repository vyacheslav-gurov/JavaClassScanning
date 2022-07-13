package ru.sber.cb.assist.model.api.utils;

import ru.sber.cb.diam.metamodel.impl.utils.StringUtils;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;

public class PathUtils {

    public static Path testResource(String name) {
        URI resourcesUri = null;
        try {
            resourcesUri = Thread.currentThread().getContextClassLoader().getResource("test.file").toURI();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e.getMessage());
        }
        Path path = Path.of(resourcesUri).getParent();
        if (StringUtils.notEmpty(name)) {
            path = path.resolve(name);
        }
        return path;
    }

}
