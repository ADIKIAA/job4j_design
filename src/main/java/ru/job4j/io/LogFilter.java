package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {

    public List<String> filter(String file) {
        List<String> list = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader("data/log.txt")))  {
            list = in.lines()
                    .filter(s -> s.contains(" 404 "))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedWriter(new FileWriter(file))
        )) {
            for (String str : log) {
                out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("data/log.txt");
        for (String st : log) {
            System.out.println(st);
        }

        save(log, "data/404.txt");
    }
}
