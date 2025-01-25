package net.sham.hmfomt.core.data.enums;

public enum EnumTools {

    HOE(0, 2, 106),
    SICKLE(1, 18, 106),
    AXE(2, 34, 106),
    HAMMER(3, 50, 106),
    WATERING_CAN(4, 66, 106),
    FISHING_ROD(5, 82, 106);

    public final int prefix, x, y;

    EnumTools(int prefix, int x, int y) {
        this.prefix = prefix;
        this.x = x;
        this.y = y;
    }

    public int getSpriteX() {
        return x;
    }

    public int getSpriteY() {
        return y;
    }
}
