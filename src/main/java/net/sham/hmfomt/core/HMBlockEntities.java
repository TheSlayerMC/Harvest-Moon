package net.sham.hmfomt.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.sham.hmfomt.common.block.entity.PlayerBedBlockEntity;

public class HMBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, HarvestMoon.MOD_ID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PlayerBedBlockEntity>> PLAYER_BED = REGISTRY.register("bed",
            () -> new BlockEntityType<>(PlayerBedBlockEntity::new, HMBlocks.PLAYER_BED.get()));
}
