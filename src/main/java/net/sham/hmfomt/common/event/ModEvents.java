package net.sham.hmfomt.common.event;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.sham.hmfomt.common.capability.HarvestSprites;
import net.sham.hmfomt.common.capability.PlayerStats;
import net.sham.hmfomt.common.capability.WorldEvents;
import net.sham.hmfomt.core.HMDataAttachments;
import net.sham.hmfomt.core.HarvestMoon;

@EventBusSubscriber(modid = HarvestMoon.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class ModEvents {

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if(event.isWasDeath()) {
            event.getEntity().getData(HMDataAttachments.PLAYER_STATS).copyFrom(event.getOriginal().getData(HMDataAttachments.PLAYER_STATS));
            event.getEntity().getData(HMDataAttachments.HARVEST_SPRITES).copyFrom(event.getOriginal().getData(HMDataAttachments.HARVEST_SPRITES));
            event.getEntity().getData(HMDataAttachments.WORLD_EVENTS).copyFrom(event.getOriginal().getData(HMDataAttachments.WORLD_EVENTS));
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        if(event.getEntity() instanceof Player player) {
            PlayerStats stats = player.getData(HMDataAttachments.PLAYER_STATS);
            stats.update(player);

            if(player instanceof ServerPlayer sp) {
                BlockPos spawn = sp.getRespawnPosition();
                if(player.level().dayTime() == 24000) {
                    assert spawn != null;
                    player.teleportTo(spawn.getX(), spawn.getY() + 1, spawn.getZ());
                }
            }

            if(stats.getJustWoke()) {
                player.level().getLevelData().setRaining(true);
                stats.setWoke(false);
            }

            HarvestSprites sprites = player.getData(HMDataAttachments.HARVEST_SPRITES);
            sprites.update(player);

            WorldEvents world = player.getData(HMDataAttachments.WORLD_EVENTS);
            world.update(player);

            if(player.level().dayTime() == 18000) {
                world.addDay();
            }
        }
    }
}