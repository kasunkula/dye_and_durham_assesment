package org.dnd;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NameSorterTest {

    NameSorter nameSorter = new NameSorter(new NameComparatorByLastNameThenGivenNames());

    @Test
    void testEmptyDataSets() {
        assertEquals(nameSorter.sort(List.of()), List.of());
    }

    @Test
    void testDifferentLastNamesWithAdditionalSpaces() {
        assertEquals(
                List.of("Mary Ann Lee", "John   Smith"), // Smith < Doe
                nameSorter.sort(List.of("Mary Ann Lee", "John   Smith"))
                );
    }

    @Test
    void testDifferentLastNamesWithLeadingAndTrailingSpaces() {
        assertEquals(
                List.of(" Jane Doe ", "John Smith"), // Smith < Doe
                nameSorter.sort(List.of("John Smith", " Jane Doe "))
        );
    }

    @Test
    void testDifferentLastNames() {
        assertEquals(
                nameSorter.sort(List.of("John Smith", "Jane Doe")),
                List.of("Jane Doe", "John Smith")); // Smith < Doe
    }

    @Test
    void testSameLastNameDifferentFirstNames() {
        assertEquals(
                nameSorter.sort(List.of("John Smith", "Adam Smith")),
                List.of("Adam Smith", "John Smith")); // John < Adam
    }

    @Test
    void testIdenticalNames() {
        assertEquals(
                nameSorter.sort(List.of("John Smith", "John Smith")),
                List.of("John Smith", "John Smith")); // identical names
    }

    @Test
    void testMixedCaseIdenticalNames() {
        assertEquals(
                nameSorter.sort(List.of("John Smith", "john smith")),
                List.of("John Smith", "john smith")); // identical names, different case
    }

    @Test
    void testMultiPartNames() {
        assertEquals(
                nameSorter.sort(List.of("Mary Ann Lee", "Mary Lee")),
                List.of("Mary Lee", "Mary Ann Lee")); // Mary Ann < Mary
    }

    @Test
    void testMixedCaseMultiPartNames() {
        assertEquals(
                nameSorter.sort(List.of("Mary ANN Lee", "mary lee")),
                List.of("mary lee", "Mary ANN Lee")); // Mary Ann < Mary
    }

    @Test
    void testExample() {
        assertEquals(
                nameSorter.sort(
                        List.of("Janet Parsons",
                                "Vaughn Lewis",
                                "Adonis Julius Archer",
                                "Shelby Nathan Yoder",
                                "Marin Alvarez",
                                "London Lindsey",
                                "Beau Tristan Bentley",
                                "Leo Gardner",
                                "Hunter Uriah Mathew Clarke",
                                "Mikayla Lopez",
                                "Frankie Conner Ritter"
                        ))
                , List.of("Marin Alvarez",
                        "Adonis Julius Archer",
                        "Beau Tristan Bentley",
                        "Hunter Uriah Mathew Clarke",
                        "Leo Gardner",
                        "Vaughn Lewis",
                        "London Lindsey",
                        "Mikayla Lopez",
                        "Janet Parsons",
                        "Frankie Conner Ritter",
                        "Shelby Nathan Yoder"));
    }
}
