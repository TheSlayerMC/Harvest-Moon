package net.sham.hmfomt.core.data.enums;

public enum WeatherType {

    SUNNY("sunny"),
    RAINY("rainy"),
    SNOWY("snowy"),
    STORMY("stormy");

    private final String name;

    WeatherType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
