package net.sham.hmfomt.client.sprites;

import net.minecraft.client.Minecraft;
import net.sham.hmfomt.core.HMDataAttachments;
import net.sham.hmfomt.core.data.enums.EnumSprites;

public class ClientHarvestSprites {

    public static void setClientHeartFloat(EnumSprites sprite, float value) {
        assert Minecraft.getInstance().player != null;
        Minecraft.getInstance().player.getData(HMDataAttachments.HARVEST_SPRITES.get()).setHeartFloat(sprite, value);
    }

    public static void setClientWaterXP(EnumSprites sprite, float value) {
        assert Minecraft.getInstance().player != null;
        Minecraft.getInstance().player.getData(HMDataAttachments.HARVEST_SPRITES.get()).setWaterXP(sprite, value);
    }

    public static void setClientHarvestXP(EnumSprites sprite, float value) {
        assert Minecraft.getInstance().player != null;
        Minecraft.getInstance().player.getData(HMDataAttachments.HARVEST_SPRITES.get()).setHarvestXP(sprite, value);
    }

    public static void setClientAnimalXP(EnumSprites sprite, float value) {
        assert Minecraft.getInstance().player != null;
        Minecraft.getInstance().player.getData(HMDataAttachments.HARVEST_SPRITES.get()).setAnimalXP(sprite, value);
    }

    public static float getClientWaterXP(EnumSprites knowledge) {
        assert Minecraft.getInstance().player != null;
        return Minecraft.getInstance().player.getData(HMDataAttachments.HARVEST_SPRITES.get()).getWaterXP(knowledge);
    }

    public static float getClientHarvestXP(EnumSprites knowledge) {
        assert Minecraft.getInstance().player != null;
        return Minecraft.getInstance().player.getData(HMDataAttachments.HARVEST_SPRITES.get()).getHarvestXP(knowledge);
    }

    public static float getClientAnimalXP(EnumSprites knowledge) {
        assert Minecraft.getInstance().player != null;
        return Minecraft.getInstance().player.getData(HMDataAttachments.HARVEST_SPRITES.get()).getAnimalXP(knowledge);
    }

    public static float getClientHeartFloat(EnumSprites knowledge) {
        assert Minecraft.getInstance().player != null;
        return Minecraft.getInstance().player.getData(HMDataAttachments.HARVEST_SPRITES.get()).getAffection(knowledge);
    }

    public static int getClientHearts(EnumSprites knowledge) {
        assert Minecraft.getInstance().player != null;
        return Minecraft.getInstance().player.getData(HMDataAttachments.HARVEST_SPRITES.get()).getHearts(knowledge);
    }
}
