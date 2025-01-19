package net.sham.hmfomt.common.capability;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.common.util.INBTSerializable;
import net.sham.hmfomt.common.packet.PacketPlayerStats;
import net.sham.hmfomt.core.data.HMNetworkRegistry;
import net.sham.hmfomt.core.data.enums.EnumSprites;
import net.sham.hmfomt.core.data.enums.EnumTools;
import org.jetbrains.annotations.UnknownNullability;

public class PlayerStats implements INBTSerializable<CompoundTag> {

    private int coin;
    private float HOE_COPPER_XP, HOE_SILVER_XP, HOE_GOLD_XP, HOE_MYTHRIL_XP;
    private float SICKLE_COPPER_XP, SICKLE_SILVER_XP, SICKLE_GOLD_XP, SICKLE_MYTHRIL_XP;
    private float AXE_COPPER_XP, AXE_SILVER_XP, AXE_GOLD_XP, AXE_MYTHRIL_XP;
    private float HAMMER_COPPER_XP, HAMMER_SILVER_XP, HAMMER_GOLD_XP, HAMMER_MYTHRIL_XP;
    private float WATERING_CAN_COPPER_XP, WATERING_CAN_SILVER_XP, WATERING_CAN_GOLD_XP, WATERING_CAN_MYTHRIL_XP;
    private float FISHING_ROD_COPPER_XP, FISHING_ROD_SILVER_XP, FISHING_ROD_GOLD_XP, FISHING_ROD_MYTHRIL_XP;

    public void copyFrom(PlayerStats stats) {
        this.coin = stats.coin;
        for(EnumTools t : EnumTools.values()) {
            setCopperXP(t, stats.getCopperXP(t));
            setSilverXP(t, stats.getSilverXP(t));
            setGoldXP(t, stats.getGoldXP(t));
            setMythrilXP(t, stats.getMythrilXP(t));
        }
    }

    public void addCopperXP(EnumTools tool, float amount, ServerPlayer player) {
        switch(tool) {
            case HOE -> HOE_COPPER_XP = HOE_COPPER_XP + amount;
            case SICKLE -> SICKLE_COPPER_XP = SICKLE_COPPER_XP + amount;
            case AXE -> AXE_COPPER_XP = AXE_COPPER_XP + amount;
            case HAMMER -> HAMMER_COPPER_XP = HAMMER_COPPER_XP + amount;
            case WATERING_CAN -> WATERING_CAN_COPPER_XP = WATERING_CAN_COPPER_XP + amount;
            case FISHING_ROD -> FISHING_ROD_COPPER_XP = FISHING_ROD_COPPER_XP + amount;
        }
        sendPacket(tool, player);
    }

    public void setCopperXP(EnumTools tool, float amount) {
        switch(tool) {
            case HOE -> HOE_COPPER_XP = amount;
            case SICKLE -> SICKLE_COPPER_XP = amount;
            case AXE -> AXE_COPPER_XP = amount;
            case HAMMER -> HAMMER_COPPER_XP = amount;
            case WATERING_CAN -> WATERING_CAN_COPPER_XP = amount;
            case FISHING_ROD -> FISHING_ROD_COPPER_XP = amount;
        }
    }

    public float getCopperXP(EnumTools tool) {
        float XP = 0.0F;
        switch(tool) {
            case HOE -> XP = HOE_COPPER_XP;
            case SICKLE -> XP = SICKLE_COPPER_XP;
            case AXE -> XP = AXE_COPPER_XP;
            case HAMMER -> XP = HAMMER_COPPER_XP;
            case WATERING_CAN -> XP = WATERING_CAN_COPPER_XP;
            case FISHING_ROD -> XP = FISHING_ROD_COPPER_XP;
        }
        return XP;
    }

    public void addSilverXP(EnumTools tool, float amount, ServerPlayer player) {
        switch(tool) {
            case HOE -> HOE_SILVER_XP = HOE_SILVER_XP + amount;
            case SICKLE -> SICKLE_SILVER_XP = SICKLE_SILVER_XP + amount;
            case AXE -> AXE_SILVER_XP = AXE_SILVER_XP + amount;
            case HAMMER -> HAMMER_SILVER_XP = HAMMER_SILVER_XP + amount;
            case WATERING_CAN -> WATERING_CAN_SILVER_XP = WATERING_CAN_SILVER_XP + amount;
            case FISHING_ROD -> FISHING_ROD_SILVER_XP = FISHING_ROD_SILVER_XP + amount;
        }
        sendPacket(tool, player);
    }

    public void setSilverXP(EnumTools tool, float amount) {
        switch(tool) {
            case HOE -> HOE_SILVER_XP = amount;
            case SICKLE -> SICKLE_SILVER_XP = amount;
            case AXE -> AXE_SILVER_XP = amount;
            case HAMMER -> HAMMER_SILVER_XP = amount;
            case WATERING_CAN -> WATERING_CAN_SILVER_XP = amount;
            case FISHING_ROD -> FISHING_ROD_SILVER_XP = amount;
        }
    }

