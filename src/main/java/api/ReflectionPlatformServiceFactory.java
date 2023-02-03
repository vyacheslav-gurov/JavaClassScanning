package api;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public abstract class ReflectionPlatformServiceFactory {
    protected final static String DbAndSystemPlatformClassService = "ru.sber.cb.diam.metamodel.impl.db.DbAndSystemPlatformClassService";
    protected final static String CachedDbAndSystemPlatformClassService = "ru.sber.cb.diam.metamodel.impl.db.CachedDbAndSystemPlatformClassService";
    protected final static String DbAndSystemPlatformClassInfoService = "ru.sber.cb.diam.metamodel.impl.db.DbAndSystemPlatformClassInfoService";
    protected final static String DbPlatformCriterionInfoService = "ru.sber.cb.diam.metamodel.impl.db.DbPlatformCriterionInfoService";
    protected final static String DbPlatformCriterionComplexService = "ru.sber.cb.diam.metamodel.impl.db.DbPlatformCriterionComplexService";
    protected final static String DbPlatformMethodInfoService = "ru.sber.cb.diam.metamodel.impl.db.DbPlatformMethodInfoService";
    protected final static String DbPlatformIndexColumnService = "ru.sber.cb.diam.metamodel.impl.db.DbPlatformIndexColumnService";
    protected final static String DbPlatformClassTableService = "ru.sber.cb.diam.metamodel.impl.db.DbPlatformClassTableService";
    protected final static String DbPlatformClassKeyService = "ru.sber.cb.diam.metamodel.impl.db.DbPlatformClassKeyService";
    protected final static String DbPlatformGuideGroupService = "ru.sber.cb.diam.metamodel.impl.db.DbPlatformGuideGroupService";
    protected final static String DbPlatformEntityService = "ru.sber.cb.diam.metamodel.impl.db.DbPlatformEntityService";
    protected final static String ModelFilePathService = "ru.sber.cb.diam.metamodel.impl.fs.services.ModelFilePathService";
    protected final static String ModelFileServiceFactory = "ru.sber.cb.diam.metamodel.impl.fs.services.ModelFileServiceFactory";
    protected final static String FsPlatformClassInfoService = "ru.sber.cb.diam.metamodel.impl.fs.FsPlatformClassInfoService";
    protected final static String FsPlatformClassService = "ru.sber.cb.diam.metamodel.impl.fs.FsPlatformClassService";
    protected final static String FsPlatformClassTableService = "ru.sber.cb.diam.metamodel.impl.fs.FsPlatformClassTableService";
    protected final static String FsPlatformTriggerInfoService = "ru.sber.cb.diam.metamodel.impl.fs.FsPlatformTriggerInfoService";
    protected final static String FsPlatformCriterionComplexService = "ru.sber.cb.diam.metamodel.impl.fs.FsPlatformCriterionComplexService";
    protected final static String FsPlatformCriterionInfoService = "ru.sber.cb.diam.metamodel.impl.fs.FsPlatformCriterionInfoService";
    protected final static String FsPlatformIndexColumnService = "ru.sber.cb.diam.metamodel.impl.fs.FsPlatformIndexColumnService";
    protected final static String FsPlatformMethodInfoService = "ru.sber.cb.diam.metamodel.impl.fs.FsPlatformMethodInfoService";
    protected final static String FsPlatformClassKeyService = "ru.sber.cb.diam.metamodel.impl.fs.FsPlatformClassKeyService";
    protected final static String FsPlatformGuideGroupService = "ru.sber.cb.diam.metamodel.impl.fs.FsPlatformGuideGroupService";
    protected final static String FsPlatformEntityService = "ru.sber.cb.diam.metamodel.impl.fs.FsPlatformEntityService";

    protected final ClassLoader classLoader;

    private static Class<?>[] listToArray(List<Class> list) {
        var res = new Class<?>[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    @SneakyThrows
    protected <T> Class<T> getServiceClassFromName(String name) {
        return (Class<T>) Class.forName(name, true, classLoader);
    }

    @SneakyThrows
    protected Object invokeClassMethod(Object service, String methodName, Object... params) {
        return service.getClass().getDeclaredMethod(methodName, objectToTypeArray(params)).invoke(service, params);
    }

    @SneakyThrows
    protected Object invokeClassMethodTypeParams(Object service, String methodName, TypeParam... params) {
        return service.getClass().getDeclaredMethod(methodName, objectToTypeArrayTypeParam(params)).invoke(service, Arrays.stream(params).map(TypeParam::getValue).toArray());
    }

    @SneakyThrows
    protected <T> T getServiceInstance(Class<T> clazz, Object... params) {
        return clazz.getDeclaredConstructor(objectToTypeArray(params)).newInstance(params);
    }

    @SneakyThrows
    protected <T> T getServiceInstanceTypeParam(Class<T> clazz, TypeParam... params) {
        return clazz.getDeclaredConstructor(objectToTypeArrayTypeParam(params)).newInstance(Arrays.stream(params).map(TypeParam::getValue).toArray());
    }

    private Class<?>[] objectToTypeArray(Object... objects) {
        return listToArray(Arrays.stream(objects).map(m -> (Class<?>) m.getClass()).collect(Collectors.toList()));
    }

    private Class<?>[] objectToTypeArrayTypeParam(TypeParam... objects) {
        return listToArray(Arrays.stream(objects).map(m -> (Class<?>) m.getType()).collect(Collectors.toList()));
    }

    protected <T> T getServiceInstanceFromNameAndParams(String name, Object... params) {
        return getServiceInstance(getServiceClassFromName(name), params);
    }

    protected <T> T getServiceInstanceFromNameAndTypeParams(String name, TypeParam... params) {
        return getServiceInstanceTypeParam(getServiceClassFromName(name), params);
    }

    @Getter
    @Builder
    protected static class TypeParam {
        Class type;
        Object value;
    }
}
