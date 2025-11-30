package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.time.LocalDateTime;

public class SleepingSession {

    private final LocalDateTime startSleeping;
    private final LocalDateTime endSleeping;
    private final String sleepQuality;

    public SleepingSession(LocalDateTime startSleeping, LocalDateTime endSleeping, String sleepQuality) {
        this.startSleeping = startSleeping;
        this.endSleeping = endSleeping;
        this.sleepQuality = sleepQuality;
    }

    public Duration getDuration() {
        return Duration.between(startSleeping, endSleeping);
    }

    public String getSleepQuality() {
        return sleepQuality;
    }

    public LocalDateTime getStartSleeping() {
        return startSleeping;
    }

    public LocalDateTime getEndSleeping() {
        return endSleeping;
    }

    @Override
    public String toString() {
        return "SleepingSession{" +
                "startSleeping=" + startSleeping +
                ", endSleeping=" + endSleeping +
                ", sleepQuality='" + sleepQuality + '\'' +
                '}';
    }
}
