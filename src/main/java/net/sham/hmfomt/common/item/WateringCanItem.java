package net.sham.hmfomt.common.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.sham.hmfomt.common.block.FarmlandBlock;
import net.sham.hmfomt.common.capability.PlayerStats;
import net.sham.hmfomt.core.HMBlocks;
import net.sham.hmfomt.core.HMDataAttachments;
import net.sham.hmfomt.core.data.enums.EnumTools;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class WateringCanItem extends HMItem {

    public WateringCanItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        BlockPos blockpos = pContext.getClickedPos();
        BlockState blockstate = level.getBlockState(blockpos);
        BlockState blockstateDown = level.getBlockState(blockpos.below());

        if(blockstate == HMBlocks.FARMLAND.get().defaultBlockState()) {
            BlockState blockstate1 = blockstate.setValue(FarmlandBlock.MOISTURE, true);
            level.setBlock(blockpos, blockstate1, 2);
            PlayerStats stats = Objects.requireNonNull(pContext.getPlayer()).getData(HMDataAttachments.PLAYER_STATS);
            stats.addXP(EnumTools.WATERING_CAN, 1F, pContext.getPlayer());
            return InteractionResult.SUCCESS_SERVER;
        }

        if(blockstateDown == HMBlocks.FARMLAND.get().defaultBlockState()) {
            BlockState blockstate1 = blockstateDown.setValue(FarmlandBlock.MOISTURE, true);
            level.setBlock(blockpos.below(), blockstate1, 2);
            PlayerStats stats = Objects.requireNonNull(pContext.getPlayer()).getData(HMDataAttachments.PLAYER_STATS);
            stats.addXP(EnumTools.WATERING_CAN, 1F, pContext.getPlayer());
            return InteractionResult.SUCCESS_SERVER;
        }
        return InteractionResult.FAIL;
    }
}
