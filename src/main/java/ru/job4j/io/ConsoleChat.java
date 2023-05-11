package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    private int random() {
        int min = 1;
        int max = 15;
        return (int) (Math.random() * (max - min) + min);
    }

    public void run() {
        List<String> answers = readPhrases();
        List<String> log = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        String answer = "Введите запрос: ";
        String question;
        System.out.println(answer + "\n");
        log.add(answer + "\n");
        String status = CONTINUE;
        while (!OUT.equals(status)) {
            question = in.nextLine();
            log.add(String.format("- %s \n", question));
            status = switch (question)  {
                case "закончить" -> OUT;
                case "стоп" -> STOP;
                case "продолжить" -> CONTINUE;
                default -> status;
            };
            if (CONTINUE.equals(status)) {
                answer = answers.get(random());
                System.out.println(String.format("- %s \n", answer));
                log.add(String.format("- %s \n", answer));
            }
        }
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        String line;
        try (BufferedReader bf = new BufferedReader(new FileReader(botAnswers))) {
            while ((line = bf.readLine()) != null) {
                phrases.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(path))) {
            for (String s : log) {
                br.write(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("data/chatLog.txt", "data/answersForBot.txt");
        cc.run();
    }
}
