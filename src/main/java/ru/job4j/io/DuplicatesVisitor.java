package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Map<FileProperty, List<String>> files = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty f = new FileProperty(Files.size(file), file.getFileName().toString());
        if (files.putIfAbsent(f, new ArrayList<>() { { add(file.toFile().getAbsolutePath()); } } ) != null) {
            files.get(f).add(file.toFile().getAbsolutePath());
        }
        return super.visitFile(file, attrs);
    }

    public Map<FileProperty, List<String>> getFiles() {
        return this.files;
    }
}
