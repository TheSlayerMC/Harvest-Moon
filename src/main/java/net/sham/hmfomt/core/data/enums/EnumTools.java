package net.sham.hmfomt.core.data.enums;

public enum EnumTools {

    HOE(2, 106),
    SICKLE(18, 106),
    AXE(34, 106),
    HAMMER(50, 106),
    WATERING_CAN(66, 106),
    FISHING_ROD(82, 106);

    public final int x;
    public final int y;

    EnumTools(int x, int y) {
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
