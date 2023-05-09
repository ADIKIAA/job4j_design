package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {

    public static void main(String[] args) throws IOException {
        DuplicatesVisitor dv = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("./"), dv);
        dv.getFiles().entrySet().stream()
                .filter(x -> x.getValue().size() > 1)
                .forEach((x) -> System.out.println(x.getKey() + "\n\t"
                        + String.join("\n\t", x.getValue())));
    }
}
