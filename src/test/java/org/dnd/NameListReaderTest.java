package org.dnd;

import org.junit.jupiter.api.Test;

import static org.dnd.Utils.toResousePath;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NameListReaderTest {

    NameListReader nameListReader = new NameListReader();

    @Test
    void testWithOnlyLastName() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> nameListReader.readNamesFromFile(toResousePath("last_name_only.txt").toString()
                )
        );
        assertEquals(
                "A Name must contain at least one given name along with the last name",
                ex.getMessage()
        );
    }

    @Test
    void testWithMoreThanThreeGivenName() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> nameListReader.readNamesFromFile(toResousePath("test3-input.txt").toString()
                )
        );
        assertEquals(
                "A Name can only contain a maximum of 3 given names along with the last name",
                ex.getMessage()
        );
    }
}
