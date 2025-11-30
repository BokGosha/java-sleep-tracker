package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class CountSleeplessNights implements Function<List<SleepingSession>, String> {

    @Override
    public String apply(List<SleepingSession> sleepingSessions) {
        if (sleepingSessions == null || sleepingSessions.isEmpty()) {
            return "0";
        }

        LocalDate startDate = sleepingSessions.getFirst().getStartSleeping().toLocalDate();
        LocalDate endDate = sleepingSessions.getLast().getEndSleeping().toLocalDate();

        int totalNights;

        if (sleepingSessions.getFirst().getStartSleeping().getHour() > 12) {
            totalNights = Period.between(startDate.plusDays(1), endDate.plusDays(1))
                    .getDays();
        } else {
            totalNights = Period.between(startDate, endDate.plusDays(1))
                    .getDays();
        }

        List<SleepingSession> nightsWithSleep = addNightWithSleep(sleepingSessions);

        String result = String.valueOf(totalNights - nightsWithSleep.size());

        return result;
    }

    public List<SleepingSession> addNightWithSleep(List<SleepingSession> sleepingSessions) {
        List<SleepingSession> nightsWithSleep = new ArrayList<>();

        sleepingSessions.forEach(sleepingSession -> {
            LocalDateTime start = sleepingSession.getStartSleeping();
            LocalDateTime end = sleepingSession.getEndSleeping();
            if (start.getDayOfYear() < end.getDayOfYear() || start.getHour() < 6) {
                nightsWithSleep.add(sleepingSession);
            }
        });

        return nightsWithSleep;
    }
}
