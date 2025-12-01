package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;
import java.util.function.Function;

public class AverageDurationSleepingSessions implements Function<List<SleepingSession>, SleepAnalysisResult> {

    private static final String DESCRIPTION = "Средняя продолжительность сессии (в минутах)";

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        if (sleepingSessions == null || sleepingSessions.isEmpty()) {
            return new SleepAnalysisResult(DESCRIPTION, 0);
        }

        double total = sleepingSessions.stream()
                .mapToDouble(sleepingSession -> sleepingSession.getDuration().toMinutes())
                .sum();

        double averageDurationOfSleepingSessions = total / sleepingSessions.size();

        return new SleepAnalysisResult(DESCRIPTION, String.format("%.0f", averageDurationOfSleepingSessions));
    }
}
