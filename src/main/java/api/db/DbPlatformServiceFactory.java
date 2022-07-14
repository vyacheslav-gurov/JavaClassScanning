package api.db;

import api.PlatformServiceFactory;
import com.google.common.cache.CacheBuilder;
import lombok.RequiredArgsConstructor;
import org.apache.commons.dbutils.QueryRunner;
import ru.sber.cb.diam.metamodel.impl.db.*;
import ru.sber.cb.diam.metamodel.services.*;
import ru.sber.cb.diam.metamodel.services.dto.PlatformClass;
import ru.sber.cb.diam.metamodel.services.filemodel.FsModelPathService;

import javax.sql.DataSource;
import java.io.Closeable;
import java.io.IOException;

@RequiredArgsConstructor
public class DbPlatformServiceFactory implements PlatformServiceFactory, Closeable {
    private final DataSource dataSource;
    private final String schema;
    private final QueryRunner queryRunner;

    public DbPlatformServiceFactory(QueryRunner queryRunner, String schema) {
        this.schema = schema;
        this.dataSource = queryRunner.getDataSource();
        this.queryRunner = queryRunner;
    }

    @Override
    public PlatformClassService createPlatformClassService() {
        return new DbAndSystemPlatformClassService(queryRunner, schema);
    }

    @Override
    public PlatformClassService createPlatformClassService(PlatformClassService platformClassService) {
        return createPlatformClassService();
    }

    @Override
    public PlatformClassService createCachedPlatformClassService() {
        return new CachedDbAndSystemPlatformClassService(createPlatformClassService());
    }

    @Override
    public PlatformClassService createCachedPlatformClassService(CacheBuilder<String, PlatformClass> cacheBuilder) {
        return new CachedDbAndSystemPlatformClassService(createPlatformClassService(), cacheBuilder);
    }

    @Override
    public PlatformClassInfoService createPlatformClassInfoService() {
        return new DbAndSystemPlatformClassInfoService(queryRunner, schema);
    }

    @Override
    public PlatformClassInfoService createPlatformClassInfoService(PlatformClassService platformClassService) {
        return createPlatformClassInfoService();
    }

    @Override
    public PlatformCriterionInfoService createPlatformCriterionInfoService() {
        return new DbPlatformCriterionInfoService(queryRunner, schema);
    }

    @Override
    public PlatformCriterionInfoService createPlatformCriterionInfoService(PlatformClassService platformClassService) {
        return createPlatformCriterionInfoService();
    }

    @Override
    public PlatformCriterionComplexService createPlatformCriterionComplexService() {
        return new DbPlatformCriterionComplexService(queryRunner, schema);
    }

    @Override
    public PlatformCriterionComplexService createPlatformCriterionComplexService(PlatformClassService platformClassService) {
        return new DbPlatformCriterionComplexService(queryRunner, schema);
    }

    @Override
    public PlatformMethodInfoService createPlatformMethodInfoService() {
        return new DbPlatformMethodInfoService(queryRunner, schema);
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
        return new DbPlatformIndexColumnService(queryRunner, schema);
    }

    @Override
    public PlatformIndexColumnService createPlatformIndexColumnService(PlatformClassService platformClassService) {
        return createPlatformIndexColumnService();
    }

    @Override
    public PlatformClassTableService createPlatformClassTableService() {
        return new DbPlatformClassTableService(queryRunner, schema);
    }

    @Override
    public PlatformClassTableService createPlatformClassTableService(PlatformClassService platformClassService) {
        return createPlatformClassTableService();
    }

    @Override
    public FsModelPathService createFsModelPathService() {
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