package ru.sber.cb.assist.model.api.mappings;

import org.junit.jupiter.api.Test;
import ru.sber.cb.diam.metamodel.impl.mappings.NamingHelper;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NamingHelperTest {

    @Test
    void getMethodFromRef() {
        var ref = "base_entities/product/product/pr_cred/methods/method1/method1-method-schema.json";
        var methodKey = NamingHelper.getMethodFromRef(ref);
        assertEquals("METHOD1", methodKey);
    }
}