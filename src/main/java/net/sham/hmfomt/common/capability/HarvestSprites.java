package net.sham.hmfomt.common.capability;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.common.util.INBTSerializable;
import net.sham.hmfomt.core.data.enums.EnumSprites;
import net.sham.hmfomt.common.packet.PacketSprites;
import net.sham.hmfomt.core.data.HMNetworkRegistry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnknownNullability;

public class HarvestSprites implements INBTSerializable<CompoundTag> {

    public float STAID_HEART_FLOAT;
    public float STAID_WATER, STAID_HARVESTING, STAID_ANIMAL;

    public float NAPPY_HEART_FLOAT;
    public float NAPPY_WATER, NAPPY_HARVESTING, NAPPY_ANIMAL;

    public float BOLD_HEART_FLOAT;
    public float BOLD_WATER, BOLD_HARVESTING, BOLD_ANIMAL;

    public float CHEF_HEART_FLOAT;
    public float CHEF_WATER, CHEF_HARVESTING, CHEF_ANIMAL;

    public float AQUA_HEART_FLOAT;
    public float AQUA_WATER, AQUA_HARVESTING, AQUA_ANIMAL;

    public float HOGGY_HEART_FLOAT;
    public float HOGGY_WATER, HOGGY_HARVESTING, HOGGY_ANIMAL;

    public float TIMID_HEART_FLOAT;
    public float TIMID_WATER, TIMID_HARVESTING, TIMID_ANIMAL;
    
    public void copyFrom(HarvestSprites sprite) {
        for(EnumSprites s : EnumSprites.values()) {
            setHeartFloat(s, sprite.getHeartFloat(s));
            setWaterXP(s, sprite.getWaterXP(s));
            setHarvestXP(s, sprite.getWaterXP(s));
            setAnimalXP(s, sprite.getWaterXP(s));
        }
    }

    public void setHeartFloat(EnumSprites sprite, float amount) {
        switch(sprite) {
            case STAID -> STAID_HEART_FLOAT = amount;
            case NAPPY -> NAPPY_HEART_FLOAT = amount;
            case BOLD -> BOLD_HEART_FLOAT = amount;
            case CHEF -> CHEF_HEART_FLOAT = amount;
            case AQUA -> AQUA_HEART_FLOAT = amount;
            case HOGGY -> HOGGY_HEART_FLOAT = amount;
            case TIMID -> TIMID_HEART_FLOAT = amount;
        }
    }

    private float getHeartFloat(EnumSprites sprite) {
        float amount = 0;
        switch(sprite) {
            case STAID -> amount = STAID_HEART_FLOAT;
            case NAPPY -> amount = NAPPY_HEART_FLOAT;
            case BOLD -> amount = BOLD_HEART_FLOAT;
            case CHEF -> amount = CHEF_HEART_FLOAT;
            case AQUA -> amount = AQUA_HEART_FLOAT;
            case HOGGY -> amount = HOGGY_HEART_FLOAT;
            case TIMID -> amount = TIMID_HEART_FLOAT;
        }
        return amount;
    }

    public int getHearts(EnumSprites sprite) {
        int level = 0;
        switch(sprite) {
            case STAID -> level = (int)STAID_HEART_FLOAT;
            case NAPPY -> level = (int)NAPPY_HEART_FLOAT;
            case BOLD -> level = (int)BOLD_HEART_FLOAT;
            case CHEF -> level = (int)CHEF_HEART_FLOAT;
            case AQUA -> level = (int)AQUA_HEART_FLOAT;
            case HOGGY -> level = (int)HOGGY_HEART_FLOAT;
            case TIMID -> level = (int)TIMID_HEART_FLOAT;
        }
        return level;
    }

    public void addAffection(EnumSprites sprite, float amount, Player player) {
        switch(sprite) {
            case STAID -> STAID_HEART_FLOAT = STAID_HEART_FLOAT + amount;
            case NAPPY -> NAPPY_HEART_FLOAT = NAPPY_HEART_FLOAT + amount;
            case BOLD -> BOLD_HEART_FLOAT = BOLD_HEART_FLOAT + amount;
            case CHEF -> CHEF_HEART_FLOAT = CHEF_HEART_FLOAT + amount;
            case AQUA -> AQUA_HEART_FLOAT = AQUA_HEART_FLOAT + amount;
            case HOGGY -> HOGGY_HEART_FLOAT = HOGGY_HEART_FLOAT + amount;
            case TIMID -> TIMID_HEART_FLOAT = TIMID_HEART_FLOAT + amount;
        }
        sendPacket(sprite, player);
    }

    public float getAffection(EnumSprites sprite) {
        float XP = 0.0F;
        switch(sprite) {
            case STAID -> XP = STAID_HEART_FLOAT;
            case NAPPY -> XP = NAPPY_HEART_FLOAT;
            case BOLD -> XP = BOLD_HEART_FLOAT;
            case CHEF -> XP = CHEF_HEART_FLOAT;
            case AQUA -> XP = AQUA_HEART_FLOAT;
            case HOGGY -> XP = HOGGY_HEART_FLOAT;
            case TIMID -> XP = TIMID_HEART_FLOAT;
        }
        return XP;
    }

