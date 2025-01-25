package net.sham.hmfomt.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.sham.hmfomt.core.HMBlockEntities;

public class PlayerBedBlockEntity extends BlockEntity {

    public PlayerBedBlockEntity(BlockPos pos, BlockState blockState) {
        super(HMBlockEntities.PLAYER_BED.get(), pos, blockState);
    }

    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}
