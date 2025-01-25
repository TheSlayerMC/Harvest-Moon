package net.sham.hmfomt.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.ChatFormatting;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.sham.hmfomt.client.ClientHelper;
import net.sham.hmfomt.common.capability.WorldEvents;
import net.sham.hmfomt.core.HMDataAttachments;
import net.sham.hmfomt.core.HarvestMoon;
import net.sham.hmfomt.core.data.enums.Seasons;
import net.sham.hmfomt.core.data.enums.WeekDay;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

@OnlyIn(Dist.CLIENT)
public class PlayerHUD implements LayeredDraw.Layer {

    private static final ResourceLocation HUD_TEXTURE = ResourceLocation.fromNamespaceAndPath(HarvestMoon.MOD_ID, "textures/gui/hud.png");

    @Override
    public void render(@NotNull GuiGraphics gui, DeltaTracker tracker) {
        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, HUD_TEXTURE);
        if(player != null) {
            Level level = player.level();
            WorldEvents worldEvents = player.getData(HMDataAttachments.WORLD_EVENTS);
            int screenHeight = gui.guiHeight();
            int screenWidth = gui.guiWidth();

            int x = screenWidth / 2 - 90;
            int y = screenHeight - 35;

            gui.pose().pushPose();

            float scale = 1.3F;
            double widthTranslation = (screenWidth / 2F);
            double heightTranslation = (screenHeight);
            gui.pose().translate(widthTranslation, heightTranslation, 0);
            gui.pose().scale(scale, scale, 0);
            gui.pose().translate(-widthTranslation, -heightTranslation, 0);

            if(worldEvents.getCurrentTickTimer() >= 3)
                gui.blit(RenderType::guiTextured, HUD_TEXTURE, x, y, 0, 16, 181, 33, 256, 256);
            else
                gui.blit(RenderType::guiTextured, HUD_TEXTURE, x, y, 0, 50, 181, 33, 256, 256);

            ChatFormatting colour = ChatFormatting.WHITE;

            switch(Seasons.getSeasonFromLoc(worldEvents.getCurrentSeason())) {
                case Seasons.SPRING -> colour = ChatFormatting.GREEN;
                case Seasons.SUMMER -> colour = ChatFormatting.LIGHT_PURPLE;
                case Seasons.AUTUMN -> colour = ChatFormatting.RED;
                case Seasons.WINTER -> colour = ChatFormatting.WHITE;
            }

            gui.drawString(minecraft.font, Seasons.getNameFromSeason(worldEvents.getCurrentSeason()), x + 7, y + 7, ArgbColor.from(colour), true);
            gui.drawString(minecraft.font, ClientHelper.AMPM(player.level().dayTime()), x + 7, y + 17, ArgbColor.from(ChatFormatting.WHITE), true);
            gui.drawString(minecraft.font, "" + (worldEvents.getCurrentDayOfMonth()), x + 30, y + 7, ArgbColor.from(ChatFormatting.WHITE), true);
            gui.drawString(minecraft.font, WeekDay.getNameFromDay(worldEvents.getCurrentDayOfWeek()), x + 45, y + 7, ArgbColor.from(ChatFormatting.WHITE), true);
            gui.drawString(minecraft.font, ClientHelper.formatTime(player.level().dayTime()), x + 30, y + 17, ArgbColor.from(ChatFormatting.WHITE), true);

            gui.pose().pushPose();

            scale = 1.5F;
            widthTranslation = (screenWidth / 2F);
            heightTranslation = (screenHeight) - 18;
            gui.pose().translate(widthTranslation, heightTranslation, 0);
            gui.pose().scale(scale, scale, 0);
            gui.pose().translate(-widthTranslation, -heightTranslation, 0);

            int weatherX = x + 85;
            int weatherY = y + 9;
            gui.blit(RenderType::guiTextured, HUD_TEXTURE, weatherX, weatherY, 0, 0, 16, 16, 256, 256);
            if(level.getLevelData().isRaining())
                gui.blit(RenderType::guiTextured, HUD_TEXTURE, weatherX, weatherY, 16, 0, 16, 16, 256, 256);
            if(level.getLevelData().isThundering())
                gui.blit(RenderType::guiTextured, HUD_TEXTURE, weatherX, weatherY, 32, 0, 16, 16, 256, 256);

            if(worldEvents.getCurrentSeason() == Seasons.getLocFromName("winter")) {
                if(level.getLevelData().isThundering()) {
                    gui.blit(RenderType::guiTextured, HUD_TEXTURE, weatherX, weatherY, 64, 0, 16, 16, 256, 256);
                } else {
                    gui.blit(RenderType::guiTextured, HUD_TEXTURE, weatherX, weatherY, 48, 0, 16, 16, 256, 256);
                }
            }


            int itemX = x + 120;
            int itemY = y + 9;
            gui.blit(RenderType::guiTextured, HUD_TEXTURE, itemX, itemY, 80, 0, 16, 16, 256, 256);
            ItemStack heldItem = player.getMainHandItem();
            gui.renderItem(heldItem, itemX, itemY);
            gui.pose().popPose();
        }
    }
}