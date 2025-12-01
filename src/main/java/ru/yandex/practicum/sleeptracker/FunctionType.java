package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.function.*;

import java.util.List;
import java.util.function.Function;

public enum FunctionType {
    COUNT_SLEEPING_SESSIONS(new CountSleepingSessions()),
    MIN_SLEEPING_SESSION(new MinSleepingSession()),
    MAX_SLEEPING_SESSION(new MaxSleepingSession()),
    AVERAGE_SLEEPING_SESSIONS(new AverageDurationSleepingSessions()),
    COUNT_BAD_SLEEPING_SESSIONS(new CountBadSleepingSessions()),
    COUNT_SLEEPLESS_NIGHTS(new CountSleeplessNights()),
    CHRONOTYPE_DEFINITION(new ChronotypeDefinition());

    private final Function<List<SleepingSession>, SleepAnalysisResult> function;

    FunctionType(Function<List<SleepingSession>, SleepAnalysisResult> function) {
        this.function = function;
    }

    public Function<List<SleepingSession>, SleepAnalysisResult> getFunction() {
        return function;
    }
}
