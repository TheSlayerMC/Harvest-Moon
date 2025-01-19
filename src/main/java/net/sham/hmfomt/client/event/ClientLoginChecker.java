package net.sham.hmfomt.client.event;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.sham.hmfomt.client.ChatUtils;
import net.sham.hmfomt.core.HarvestMoon;
import net.sham.hmfomt.core.data.Config;

import java.io.IOException;
import java.net.SocketException;

@EventBusSubscriber(modid = HarvestMoon.MOD_ID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class ClientLoginChecker {

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        if(Config.UPDATE_MESSAGE.get()) {
            if(!player.level().isClientSide) {
                ChatUtils.sendColouredMessage(player, ChatFormatting.GOLD, "[|---------------------------------------------------|]");
                ChatUtils.sendColouredMessage(player, ChatFormatting.GOLD, "[" + HarvestMoon.MOD_NAME + "]");
                ChatUtils.sendColouredTranslatedMessage(player, ChatFormatting.LIGHT_PURPLE, "hmfomt.message.thank_you", player.getDisplayName());
                ChatUtils.sendColouredTranslatedMessage(player, ChatFormatting.BLUE, "hmfomt.message.current_version", HarvestMoon.MOD_VERSION);

                try {
                    if(!InternetHandler.isOnline()) {
                        MutableComponent msg = Component.translatable("hmfomt.message.no_internet");
                        msg.withStyle(ChatFormatting.RED);
                        ChatUtils.addChatBarChat(player, msg);
                    }
                    try {
                        if(InternetHandler.isUpdateAvailable() && InternetHandler.isOnline()) {
                            ChatUtils.sendColouredTranslatedMessage(player, ChatFormatting.GREEN, "hmfomt.message.update_available", InternetHandler.getUpdateVersion());
                        }
                        if(!InternetHandler.isUpdateAvailable() && InternetHandler.isOnline()) {
                            ChatUtils.sendColouredTranslatedMessage(player, ChatFormatting.AQUA, "hmfomt.message.up_to_date");
                        }
                    } catch(IOException ignored) {
                    }
                } catch(SocketException ignored) {
                }
                ChatUtils.sendColouredMessage(player, ChatFormatting.GOLD, "[|---------------------------------------------------|]");
            }
        }
    }
}