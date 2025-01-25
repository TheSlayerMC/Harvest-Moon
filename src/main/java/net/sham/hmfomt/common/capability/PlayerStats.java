package net.sham.hmfomt.common.capability;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.common.util.INBTSerializable;
import net.sham.hmfomt.common.packet.PacketPlayerStats;
import net.sham.hmfomt.core.data.HMNetworkRegistry;
import net.sham.hmfomt.core.data.enums.EnumTools;
import org.jetbrains.annotations.UnknownNullability;

public class PlayerStats implements INBTSerializable<CompoundTag> {

    private int coin;
    private boolean JUST_WOKE;

    private float HOE_XP, SICKLE_XP, AXE_XP, HAMMER_XP, WATERING_CAN_XP, FISHING_ROD_XP;
    private int HOE_LEVEL, SICKLE_LEVEL, AXE_LEVEL, HAMMER_LEVEL, WATERING_CAN_LEVEL, FISHING_ROD_LEVEL;

    public void copyFrom(PlayerStats stats) {
        this.coin = stats.coin;
        for(EnumTools t : EnumTools.values()) {
            setXP(t, stats.getXP(t));
            setLevel(t, stats.getLevel(t));
        }
        this.JUST_WOKE = stats.JUST_WOKE;
    }

    public void setWoke(boolean woke) {
        this.JUST_WOKE = woke;
    }

    public boolean getJustWoke() {
        return JUST_WOKE;
    }

    public int getLevelCapacity() {
        return 150;
    }

    public void addXP(EnumTools tool, float amount, Player player) {
        if(getXP(tool) + amount >= getLevelCapacity()) {
            setXP(tool, getXP(tool) + amount - getLevelCapacity());
            addLevel(tool,1);
        } else {
            setXP(tool, getXP(tool) + amount);
        }
        sendPacket(tool, player);
    }

    public void setXP(EnumTools tool, float amount) {
        switch(tool) {
            case HOE -> HOE_XP = amount;
            case SICKLE -> SICKLE_XP = amount;
            case AXE -> AXE_XP = amount;
            case HAMMER -> HAMMER_XP = amount;
            case WATERING_CAN -> WATERING_CAN_XP = amount;
            case FISHING_ROD -> FISHING_ROD_XP = amount;
        }
    }

    public float getXP(EnumTools tool) {
        float XP = 0.0F;
        switch(tool) {
            case HOE -> XP = HOE_XP;
            case SICKLE -> XP = SICKLE_XP;
            case AXE -> XP = AXE_XP;
            case HAMMER -> XP = HAMMER_XP;
            case WATERING_CAN -> XP = WATERING_CAN_XP;
            case FISHING_ROD -> XP = FISHING_ROD_XP;
        }
        return XP;
    }

    public void addLevel(EnumTools tool, int amount) {
        switch(tool) {
            case HOE -> HOE_LEVEL = HOE_LEVEL + amount;
            case SICKLE -> SICKLE_LEVEL = SICKLE_LEVEL + amount;
            case AXE -> AXE_LEVEL = AXE_LEVEL + amount;
            case HAMMER -> HAMMER_LEVEL = HAMMER_LEVEL + amount;
            case WATERING_CAN -> WATERING_CAN_LEVEL = WATERING_CAN_LEVEL + amount;
            case FISHING_ROD -> FISHING_ROD_LEVEL = FISHING_ROD_LEVEL + amount;
        }
    }

    public void setLevel(EnumTools tool, int amount) {
        switch(tool) {
            case HOE -> HOE_LEVEL = amount;
            case SICKLE -> SICKLE_LEVEL = amount;
            case AXE -> AXE_LEVEL = amount;
            case HAMMER -> HAMMER_LEVEL = amount;
            case WATERING_CAN -> WATERING_CAN_LEVEL = amount;
            case FISHING_ROD -> FISHING_ROD_LEVEL = amount;
        }
    }

    public int getLevel(EnumTools tool) {
        int level = 0;
        switch(tool) {
            case HOE -> level = HOE_LEVEL;
            case SICKLE -> level = SICKLE_LEVEL;
            case AXE -> level = AXE_LEVEL;
            case HAMMER -> level = HAMMER_LEVEL;
            case WATERING_CAN -> level = WATERING_CAN_LEVEL;
            case FISHING_ROD -> level = FISHING_ROD_LEVEL;
        }
        return level;
    }

    public int getCoins(){
        return coin;
    }
    public void setCoins(int value){
        coin = value;
    }

    public boolean useCoins(int amount) {
        coin -= amount;
        if(coin >= amount) {
            coin -= amount;
            return true;
        } else {
            return false;
        }
    }

    public void addCoins(int amount) {
        coin += amount;
    }

    public void sendPacket(EnumTools tool, Player player) {
        if(player instanceof ServerPlayer sp) {
            HMNetworkRegistry.sendToPlayer(sp, new PacketPlayerStats(getCoins(), getJustWoke(), getXP(tool), getLevel(tool), tool));
        }
    }

    public void update(Player player) {
        for(EnumTools tool : EnumTools.values()) {
            if(getXP(tool) >= 150) {
                setXP(tool, 150);
            }

            if(getLevel(tool) >= 4) {
                setLevel(tool, 4);
                setXP(tool, 150);
            }
//            setXP(tool, 0);
//            setLevel(tool, 0);
            sendPacket(tool, player);
        }
    }

    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag tag = new CompoundTag();

        tag.putInt("sentacoins", this.coin);
        tag.putBoolean("woke", this.JUST_WOKE);

        tag.putFloat("hoe", this.HOE_XP);
        tag.putInt("hoe_level", this.HOE_LEVEL);

        tag.putFloat("sickle", this.SICKLE_XP);
        tag.putInt("sickle_level", this.SICKLE_LEVEL);

        tag.putFloat("axe", this.AXE_XP);
        tag.putInt("axe_level", this.AXE_LEVEL);

        tag.putFloat("hammer", this.HAMMER_XP);
        tag.putInt("hammer_level", this.HAMMER_LEVEL);

        tag.putFloat("watering_can", this.WATERING_CAN_XP);
        tag.putInt("watering_can_level", this.WATERING_CAN_LEVEL);

        tag.putFloat("fishing_rod", this.FISHING_ROD_XP);
        tag.putInt("fishing_rod_level", this.FISHING_ROD_LEVEL);
        return tag;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag tag) {
        coin = tag.getInt("sentacoins");
        JUST_WOKE = tag.getBoolean("woke");

        HOE_XP = tag.getFloat("hoe");
        HOE_LEVEL = tag.getInt("hoe_level");

        SICKLE_XP = tag.getFloat("sickle");
        SICKLE_LEVEL = tag.getInt("sickle_level");

        AXE_XP = tag.getFloat("axe");
        AXE_LEVEL = tag.getInt("axe_level");

        HAMMER_XP = tag.getFloat("hammer");
        HAMMER_LEVEL = tag.getInt("hammer_level");

        WATERING_CAN_XP = tag.getFloat("watering_can");
        WATERING_CAN_LEVEL = tag.getInt("watering_can_level");

        FISHING_ROD_XP = tag.getFloat("fishing_rod");
        FISHING_ROD_LEVEL = tag.getInt("fishing_rod_level");
    }
}