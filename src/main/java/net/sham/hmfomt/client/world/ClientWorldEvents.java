package net.sham.hmfomt.client.world;

import net.minecraft.client.Minecraft;
import net.sham.hmfomt.core.HMDataAttachments;
import net.sham.hmfomt.core.data.enums.Seasons;

public class ClientWorldEvents {

    public static int getTickTimer() {
        assert Minecraft.getInstance().player != null;
        return Minecraft.getInstance().player.getData(HMDataAttachments.WORLD_EVENTS.get()).getCurrentTickTimer();
    }

    public static void setTickTimer(int value) {
        assert Minecraft.getInstance().player != null;
        Minecraft.getInstance().player.getData(HMDataAttachments.WORLD_EVENTS.get()).setTickTimer(value);
    }

    public static void setCurrentDay(int value) {
        assert Minecraft.getInstance().player != null;
        Minecraft.getInstance().player.getData(HMDataAttachments.WORLD_EVENTS.get()).setDayOfWeek(value);
    }

    public static int getCurrentDay() {
        assert Minecraft.getInstance().player != null;
        return Minecraft.getInstance().player.getData(HMDataAttachments.WORLD_EVENTS.get()).getCurrentDayOfWeek();
    }

    public static void setCurrentDayOfMonth(int value) {
        assert Minecraft.getInstance().player != null;
        Minecraft.getInstance().player.getData(HMDataAttachments.WORLD_EVENTS.get()).setDayOfMonth(value);
    }

    public static int getCurrentDayMonth() {
        assert Minecraft.getInstance().player != null;
        return Minecraft.getInstance().player.getData(HMDataAttachments.WORLD_EVENTS.get()).getCurrentDayOfMonth();
    }

    public static int getCurrentSeason() {
        assert Minecraft.getInstance().player != null;
        return Minecraft.getInstance().player.getData(HMDataAttachments.WORLD_EVENTS.get()).getCurrentSeason();
    }

    public static void setCurrentSeason(int value) {
        assert Minecraft.getInstance().player != null;
        Minecraft.getInstance().player.getData(HMDataAttachments.WORLD_EVENTS.get()).setSeason(value);
    }
}
