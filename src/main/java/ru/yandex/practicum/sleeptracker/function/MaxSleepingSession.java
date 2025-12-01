package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;
import java.util.function.Function;

public class MaxSleepingSession implements Function<List<SleepingSession>, SleepAnalysisResult> {

    private static final String DESCRIPTION = "Максимальная продолжительность сессии (в минутах)";

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        if (sleepingSessions == null) {
            return new SleepAnalysisResult(DESCRIPTION, 0);
        }

        long maxDurationOfSleepingSessions = sleepingSessions.stream()
                .map(sleepingSession -> sleepingSession.getDuration().toMinutes())
                .max(Long::compareTo)
                .orElse(0L);

        return new SleepAnalysisResult(DESCRIPTION, maxDurationOfSleepingSessions);
    }
}
