package net.sham.hmfomt.core.data.enums;

public enum Seasons {

    SPRING("spring", 0, 30),
    SUMMER("summer", 1, 30),
    AUTUMN("autumn", 2, 30),
    WINTER("winter", 3, 30);

    private final String name;
    private final int days, loc;

    Seasons(String name, int loc, int days) {
        this.name = name;
        this.loc = loc;
        this.days = days;
    }

    public static String getNameFromSeason(int season) {
        String sesaonName = "UNKNOWN";
        switch(season) {
            case 0 -> sesaonName = "SP";
            case 1 -> sesaonName = "SU";
            case 2 -> sesaonName = "AU";
            case 3 -> sesaonName = "WI";
        }
        return sesaonName;
    }

    public static Seasons getSeasonFromLoc(int season) {
        Seasons seasons = SPRING;
        switch(season) {
            case 0 -> seasons = SPRING;
            case 1 -> seasons = SUMMER;
            case 2 -> seasons = AUTUMN;
            case 3 -> seasons = WINTER;
        }
        return seasons;
    }

    public static int getLocFromName(String season) {
        int sesaonLoc = 0;
        switch(season) {
            case "spring" -> sesaonLoc = 0;
            case "summer" -> sesaonLoc = 1;
            case "autumn" -> sesaonLoc = 2;
            case "winter" -> sesaonLoc = 3;
        }
        return sesaonLoc;
    }

    public String getName() {
        return name;
    }

    public int getMaxDays() {
        return days;
    }
}
