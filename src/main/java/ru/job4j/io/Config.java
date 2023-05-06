package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader in = new BufferedReader(new FileReader(this.path))) {
            List<String> list;
            list = in.lines()
                        .filter(str -> !str.startsWith("#") && !str.isEmpty())
                        .collect(Collectors.toList());
            for (String l : list) {
                if (!l.contains("=") || l.startsWith("=") || l.endsWith("=")) {
                    throw new IllegalArgumentException();
                }
            }
            list.stream()
                    .map(str -> str.split("=", 2))
                    .forEach(ar -> values.put(ar[0], ar[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
    }
}
