package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepQuality;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;
import java.util.function.Function;

public class CountBadSleepingSessions implements Function<List<SleepingSession>, SleepAnalysisResult> {

    private static final String DESCRIPTION = "Количество сессий с плохим качеством сна";

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        if (sleepingSessions == null || sleepingSessions.isEmpty()) {
            return new SleepAnalysisResult(DESCRIPTION, 0);
        }

        int countOfBadSleepingSessions = sleepingSessions.stream()
                .map(SleepingSession::getSleepQuality)
                .filter(quality -> quality.equals(SleepQuality.BAD))
                .toList().size();

        return new SleepAnalysisResult(DESCRIPTION, countOfBadSleepingSessions);
    }
}
