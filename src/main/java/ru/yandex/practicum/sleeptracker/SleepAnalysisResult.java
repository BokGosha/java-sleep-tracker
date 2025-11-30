package ru.yandex.practicum.sleeptracker;

public class SleepAnalysisResult {

    private final FunctionType functionType;
    private final String result;

    public SleepAnalysisResult(FunctionType functionType, String result) {
        this.functionType = functionType;
        this.result = result;
    }

    public String getFormattedResult() {
        String description = getDescription();
        return String.format("%s: %s", description, result);
    }

    public String getDescription() {
        return switch (functionType) {
            case COUNT_SLEEPING_SESSIONS -> "Количество сессий сна";
            case AVERAGE_SLEEPING_SESSIONS -> "Средняя продолжительность сессии (в минутах)";
            case MIN_SLEEPING_SESSION -> "Минимальная продолжительность сессии (в минутах)";
            case MAX_SLEEPING_SESSION -> "Максимальная продолжительность сессии (в минутах)";
            case COUNT_BAD_SLEEPING_SESSIONS -> "Количество сессий с плохим качеством сна";
            case COUNT_SLEEPLESS_NIGHTS -> "Количество бессонных ночей";
            case CHRONOTYPE_DEFINITION -> "Тип человека";
        };
    }
}
