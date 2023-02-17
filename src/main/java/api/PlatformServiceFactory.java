package api;

import com.google.common.cache.CacheBuilder;
import ru.sber.cb.diam.metamodel.services.*;
import ru.sber.cb.diam.metamodel.services.dto.PlatformClass;
import ru.sber.cb.diam.metamodel.services.filemodel.FsModelPathService;

public interface PlatformServiceFactory {
    PlatformClassService createPlatformClassService();
    PlatformClassService createPlatformClassService(PlatformClassService platformClassService);
    PlatformClassService createCachedPlatformClassService();
    PlatformClassService createCachedPlatformClassService(CacheBuilder<String, PlatformClass> cacheBuilder);
    PlatformClassInfoService createPlatformClassInfoService();
    PlatformClassInfoService createPlatformClassInfoService(PlatformClassService platformClassService);
    PlatformCriterionInfoService createPlatformCriterionInfoService();
    PlatformCriterionInfoService createPlatformCriterionInfoService(PlatformClassService platformClassService);
    PlatformCriterionComplexService createPlatformCriterionComplexService();
    PlatformCriterionComplexService createPlatformCriterionComplexService(PlatformClassService platformClassService);
    PlatformMethodInfoService createPlatformMethodInfoService();
    PlatformMethodInfoService createPlatformMethodInfoService(PlatformClassService platformClassService);
    PlatformClassTriggerInfoService createPlatformClassTrigger();
    PlatformClassTriggerInfoService createPlatformClassTrigger(PlatformClassService platformClassService);
    PlatformIndexColumnService createPlatformIndexColumnService();

    PlatformIndexColumnService createPlatformIndexColumnService(PlatformClassService platformClassService);

    PlatformClassTableService createPlatformClassTableService();

    PlatformClassTableService createPlatformClassTableService(PlatformClassService platformClassService);

    PlatformEntityService createPlatformEntityService();

    PlatformEntityService createPlatformEntityService(PlatformClassService platformClassService);

    PlatformClassKeysService createPlatformClassKeysService();

    PlatformGuideGroupService createPlatformGuideGroupService();

    FsModelPathService createFsModelPathService();

    FsModelPathService createFsModelPathService(boolean createSchemaVersion);

    MetaModelStorage createMetaModelStorage();

}