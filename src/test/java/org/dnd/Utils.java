package org.dnd;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Utils {
    private Utils() {
    }

    static Path toResousePath(String fileName) throws URISyntaxException {
        return Paths.get(Utils.class.getClassLoader().getResource(fileName).toURI());
    }

}
