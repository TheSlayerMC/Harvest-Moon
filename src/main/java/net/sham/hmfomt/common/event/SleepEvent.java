package net.sham.hmfomt.common.event;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.SleepFinishedTimeEvent;
import net.sham.hmfomt.common.block.FarmlandBlock;
import net.sham.hmfomt.core.HMBlocks;
import net.sham.hmfomt.core.HarvestMoon;

@EventBusSubscriber(modid = HarvestMoon.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class SleepEvent {

    @SubscribeEvent
    public static void finishedSleeping(final SleepFinishedTimeEvent event) {
        LevelAccessor level = event.getLevel();
        Player player = event.getLevel().players().getFirst();
        final BlockPos entityPos = BlockPos.containing(player.position());



        resetFarmland(100, entityPos, level);
    }

    public static void resetFarmland(int checkRadius, BlockPos entityPos, LevelAccessor level) {
        for (int x = -checkRadius; x <= checkRadius; x++) {
            for (int z = -checkRadius; z <= checkRadius; z++) {
                for (int y = -2; y <= 1; y++) {
                    final BlockPos pos = entityPos.offset(x, y, z);
                    final Block block = level.getBlockState(pos).getBlock();
                    if(block == HMBlocks.FARMLAND.get()) {
                        BlockState state = level.getBlockState(pos).setValue(FarmlandBlock.MOISTURE, false);
                        level.setBlock(pos, state, 2);
                    }
                }
            }
        }
    }
}