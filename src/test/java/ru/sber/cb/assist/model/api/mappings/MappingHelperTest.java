package ru.sber.cb.assist.model.api.mappings;

import org.junit.jupiter.api.Test;
import ru.sber.cb.diam.metamodel.impl.fs.EntityType;
import ru.sber.cb.diam.metamodel.impl.mappings.MappingHelper;

import static org.junit.jupiter.api.Assertions.*;

class MappingHelperTest {

    @Test
    void stringToList() {
        var props = "|BUF |CMD Y|";
        var list = MappingHelper.stringToList(props);
        assertEquals(4, list.size());
        assertEquals(props, MappingHelper.listToString(list));
    }

    @Test
    void createClassRelativePathRef() {
        String fromClassPath = "/base_entities/product/product/pr_cred";
        String toClassPath = "/base_entities/client/client/cl_priv";

        assertEquals("../../../../base_entities/client/client/cl_priv/cl_priv-class-schema.json",
            MappingHelper.createClassRelativePathRef(fromClassPath, EntityType.CLASS, toClassPath, EntityType.CLASS, null));

        assertEquals("../../../../base_entities/client/client/cl_priv/methods/edit#auto/edit#auto-method-schema.json",
                MappingHelper.createClassRelativePathRef(fromClassPath, EntityType.CLASS, toClassPath, EntityType.METHOD, "EDIT#AUTO"));

        assertEquals("../../../../base_entities/client/client/cl_priv/criteria/vw_crit_cl_priv/vw_crit_cl_priv-criteria-schema.json",
                MappingHelper.createClassRelativePathRef(fromClassPath, EntityType.CLASS, toClassPath, EntityType.CRITERIA, "VW_CRIT_CL_PRIV"));

        assertThrows(IllegalArgumentException.class, () ->
            MappingHelper.createClassRelativePathRef(fromClassPath, EntityType.CLASS, toClassPath, EntityType.SOURCE, null));

        assertThrows(IllegalArgumentException.class, () ->
                MappingHelper.createClassRelativePathRef(fromClassPath, EntityType.CLASS, toClassPath, EntityType.ENTITY, null));

        assertEquals("../../../../../../base_entities/client/client/cl_priv/cl_priv-class-schema.json",
                MappingHelper.createClassRelativePathRef(fromClassPath, EntityType.METHOD, toClassPath, EntityType.CLASS, null));

        assertEquals("../../../../../../base_entities/client/client/cl_priv/cl_priv-class-schema.json",
                MappingHelper.createClassRelativePathRef(fromClassPath, EntityType.CRITERIA, toClassPath, EntityType.CLASS, null));

        assertEquals("../../../../../../../base_entities/client/client/cl_priv/cl_priv-class-schema.json",
                MappingHelper.createClassRelativePathRef(fromClassPath, EntityType.SOURCE, toClassPath, EntityType.CLASS, null));

        assertThrows(IllegalArgumentException.class, () ->
                MappingHelper.createClassRelativePathRef(fromClassPath, EntityType.ENTITY, toClassPath, EntityType.CLASS, null));

        assertNull(MappingHelper.createClassRelativePathRef(fromClassPath, EntityType.CLASS, null, EntityType.CLASS, null));

    }
}