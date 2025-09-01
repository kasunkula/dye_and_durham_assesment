package org.dnd;

import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A {@link Comparator} implementation for comparing names represented as strings.
 * <p>
 * Names are compared using the following rules:
 * </p>
 * <ol>
 *     <li>First by the last name (case-insensitive).</li>
 *     <li>If the last names are the same, then by the sequence of given names
 *         (also case-insensitive).</li>
 * </ol>
 *
 * <p>Example:</p>
 * <pre>
 *     Comparator&lt;String&gt; comparator = new NameComparatorByLastNameThenGivenNames();
 *     List&lt;String&gt; names = List.of("John Smith", "Adam Smith", "Jane Doe");
 *     names.sort(comparator);
 *
 *     // Result: ["Jane Doe", "Adam Smith", "John Smith"]
 * </pre>
 */
public class NameComparatorByLastNameThenGivenNames implements Comparator<String> {

    static final Pattern pattern = Pattern.compile("^(.*)\\s+(\\S+)$");

    /**
     * Compares two names by last name, and if the last names are equal,
     * compares by the sequence of given names.
     *
     * @param a the first name to compare (must not be {@code null})
     * @param b the second name to compare (must not be {@code null})
     * @return a negative integer, zero, or a positive integer if the first name
     * is less than, equal to, or greater than the second name
     */
    @Override
    public int compare(final String a, final String b) {
        Name nameA = new Name(a);
        Name nameB = new Name(b);

        // Compare last names first
        int lastNameComparison = nameA.lastName.compareToIgnoreCase(nameB.lastName);
        if (lastNameComparison != 0) {
            return lastNameComparison;
        } else {
            // If last names are equal, compare given names
            return nameA.givenNames.compareToIgnoreCase(nameB.givenNames);
        }
    }

    private static class Name {

        final String givenNames;
        final String lastName;

        public Name(final String fullName) {
            Matcher matcher = pattern.matcher(fullName);
            if (matcher.matches() && matcher.groupCount() == 2) {
                this.givenNames = matcher.group(1).trim();
                this.lastName = matcher.group(2).trim();
            } else {
                this.givenNames = "";
                this.lastName = fullName;
            }
        }
    }

}