    public void addWaterXP(EnumSprites sprite, float amount, Player player) {
        switch(sprite) {
            case STAID -> STAID_WATER = STAID_WATER + amount;
            case NAPPY -> NAPPY_WATER = NAPPY_WATER + amount;
            case BOLD -> BOLD_WATER = BOLD_WATER + amount;
            case CHEF -> CHEF_WATER = CHEF_WATER + amount;
            case AQUA -> AQUA_WATER = AQUA_WATER + amount;
            case HOGGY -> HOGGY_WATER = HOGGY_WATER + amount;
            case TIMID -> TIMID_WATER = TIMID_WATER + amount;
        }
        sendPacket(sprite, player);
    }

    public void setWaterXP(EnumSprites sprite, float amount) {
        switch(sprite) {
            case STAID -> STAID_WATER = amount;
            case NAPPY -> NAPPY_WATER = amount;
            case BOLD -> BOLD_WATER = amount;
            case CHEF -> CHEF_WATER = amount;
            case AQUA -> AQUA_WATER = amount;
            case HOGGY -> HOGGY_WATER = amount;
            case TIMID -> TIMID_WATER = amount;
        }
    }

    public float getWaterXP(EnumSprites sprite) {
        float XP = 0.0F;
        switch(sprite) {
            case STAID -> XP = STAID_WATER;
            case NAPPY -> XP = NAPPY_WATER;
            case BOLD -> XP = BOLD_WATER;
            case CHEF -> XP = CHEF_WATER;
            case AQUA -> XP = AQUA_WATER;
            case HOGGY -> XP = HOGGY_WATER;
            case TIMID -> XP = TIMID_WATER;
        }
        return XP;
    }

    public void addHarvestXP(EnumSprites sprite, float amount, Player player) {
        switch(sprite) {
            case STAID -> STAID_HARVESTING = STAID_HARVESTING + amount;
            case NAPPY -> NAPPY_HARVESTING = NAPPY_HARVESTING + amount;
            case BOLD -> BOLD_HARVESTING = BOLD_HARVESTING + amount;
            case CHEF -> CHEF_HARVESTING = CHEF_HARVESTING + amount;
            case AQUA -> AQUA_HARVESTING = AQUA_HARVESTING + amount;
            case HOGGY -> HOGGY_HARVESTING = HOGGY_HARVESTING + amount;
            case TIMID -> TIMID_HARVESTING = TIMID_HARVESTING + amount;
        }
        sendPacket(sprite, player);
    }

    public void setHarvestXP(EnumSprites sprite, float amount) {
        switch(sprite) {
            case STAID -> STAID_HARVESTING = amount;
            case NAPPY -> NAPPY_HARVESTING = amount;
            case BOLD -> BOLD_HARVESTING = amount;
            case CHEF -> CHEF_HARVESTING = amount;
            case AQUA -> AQUA_HARVESTING = amount;
            case HOGGY -> HOGGY_HARVESTING = amount;
            case TIMID -> TIMID_HARVESTING = amount;
        }
    }

    public float getHarvestXP(EnumSprites sprite) {
        float XP = 0.0F;
        switch(sprite) {
            case STAID -> XP = STAID_HARVESTING;
            case NAPPY -> XP = NAPPY_HARVESTING;
            case BOLD -> XP = BOLD_HARVESTING;
            case CHEF -> XP = CHEF_HARVESTING;
            case AQUA -> XP = AQUA_HARVESTING;
            case HOGGY -> XP = HOGGY_HARVESTING;
            case TIMID -> XP = TIMID_HARVESTING;
        }
        return XP;
    }

    public void addAnimalXP(EnumSprites sprite, float amount, Player player) {
        switch(sprite) {
            case STAID -> STAID_ANIMAL = STAID_ANIMAL + amount;
            case NAPPY -> NAPPY_ANIMAL = NAPPY_ANIMAL + amount;
            case BOLD -> BOLD_ANIMAL = BOLD_ANIMAL + amount;
            case CHEF -> CHEF_ANIMAL = CHEF_ANIMAL + amount;
            case AQUA -> AQUA_ANIMAL = AQUA_ANIMAL + amount;
            case HOGGY -> HOGGY_ANIMAL = HOGGY_ANIMAL + amount;
            case TIMID -> TIMID_ANIMAL = TIMID_ANIMAL + amount;
        }
        sendPacket(sprite, player);
    }

    public void setAnimalXP(EnumSprites sprite, float amount) {
        switch(sprite) {
            case STAID -> STAID_ANIMAL = amount;
            case NAPPY -> NAPPY_ANIMAL = amount;
            case BOLD -> BOLD_ANIMAL = amount;
            case CHEF -> CHEF_ANIMAL = amount;
            case AQUA -> AQUA_ANIMAL = amount;
            case HOGGY -> HOGGY_ANIMAL = amount;
            case TIMID -> TIMID_ANIMAL = amount;
        }
    }

