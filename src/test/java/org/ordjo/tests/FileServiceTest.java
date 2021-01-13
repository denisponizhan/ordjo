package org.ordjo.tests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ordjo.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.io.File;
import java.lang.reflect.Method;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@Transactional
public class FileServiceTest {
    @Autowired
    private FileService fileService;

    @Value("${photo.upload.directory}")
    private String photoUploadDirectory;

    @Test
    public void testGetExtension() throws Exception {
        //this is how to get access to private method
        Method method = FileService.class.getDeclaredMethod(
                "getFileExtension",
                String.class);

        method.setAccessible(true);
        assertEquals(
                "Should be png",
                "png",
                (String) method.invoke(fileService, "test.png"));

        assertEquals(
                "Should be doc",
                "doc",
                (String) method.invoke(fileService, "hello.hello.doc"));

        assertEquals(
                "Should be jpeg",
                "jpeg",
                (String) method.invoke(fileService, "file.jpg.file.jpeg"));

        assertNull(
                "Should be null",
                (String) method.invoke(fileService, "test"));
    }

    @Test
    public void testIsImageExtension() throws Exception {
        Method method = FileService.class.getDeclaredMethod(
                "isImageExtension",
                String.class);

        method.setAccessible(true);
        assertTrue(
                "png should be valid",
                (Boolean) method.invoke(fileService, "png"));

        assertTrue(
                "PNG should be valid",
                (Boolean) method.invoke(fileService, "PNG"));

        assertTrue(
                "jpg should be valid",
                (Boolean) method.invoke(fileService, "jpg"));

        assertTrue(
                "jpeg should be valid",
                (Boolean) method.invoke(fileService, "jpeg"));

        assertTrue(
                "gif should be valid",
                (Boolean) method.invoke(fileService, "gif"));

        assertFalse(
                "doc should be invalid",
                (Boolean) method.invoke(fileService, "doc"));

    }

    @Test
    public void testCreateDirectory() throws Exception {
        Method method = FileService.class.getDeclaredMethod(
                "makeSubdirectory",
                String.class,
                String.class);

        method.setAccessible(true);

        for (int i = 0; i < 10000; i++) {
            File created = (File) method.invoke(
                    fileService,
                    photoUploadDirectory,
                    "photo");

            assertTrue("Directory should exist " + created.getAbsolutePath(),
                    created.exists());
        }
    }
}
