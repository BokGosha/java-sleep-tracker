package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class CountSleeplessNights implements Function<List<SleepingSession>, SleepAnalysisResult> {

    private static final String DESCRIPTION = "Количество бессонных ночей";

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        if (sleepingSessions == null || sleepingSessions.isEmpty()) {
            return new SleepAnalysisResult(DESCRIPTION, 0);
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

        int totalNightsWithoutSleep = totalNights - nightsWithSleep.size();

        return new SleepAnalysisResult(DESCRIPTION, totalNightsWithoutSleep);
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
