package org.dnd;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * A utility class that provides functionality to sort a list of names
 * based on a provided {@link Comparator}.
 * <p>
 * This class creates a defensive copy of the provided list of names
 * before sorting, ensuring that the original list is not mutated.
 * </p>
 *
 * <pre>
 * Example usage:
 *
 * Comparator<String> nameComparator = Comparator
 *     .comparing((String name) -> name.substring(name.lastIndexOf(" ") + 1)) // by last name
 *     .thenComparing(name -> name); // then by full name
 *
 * NameSorter sorter = new NameSorter(nameComparator);
 * List<String> sorted = sorter.sort(List.of("John Doe", "Jane Smith", "Adam Doe"));
 * </pre>
 */
public class NameSorter {

    /**
     * The comparator used to define the ordering of names.
     */
    private final Comparator<String> comparator;

    /**
     * Constructs a {@code NameSorter} with the specified comparator.
     *
     * @param comparator the comparator that determines the order of the names;
     *                   must not be {@code null}
     */
    public NameSorter(Comparator<String> comparator) {
        this.comparator = comparator;
    }

    /**
     * Sorts the given list of names using the configured comparator.
     * <p>
     * This method does not mutate the input list. Instead, it creates
     * and sorts a new {@link ArrayList} containing the same elements.
     * </p>
     *
     * @param names the list of names to sort; must not be {@code null}
     * @return a new list containing the sorted names
     * @throws NullPointerException if {@code names} is {@code null}
     */
    public List<String> sort(final List<String> names) {
        List<String> sortedNames = new ArrayList<>(names); // copy to avoid mutating input
        sortedNames.sort(comparator);
        return sortedNames;
    }
}