    public float getAnimalXP(EnumSprites sprite) {
        float XP = 0.0F;
        switch(sprite) {
            case STAID -> XP = STAID_ANIMAL;
            case NAPPY -> XP = NAPPY_ANIMAL;
            case BOLD -> XP = BOLD_ANIMAL;
            case CHEF -> XP = CHEF_ANIMAL;
            case AQUA -> XP = AQUA_ANIMAL;
            case HOGGY -> XP = HOGGY_ANIMAL;
            case TIMID -> XP = TIMID_ANIMAL;
        }
        return XP;
    }

    public void sendPacket(EnumSprites sprite, Player player) {
        if(player instanceof ServerPlayer sp) {
            HMNetworkRegistry.sendToPlayer(sp, new PacketSprites(getHeartFloat(sprite), getWaterXP(sprite), getHarvestXP(sprite), getAnimalXP(sprite), sprite));
        }
    }

    public void update(Player player) {
        for(EnumSprites sprites : EnumSprites.values()) {
            sendPacket(sprites, player);
        }
    }
    
    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.@NotNull Provider provider) {
        CompoundTag tag = new CompoundTag();
        
        tag.putFloat("staid_heart", STAID_HEART_FLOAT);
        tag.putFloat("staid_water", STAID_WATER);
        tag.putFloat("staid_harvest", STAID_HARVESTING);
        tag.putFloat("staid_animal", STAID_ANIMAL);

        tag.putFloat("nappy_heart", NAPPY_HEART_FLOAT);
        tag.putFloat("nappy_water", NAPPY_WATER);
        tag.putFloat("nappy_harvest", NAPPY_HARVESTING);
        tag.putFloat("nappy_animal", NAPPY_ANIMAL);

        tag.putFloat("bold_heart", BOLD_HEART_FLOAT);
        tag.putFloat("bold_water", BOLD_WATER);
        tag.putFloat("bold_harvest", BOLD_HARVESTING);
        tag.putFloat("bold_animal", BOLD_ANIMAL);

        tag.putFloat("chef_heart", CHEF_HEART_FLOAT);
        tag.putFloat("chef_water", CHEF_WATER);
        tag.putFloat("chef_harvest", CHEF_HARVESTING);
        tag.putFloat("chef_animal", CHEF_ANIMAL);

        tag.putFloat("aqua_heart", AQUA_HEART_FLOAT);
        tag.putFloat("aqua_water", AQUA_WATER);
        tag.putFloat("aqua_harvest", AQUA_HARVESTING);
        tag.putFloat("aqua_animal", AQUA_ANIMAL);

        tag.putFloat("hoggy_heart", HOGGY_HEART_FLOAT);
        tag.putFloat("hoggy_water", HOGGY_WATER);
        tag.putFloat("hoggy_harvest", HOGGY_HARVESTING);
        tag.putFloat("hoggy_animal", HOGGY_ANIMAL);

        tag.putFloat("timid_heart", TIMID_HEART_FLOAT);
        tag.putFloat("timid_water", TIMID_WATER);
        tag.putFloat("timid_harvest", TIMID_HARVESTING);
        tag.putFloat("timid_animal", TIMID_ANIMAL);
        return tag;
    }

    @Override
    public void deserializeNBT(HolderLookup.@NotNull Provider provider, CompoundTag tag) {
        STAID_HEART_FLOAT = tag.getFloat("staid_heart");
        STAID_WATER = tag.getFloat("staid_water");
        STAID_HARVESTING = tag.getFloat("staid_harvest");
        STAID_ANIMAL = tag.getFloat("staid_animal");

        NAPPY_HEART_FLOAT = tag.getFloat("nappy_heart");
        NAPPY_WATER = tag.getFloat("nappy_water");
        NAPPY_HARVESTING = tag.getFloat("nappy_harvest");
        NAPPY_ANIMAL = tag.getFloat("nappy_animal");

        BOLD_HEART_FLOAT = tag.getFloat("bold_heart");
        BOLD_WATER = tag.getFloat("bold_water");
        BOLD_HARVESTING = tag.getFloat("bold_harvest");
        BOLD_ANIMAL = tag.getFloat("bold_animal");

        CHEF_HEART_FLOAT = tag.getFloat("chef_heart");
        CHEF_WATER = tag.getFloat("chef_water");
        CHEF_HARVESTING = tag.getFloat("chef_harvest");
        CHEF_ANIMAL = tag.getFloat("chef_animal");

        AQUA_HEART_FLOAT = tag.getFloat("aqua_heart");
        AQUA_WATER = tag.getFloat("aqua_water");
        AQUA_HARVESTING = tag.getFloat("aqua_harvest");
        AQUA_ANIMAL = tag.getFloat("aqua_animal");

        HOGGY_HEART_FLOAT = tag.getFloat("hoggy_heart");
        HOGGY_WATER = tag.getFloat("hoggy_water");
        HOGGY_HARVESTING = tag.getFloat("hoggy_harvest");
        HOGGY_ANIMAL = tag.getFloat("hoggy_animal");

        TIMID_HEART_FLOAT = tag.getFloat("timid_heart");
        TIMID_WATER = tag.getFloat("timid_water");
        TIMID_HARVESTING = tag.getFloat("timid_harvest");
        TIMID_ANIMAL = tag.getFloat("timid_animal");
    }
}