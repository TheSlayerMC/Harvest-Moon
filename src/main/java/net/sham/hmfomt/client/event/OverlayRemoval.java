package net.sham.hmfomt.client.event;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
import net.sham.hmfomt.core.HarvestMoon;

@EventBusSubscriber(modid = HarvestMoon.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.GAME)
public class OverlayRemoval {

    @SubscribeEvent
    public static void removeOverlays(RenderGuiLayerEvent.Pre event) {
        if(event.getName().equals(VanillaGuiLayers.HOTBAR) || event.getName().equals(VanillaGuiLayers.PLAYER_HEALTH)
                || event.getName().equals(VanillaGuiLayers.AIR_LEVEL) || event.getName().equals(VanillaGuiLayers.EXPERIENCE_BAR)
                || event.getName().equals(VanillaGuiLayers.EXPERIENCE_LEVEL) || event.getName().equals(VanillaGuiLayers.FOOD_LEVEL)
                || event.getName().equals(VanillaGuiLayers.JUMP_METER) || event.getName().equals(VanillaGuiLayers.VEHICLE_HEALTH)
                || event.getName().equals(VanillaGuiLayers.BOSS_OVERLAY) || event.getName().equals(VanillaGuiLayers.SELECTED_ITEM_NAME)) {
            event.setCanceled(true);
        }
    }
}