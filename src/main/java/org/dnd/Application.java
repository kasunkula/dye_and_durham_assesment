package org.dnd;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Application {

    static String OUTPUT_FILE = "sorted-names-list.txt";

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Please provide the input file path as a command-line argument.");
            return;
        }

        try {
            NameSorter nameSorter = new NameSorter(new NameComparatorByLastNameThenGivenNames());
            NameListReader nameListReader = new NameListReader();
            List<String> names = nameListReader.readNamesFromFile(args[0]);
            List<String> sortedNames = nameSorter.sort(names);
            sortedNames.forEach(System.out::println);
            Files.write(Path.of(OUTPUT_FILE), sortedNames);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}