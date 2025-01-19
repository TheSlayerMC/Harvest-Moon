package net.sham.hmfomt.client.event;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.InputEvent;
import net.sham.hmfomt.client.screen.PlayerStatsScreen;
import net.sham.hmfomt.common.capability.PlayerStats;
import org.lwjgl.glfw.GLFW;

public class KeyUsedEvent {

    private static final Minecraft MINECRAFT = Minecraft.getInstance();

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        InputConstants.Key key = InputConstants.getKey(event.getKey(), event.getScanCode());
        if(MINECRAFT.screen == null) {
            assert MINECRAFT.player != null;
            int action = event.getAction();
            if(action == GLFW.GLFW_PRESS) {
                if(key == KeyBindEvents.keyStats.getKey()) {
                    assert Minecraft.getInstance().player != null;
                    displayPlayerStats(Minecraft.getInstance().player);
                }
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static void displayPlayerStats(Player player) {
        Minecraft.getInstance().setScreen(new PlayerStatsScreen(player));
    }
}
