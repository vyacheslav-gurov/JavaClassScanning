package api;

import com.google.common.cache.CacheBuilder;
import lombok.NonNull;
import ru.sber.cb.diam.metamodel.services.*;
import ru.sber.cb.diam.metamodel.services.dto.PlatformClass;
import ru.sber.cb.diam.metamodel.services.filemodel.FsModelPathService;

public class FsPlatformServiceFactory extends ReflectionPlatformServiceFactory implements PlatformServiceFactory {

    @NonNull
    private final String rootPath;

    protected FsPlatformServiceFactory(@NonNull String rootPath, ClassLoader classLoader) {
        super(classLoader);
        this.rootPath = rootPath;
    }

    @Override
    public PlatformClassService createPlatformClassService() {
        return new MetaModelFsServiceProxy(rootPath, null, classLoader);
    }

    @Override
    public PlatformClassService createPlatformClassService(PlatformClassService platformClassService) {
        return new MetaModelFsServiceProxy(rootPath, platformClassService, classLoader);
    }

    @Override
    public PlatformClassService createCachedPlatformClassService() {
        throw new UnsupportedOperationException();
    }

    @Override
    public PlatformClassService createCachedPlatformClassService(CacheBuilder<String, PlatformClass> cacheBuilder) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public PlatformClassInfoService createPlatformClassInfoService() {
        return new MetaModelFsServiceProxy(rootPath, createPlatformClassService(), classLoader);
    }

    @Override
    public PlatformClassInfoService createPlatformClassInfoService(PlatformClassService platformClassService) {
        return new MetaModelFsServiceProxy(rootPath, platformClassService, classLoader);
    }

    @Override
    public PlatformCriterionInfoService createPlatformCriterionInfoService() {
        throw new UnsupportedOperationException();
    }

    @Override
    public PlatformCriterionInfoService createPlatformCriterionInfoService(PlatformClassService platformClassService) {
        return new MetaModelFsServiceProxy(rootPath, platformClassService, classLoader);
    }

    @Override
    public PlatformCriterionComplexService createPlatformCriterionComplexService() {
        return new MetaModelFsServiceProxy(rootPath, null, classLoader);
    }

    @Override
    public PlatformCriterionComplexService createPlatformCriterionComplexService(PlatformClassService platformClassService) {
        return new MetaModelFsServiceProxy(rootPath, platformClassService, classLoader);
    }

    @Override
    public PlatformMethodInfoService createPlatformMethodInfoService() {
        return new MetaModelFsServiceProxy(rootPath, createPlatformClassService(), classLoader);
    }

    @Override
    public PlatformMethodInfoService createPlatformMethodInfoService(PlatformClassService platformClassService) {
        return new MetaModelFsServiceProxy(rootPath, platformClassService, classLoader);
    }

    @Override
    public PlatformClassTriggerInfoService createPlatformClassTrigger() {
        return new MetaModelFsServiceProxy(rootPath, createPlatformClassService(), classLoader);
    }

    @Override
    public PlatformClassTriggerInfoService createPlatformClassTrigger(PlatformClassService platformClassService) {
        return new MetaModelFsServiceProxy(rootPath, platformClassService, classLoader);
    }

    @Override
    public PlatformIndexColumnService createPlatformIndexColumnService() {
        return new MetaModelFsServiceProxy(rootPath, createPlatformClassService(), classLoader);
    }

    @Override
    public PlatformIndexColumnService createPlatformIndexColumnService(PlatformClassService platformClassService) {
        return new MetaModelFsServiceProxy(rootPath, platformClassService, classLoader);
    }

    @Override
    public PlatformClassTableService createPlatformClassTableService(PlatformClassService platformClassService) {
        return new MetaModelFsServiceProxy(rootPath, platformClassService, classLoader);
    }

    @Override
    public FsModelPathService createFsModelPathService() {
        return getServiceInstanceFromNameAndParams(ModelFilePathService, invokeClassMethod(getServiceInstanceFromNameAndParams(ModelFileServiceFactory), "getService", rootPath));
    }

    @Override
    public PlatformClassTableService createPlatformClassTableService() {
        return new MetaModelFsServiceProxy(rootPath, createPlatformClassService(), classLoader);
    }
}
