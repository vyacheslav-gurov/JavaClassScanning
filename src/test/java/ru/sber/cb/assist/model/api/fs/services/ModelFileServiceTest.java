package ru.sber.cb.assist.model.api.fs.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.sber.cb.assist.model.api.utils.PathUtils;
import ru.sber.cb.diam.metamodel.impl.fs.EntityType;
import ru.sber.cb.diam.metamodel.impl.fs.services.FileNode;
import ru.sber.cb.diam.metamodel.impl.fs.services.ModelFileService;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

class ModelFileServiceTest {

    private static ModelFileService modelFileService;

    @BeforeAll
    static void init() {
        modelFileService = new ModelFileService(PathUtils.testResource("schemasMultithread").toString());
    }

    @Test
    void getClassFile() {
        var node = modelFileService.getClassFile("PR_CRED");
        assertNotNull(node);
        assertEquals("PR_CRED", node.getClassId());
        assertEquals("PRODUCT", node.getParentId());
        assertNotNull(node.getPath());

        node = (FileNode.ClassFileNode) node.getParent();
        assertNotNull(node.getChilds());
        assertTrue(node.getChilds().size() >= 1);
        assertTrue(node.getChilds(EntityType.CLASS).stream()
                .map(FileNode::getId)
                .anyMatch("PR_CRED"::equals));
    }

    @Test
    void checkMultiThreadingErrors() throws InterruptedException {
        var node = modelFileService;
        int numberOfThreads = 100;
        ExecutorService service = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);
        for (int i = 0; i < numberOfThreads; i++) {
            service.execute(() -> {
                try {
                    node.getMethodFile("USER", "DELETE#AUTO");
                    node.getCriterionFile("USER","VW_CRIT_USER_USERS");
                    node.getIndexFile("USER", "PK_Z#USER_ID");
                    node.getTriggerFile("USER", "LOG_Z#USER");
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();
        assertEquals(modelFileService.getMethodFiles().size(), 5);
        assertEquals(modelFileService.getCriterionFiles().size(), 8);
        assertEquals(modelFileService.getIndexFiles().size(), 1);
        assertEquals(modelFileService.getTriggerFiles().size(), 1);
    }

    @Test
    void getClassFiles() {
        var nodes = modelFileService.getClassFiles();
        assertNotNull(nodes);
        assertFalse(nodes.size() < 2);
        assertTrue(nodes.keySet().containsAll(List.of("PRODUCT", "PR_CRED")));
    }
}