    public float getSilverXP(EnumTools tool) {
        float XP = 0.0F;
        switch(tool) {
            case HOE -> XP = HOE_SILVER_XP;
            case SICKLE -> XP = SICKLE_SILVER_XP;
            case AXE -> XP = AXE_SILVER_XP;
            case HAMMER -> XP = HAMMER_SILVER_XP;
            case WATERING_CAN -> XP = WATERING_CAN_SILVER_XP;
            case FISHING_ROD -> XP = FISHING_ROD_SILVER_XP;
        }
        return XP;
    }

    public void addGoldXP(EnumTools tool, float amount, ServerPlayer player) {
        switch(tool) {
            case HOE -> HOE_GOLD_XP = HOE_GOLD_XP + amount;
            case SICKLE -> SICKLE_GOLD_XP = SICKLE_GOLD_XP + amount;
            case AXE -> AXE_GOLD_XP = AXE_GOLD_XP + amount;
            case HAMMER -> HAMMER_GOLD_XP = HAMMER_GOLD_XP + amount;
            case WATERING_CAN -> WATERING_CAN_GOLD_XP = WATERING_CAN_GOLD_XP + amount;
            case FISHING_ROD -> FISHING_ROD_GOLD_XP = FISHING_ROD_GOLD_XP + amount;
        }
        sendPacket(tool, player);
    }

    public void setGoldXP(EnumTools tool, float amount) {
        switch(tool) {
            case HOE -> HOE_GOLD_XP = amount;
            case SICKLE -> SICKLE_GOLD_XP = amount;
            case AXE -> AXE_GOLD_XP = amount;
            case HAMMER -> HAMMER_GOLD_XP = amount;
            case WATERING_CAN -> WATERING_CAN_GOLD_XP = amount;
            case FISHING_ROD -> FISHING_ROD_GOLD_XP = amount;
        }
    }

    public float getGoldXP(EnumTools tool) {
        float XP = 0.0F;
        switch(tool) {
            case HOE -> XP = HOE_GOLD_XP;
            case SICKLE -> XP = SICKLE_GOLD_XP;
            case AXE -> XP = AXE_GOLD_XP;
            case HAMMER -> XP = HAMMER_GOLD_XP;
            case WATERING_CAN -> XP = WATERING_CAN_GOLD_XP;
            case FISHING_ROD -> XP = FISHING_ROD_GOLD_XP;
        }
        return XP;
    }

    public void addMythrilXP(EnumTools tool, float amount, ServerPlayer player) {
        switch(tool) {
            case HOE -> HOE_MYTHRIL_XP = HOE_MYTHRIL_XP + amount;
            case SICKLE -> SICKLE_MYTHRIL_XP = SICKLE_MYTHRIL_XP + amount;
            case AXE -> AXE_MYTHRIL_XP = AXE_MYTHRIL_XP + amount;
            case HAMMER -> HAMMER_MYTHRIL_XP = HAMMER_MYTHRIL_XP + amount;
            case WATERING_CAN -> WATERING_CAN_MYTHRIL_XP = WATERING_CAN_MYTHRIL_XP + amount;
            case FISHING_ROD -> FISHING_ROD_MYTHRIL_XP = FISHING_ROD_MYTHRIL_XP + amount;
        }
        sendPacket(tool, player);
    }

    public void setMythrilXP(EnumTools tool, float amount) {
        switch(tool) {
            case HOE -> HOE_MYTHRIL_XP = amount;
            case SICKLE -> SICKLE_MYTHRIL_XP = amount;
            case AXE -> AXE_MYTHRIL_XP = amount;
            case HAMMER -> HAMMER_MYTHRIL_XP = amount;
            case WATERING_CAN -> WATERING_CAN_MYTHRIL_XP = amount;
            case FISHING_ROD -> FISHING_ROD_MYTHRIL_XP = amount;
        }
    }

