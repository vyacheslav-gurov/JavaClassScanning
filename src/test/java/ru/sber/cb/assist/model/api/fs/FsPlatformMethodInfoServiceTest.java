package ru.sber.cb.assist.model.api.fs;

import org.junit.jupiter.api.*;
import ru.sber.cb.assist.model.api.utils.PathUtils;
import ru.sber.cb.diam.metamodel.impl.fs.BaseClassFileService;
import ru.sber.cb.diam.metamodel.impl.fs.services.ModelFileService;
import ru.sber.cb.diam.metamodel.services.PlatformClassInfoService;
import ru.sber.cb.diam.metamodel.services.PlatformMethodInfoService;
import ru.sber.cb.diam.metamodel.services.dto.ControlType;
import ru.sber.cb.diam.metamodel.services.dto.PlatformClassMethod;
import ru.sber.cb.diam.metamodel.services.dto.PlatformClassMethodAttribute;
import ru.sber.cb.diam.metamodel.services.dto.PlatformClassMethodFormControl;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FsPlatformMethodInfoServiceTest extends BasePlatformServiceTest {

    static PlatformMethodInfoService fsPlatformMethodInfoService;
    static PlatformClassInfoService fsPlatformClassInfoService;

    @BeforeAll
    static void init() {
        fsPlatformMethodInfoService = platformServiceFactory.createPlatformMethodInfoService();
        fsPlatformClassInfoService = platformServiceFactory.createPlatformClassInfoService();
    }

    @Test
    @Order(1)
    void createOrReplaceMethod() {
        PlatformClassMethod platformClassMethod = PlatformClassMethod.builder()
                .classId("PR_CRED")
                .title("метод 1")
                .name("METHOD1")
                .reportClassId("PR_CRED")
                .created(new Date())
                .modified(new Date())
                .properties("|BUF |CMD Y|")
                .status("NOT COMPILED")
                .build();
        fsPlatformClassInfoService.createOrReplaceMethod(platformClassMethod);
    }

    @Test
    @Order(2)
    void createOrReplaceSource() {

        String text = "function sald(Date_Beg date, Date_End date, Date_res date, REF_ACC ref [AC_FIN], par string(1)) return number;\n" +
                "pragma restrict_references(sald, wnds);\n" +
                "\n" +
                "Function FIO(sName\tstring) return string;\n" +
                "pragma restrict_references(FIO, wnds, wnps);\n" +
                "\n" +
                "\n" +
                "Function chk_storno(rDoc\tref [MAIN_DOCUM]) Return varchar2;\n" +
                "pragma restrict_references(chk_storno, wnds, wnps);\n" +
                "\n" +
                "Procedure free_memory;\n" +
                "\n" +
                "Procedure Set_Context\n" +
                "\t\t( A_SHOW_EMPTY\t\t\t\tstring(1)\t\t:= '0'\n" +
                "\t\t, A_ACC_SHIFR\t\t\t\tstring(1)\t\t:= '0'\n" +
                "\t\t, A_CHANGE\t\t\t\t\tstring(1)\t\t:= '0'\n" +
                "\t\t, A_SD\t\t\t\t\t\tdate\n" +
                "\t\t, A_ED\t\t\t\t\t\tdate\n" +
                "\t\t, A_DATE_OP_DAY\t\t\t\tdate\n" +
                "\t\t, A_ACCOUNT\t\t\t\t\tstring\n" +
                "\t\t, A_OTV_USER\t\t\t\tstring\n" +
                "\t\t, A_GRP_IN\t\t\t\t\tstring(1)\t\t:= '0'\n" +
                "\t\t, A_GRP_OUT\t\t\t\t\tstring(1)\t\t:= '0'\n" +
                "\t\t, A_CARD_INFO\t\t\t\tstring(1)\t\t:= '0'\n" +
                "\t\t, A_DEP_GRP_CODE\t\t\tvarchar2\t\t:= null\n" +
                "\t\t);\n" +
                "\n" +
                "Procedure trace(P_START\tvarchar2 := '1');";
        fsPlatformMethodInfoService.createOrReplaceSource("PR_CRED", "method1", "PUBLIC", text);
        var text1 = fsPlatformMethodInfoService.getSources("PR_CRED", "method1", "PUBLIC");
        assertEquals(text, text1.get("PUBLIC"));

        Assertions.assertDoesNotThrow(() ->
                fsPlatformMethodInfoService.removeSource("PR_CRED", "method1", "PUBLIC"));
    }

    @Test
    @Order(3)
    void createOrReplaceControl() {
        var form = PlatformClassMethodFormControl.builder()
                .methodClass("PR_CRED")
                .methodName("METHOD1")
                .position(0)
                .name("Form")
                .title("Form title")
                .type(ControlType.FORM)
                .props("|A|B|")
                .build();
        fsPlatformMethodInfoService.createOrReplaceControl(form);

        PlatformClassMethodFormControl control = PlatformClassMethodFormControl.builder()
                .methodClass("PR_CRED")
                .methodName("METHOD1")
                .position(1)
                .parentControlPosition(0)
                .name("Text")
                .title("Text")
                .type(ControlType.TEXT)
                .build();
        fsPlatformMethodInfoService.createOrReplaceControl(control);

        var controls = fsPlatformMethodInfoService.getControls("PR_CRED", "METHOD1").iterator();
        var form1 = controls.next();
        assertEquals(form, form1);
        var control1 = controls.next();
        assertEquals(control, control1);

        assertDoesNotThrow(() -> fsPlatformMethodInfoService.removeControl(control));
    }

    @Test
    @Order(4)
    void createOrReplaceMethodCopy() {
        fsPlatformClassInfoService.createOrReplaceMethod(PlatformClassMethod.builder()
                .classId("PR_CRED")
                .title("метод 2")
                .name("METHOD2")
                .reportClassId("PR_CRED")
                .created(new Date())
                .modified(new Date())
                .properties("|BUF |CMD Y|")
                .status("NOT COMPILED")
                .formMethodClassId("PR_CRED")
                .formMethodTitle("METHOD1")
                .build());




        var node = new ModelFileService(PathUtils.testResource("schemas").toString()).getMethodFile("PR_CRED", "METHOD2");
        BaseClassFileService baseClassFileService = (BaseClassFileService) fsPlatformClassInfoService;
        var dto = baseClassFileService.readMethodFile(node);
        assertNotNull(dto.getForm());
        assertEquals("../../../../../../base_entities/product/product/pr_cred/methods/method1/method1-method-schema.json",
            dto.getForm().get$ref());
    }

    @Test
    @Order(4)
    void createOrReplaceParameter() {
        fsPlatformMethodInfoService.createOrReplaceParameter(PlatformClassMethodAttribute.builder()
                        .classId("PR_CRED")
                        .method("METHOD2")
                        .position(1)
                        .title("param1")
                        .name("Param 1")
                        .type("BOOLEAN")
                        .build());
        var node = new ModelFileService(PathUtils.testResource("schemas").toString()).getMethodFile("PR_CRED", "METHOD2");
        BaseClassFileService baseClassFileService = (BaseClassFileService) fsPlatformClassInfoService;
        var dto = baseClassFileService.readMethodFile(node);
        var param = dto.getParameters().getAdditionalProperties().get("param1");
        assertNotNull(param);
        assertNotNull(param.getType());
        assertEquals("../../../../../../type/boolean/boolean/boolean-class-schema.json", param.getType().get$ref());
    }

}