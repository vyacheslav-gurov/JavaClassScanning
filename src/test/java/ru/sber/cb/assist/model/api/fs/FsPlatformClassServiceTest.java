package ru.sber.cb.assist.model.api.fs;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.sber.cb.assist.model.api.utils.PathUtils;
import ru.sber.cb.diam.metamodel.impl.fs.FsPlatformClassService;
import ru.sber.cb.diam.metamodel.services.PlatformClassService;
import ru.sber.cb.diam.metamodel.services.dto.PlatformClass;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FsPlatformClassServiceTest extends BasePlatformServiceTest {

    static PlatformClassService fsPlatformClassService;

    @BeforeAll
    static void init() {
        fsPlatformClassService = platformServiceFactory.createPlatformClassService();
    }

    @Test
    void getAllPlatformClasses() {
        var allPlatformClasses = fsPlatformClassService.getAllPlatformClasses();
        assertNotNull(allPlatformClasses);
        List<PlatformClass> list = (List) allPlatformClasses;
        assertTrue(list.size() >= 2);

        list = (List)fsPlatformClassService.getEntityRootPlatformClass("TYPE");
        assertTrue(list.size() == 1);
    }

    @Test
    void getPlatformClass() {
        var platformClass = fsPlatformClassService.getPlatformClass("PR_CRED");
        assertPrCred(platformClass);
    }

    @Test
    void getChildPlatformClass() {
        var platformClasses = fsPlatformClassService.getChildPlatformClass("PRODUCT");
        platformClasses.forEach(platformClass -> {
            if ("PR_CRED".equals(platformClass.getId())) {
                assertPrCred(platformClass);
            }
        });
    }

    void assertPrCred(PlatformClass platformClass) {
        assertEquals("PR_CRED", platformClass.getId());
        assertEquals("STRUCTURE", platformClass.getBase());
        assertEquals("PRODUCT", platformClass.getEntity());
        assertEquals("PRODUCT", platformClass.getParent());
        assertEquals("Кредиты", platformClass.getTitle());
        assertNotNull(platformClass.getModified());
    }

    @Test
    void getEntityRootPlatformClass() {
        var platformClasses = fsPlatformClassService.getEntityRootPlatformClass("BASE_ENTITIES");
        platformClasses.forEach(platformClass -> {
            if ("PRODUCT".equals(platformClass.getId())) {
                assertEquals("STRUCTURE", platformClass.getBase());
                assertEquals("PRODUCT", platformClass.getEntity());
                assertNull(platformClass.getParent());
                assertEquals("Продукты", platformClass.getTitle());
                assertNotNull(platformClass.getModified());
            }
        });
    }

    @Test
    void createOrReplacePlatformClass() {
        PlatformClass platformClass = PlatformClass.builder()
                .id("DISTRIB")
                .parent("PR_CRED")
                .base("STRUCTURE")
                .entity("PRODUCT")
                .title("Гарантированное размещение средств")
                .build();
        fsPlatformClassService.createOrReplacePlatformClass(platformClass);
        var platformClass1 = fsPlatformClassService.getPlatformClass("DISTRIB");
        assertEquals(platformClass, platformClass1);
    }

    @Test
    void getClassPathForMetadata() {
        PlatformClassService pcs = Mockito.mock(PlatformClassService.class);
        PlatformClass childPlatformClass = PlatformClass.builder()
                .id("CHILD")
                .parent("PARENT")
                .base("STRUCTURE")
                .entity("PRODUCT")
                .title("Гарантированное размещение средств")
                .isKernel(true)
                .build();
        PlatformClass parentPlatformClass = PlatformClass.builder()
                .id("PARENT")
                .parent(null)
                .base("STRUCTURE")
                .entity("PRODUCT")
                .title("Гарантированное размещение средств")
                .isKernel(true)
                .build();
        Mockito.when(pcs.getPlatformClass("CHILD")).thenReturn(childPlatformClass);
        Mockito.when(pcs.getPlatformClass("PARENT")).thenReturn(parentPlatformClass);
        FsPlatformClassService fs = new FsPlatformClassService(PathUtils.testResource("schemas").toString(), pcs);
        String path = fs.getClassPath(childPlatformClass.getId());
        assertEquals("/metadata/parent/child", path);
    }

}