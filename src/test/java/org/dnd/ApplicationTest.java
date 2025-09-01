package org.dnd;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.nio.file.Path;

import static org.dnd.Utils.toResousePath;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationTest {

    private static final String TEST_OUTPUT_FILE = "test-sorted-names-list.txt";

    @BeforeAll
    static void setup() throws NoSuchFieldException, IllegalAccessException {
        Field field = Application.class.getDeclaredField("OUTPUT_FILE");
        field.setAccessible(true);
        field.set(null, TEST_OUTPUT_FILE);
    }

    @AfterEach
    void tearDown() throws IOException {
        java.nio.file.Files.deleteIfExists(Path.of(TEST_OUTPUT_FILE));
    }

    @AfterAll
    static void cleanup() throws IOException {
        java.nio.file.Files.deleteIfExists(Path.of(TEST_OUTPUT_FILE));
    }

    @Test
    void testMainWithValidFile() throws URISyntaxException, IOException {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String[] args = {toResousePath("test1-input.txt").toString()};
        Application.main(args);

        System.setOut(originalOut);
        String output = outContent.toString();

        String expected = java.nio.file.Files.readString(toResousePath("test1-expected.txt"));
        assertTrue(output.contains(expected), "Console output should contain the sorted names");
        assertEquals(
                expected,
                java.nio.file.Files.readString(Path.of(TEST_OUTPUT_FILE)),
                "Output file content should match expected sorted file"
        );
    }


    @Test
    void testMainWithNoArgs() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        Application.main(new String[]{});

        System.setOut(originalOut);
        String output = outContent.toString();
        assertTrue(output.contains("Please provide the input file path"));
    }
}
