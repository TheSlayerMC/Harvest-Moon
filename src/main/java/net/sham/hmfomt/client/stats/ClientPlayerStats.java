package net.sham.hmfomt.client.stats;

import net.minecraft.client.Minecraft;
import net.sham.hmfomt.core.HMDataAttachments;
import net.sham.hmfomt.core.data.enums.EnumTools;

public class ClientPlayerStats {

    public static void addSentacoins(int value) {
        assert Minecraft.getInstance().player != null;
        Minecraft.getInstance().player.getData(HMDataAttachments.PLAYER_STATS.get()).addCoins(value);
    }

    public static void useSentacoins(int value) {
        assert Minecraft.getInstance().player != null;
        Minecraft.getInstance().player.getData(HMDataAttachments.PLAYER_STATS.get()).useCoins(value);
    }

    public static int getSentacoins() {
        assert Minecraft.getInstance().player != null;
        return Minecraft.getInstance().player.getData(HMDataAttachments.PLAYER_STATS.get()).getCoins();
    }

    public static void setSentacoins(int value) {
        assert Minecraft.getInstance().player != null;
        Minecraft.getInstance().player.getData(HMDataAttachments.PLAYER_STATS.get()).setCoins(value);
    }

    public static void setWoke(boolean value) {
        assert Minecraft.getInstance().player != null;
        Minecraft.getInstance().player.getData(HMDataAttachments.PLAYER_STATS.get()).setWoke(value);
    }

    public static void setClientXP(EnumTools tool, float value) {
        assert Minecraft.getInstance().player != null;
        Minecraft.getInstance().player.getData(HMDataAttachments.PLAYER_STATS.get()).setXP(tool, value);
    }

    public static void setClientLevel(EnumTools tool, int value) {
        assert Minecraft.getInstance().player != null;
        Minecraft.getInstance().player.getData(HMDataAttachments.PLAYER_STATS.get()).setLevel(tool, value);
    }

    public static float getClientXP(EnumTools tool) {
        assert Minecraft.getInstance().player != null;
        return Minecraft.getInstance().player.getData(HMDataAttachments.PLAYER_STATS.get()).getXP(tool);
    }

    public static int getClientLevel(EnumTools tool) {
        assert Minecraft.getInstance().player != null;
        return Minecraft.getInstance().player.getData(HMDataAttachments.PLAYER_STATS.get()).getLevel(tool);
    }
}
