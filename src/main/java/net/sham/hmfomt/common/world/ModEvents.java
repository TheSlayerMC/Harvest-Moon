package net.sham.hmfomt.common.world;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Portal;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.sham.hmfomt.common.capability.HarvestSprites;
import net.sham.hmfomt.common.capability.PlayerStats;
import net.sham.hmfomt.core.HMDataAttachments;
import net.sham.hmfomt.core.HarvestMoon;

import java.util.Objects;

@EventBusSubscriber(modid = HarvestMoon.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class ModEvents {

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if(event.isWasDeath()) {
            event.getEntity().getData(HMDataAttachments.PLAYER_STATS).copyFrom(event.getOriginal().getData(HMDataAttachments.PLAYER_STATS));
            event.getEntity().getData(HMDataAttachments.HARVEST_SPRITES).copyFrom(event.getOriginal().getData(HMDataAttachments.HARVEST_SPRITES));
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        if(event.getEntity() instanceof Player player) {
            PlayerStats stats = player.getData(HMDataAttachments.PLAYER_STATS);
            stats.update(player);

            HarvestSprites sprites = player.getData(HMDataAttachments.HARVEST_SPRITES);
            sprites.update(player);
        }
    }
}