package org.dnd;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Utility class responsible for reading and validating a list of names
 * from a text file. Each line in the file represents a single name.
 *
 * <p>Responsibilities:</p>
 * <ul>
 *     <li>Read all names from a given file path.</li>
 *     <li>Validate that each name contains at least 2 parts
 *         (a given name and a last name) and at most 4 parts
 *         (up to 3 given names plus a last name).</li>
 *     <li>Sanitize the list of names by trimming whitespace.</li>
 * </ul>
 *
 * <p>Example usage:</p>
 * <pre>
 *     NameListReader reader = new NameListReader();
 *     List&lt;String&gt; names = reader.readNamesFromFile("unsorted-names-list.txt");
 * </pre>
 */
public class NameListReader {

    static final int MIN_GIVEN_NAMES = 1; // this could be turned in to attributes if needed
    static final int MAX_GIVEN_NAMES = 3; // this could be turned in to attributes if needed

    static final int MIN_PARTS_IN_NAMES = MIN_GIVEN_NAMES + 1;
    static final int MAX_PARTS_IN_NAMES = MAX_GIVEN_NAMES + 1;

    static final Pattern pattern = Pattern.compile("\\s+");

    /**
     * Reads a list of names from the specified file path, validates them,
     * and sanitizes them by trimming whitespace.
     *
     * @param filePath the path to the text file containing names;
     *                 each line should represent a name
     * @return a list of validated and sanitized names
     * @throws IOException              if an I/O error occurs reading from the file
     * @throws IllegalArgumentException if any name is invalid:
     *                                  <ul>
     *                                      <li>Fewer than 2 parts (e.g., missing last name)</li>
     *                                      <li>More than 4 parts (too many given names)</li>
     *                                  </ul>
     */
    List<String> readNamesFromFile(String filePath) throws IOException, IllegalArgumentException {
        List<String> names = Files.readAllLines(Path.of(filePath));
        validate(names);
        sanitize(names);
        return names;
    }

    /**
     * Validates that each name in the list has between 2 and 4 parts.
     * <p>
     * A valid name must have:
     * <ul>
     *     <li>At least 2 parts: a given name and a last name</li>
     *     <li>At most 4 parts: up to 3 given names plus a last name</li>
     * </ul>
     *
     * @param names the list of names to validate
     * @throws IllegalArgumentException if any name is invalid
     */
    private static void validate(List<String> names) {
        names.forEach(name -> {
            long partsOfName = countParts(name);
            if (partsOfName < MIN_PARTS_IN_NAMES) {
                throw new IllegalArgumentException(
                        "A Name must contain at least one given name along with the last name"
                );
            } else if (partsOfName > MAX_PARTS_IN_NAMES) {
                throw new IllegalArgumentException(
                        "A Name can only contain a maximum of 3 given names along with the last name"
                );
            }
        });
    }

    /**
     * Sanitizes the list of names by trimming whitespace from each name.
     *
     * @param names the list of names to sanitize
     */
    private static void sanitize(List<String> names) {
        names.replaceAll(String::trim);
    }

    /**
     * counts the parts of a name. Yields better results than split
     *
     * @param name the name to count parts
     */
    private static long countParts(String name) {
        return pattern.matcher(name).results().count() + 1;
    }

}
