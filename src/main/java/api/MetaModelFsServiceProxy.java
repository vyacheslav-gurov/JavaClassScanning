package api;

import ru.sber.cb.diam.metamodel.services.*;
import ru.sber.cb.diam.metamodel.services.dto.*;

import java.util.List;
import java.util.Map;

class MetaModelFsServiceProxy extends ReflectionPlatformServiceFactory implements
        PlatformClassInfoService,
        PlatformClassService,
        PlatformClassTableService,
        PlatformClassTriggerInfoService,
        PlatformCriterionComplexService,
        PlatformCriterionInfoService,
        PlatformIndexColumnService,
        PlatformMethodInfoService,
        PlatformClassKeysService,
        PlatformGuideGroupService,
        PlatformEntityService {

    private final PlatformClassInfoService platformClassInfoService;
    private final PlatformClassService platformClassService;
    private final PlatformClassTableService platformClassTableService;
    private final PlatformClassTriggerInfoService platformClassTriggerInfoService;
    private final PlatformCriterionComplexService platformCriterionComplexService;
    private final PlatformCriterionInfoService platformCriterionInfoService;
    private final PlatformIndexColumnService platformIndexColumnService;
    private final PlatformMethodInfoService platformMethodInfoService;
    private final PlatformClassKeysService platformClassKeysService;
    private final PlatformGuideGroupService platformGuideGroupService;
    private final PlatformEntityService platformEntityService;

    protected MetaModelFsServiceProxy(String rootPath,
                                      PlatformClassService platformClassService, ClassLoader classLoader) {
        super(classLoader);
        TypeParam[] typeParams = new TypeParam[]{TypeParam.builder().type(rootPath.getClass()).value(rootPath).build(), TypeParam.builder().type(PlatformClassService.class).value(platformClassService).build()};
        this.platformClassInfoService = getServiceInstanceFromNameAndTypeParams(FsPlatformClassInfoService, typeParams);
        this.platformClassService = getServiceInstanceFromNameAndTypeParams(FsPlatformClassService, typeParams);
        this.platformClassTableService = getServiceInstanceFromNameAndTypeParams(FsPlatformClassTableService, typeParams);
        this.platformClassTriggerInfoService = getServiceInstanceFromNameAndTypeParams(FsPlatformTriggerInfoService, typeParams);
        this.platformCriterionComplexService = getServiceInstanceFromNameAndTypeParams(FsPlatformCriterionComplexService, typeParams);
        this.platformCriterionInfoService = getServiceInstanceFromNameAndTypeParams(FsPlatformCriterionInfoService, typeParams);
        this.platformIndexColumnService = getServiceInstanceFromNameAndTypeParams(FsPlatformIndexColumnService, typeParams);
        this.platformMethodInfoService = getServiceInstanceFromNameAndTypeParams(FsPlatformMethodInfoService, typeParams);
        this.platformClassKeysService = getServiceInstanceFromNameAndTypeParams(FsPlatformClassKeyService, typeParams);
        this.platformGuideGroupService = getServiceInstanceFromNameAndTypeParams(FsPlatformGuideGroupService, typeParams);
        this.platformEntityService = getServiceInstanceFromNameAndTypeParams(FsPlatformEntityService, typeParams);
    }

    @Override
    public Iterable<PlatformClassField> getFields(String clazz) {
        return platformClassInfoService.getFields(clazz);
    }

    @Override
    public Iterable<PlatformClassMethod> getMethods(String clazz) {
        return platformClassInfoService.getMethods(clazz);
    }

    @Override
    public Iterable<PlatformClassCriterion> getCriteria(String clazz) {
        return platformClassInfoService.getCriteria(clazz);
    }

    @Override
    public Iterable<PlatformClassState> getStates(String clazz) {
        return platformClassInfoService.getStates(clazz);
    }

    @Override
    public Iterable<PlatformClassTransition> getTransitions(String clazz) {
        return platformClassInfoService.getTransitions(clazz);
    }

    @Override
    public Iterable<PlatformClassConstraint> getConstraints(String clazz) {
        return platformClassInfoService.getConstraints(clazz);
    }

    @Override
    public Iterable<PlatformClassIndex> getIndexes(String clazz) {
        return platformClassInfoService.getIndexes(clazz);
    }

    @Override
    public Iterable<PlatformClassTrigger> getTriggers(String clazz) {
        return platformClassInfoService.getTriggers(clazz);
    }

    @Override
    public void createOrReplaceTrigger(PlatformClassTrigger trigger) {
        platformClassInfoService.createOrReplaceTrigger(trigger);
    }

    @Override
    public void removeTrigger(PlatformClassTrigger trigger) {
        platformClassInfoService.removeTrigger(trigger);
    }

    @Override
    public void createOrReplaceField(PlatformClassField field) {
        platformClassInfoService.createOrReplaceField(field);
    }

    @Override
    public void removeField(PlatformClassField field) {
        platformClassInfoService.removeField(field);
    }

    @Override
    public void removeStatic(PlatformClassField field) {
        platformClassInfoService.removeStatic(field);
    }

    @Override
    public void createOrReplaceMethod(PlatformClassMethod method) {
        platformClassInfoService.createOrReplaceMethod(method);
    }

    @Override
    public void removeMethod(PlatformClassMethod method) {
        platformClassInfoService.removeMethod(method);
    }

    @Override
    public void createOrReplaceCriterion(PlatformClassCriterion criterion) {
        platformClassInfoService.createOrReplaceCriterion(criterion);
    }

    @Override
    public void removeCriterion(PlatformClassCriterion criterion) {
        platformClassInfoService.removeCriterion(criterion);
    }

    @Override
    public void createOrReplaceState(PlatformClassState state) {
        platformClassInfoService.createOrReplaceState(state);
    }

    @Override
    public void removeState(PlatformClassState state) {
        platformClassInfoService.removeState(state);
    }

    @Override
    public void createOrReplaceTransition(PlatformClassTransition transition) {
        platformClassInfoService.createOrReplaceTransition(transition);
    }

    @Override
    public void removeTransition(PlatformClassTransition transition) {
        platformClassInfoService.removeTransition(transition);
    }

    @Override
    public void createOrReplaceConstraint(PlatformClassConstraint constraint) {
        platformClassInfoService.createOrReplaceConstraint(constraint);
    }

    @Override
    public void removeConstraint(PlatformClassConstraint constraint) {
        platformClassInfoService.removeConstraint(constraint);
    }

    @Override
    public void createOrReplaceIndex(PlatformClassIndex index) {
        platformClassInfoService.createOrReplaceIndex(index);
    }

    @Override
    public void removeIndex(PlatformClassIndex index) {
        platformClassInfoService.removeIndex(index);
    }

    @Override
    public void fillDefaultValuesForDataTables(List<PlatformClassField> fields) {
        platformClassInfoService.fillDefaultValuesForDataTables(fields);
    }

    @Override
    public Iterable<PlatformClass> getAllPlatformClasses() {
        return platformClassService.getAllPlatformClasses();
    }

    @Override
    public PlatformClass getPlatformClass(String className) {
        return platformClassService.getPlatformClass(className);
    }

    @Override
    public List<PlatformClass> getPlatformClasses(List<String> classNames) {
        return platformClassService.getPlatformClasses(classNames);
    }

    @Override
    public Iterable<PlatformClass> getChildPlatformClass(String parent) {
        return platformClassService.getChildPlatformClass(parent);
    }

    @Override
    public Iterable<PlatformClass> getEntityRootPlatformClass(String entity) {
        return platformClassService.getEntityRootPlatformClass(entity);
    }

    @Override
    public void createOrReplacePlatformClass(PlatformClass platformClass) {
        platformClassService.createOrReplacePlatformClass(platformClass);
    }

    @Override
    public void compilePlatformClass(String className) {
        platformClassService.compilePlatformClass(className);
    }

    @Override
    public void compilePlatformClass(List<String> classNames) {
        platformClassService.compilePlatformClass(classNames);
    }

    @Override
    public String getClassPath(String s) {
        return platformClassService.getClassPath(s);
    }

    @Override
    public PlatformClassCriterion getCriterion(String clazz, String criterion) {
        return platformCriterionInfoService.getCriterion(clazz, criterion);
    }

    @Override
    public Iterable<PlatformClassCriterionColumn> getCriterionColumns(String clazz, String criterion) {
        return platformCriterionInfoService.getCriterionColumns(clazz, criterion);
    }

    @Override
    public Iterable<PlatformClassIndexColumn> getIndexColumns(String clazz, String indexName) {
        return platformIndexColumnService.getIndexColumns(clazz, indexName);
    }

    @Override
    public void createOrReplaceColumn(PlatformClassIndexColumn column, String classId) {
        platformIndexColumnService.createOrReplaceColumn(column, classId);
    }

    @Override
    public void removeColumn(PlatformClassIndexColumn column, String classId) {
        platformIndexColumnService.removeColumn(column, classId);
    }

    @Override
    public void compileIndex(String classId) {
        platformIndexColumnService.compileIndex(classId);
    }

    @Override
    public Iterable<PlatformClassCriterionComplex> getComplexes(String clazz, String criterion) {
        return platformCriterionInfoService.getComplexes(clazz, criterion);
    }

    @Override
    public Iterable<PlatformClassMethodReference> getMethodsReference(String clazz, String criterion) {
        return platformCriterionInfoService.getMethodsReference(clazz, criterion);
    }

    @Override
    public Iterable<PlatformClassCriterionTriesItem> getTries(String clazz, String criterion) {
        return platformCriterionInfoService.getTries(clazz, criterion);
    }

    @Override
    public Iterable<PlatformClassCriterionPrintsItem> getPrints(String clazz, String criterion) {
        return platformCriterionInfoService.getPrints(clazz, criterion);
    }

    @Override
    public String getConditionSource(String clazz, String criterion) {
        return platformCriterionInfoService.getConditionSource(clazz, criterion);
    }

    @Override
    public String getCellStyleSource(String clazz, String criterion) {
        return platformCriterionInfoService.getCellStyleSource(clazz, criterion);
    }

    @Override
    public String getOrderBySource(String clazz, String criterion) {
        return platformCriterionInfoService.getOrderBySource(clazz, criterion);
    }

    @Override
    public String getGroupBySource(String clazz, String criterion) {
        return platformCriterionInfoService.getGroupBySource(clazz, criterion);
    }

    @Override
    public String getRightsSource(String clazz, String criterion) {
        return platformCriterionInfoService.getRightsSource(clazz, criterion);
    }

    @Override
    public void createOrReplaceColumn(PlatformClassCriterionColumn column) {
        platformCriterionInfoService.createOrReplaceColumn(column);
    }

    @Override
    public void removeColumn(PlatformClassCriterionColumn column) {
        platformCriterionInfoService.removeColumn(column);
    }

    @Override
    public void createOrReplaceComplex(PlatformClassCriterionComplex complex) {
        platformCriterionInfoService.createOrReplaceComplex(complex);
    }

    @Override
    public void removeComplex(PlatformClassCriterionComplex complex) {
        platformCriterionInfoService.removeComplex(complex);
    }

    @Override
    public void createOrReplaceMethodReference(PlatformClassMethodReference method) {
        platformCriterionInfoService.createOrReplaceMethodReference(method);
    }

    @Override
    public void removeMethodReference(PlatformClassMethodReference method) {
        platformCriterionInfoService.removeMethodReference(method);
    }

    @Override
    public void createOrReplaceTriesItem(PlatformClassCriterionTriesItem triesItem) {
        platformCriterionInfoService.createOrReplaceTriesItem(triesItem);
    }

    @Override
    public void removeTriesItem(PlatformClassCriterionTriesItem triesItem) {
        platformCriterionInfoService.removeTriesItem(triesItem);
    }

    @Override
    public void createOrReplacePrintsItem(PlatformClassCriterionPrintsItem printsItem) {
        platformCriterionInfoService.createOrReplacePrintsItem(printsItem);
    }

    @Override
    public void removePrintsItem(PlatformClassCriterionPrintsItem printsItem) {
        platformCriterionInfoService.removePrintsItem(printsItem);
    }

    @Override
    public void createOrReplaceConditionSource(String clazz, String criterion, String text) {
        platformCriterionInfoService.createOrReplaceConditionSource(clazz, criterion, text);
    }

    @Override
    public void removeConditionSource(String clazz, String criterion) {
        platformCriterionInfoService.removeConditionSource(clazz, criterion);
    }

    @Override
    public void createOrReplaceCellStyleSource(String clazz, String criterion, String text) {
        platformCriterionInfoService.createOrReplaceCellStyleSource(clazz, criterion, text);
    }

    @Override
    public void removeCellStyleSource(String clazz, String criterion) {
        platformCriterionInfoService.removeCellStyleSource(clazz, criterion);
    }

    @Override
    public void createOrReplaceOrderBySource(String clazz, String criterion, String text) {
        platformCriterionInfoService.createOrReplaceOrderBySource(clazz, criterion, text);
    }

    @Override
    public void removeOrderBySource(String clazz, String criterion) {
        platformCriterionInfoService.removeOrderBySource(clazz, criterion);
    }

    @Override
    public void createOrReplaceGroupBySource(String clazz, String criterion, String text) {
        platformCriterionInfoService.createOrReplaceGroupBySource(clazz, criterion, text);
    }

    @Override
    public void removeGroupBySource(String clazz, String criterion) {
        platformCriterionInfoService.removeGroupBySource(clazz, criterion);
    }

    @Override
    public void createOrReplaceRightsSource(String clazz, String criterion, String text) {
        platformCriterionInfoService.createOrReplaceRightsSource(clazz, criterion, text);
    }

    @Override
    public void removeRightsSource(String clazz, String criterion) {
        platformCriterionInfoService.removeRightsSource(clazz, criterion);
    }

    @Override
    public void compileCriterion(String criterion) {
        platformCriterionInfoService.compileCriterion(criterion);
    }

    @Override
    public PlatformClassTable getPlatformClassTable(String clazz) {
        return platformClassTableService.getPlatformClassTable(clazz);
    }

    @Override
    public Iterable<PlatformClassFieldTable> getTableFields(String clazz) {
        return platformClassTableService.getTableFields(clazz);
    }

    @Override
    public Iterable<PlatformClassPartition> getTablePartitions(String clazz) {
        return platformClassTableService.getTablePartitions(clazz);
    }

    @Override
    public Iterable<PlatformClassPartitionField> getPartitionFields(String clazz, Integer partition) {
        return platformClassTableService.getPartitionFields(clazz, partition);
    }

    @Override
    public void createOrReplacePlatformClassTable(PlatformClassTable platformClassTable) {
        platformClassTableService.createOrReplacePlatformClassTable(platformClassTable);
    }

    @Override
    public void removePlatformClassTable(String clazz) {
        platformClassTableService.removePlatformClassTable(clazz);
    }

    @Override
    public void createOrReplaceTableField(PlatformClassFieldTable fieldTable) {
        platformClassTableService.createOrReplaceTableField(fieldTable);
    }

    @Override
    public void removeTableField(PlatformClassFieldTable fieldTable) {
        platformClassTableService.removeTableField(fieldTable);
    }

    @Override
    public void createOrReplaceTablePartition(PlatformClassPartition partition) {
        platformClassTableService.createOrReplaceTablePartition(partition);
    }

    @Override
    public void removeTablePartition(PlatformClassPartition partition) {
        platformClassTableService.removeTablePartition(partition);
    }

    @Override
    public void createOrReplaceTablePartitionField(PlatformClassPartitionField partitionField) {
        platformClassTableService.createOrReplaceTablePartitionField(partitionField);
    }

    @Override
    public void removeTablePartitionField(PlatformClassPartitionField partitionField) {
        platformClassTableService.removeTablePartitionField(partitionField);
    }

    @Override
    public String getSources(String classId, String trigger) {
        return platformClassTriggerInfoService.getSources(classId, trigger);
    }

    @Override
    public void createOrReplaceSource(String classId, String trigger, String text) {
        platformClassTriggerInfoService.createOrReplaceSource(classId, trigger, text);
    }

    @Override
    public Iterable<PlatformClassMethodAttribute> getParameters(String classId, String method) {
        return platformMethodInfoService.getParameters(classId, method);
    }

    @Override
    public Iterable<PlatformClassMethodAttribute> getVariables(String classId, String method) {
        return platformMethodInfoService.getVariables(classId, method);
    }

    @Override
    public Iterable<PlatformClassMethodFormControl> getControls(String classId, String method) {
        return platformMethodInfoService.getControls(classId, method);
    }

    @Override
    public Map<String, String> getSources(String classId, String method, String type) {
        return platformMethodInfoService.getSources(classId, method, type);
    }

    @Override
    public void createOrReplaceSource(String classId, String method, String type, String text) {
        platformMethodInfoService.createOrReplaceSource(classId, method, type, text);
    }

    @Override
    public void removeSource(String classId, String trigger, String type) {
        platformMethodInfoService.removeSource(classId, trigger, type);
    }

    @Override
    public void createOrReplaceParameter(PlatformClassMethodAttribute parameter) {
        platformMethodInfoService.createOrReplaceParameter(parameter);
    }

    @Override
    public void removeParameter(PlatformClassMethodAttribute parameter) {
        platformMethodInfoService.removeParameter(parameter);
    }

    @Override
    public void createOrReplaceVariable(PlatformClassMethodAttribute variable) {
        platformMethodInfoService.createOrReplaceVariable(variable);
    }

    @Override
    public void removeVariable(PlatformClassMethodAttribute variable) {
        platformMethodInfoService.removeVariable(variable);
    }

    @Override
    public void createOrReplaceControl(PlatformClassMethodFormControl control) {
        platformMethodInfoService.createOrReplaceControl(control);
    }

    @Override
    public void removeControl(PlatformClassMethodFormControl control) {
        platformMethodInfoService.removeControl(control);
    }

    @Override
    public void compileMethod(String classId, String method) {
        platformMethodInfoService.compileMethod(classId, method);
    }

    @Override
    public void compileMethods(List<PlatformClassMethod> methods) {
        platformMethodInfoService.compileMethods(methods);
    }

    @Override
    public String getConditionSource(String clazz, String criterion, Integer complexPosition) {
        return platformCriterionComplexService.getConditionSource(clazz, criterion, complexPosition);
    }

    @Override
    public String getGroupBySource(String clazz, String criterion, Integer complexPosition) {
        return platformCriterionComplexService.getGroupBySource(clazz, criterion, complexPosition);
    }

    @Override
    public void createOrReplaceConditionSource(String clazz, String criterion, Integer complexPosition, String text) {
        platformCriterionComplexService.createOrReplaceConditionSource(clazz, criterion, complexPosition, text);
    }

    @Override
    public void removeConditionSource(String clazz, String criterion, Integer complexPosition) {
        platformCriterionComplexService.removeConditionSource(clazz, criterion, complexPosition);
    }

    @Override
    public void createOrReplaceGroupBySource(String clazz, String criterion, Integer complexPosition, String text) {
        platformCriterionComplexService.createOrReplaceGroupBySource(clazz, criterion, complexPosition, text);
    }

    @Override
    public void removeGroupBySource(String clazz, String criterion, Integer complexPosition) {
        platformCriterionComplexService.removeGroupBySource(clazz, criterion, complexPosition);
    }

    @Override
    public Iterable<PlatformClassCriterionComplexColumn> getComplexColumns(String clazz, String criterion, Integer complexPosition) {
        return platformCriterionComplexService.getComplexColumns(clazz, criterion, complexPosition);
    }

    @Override
    public void createOrReplaceComplexColumn(PlatformClassCriterionComplexColumn complexColumn) {
        platformCriterionComplexService.createOrReplaceComplexColumn(complexColumn);
    }

    @Override
    public void removeComplexColumn(PlatformClassCriterionComplexColumn complexColumn) {
        platformCriterionComplexService.removeComplexColumn(complexColumn);
    }

    @Override
    public Iterable<PlatformClassKey> getKeys(String clazz) {
        return platformClassKeysService.getKeys(clazz);
    }

    @Override
    public Iterable<PlatformClassKeyField> getKeyFields(String clazz, String key) {
        return platformClassKeysService.getKeyFields(clazz, key);
    }

    @Override
    public void createOrReplaceKey(PlatformClassKey key) {
        platformClassKeysService.createOrReplaceKey(key);
    }

    @Override
    public void removeKey(PlatformClassKey key) {
        platformClassKeysService.removeKey(key);
    }

    @Override
    public void createOrReplaceKeyField(PlatformClassKeyField keyField) {
        platformClassKeysService.createOrReplaceKeyField(keyField);
    }

    @Override
    public void removeKeyField(PlatformClassKeyField keyField) {
        platformClassKeysService.removeKeyField(keyField);
    }

    @Override
    public Iterable<PlatformEntity> getPlatformEntities() {
        return platformEntityService.getPlatformEntities();
    }

    @Override
    public PlatformEntity getPlatformEntity(String id) {
        return platformEntityService.getPlatformEntity(id);
    }

    @Override
    public Map<String, Object> getEntityMap(String id) {
        return platformEntityService.getEntityMap(id);
    }

    @Override
    public Iterable<PlatformGuideGroup> getAllPlatformGuideGroups() {
        return platformGuideGroupService.getAllPlatformGuideGroups();
    }

    @Override
    public void createOrReplacePlatformGuideGroup(PlatformGuideGroup platformGuideGroup) {
        platformGuideGroupService.createOrReplacePlatformGuideGroup(platformGuideGroup);
    }

    @Override
    public void removePlatformGuideGroup(String platformGuideGroupId) {
        platformGuideGroupService.removePlatformGuideGroup(platformGuideGroupId);
    }
}
