package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;
import java.util.function.Function;

public class MaxSleepingSession implements Function<List<SleepingSession>, String> {

    @Override
    public String apply(List<SleepingSession> sleepingSessions) {
        if (sleepingSessions == null) {
            return "0";
        }

        String result = String.valueOf(sleepingSessions.stream()
                .map(sleepingSession -> sleepingSession.getDuration().toMinutes())
                .max(Long::compareTo)
                .orElse(0L));

        return result;
    }
}
