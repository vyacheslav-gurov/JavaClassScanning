package api;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import ru.sber.cb.diam.metamodel.services.*;

@Builder(access = AccessLevel.PACKAGE)
@Getter
public class MetaModelStorage {
    private PlatformServiceFactory platformServiceFactory;
    private PlatformClassService platformClassService;
    private PlatformClassInfoService platformClassInfoService;
    private PlatformCriterionInfoService platformCriterionInfoService;
    private PlatformCriterionComplexService platformCriterionComplexService;
    private PlatformMethodInfoService platformMethodInfoService;
    private PlatformClassTriggerInfoService platformClassTriggerInfoService;
    private PlatformIndexColumnService platformIndexColumnService;
    private PlatformClassTableService platformClassTableService;
    private PlatformClassKeysService platformClassKeysService;
}