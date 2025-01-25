package net.sham.hmfomt.common.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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

public class HoeItem extends HMItem {

    public HoeItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        BlockPos blockpos = pContext.getClickedPos();
        BlockState blockstate = level.getBlockState(blockpos);
        if(blockstate == HMBlocks.SOIL.get().defaultBlockState() && pContext.getClickedFace() == Direction.UP) {
            PlayerStats stats = Objects.requireNonNull(pContext.getPlayer()).getData(HMDataAttachments.PLAYER_STATS);
            stats.addXP(EnumTools.HOE, 1F, pContext.getPlayer());
            level.setBlock(blockpos, HMBlocks.FARMLAND.get().defaultBlockState(), 2);
            return InteractionResult.SUCCESS_SERVER;
        }
        return InteractionResult.SUCCESS;
    }
}
