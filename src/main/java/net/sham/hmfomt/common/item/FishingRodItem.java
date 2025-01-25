package net.sham.hmfomt.common.item;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.sham.hmfomt.common.capability.PlayerStats;
import net.sham.hmfomt.core.HMBlocks;
import net.sham.hmfomt.core.HMDataAttachments;
import net.sham.hmfomt.core.data.enums.EnumTools;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class FishingRodItem extends HMItem {

    public FishingRodItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        BlockPos blockpos = pContext.getClickedPos();
        BlockState blockstate = level.getBlockState(blockpos);
        if(blockstate == HMBlocks.YARD_WOOD.get().defaultBlockState()) {
            PlayerStats stats = Objects.requireNonNull(pContext.getPlayer()).getData(HMDataAttachments.PLAYER_STATS);
            stats.addXP(EnumTools.AXE, 1F, pContext.getPlayer());
            level.destroyBlock(blockpos, false);
            return InteractionResult.SUCCESS_SERVER;
        }
        return InteractionResult.SUCCESS;
    }
}
