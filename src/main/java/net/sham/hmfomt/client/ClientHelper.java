package net.sham.hmfomt.client;

public class ClientHelper {

    public static String formatTime(Long time) {
        int hours24 = (int)(time.longValue() / 1000L + 6L) % 24;
        int hours = hours24 % 12;
        int minutes = (int)((float)time.longValue() / 16.666666F % 60.0F);
        return String.format("%02d:%02d", hours < 1 ? 12 : hours, minutes);
    }

    public static String AMPM(Long time) {
        int hours24 = (int)(time.longValue() / 1000L + 6L) % 24;
        return String.format("%s", hours24 < 12 ? "AM" : "PM");
    }

    public static String formatFullTime(Long time) {
        int hours24 = (int)(time.longValue() / 1000L + 6L) % 24;
        int hours = hours24 % 12;
        int minutes = (int)((float)time.longValue() / 16.666666F % 60.0F);
        return String.format("%02d:%02d %s", hours < 1 ? 12 : hours, minutes, hours24 < 12 ? "AM" : "PM");
    }
}
