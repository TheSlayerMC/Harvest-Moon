package net.sham.hmfomt.common.event;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.CanContinueSleepingEvent;
import net.neoforged.neoforge.event.entity.player.CanPlayerSleepEvent;
import net.neoforged.neoforge.event.level.SleepFinishedTimeEvent;
import net.sham.hmfomt.common.block.FarmlandBlock;
import net.sham.hmfomt.core.HMBlocks;
import net.sham.hmfomt.core.HMDataAttachments;
import net.sham.hmfomt.core.HarvestMoon;

import java.util.Objects;

@EventBusSubscriber(modid = HarvestMoon.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class SleepEvent {

    @SubscribeEvent
    public static void finishedSleeping(final SleepFinishedTimeEvent event) {
        LevelAccessor level = event.getLevel();
        Player player = event.getLevel().players().getFirst();
        final BlockPos entityPos = BlockPos.containing(player.position());
        event.getLevel().players().getFirst().getData(HMDataAttachments.PLAYER_STATS).setWoke(true);

        resetFarmland(100, entityPos, level);

        if(!(level.getLevelData().getDayTime() <= 24000 && level.getLevelData().getDayTime() >= 18000))
            event.getLevel().players().getFirst().getData(HMDataAttachments.WORLD_EVENTS).addDay();

        resetTime(event);
    }

    @SubscribeEvent
    public static void canSleep(final CanPlayerSleepEvent event) {
        if(event.getProblem() == Player.BedSleepingProblem.NOT_POSSIBLE_NOW) {
            event.setProblem(null);
        }
    }

    @SubscribeEvent
    public static void continueSleeping(final CanContinueSleepingEvent event) {
        if(event.getProblem() == Player.BedSleepingProblem.NOT_POSSIBLE_NOW) {
            event.setContinueSleeping(true);
        }
    }

    public static void resetTime(SleepFinishedTimeEvent event) {
        ServerLevel level = Objects.requireNonNull(event.getLevel().getServer()).overworld();
        level.getGameRules().getRule(GameRules.RULE_WEATHER_CYCLE).set(false, level.getServer());
        event.setTimeAddition(0);
        level.setDayTime(0);
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