package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.function.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SleepTrackerFunctionsTest {

    CountBadSleepingSessions countBadSleepingSessions = new CountBadSleepingSessions();
    CountSleepingSessions countSleepingSessions = new CountSleepingSessions();
    MinSleepingSession minSleepingSession = new MinSleepingSession();
    MaxSleepingSession maxSleepingSession = new MaxSleepingSession();
    AverageDurationSleepingSessions averageSleepingSessions = new AverageDurationSleepingSessions();
    ChronotypeDefinition chronotypeDefinition = new ChronotypeDefinition();

    private List<SleepingSession> sessions = new ArrayList<>();

    @BeforeEach
    void beforeEach() {
        sessions.clear();

        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 11, 30, 22, 0),
                LocalDateTime.of(2025, 12, 1, 6, 0),
                SleepQuality.GOOD
        ));

        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 11, 29, 23, 0),
                LocalDateTime.of(2025, 11, 30, 4, 30),
                SleepQuality.BAD
        ));

        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 11, 28, 23, 30),
                LocalDateTime.of(2025, 11, 29, 7, 30),
                SleepQuality.GOOD
        ));

        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 11, 27, 2, 0),
                LocalDateTime.of(2025, 11, 27, 5, 0),
                SleepQuality.BAD
        ));

        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 11, 26, 21, 0),
                LocalDateTime.of(2025, 11, 27, 8, 0),
                SleepQuality.GOOD
        ));
    }

    @Test
    void testPigeonChronotypeDefinition() {
        SleepAnalysisResult result = chronotypeDefinition.apply(sessions);

        assertEquals("голубь", result.getResult());
    }

    @Test
    void testLarkChronotypeDefinition() {
        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 11, 26, 21, 0),
                LocalDateTime.of(2025, 11, 27, 6, 0),
                SleepQuality.GOOD
        ));

        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 11, 28, 21, 0),
                LocalDateTime.of(2025, 11, 29, 6, 0),
                SleepQuality.GOOD
        ));

        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 11, 29, 21, 0),
                LocalDateTime.of(2025, 11, 30, 6, 0),
                SleepQuality.GOOD
        ));

        SleepAnalysisResult result = chronotypeDefinition.apply(sessions);

        assertEquals("жаворонок", result.getResult());
    }

    @Test
    void testOwlChronotypeDefinition() {
        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 12, 26, 23, 15),
                LocalDateTime.of(2025, 12, 27, 10, 0),
                SleepQuality.GOOD
        ));

        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 11, 28, 23, 15),
                LocalDateTime.of(2025, 11, 29, 10, 0),
                SleepQuality.GOOD
        ));

        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 11, 29, 23, 15),
                LocalDateTime.of(2025, 11, 30, 10, 0),
                SleepQuality.GOOD
        ));

        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 12, 29, 23, 15),
                LocalDateTime.of(2025, 12, 30, 10, 0),
                SleepQuality.GOOD
        ));

        SleepAnalysisResult result = chronotypeDefinition.apply(sessions);

        assertEquals("сова", result.getResult());
    }

    @Test
    void testAverageSleepingSessions() {
        SleepAnalysisResult result = averageSleepingSessions.apply(sessions);

        assertEquals("426", result.getResult());
    }

    @Test
    void testMinSleepingSession() {
        SleepAnalysisResult result = minSleepingSession.apply(sessions);

        assertEquals(180L, result.getResult());
    }

    @Test
    void testMaxSleepingSession() {
        SleepAnalysisResult result = maxSleepingSession.apply(sessions);

        assertEquals(660L, result.getResult());
    }

    @Test
    void testCountSleepingSessions() {
        SleepAnalysisResult result = countSleepingSessions.apply(sessions);

        assertEquals(5, result.getResult());
    }

    @Test
    void testCountEmptySleepingSessions() {
        SleepAnalysisResult result = countSleepingSessions.apply(new ArrayList<>());

        assertEquals(0, result.getResult());
    }

    @Test
    void testCountSleepingSessionsWithBadQuality() {
        SleepAnalysisResult result = countBadSleepingSessions.apply(sessions);

        assertEquals(2, result.getResult());
    }

    @Test
    void testCountSleepingSessionsWithoutBadQuality() {
        sessions = sessions.stream()
                .filter(sleepingSession -> !sleepingSession.getSleepQuality().equals(SleepQuality.BAD))
                .toList();

        SleepAnalysisResult result = countBadSleepingSessions.apply(sessions);

        assertEquals(0, result.getResult());
    }
}
