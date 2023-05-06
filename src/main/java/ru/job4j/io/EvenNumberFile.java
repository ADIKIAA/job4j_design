package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {

    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("data/even.txt")) {
            StringBuilder text = new StringBuilder();
            int i;
            while ((i = in.read()) != -1) {
                text.append((char) i);
            }
            String[] array = text.toString().split(System.lineSeparator());
            for (String number : array) {
                System.out.println(Integer.parseInt(number) % 2 == 0 ? "true" : "false");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
