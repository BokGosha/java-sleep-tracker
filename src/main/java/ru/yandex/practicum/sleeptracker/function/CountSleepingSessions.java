package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;
import java.util.function.Function;

public class CountSleepingSessions implements Function<List<SleepingSession>, SleepAnalysisResult> {

    private static final String DESCRIPTION = "Общее количество сессий сна";

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        if (sleepingSessions == null || sleepingSessions.isEmpty()) {
            return new SleepAnalysisResult(DESCRIPTION, 0);
        }

        int countOfSleepingSessions = sleepingSessions.size();

        return new SleepAnalysisResult(DESCRIPTION, countOfSleepingSessions);
    }
}
