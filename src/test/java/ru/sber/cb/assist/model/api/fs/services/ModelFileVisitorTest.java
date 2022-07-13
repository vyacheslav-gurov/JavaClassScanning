package ru.sber.cb.assist.model.api.fs.services;

import org.junit.jupiter.api.Test;
import ru.sber.cb.assist.model.api.utils.PathUtils;
import ru.sber.cb.diam.metamodel.impl.fs.services.ModelFileVisitor;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ModelFileVisitorTest {

    @Test
    void visitFile() {
        var visits = new ArrayList<>();
        var visitor = ModelFileVisitor.builder()
                .withPrefixName("test")
                .withSuffixName("file")
                .withAcceptor(visits::add)
                .build();
        assertAll(() -> visitor.visitFile(PathUtils.testResource("test.file"), null));
        assertEquals(1, visits.size());
    }

    @Test
    void visitFileNoMatch() {
        var visits = new ArrayList<>();
        var visitor = ModelFileVisitor.builder()
                .withPrefixName("no-file")
                .withAcceptor(visits::add)
                .build();
        assertAll(() -> visitor.visitFile(PathUtils.testResource("test.file"), null));
        assertEquals(0, visits.size());
    }
}