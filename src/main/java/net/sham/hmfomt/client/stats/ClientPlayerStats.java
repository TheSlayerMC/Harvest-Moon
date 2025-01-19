package net.sham.hmfomt.client.stats;

import net.minecraft.client.Minecraft;
import net.sham.hmfomt.core.HMDataAttachments;
import net.sham.hmfomt.core.data.enums.EnumSprites;
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

    public static void setClientCopperXP(EnumTools tool, float value) {
        assert Minecraft.getInstance().player != null;
        Minecraft.getInstance().player.getData(HMDataAttachments.PLAYER_STATS.get()).setCopperXP(tool, value);
    }

    public static void setClientSilverXP(EnumTools tool, float value) {
        assert Minecraft.getInstance().player != null;
        Minecraft.getInstance().player.getData(HMDataAttachments.PLAYER_STATS.get()).setSilverXP(tool, value);
    }

    public static void setClientGoldXP(EnumTools tool, float value) {
        assert Minecraft.getInstance().player != null;
        Minecraft.getInstance().player.getData(HMDataAttachments.PLAYER_STATS.get()).setGoldXP(tool, value);
    }

    public static void setClientMythrilXP(EnumTools tool, float value) {
        assert Minecraft.getInstance().player != null;
        Minecraft.getInstance().player.getData(HMDataAttachments.PLAYER_STATS.get()).setMythrilXP(tool, value);
    }

    public static void getClientCopperXP(EnumTools tool) {
        assert Minecraft.getInstance().player != null;
        Minecraft.getInstance().player.getData(HMDataAttachments.PLAYER_STATS.get()).getCopperXP(tool);
    }

    public static void getClientSilverXP(EnumTools tool) {
        assert Minecraft.getInstance().player != null;
        Minecraft.getInstance().player.getData(HMDataAttachments.PLAYER_STATS.get()).getSilverXP(tool);
    }

    public static void getClientGoldXP(EnumTools tool) {
        assert Minecraft.getInstance().player != null;
        Minecraft.getInstance().player.getData(HMDataAttachments.PLAYER_STATS.get()).getGoldXP(tool);
    }

    public static void getClientMythrilXP(EnumTools tool) {
        assert Minecraft.getInstance().player != null;
        Minecraft.getInstance().player.getData(HMDataAttachments.PLAYER_STATS.get()).getMythrilXP(tool);
    }
}
