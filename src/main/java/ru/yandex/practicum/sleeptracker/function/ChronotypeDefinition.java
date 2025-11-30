package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;

public class ChronotypeDefinition implements Function<List<SleepingSession>, String> {

    @Override
    public String apply(List<SleepingSession> sleepingSessions) {
        Map<String, Integer> sessionsCount = new HashMap<>();

        List<SleepingSession> nights = new CountSleeplessNights().addNightWithSleep(sleepingSessions);

        nights.forEach(session -> {
            String type = determineSessionType(session);

            sessionsCount.put(type, sessionsCount.getOrDefault(type, 0) + 1);
        });

        return sessionsCount.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("не определено");
    }

    private String determineSessionType(SleepingSession session) {
        LocalDateTime start = session.getStartSleeping();
        LocalDateTime end = session.getEndSleeping();

        if (start.getHour() <= 22 && end.getHour() <= 7) {
            return "жаворонок";
        } else if (end.getHour() >= 9) {
            return "сова";
        } else {
            return "голубь";
        }
    }
}
