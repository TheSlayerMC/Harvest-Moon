package net.sham.hmfomt.common.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.sham.hmfomt.common.block.FarmlandBlock;
import net.sham.hmfomt.core.HMBlocks;
import org.jetbrains.annotations.NotNull;

public class WateringCanItem extends HMItem {

    public WateringCanItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        BlockPos blockpos = pContext.getClickedPos();
        BlockState blockstate = level.getBlockState(blockpos);
        if(blockstate == HMBlocks.FARMLAND.get().defaultBlockState() && pContext.getClickedFace() == Direction.UP) {
                BlockState blockstate1 = blockstate.setValue(FarmlandBlock.MOISTURE, true);
                level.setBlock(blockpos, blockstate1, 2);
            return InteractionResult.SUCCESS_SERVER;
        }
        return InteractionResult.FAIL;
    }
}
