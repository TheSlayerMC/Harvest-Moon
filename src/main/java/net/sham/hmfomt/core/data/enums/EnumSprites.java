package net.sham.hmfomt.core.data.enums;

public enum EnumSprites {

    STAID("Staid", 2, 87),
    NAPPY("Nappy", 18, 87),
    BOLD("Bold", 34, 87),
    CHEF("Chef", 50, 87),
    AQUA("Aqua", 66, 87),
    HOGGY("Hoggy", 82, 87),
    TIMID("Timid", 98, 87);

    public final int x;
    public final int y;
    public final String name;

    EnumSprites(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public int getSpriteX() {
        return x;
    }

    public int getSpriteY() {
        return y;
    }

    public String getName() {
        return name;
    }
}
