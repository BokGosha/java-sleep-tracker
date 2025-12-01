package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.function.CountSleeplessNights;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CountSleeplessNightsFunctionTest {

    private static CountSleeplessNights countSleeplessNights;

    @BeforeAll
    static void beforeAll() {
        countSleeplessNights = new CountSleeplessNights();
    }

    @Test
    void testSleepBeforeMidnight() {
        SleepingSession session1 = new SleepingSession(
                LocalDateTime.of(2025, 10, 30, 21, 0),
                LocalDateTime.of(2025, 10, 30, 23, 0),
                SleepQuality.BAD
        );

        SleepingSession session2 = new SleepingSession(
                LocalDateTime.of(2025, 10, 31, 21, 0),
                LocalDateTime.of(2025, 10, 31, 23, 0),
                SleepQuality.BAD
        );

        SleepAnalysisResult result = countSleeplessNights.apply(List.of(session1, session2));

        assertEquals(1, result.getResult());
    }

    @Test
    void testSleepAfterMidnight() {
        SleepingSession session2 = new SleepingSession(
                LocalDateTime.of(2025, 11, 30, 1, 0),
                LocalDateTime.of(2025, 11, 30, 5, 0),
                SleepQuality.BAD
        );

        SleepAnalysisResult result = countSleeplessNights.apply(List.of(session2));

        assertEquals(0, result.getResult());
    }

    @Test
    void testSleepCrossingNight() {
        SleepingSession session3 = new SleepingSession(
                LocalDateTime.of(2025, 11, 30, 22, 0),
                LocalDateTime.of(2025, 12, 1, 6, 0),
                SleepQuality.GOOD
        );

        SleepAnalysisResult result = countSleeplessNights.apply(List.of(session3));

        assertEquals(0, result.getResult());
    }

    @Test
    void testSleepBetweenMidnightAndSix() {
        SleepingSession session4 = new SleepingSession(
                LocalDateTime.of(2025, 11, 30, 2, 0),
                LocalDateTime.of(2025, 11, 30, 4, 0),
                SleepQuality.BAD
        );

        SleepAnalysisResult result = countSleeplessNights.apply(List.of(session4));

        assertEquals(0, result.getResult());
    }
}
