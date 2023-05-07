package ru.job4j.io;

import java.io.*;

public class Analysis {

    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(target)))) {
            String str;
            boolean status = true;
            boolean check = true;
            while ((str = in.readLine()) != null) {
                status = !str.startsWith("400") && !str.startsWith("500");
                if (!status && check) {
                    out.print(str.substring(3) + "; ");
                } else if (status && !check) {
                    out.print(str.substring(3) + "; " + "\n");
                }
                check = status;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}
