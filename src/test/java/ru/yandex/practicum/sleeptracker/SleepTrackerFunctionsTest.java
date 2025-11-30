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
    AverageSleepingSessions averageSleepingSessions = new AverageSleepingSessions();
    ChronotypeDefinition chronotypeDefinition = new ChronotypeDefinition();

    private List<SleepingSession> sessions = new ArrayList<>();

    @BeforeEach
    void beforeEach() {
        sessions.clear();

        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 11, 30, 22, 0),
                LocalDateTime.of(2025, 12, 1, 6, 0),
                "GOOD"
        ));

        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 11, 29, 23, 0),
                LocalDateTime.of(2025, 11, 30, 4, 30),
                "BAD"
        ));

        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 11, 28, 23, 30),
                LocalDateTime.of(2025, 11, 29, 7, 30),
                "GOOD"
        ));

        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 11, 27, 2, 0),
                LocalDateTime.of(2025, 11, 27, 5, 0),
                "BAD"
        ));

        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 11, 26, 21, 0),
                LocalDateTime.of(2025, 11, 27, 8, 0),
                "GOOD"
        ));
    }

    @Test
    void testPigeonChronotypeDefinition() {
        String result = chronotypeDefinition.apply(sessions);

        assertEquals("голубь", result);
    }

    @Test
    void testLarkChronotypeDefinition() {
        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 11, 26, 21, 0),
                LocalDateTime.of(2025, 11, 27, 6, 0),
                "GOOD"
        ));

        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 11, 28, 21, 0),
                LocalDateTime.of(2025, 11, 29, 6, 0),
                "GOOD"
        ));

        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 11, 29, 21, 0),
                LocalDateTime.of(2025, 11, 30, 6, 0),
                "GOOD"
        ));

        String result = chronotypeDefinition.apply(sessions);

        assertEquals("жаворонок", result);
    }

    @Test
    void testOwlChronotypeDefinition() {
        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 12, 26, 23, 15),
                LocalDateTime.of(2025, 12, 27, 10, 0),
                "GOOD"
        ));

        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 11, 28, 23, 15),
                LocalDateTime.of(2025, 11, 29, 10, 0),
                "GOOD"
        ));

        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 11, 29, 23, 15),
                LocalDateTime.of(2025, 11, 30, 10, 0),
                "GOOD"
        ));

        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 12, 29, 23, 15),
                LocalDateTime.of(2025, 12, 30, 10, 0),
                "GOOD"
        ));

        String result = chronotypeDefinition.apply(sessions);

        assertEquals("сова", result);
    }

    @Test
    void testAverageSleepingSessions() {
        String result = averageSleepingSessions.apply(sessions);

        assertEquals("426", result);
    }

    @Test
    void testMinSleepingSession() {
        String result = minSleepingSession.apply(sessions);

        assertEquals("180", result);
    }

    @Test
    void testMaxSleepingSession() {
        String result = maxSleepingSession.apply(sessions);

        assertEquals("660", result);
    }

    @Test
    void testCountSleepingSessions() {
        String result = countSleepingSessions.apply(sessions);

        assertEquals("5", result);
    }

    @Test
    void testCountEmptySleepingSessions() {
        String result = countSleepingSessions.apply(new ArrayList<>());

        assertEquals("0", result);
    }

    @Test
    void testCountSleepingSessionsWithBadQuality() {
        String result = countBadSleepingSessions.apply(sessions);

        assertEquals("2", result);
    }

    @Test
    void testCountSleepingSessionsWithoutBadQuality() {
        sessions = sessions.stream()
                .filter(sleepingSession -> !sleepingSession.getSleepQuality().equals("BAD"))
                .toList();

        String result = countBadSleepingSessions.apply(sessions);

        assertEquals("0", result);
    }
}
