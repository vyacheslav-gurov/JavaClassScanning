package ru.sber.cb.assist.model.api.utils;

import org.junit.jupiter.api.Test;
import ru.sber.cb.diam.metamodel.impl.utils.StringUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringUtilsTest {

    @Test
    void lastPathNameTest() {
        assertEquals("two", StringUtils.lastPathName("one/two"));
        assertEquals("two", StringUtils.lastPathName("/two"));
        assertEquals("", StringUtils.lastPathName("one/"));
        assertEquals("one", StringUtils.lastPathName("one"));
    }
}