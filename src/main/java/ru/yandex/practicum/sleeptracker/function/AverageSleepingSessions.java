package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;
import java.util.function.Function;

public class AverageSleepingSessions implements Function<List<SleepingSession>, String> {

    @Override
    public String apply(List<SleepingSession> sleepingSessions) {
        if (sleepingSessions == null || sleepingSessions.isEmpty()) {
            return "0";
        }

        double total = sleepingSessions.stream()
                .mapToDouble(sleepingSession -> sleepingSession.getDuration().toMinutes())
                .sum();

        String result = String.valueOf(total / sleepingSessions.size());

        return result.substring(0, result.indexOf("."));
    }
}
