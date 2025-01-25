package net.sham.hmfomt.common.capability;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.common.util.INBTSerializable;
import net.sham.hmfomt.common.packet.PacketPlayerStats;
import net.sham.hmfomt.common.packet.PacketWorldEvents;
import net.sham.hmfomt.core.data.HMNetworkRegistry;
import net.sham.hmfomt.core.data.enums.EnumTools;
import net.sham.hmfomt.core.data.enums.Seasons;
import org.jetbrains.annotations.UnknownNullability;

public class WorldEvents implements INBTSerializable<CompoundTag> {

    private int CURRENT_DAY = 1, WEEK_DAY = 1, DISPLAY_TICK_TIMER, SEASON;

    public void copyFrom(WorldEvents stats) {
        this.DISPLAY_TICK_TIMER = stats.DISPLAY_TICK_TIMER;
        this.CURRENT_DAY = stats.CURRENT_DAY;
        this.WEEK_DAY = stats.WEEK_DAY;
        this.SEASON = stats.SEASON;
    }

    public int getCurrentTickTimer() {
        return DISPLAY_TICK_TIMER;
    }

    public void setTickTimer(int tickTimer) {
        this.DISPLAY_TICK_TIMER = tickTimer;
    }

    public void setSeason(int season) {
        this.SEASON = season;
    }

    public int getCurrentSeason() {
        return this.SEASON;
    }

    public void setDayOfMonth(int day) {
        this.CURRENT_DAY = day;
    }

    public int getCurrentDayOfMonth() {
        return this.CURRENT_DAY;
    }

    public void setDayOfWeek(int day) {
        this.WEEK_DAY = day;
    }

    public int getCurrentDayOfWeek() {
        return this.WEEK_DAY;
    }

    public int getMaxDaysOfMonth(int season) {
        return Seasons.getSeasonFromLoc(season).getMaxDays();
    }

    public void addDay() {
        int season = getCurrentSeason();
        if(getCurrentDayOfMonth() >= getMaxDaysOfMonth(season)) {
            switch(season) {
                case 0 -> setSeason(1);
                case 1 -> setSeason(2);
                case 2 -> setSeason(3);
                case 3 -> setSeason(0);
            }
            setDayOfMonth(0);
        }
        if(getCurrentDayOfWeek() >= 7) {
            setDayOfMonth(getCurrentDayOfMonth() + 1);
            setDayOfWeek(1);
        } else {
            setDayOfMonth(getCurrentDayOfMonth() + 1);
            setDayOfWeek(getCurrentDayOfWeek() + 1);
        }
    }

    public void sendPacket(Player player) {
        if(player instanceof ServerPlayer sp) {
            HMNetworkRegistry.sendToPlayer(sp, new PacketWorldEvents(getCurrentTickTimer(), getCurrentSeason(), getCurrentDayOfWeek(), getCurrentDayOfMonth()));
        }
    }

    public void update(Player player) {
        sendPacket(player);
        setTickTimer(getCurrentTickTimer() + 1);
        if(getCurrentTickTimer() == 6) {
            setTickTimer(0);
        }
        if(getCurrentDayOfWeek() >= 7) {
            setDayOfWeek(7);
        }
        setSeason(2);
    }


    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag tag = new CompoundTag();
        tag.putInt("month_day", this.CURRENT_DAY);
        tag.putInt("week_day", this.WEEK_DAY);
        tag.putInt("tick", this.DISPLAY_TICK_TIMER);
        tag.putInt("season", this.SEASON);
        return tag;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag tag) {
        CURRENT_DAY = tag.getInt("month_day");
        WEEK_DAY = tag.getInt("week_day");
        DISPLAY_TICK_TIMER = tag.getInt("tick");
        SEASON = tag.getInt("season");
    }
}