    public float getMythrilXP(EnumTools tool) {
        float XP = 0.0F;
        switch(tool) {
            case HOE -> XP = HOE_MYTHRIL_XP;
            case SICKLE -> XP = SICKLE_MYTHRIL_XP;
            case AXE -> XP = AXE_MYTHRIL_XP;
            case HAMMER -> XP = HAMMER_MYTHRIL_XP;
            case WATERING_CAN -> XP = WATERING_CAN_MYTHRIL_XP;
            case FISHING_ROD -> XP = FISHING_ROD_MYTHRIL_XP;
        }
        return XP;
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
            HMNetworkRegistry.sendToPlayer(sp, new PacketPlayerStats(getCoins(), getCopperXP(tool), getSilverXP(tool), getGoldXP(tool), getMythrilXP(tool), tool));
        }
    }

    public void update(Player player) {
        for(EnumTools tool : EnumTools.values()) {
            sendPacket(tool, player);

            if(getCopperXP(tool)  >= 150) {
                setCopperXP(tool, 150);
            }

            if(getSilverXP(tool)  >= 150) {
                setSilverXP(tool, 150);
            }

            if(getGoldXP(tool)  >= 150) {
                setGoldXP(tool, 150);
            }

            if(getMythrilXP(tool)  >= 150) {
                setMythrilXP(tool, 150);
            }
        }
    }


    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag tag = new CompoundTag();

        tag.putInt("sentacoins", this.coin);

        tag.putFloat("hoe_copper", this.HOE_COPPER_XP);
        tag.putFloat("hoe_silver", this.HOE_SILVER_XP);
        tag.putFloat("hoe_gold", this.HOE_GOLD_XP);
        tag.putFloat("hoe_mythril", this.HOE_MYTHRIL_XP);

        tag.putFloat("sickle_copper", this.SICKLE_COPPER_XP);
        tag.putFloat("sickle_silver", this.SICKLE_SILVER_XP);
        tag.putFloat("sickle_gold", this.SICKLE_GOLD_XP);
        tag.putFloat("sickle_mythril", this.SICKLE_MYTHRIL_XP);

        tag.putFloat("axe_copper", this.AXE_COPPER_XP);
        tag.putFloat("axe_silver", this.AXE_SILVER_XP);
        tag.putFloat("axe_gold", this.AXE_GOLD_XP);
        tag.putFloat("axe_mythril", this.AXE_MYTHRIL_XP);

        tag.putFloat("hammer_copper", this.HAMMER_COPPER_XP);
        tag.putFloat("hammer_silver", this.HAMMER_SILVER_XP);
        tag.putFloat("hammer_gold", this.HAMMER_GOLD_XP);
        tag.putFloat("hammer_mythril", this.HAMMER_MYTHRIL_XP);

        tag.putFloat("watering_can_copper", this.WATERING_CAN_COPPER_XP);
        tag.putFloat("watering_can_silver", this.WATERING_CAN_SILVER_XP);
        tag.putFloat("watering_can_gold", this.WATERING_CAN_GOLD_XP);
        tag.putFloat("watering_can_mythril", this.WATERING_CAN_MYTHRIL_XP);

        tag.putFloat("fishing_rod_copper", this.FISHING_ROD_COPPER_XP);
        tag.putFloat("fishing_rod_silver", this.FISHING_ROD_SILVER_XP);
        tag.putFloat("fishing_rod_gold", this.FISHING_ROD_GOLD_XP);
        tag.putFloat("fishing_rod_mythril", this.FISHING_ROD_MYTHRIL_XP);
        return tag;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag tag) {
        coin = tag.getInt("sentacoins");

        HOE_COPPER_XP = tag.getInt("hoe_copper");
        HOE_SILVER_XP = tag.getInt("hoe_silver");
        HOE_GOLD_XP = tag.getInt("hoe_gold");
        HOE_MYTHRIL_XP = tag.getInt("hoe_mythril");

        SICKLE_COPPER_XP = tag.getInt("sickle_copper");
        SICKLE_SILVER_XP = tag.getInt("sickle_silver");
        SICKLE_GOLD_XP = tag.getInt("sickle_gold");
        SICKLE_MYTHRIL_XP = tag.getInt("sickle_mythril");

        AXE_COPPER_XP = tag.getInt("axe_copper");
        AXE_SILVER_XP = tag.getInt("axe_silver");
        AXE_GOLD_XP = tag.getInt("axe_gold");
        AXE_MYTHRIL_XP = tag.getInt("axe_mythril");

        HAMMER_COPPER_XP = tag.getInt("hammer_copper");
        HAMMER_SILVER_XP = tag.getInt("hammer_silver");
        HAMMER_GOLD_XP = tag.getInt("hammer_gold");
        HAMMER_MYTHRIL_XP = tag.getInt("hammer_mythril");

        WATERING_CAN_COPPER_XP = tag.getInt("watering_can_copper");
        WATERING_CAN_SILVER_XP = tag.getInt("watering_can_silver");
        WATERING_CAN_GOLD_XP = tag.getInt("watering_can_gold");
        WATERING_CAN_MYTHRIL_XP = tag.getInt("watering_can_mythril");

        FISHING_ROD_COPPER_XP = tag.getInt("fishing_rod_copper");
        FISHING_ROD_SILVER_XP = tag.getInt("fishing_rod_silver");
        FISHING_ROD_GOLD_XP = tag.getInt("fishing_rod_gold");
        FISHING_ROD_MYTHRIL_XP = tag.getInt("fishing_rod_mythril");
    }
}