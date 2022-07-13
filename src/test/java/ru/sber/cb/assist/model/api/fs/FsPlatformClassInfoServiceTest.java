package ru.sber.cb.assist.model.api.fs;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.sber.cb.diam.metamodel.services.PlatformClassInfoService;
import ru.sber.cb.diam.metamodel.services.dto.PlatformClassField;
import ru.sber.cb.diam.metamodel.services.dto.PlatformClassMethod;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FsPlatformClassInfoServiceTest extends BasePlatformServiceTest {

    static PlatformClassInfoService fsPlatformClassInfoService;

    @BeforeAll
    static void init() {
        fsPlatformClassInfoService = platformServiceFactory.createPlatformClassInfoService();
    }

    @Test
    void getFields() {
        List<PlatformClassField> fields = (List) fsPlatformClassInfoService.getFields("PR_CRED");
        assertNotNull(fields);
        assertTrue(fields.size() > 0);
        fields.forEach(field -> {
            assertEquals("PR_CRED", field.getClassId());
            assertNotNull(field.getName());
            assertNotNull(field.getTitle());
            assertNotNull(field.getType());
        });
    }

    @Test
    void createOrReplaceField() {
        PlatformClassField field = PlatformClassField.builder()
                .classId("PR_CRED")
                .name("TEST_FIELD")
                .title("title")
                .type("PR_CRED")
                .position(100)
                .build();
        fsPlatformClassInfoService.createOrReplaceField(field);

        PlatformClassField field1 = ((List<PlatformClassField>) fsPlatformClassInfoService.getFields("PR_CRED")).stream()
                .filter(f -> "TEST_FIELD".equals(f.getName()))
                .findAny()
                .get();
        assertEquals(field, field1);

        fsPlatformClassInfoService.removeField(field1);

        assertTrue(((List<PlatformClassField>) fsPlatformClassInfoService.getFields("PR_CRED")).stream()
                .filter(f -> "TEST_FIELD".equals(f.getName()))
                .findAny()
                .isEmpty());
    }

    @Test
    void createOrReplaceMethod() {
        PlatformClassMethod platformClassMethod = PlatformClassMethod.builder()
                .classId("PR_CRED")
                .title("метод 1")
                .name("METHOD1")
                .reportClassId("PR_CRED")
                .created(new Date())
                .properties("|BUF |CMD Y|")
                .userDriven(false)
                .build();
        fsPlatformClassInfoService.createOrReplaceMethod(platformClassMethod);
        var platformClassMethod1 = fsPlatformClassInfoService.getMethods("PR_CRED").iterator().next();
        assertEquals(platformClassMethod, platformClassMethod1);

        fsPlatformClassInfoService.removeMethod(platformClassMethod1);
        fsPlatformClassInfoService.getMethods("PR_CRED").forEach(m -> {
            if (m.getName().equals("METHOD1")) fail();
        });
    }
}