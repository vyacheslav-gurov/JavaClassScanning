package ru.sber.cb.assist.model.api.utils;

import org.junit.jupiter.api.Test;
import ru.sber.cb.diam.metamodel.impl.utils.BeanUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BeanUtilsTest {

    class TestObject {
        private int one = 1;
        private String two = "2";

        public int getOne() { return one; }
        public void setOne(int one) { this.one = one; }
        public String getTwo() { return two; }
        public void setTwo(String two) { this.two = two; }
    }

    @Test
    void fieldValuesByList() {
        var obj = new TestObject();
        var res = BeanUtils.fieldValuesByList(obj, List.of("two", "one"));

        assertEquals(2, res.length);
        assertEquals(obj.getTwo(), res[0]);
        assertEquals(obj.getOne(), res[1]);
    }

    @Test
    void negativeFieldValuesByList() {
        var obj = new TestObject();

        assertThrows(IllegalArgumentException.class, () -> BeanUtils.fieldValuesByList(obj, List.of("none")));
    }

    @Test
    void setByName() {
        var obj = new TestObject();
        BeanUtils.setByName(obj, "one", 2);
        BeanUtils.setByName(obj, "two", "1");
        assertEquals(2, obj.getOne());
        assertEquals("1", obj.getTwo());
    }

}