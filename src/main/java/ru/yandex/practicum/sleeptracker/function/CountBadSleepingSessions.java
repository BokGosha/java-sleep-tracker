package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;
import java.util.function.Function;

public class CountBadSleepingSessions implements Function<List<SleepingSession>, String> {

    @Override
    public String apply(List<SleepingSession> sleepingSessions) {
        if (sleepingSessions == null || sleepingSessions.isEmpty()) {
            return "0";
        }

        String result = String.valueOf(sleepingSessions.stream()
                .map(SleepingSession::getSleepQuality)
                .filter(quality -> quality.equals("BAD"))
                .toList().size());

        return result;
    }
}
