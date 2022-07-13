package ru.sber.cb.assist.model.api.fs;

import org.junit.jupiter.api.*;
import ru.sber.cb.diam.metamodel.services.PlatformClassTableService;
import ru.sber.cb.diam.metamodel.services.dto.PlatformClassFieldTable;
import ru.sber.cb.diam.metamodel.services.dto.PlatformClassPartition;
import ru.sber.cb.diam.metamodel.services.dto.PlatformClassPartitionField;
import ru.sber.cb.diam.metamodel.services.dto.PlatformClassTable;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FsPlatformClassTableServiceTest extends BasePlatformServiceTest {

    static PlatformClassTableService fsPlatformClassTableService;

    @BeforeAll
    static void init() {
        fsPlatformClassTableService = platformServiceFactory.createPlatformClassTableService();
    }

    @Test
    @Order(1)
    void createOrReplacePlatformClassTable() {
        PlatformClassTable platformClassTable = PlatformClassTable.builder()
                .classId("PR_CRED")
                .name("Z#USER")
                .cached(0)
                .paramGroup("SMALL")
                .flags("0")
                .build();

        fsPlatformClassTableService.createOrReplacePlatformClassTable(platformClassTable);

        PlatformClassTable platformClassTable1 = fsPlatformClassTableService.getPlatformClassTable("PR_CRED");
        assertEquals(platformClassTable, platformClassTable1);
    }

    @Test
    @Order(2)
    void createOrReplaceTableField() {
        PlatformClassFieldTable platformClassFieldTable = PlatformClassFieldTable.builder()
                .classId("PR_CRED")
                .fieldTitle("ACC_DEP")
                .name("C_ACC_DEP")
                .deleted(true)
                .indexed(false)
                .self_class_id("DEPOSIT_PRIV_REF")
                .base_class_id("REFERENCE")
                .def("0")
                .target_class_id("DEPOSIT_PRIV")
                .logging("0")
                ._static(false)
                .nullable(true)
                .table_name("Z#USER")
                .editable(false)
                .position(58)
                .host_type("NUMBER")
                .not_null(false)
                .not_cached(true)
                .qual_pos(66)
                .build();

        fsPlatformClassTableService.createOrReplaceTableField(platformClassFieldTable);

        PlatformClassFieldTable platformClassFieldTable1 = fsPlatformClassTableService.getTableFields("PR_CRED").iterator().next();
        assertEquals(platformClassFieldTable, platformClassFieldTable1);
    }

    @Test
    @Order(3)
    void removeTableField() {
        PlatformClassFieldTable platformClassFieldTable = PlatformClassFieldTable.builder()
                .classId("PR_CRED")
                .fieldTitle("ACC_DEP")
                .build();
        fsPlatformClassTableService.removeTableField(platformClassFieldTable);
        assertFalse(fsPlatformClassTableService.getTableFields("PR_CRED").iterator().hasNext());
    }

    @Test
    @Order(4)
    void createOrReplaceTablePartition() {
        PlatformClassPartition platformClassPartition = PlatformClassPartition.builder()
                .classId("PR_CRED")
                .title(1)
                .name("C_ACC_DEP")
                .build();

        fsPlatformClassTableService.createOrReplaceTablePartition(platformClassPartition);

        PlatformClassPartition platformClassPartition1 = fsPlatformClassTableService.getTablePartitions("PR_CRED").iterator().next();
        assertEquals(platformClassPartition, platformClassPartition1);
    }

    @Test
    @Order(5)
    void createOrReplaceTablePartitionField() {
        PlatformClassPartitionField partitionField = PlatformClassPartitionField.builder()
                .classId("PR_CRED")
                .partitionTitle(1)
                .title("MDJ_DHD")
                .name("C_ACC_DEP")
                .build();

        fsPlatformClassTableService.createOrReplaceTablePartitionField(partitionField);

        PlatformClassPartitionField partitionField1 = fsPlatformClassTableService.getPartitionFields("PR_CRED", 1).iterator().next();
        assertEquals(partitionField, partitionField1);
    }

    @Test
    @Order(6)
    void removeTablePartitionField() {
        PlatformClassPartitionField partitionField = PlatformClassPartitionField.builder()
                .classId("PR_CRED")
                .partitionTitle(1)
                .title("MDJ_DHD")
                .name("C_ACC_DEP")
                .build();
        fsPlatformClassTableService.removeTablePartitionField(partitionField);
        assertFalse(fsPlatformClassTableService.getPartitionFields("PR_CRED", 1).iterator().hasNext());
    }

    @Test
    @Order(7)
    void removeTablePartition() {
        PlatformClassPartition platformClassPartition = PlatformClassPartition.builder()
                .classId("PR_CRED")
                .title(1)
                .name("C_ACC_DEP")
                .build();
        fsPlatformClassTableService.removeTablePartition(platformClassPartition);
        assertFalse(fsPlatformClassTableService.getTablePartitions("PR_CRED").iterator().hasNext());
    }

    @Test
    @Order(8)
    void removePlatformClassTable() {
        fsPlatformClassTableService.removePlatformClassTable("PR_CRED");
        assertNull(fsPlatformClassTableService.getPlatformClassTable("PR_CRED"));
    }
}