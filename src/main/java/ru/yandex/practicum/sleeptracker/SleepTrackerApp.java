package ru.yandex.practicum.sleeptracker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class SleepTrackerApp {

    private static final String FILE_NAME = "src/main/resources/sleep_log.txt";

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

    private static final List<FunctionType> functions = new ArrayList<>();
    private static final List<SleepingSession> sessions = new ArrayList<>();

    public static void main(String[] args) {
        processFile();

        functions.addAll(Arrays.asList(FunctionType.values()));

        functions.forEach(functionType -> {
            SleepAnalysisResult resultValue = functionType.getFunction().apply(sessions);

            System.out.println(resultValue);
        });
    }

    private static void processFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            sessions.addAll(br.lines()
                    .map(SleepTrackerApp::getSession)
                    .flatMap(Optional::stream)
                    .toList());
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private static Optional<SleepingSession> getSession(String line) {
        String[] lines = line.split(";");

        LocalDateTime startSleeping = LocalDateTime.parse(lines[0], formatter);
        LocalDateTime endSleeping = LocalDateTime.parse(lines[1], formatter);

        SleepQuality sleepQuality = SleepQuality.valueOf(lines[2]);

        SleepingSession sleepingSession = new SleepingSession(startSleeping, endSleeping, sleepQuality);

        return Optional.of(sleepingSession);
    }
}
