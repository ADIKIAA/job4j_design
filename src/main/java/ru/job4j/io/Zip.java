package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    private static void validate(Map<String, String> args) {
        if (args.size() != 3) {
            throw new IllegalArgumentException("Set all arguments");
        }
        if (!Files.exists(Paths.get(args.get("d")))) {
            throw new IllegalArgumentException(String.format("This folder '%s' does not exist", args.get("d")));
        }
    }

    public void packFiles(List<Path> source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path p : source) {
                zip.putNextEntry(new ZipEntry(p.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(p.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );

        Zip zip1 = new Zip();
        Map<String, String> arguments = ArgsName.of(args).values;
        validate(arguments);
        zip1.packFiles(
                Search.search(Paths.get(arguments.get("d")), (p -> !p.toString().endsWith(arguments.get("e")))),
                Paths.get(arguments.get("o")).toFile());
    }
}
