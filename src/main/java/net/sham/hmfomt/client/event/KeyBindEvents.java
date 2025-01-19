package net.sham.hmfomt.client.event;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.resources.language.I18n;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.sham.hmfomt.core.HarvestMoon;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(modid = HarvestMoon.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KeyBindEvents {

    public static KeyMapping keyStats = new KeyMapping("Open Player Stats", GLFW.GLFW_KEY_BACKSPACE, I18n.get("hmfomt.key"));

    @SubscribeEvent
    public static void onKeyRegister(RegisterKeyMappingsEvent event) {
        event.register(keyStats);
    }
}