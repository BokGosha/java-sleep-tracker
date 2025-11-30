package ru.yandex.practicum.sleeptracker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SleepTrackerApp {

    private static final String FILE_NAME = "src/main/resources/sleep_log.txt";

    private static final List<FunctionType> functions = new ArrayList<>();
    private static final List<SleepingSession> sessions = new ArrayList<>();

    public static void main(String[] args) {
        processFile();

        List<SleepAnalysisResult> results = new ArrayList<>();

        functions.addAll(Arrays.asList(FunctionType.values()));

        functions.forEach(functionType -> {
            String resultValue = functionType.getFunction().apply(sessions);

            SleepAnalysisResult result = new SleepAnalysisResult(functionType, resultValue);

            results.add(result);
        });

        results.forEach(result -> System.out.println(result.getFormattedResult()));
    }

    private static void processFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            while (br.ready()) {
                String line = br.readLine();

                addSession(line);
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private static void addSession(String line) {
        String[] lines = line.split(";");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
        LocalDateTime startSleeping = LocalDateTime.parse(lines[0], formatter);
        LocalDateTime endSleeping = LocalDateTime.parse(lines[1], formatter);

        String sleepQuality = lines[2];

        SleepingSession sleepingSession = new SleepingSession(startSleeping, endSleeping, sleepQuality);
        sessions.add(sleepingSession);
    }
}
