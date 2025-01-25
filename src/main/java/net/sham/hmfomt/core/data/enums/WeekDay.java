package net.sham.hmfomt.core.data.enums;

public enum WeekDay {

    MONDAY("monday", 1),
    TUESDAY("tuesday", 2),
    WEDNESDAY("wednesday", 3),
    THURSDAY("thursday", 4),
    FRIDAY("friday", 5),
    SATURDAY("saturday", 6),
    SUNDAY("sunday", 0);

    private final String name;
    private final int dayOfWeek;

    WeekDay(String day, int dayOfWeek) {
        this.name = day;
        this.dayOfWeek = dayOfWeek;
    }

    public static String getNameFromDay(int day) {
        String dayName = "UNKNOWN";
        switch(day) {
            case 2 -> dayName = "Mon";
            case 3 -> dayName = "Tue";
            case 4 -> dayName = "Wed";
            case 5 -> dayName = "Thu";
            case 6 -> dayName = "Fri";
            case 7 -> dayName = "Sat";
            case 1 -> dayName = "Sun";
        }
        return dayName;
    }

    public String getName() {
        return name;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }
}