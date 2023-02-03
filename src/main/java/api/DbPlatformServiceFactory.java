package api;

import com.google.common.cache.CacheBuilder;
import org.apache.commons.dbutils.QueryRunner;
import ru.sber.cb.diam.metamodel.services.*;
import ru.sber.cb.diam.metamodel.services.dto.PlatformClass;
import ru.sber.cb.diam.metamodel.services.filemodel.FsModelPathService;

import javax.sql.DataSource;
import java.io.Closeable;
import java.io.IOException;

public class DbPlatformServiceFactory extends ReflectionPlatformServiceFactory implements PlatformServiceFactory, Closeable {
    private final DataSource dataSource;
    private final String schema;
    private final QueryRunner queryRunner;

    protected DbPlatformServiceFactory(QueryRunner queryRunner, String schema, ClassLoader classLoader) {
        super(classLoader);
        this.schema = schema;
        this.dataSource = queryRunner.getDataSource();
        this.queryRunner = queryRunner;
    }

    @Override
    public PlatformClassService createPlatformClassService() {
        return getServiceInstanceFromNameAndParams(DbAndSystemPlatformClassService, queryRunner, schema);
    }

    @Override
    public PlatformClassService createPlatformClassService(PlatformClassService platformClassService) {
        return createPlatformClassService();
    }

    @Override
    public PlatformClassService createCachedPlatformClassService() {
        return getServiceInstanceFromNameAndTypeParams(CachedDbAndSystemPlatformClassService, TypeParam.builder().type(PlatformClassService.class).value(createPlatformClassService()).build());
    }

    @Override
    public PlatformClassService createCachedPlatformClassService(CacheBuilder<String, PlatformClass> cacheBuilder) {
        return getServiceInstanceFromNameAndTypeParams(CachedDbAndSystemPlatformClassService, TypeParam.builder().type(PlatformClassService.class).value(createPlatformClassService()).build(), TypeParam.builder().type(cacheBuilder.getClass()).value(cacheBuilder).build());
    }

    @Override
    public PlatformClassInfoService createPlatformClassInfoService() {
        return getServiceInstanceFromNameAndParams(DbAndSystemPlatformClassInfoService, queryRunner, schema);
    }

    @Override
    public PlatformClassInfoService createPlatformClassInfoService(PlatformClassService platformClassService) {
        return createPlatformClassInfoService();
    }

    @Override
    public PlatformCriterionInfoService createPlatformCriterionInfoService() {
        return getServiceInstanceFromNameAndParams(DbPlatformCriterionInfoService, queryRunner, schema);
    }

    @Override
    public PlatformCriterionInfoService createPlatformCriterionInfoService(PlatformClassService platformClassService) {
        return createPlatformCriterionInfoService();
    }

    @Override
    public PlatformCriterionComplexService createPlatformCriterionComplexService() {
        return getServiceInstanceFromNameAndParams(DbPlatformCriterionComplexService, queryRunner, schema);
    }

    @Override
    public PlatformCriterionComplexService createPlatformCriterionComplexService(PlatformClassService platformClassService) {
        return getServiceInstanceFromNameAndParams(DbPlatformCriterionComplexService, queryRunner, schema);
    }

    @Override
    public PlatformMethodInfoService createPlatformMethodInfoService() {
        return getServiceInstanceFromNameAndParams(DbPlatformMethodInfoService, queryRunner, schema);
    }

    @Override
    public PlatformMethodInfoService createPlatformMethodInfoService(PlatformClassService platformClassService) {
        return createPlatformMethodInfoService();
    }

    @Override
    public PlatformClassTriggerInfoService createPlatformClassTrigger() {
        throw new UnsupportedOperationException();
    }

    @Override
    public PlatformClassTriggerInfoService createPlatformClassTrigger(PlatformClassService platformClassService) {
        throw new UnsupportedOperationException();
    }

    @Override
    public PlatformIndexColumnService createPlatformIndexColumnService() {
        return getServiceInstanceFromNameAndParams(DbPlatformIndexColumnService, queryRunner, schema);
    }

    @Override
    public PlatformIndexColumnService createPlatformIndexColumnService(PlatformClassService platformClassService) {
        return createPlatformIndexColumnService();
    }

    @Override
    public PlatformClassTableService createPlatformClassTableService() {
        return getServiceInstanceFromNameAndParams(DbPlatformClassTableService, queryRunner, schema);
    }

    @Override
    public PlatformClassTableService createPlatformClassTableService(PlatformClassService platformClassService) {
        return createPlatformClassTableService();
    }

    @Override
    public PlatformEntityService createPlatformEntityService() {
        return getServiceInstanceFromNameAndParams(DbPlatformEntityService, queryRunner, schema);
    }

    @Override
    public PlatformEntityService createPlatformEntityService(PlatformClassService platformClassService) {
        return createPlatformEntityService();
    }

    @Override
    public PlatformClassKeysService createPlatformClassKeysService() {
        return getServiceInstanceFromNameAndParams(DbPlatformClassKeyService, queryRunner, schema);
    }

    @Override
    public PlatformGuideGroupService createPlatformGuideGroupService() {
        return getServiceInstanceFromNameAndParams(DbPlatformGuideGroupService, queryRunner, schema);
    }

    @Override
    public FsModelPathService createFsModelPathService() {
        return createFsModelPathService(false);
    }

    @Override
    public FsModelPathService createFsModelPathService(boolean createSchemaVersion) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void close() throws IOException {
        if (Closeable.class.isAssignableFrom(dataSource.getClass())) {
            Closeable closeable = (Closeable) dataSource;
            closeable.close();
        }
    }
}