package api.fs;

import api.PlatformServiceFactory;
import api.proxy.MetaModelFsServiceProxy;
import com.google.common.cache.CacheBuilder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ru.sber.cb.diam.metamodel.services.*;
import ru.sber.cb.diam.metamodel.services.dto.PlatformClass;

@RequiredArgsConstructor
public class FsPlatformServiceFactory implements PlatformServiceFactory {

    @NonNull
    private final String rootPath;

    @Override
    public PlatformClassService createPlatformClassService() {
        return new MetaModelFsServiceProxy(rootPath, null);
    }

    @Override
    public PlatformClassService createPlatformClassService(PlatformClassService platformClassService) {
        return new MetaModelFsServiceProxy(rootPath, platformClassService);
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
        return new MetaModelFsServiceProxy(rootPath, createPlatformClassService());
    }

    @Override
    public PlatformClassInfoService createPlatformClassInfoService(PlatformClassService platformClassService) {
        return new MetaModelFsServiceProxy(rootPath, platformClassService);
    }

    @Override
    public PlatformCriterionInfoService createPlatformCriterionInfoService() {
        throw new UnsupportedOperationException();
    }

    @Override
    public PlatformCriterionInfoService createPlatformCriterionInfoService(PlatformClassService platformClassService) {
        return new MetaModelFsServiceProxy(rootPath, platformClassService);
    }

    @Override
    public PlatformCriterionComplexService createPlatformCriterionComplexService() {
        return new MetaModelFsServiceProxy(rootPath, null);
    }

    @Override
    public PlatformCriterionComplexService createPlatformCriterionComplexService(PlatformClassService platformClassService) {
        return new MetaModelFsServiceProxy(rootPath, platformClassService);
    }

    @Override
    public PlatformMethodInfoService createPlatformMethodInfoService() {
        return new MetaModelFsServiceProxy(rootPath, createPlatformClassService());
    }

    @Override
    public PlatformMethodInfoService createPlatformMethodInfoService(PlatformClassService platformClassService) {
        return new MetaModelFsServiceProxy(rootPath, platformClassService);
    }

    @Override
    public PlatformClassTriggerInfoService createPlatformClassTrigger() {
        return new MetaModelFsServiceProxy(rootPath, createPlatformClassService());
    }

    @Override
    public PlatformClassTriggerInfoService createPlatformClassTrigger(PlatformClassService platformClassService) {
        return new MetaModelFsServiceProxy(rootPath, platformClassService);
    }

    @Override
    public PlatformIndexColumnService createPlatformIndexColumnService() {
        return new MetaModelFsServiceProxy(rootPath, createPlatformClassService());
    }

    @Override
    public PlatformIndexColumnService createPlatformIndexColumnService(PlatformClassService platformClassService) {
        return new MetaModelFsServiceProxy(rootPath, platformClassService);
    }

    @Override
    public PlatformClassTableService createPlatformClassTableService(PlatformClassService platformClassService) {
        return new MetaModelFsServiceProxy(rootPath, platformClassService);
    }

    @Override
    public PlatformClassTableService createPlatformClassTableService() {
        return new MetaModelFsServiceProxy(rootPath, createPlatformClassService());
    }
}
