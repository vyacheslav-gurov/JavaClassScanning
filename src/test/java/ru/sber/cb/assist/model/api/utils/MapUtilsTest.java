package ru.sber.cb.assist.model.api.utils;

import org.junit.jupiter.api.Test;
import ru.sber.cb.diam.metamodel.impl.utils.MapUtils;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MapUtilsTest {

    void assertMap(Map<String, String> map) {
        assertEquals(2, map.entrySet().size());
        assertEquals("aa", map.get("A"));
        assertEquals("bb", map.get("B"));
        assertThrows(UnsupportedOperationException.class, () -> map.put("A", "ccc"));
        assertThrows(UnsupportedOperationException.class, () -> map.put("C", "ccc"));
        assertThrows(UnsupportedOperationException.class, () -> map.remove("A"));
    }

    @Test
    void mapOfTest() {
        Map<String, String> map = MapUtils.mapOf(
                "A", "aa",
                "B", "bb"
        );
        assertMap(map);

        assertDoesNotThrow(() -> MapUtils.mapOf());
        assertThrows(IllegalArgumentException.class, () -> MapUtils.mapOf("A"));
        assertThrows(IllegalArgumentException.class, () -> MapUtils.mapOf("A", "a", "A", "b"));
        assertThrows(NullPointerException.class, () -> MapUtils.mapOf("A", null));
        assertThrows(NullPointerException.class, () -> MapUtils.mapOf(null, "A"));

    }

    @Test
    void mapBuilderTest() {

        var map = MapUtils.<String, String>mapBuilder()
                .put("A", "aa")
                .put("B", "bb")
                .build();
        assertMap(map);

        assertDoesNotThrow(() -> MapUtils.mapBuilder().build());
        assertThrows(IllegalArgumentException.class, () -> MapUtils.mapBuilder().put("A", "a").put("A", "b"));
        assertThrows(NullPointerException.class, () -> MapUtils.mapBuilder().put("A", null));
        assertThrows(NullPointerException.class, () -> MapUtils.mapBuilder().put(null, "A"));
    }


}