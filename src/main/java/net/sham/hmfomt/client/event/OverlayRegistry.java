package net.sham.hmfomt.client.event;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.sham.hmfomt.client.screen.PlayerHUD;
import net.sham.hmfomt.core.HarvestMoon;

@EventBusSubscriber(modid = HarvestMoon.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class OverlayRegistry {

    @SubscribeEvent
    public static void registerOverlays(RegisterGuiLayersEvent event) {
        event.registerAboveAll(HarvestMoon.rl("player_hud"), new PlayerHUD());
    }
}