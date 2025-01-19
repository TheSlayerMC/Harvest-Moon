package net.sham.hmfomt.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.PageButton;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.sham.hmfomt.common.capability.HarvestSprites;
import net.sham.hmfomt.common.capability.PlayerStats;
import net.sham.hmfomt.common.container.EmptyContainer;
import net.sham.hmfomt.core.HMDataAttachments;
import net.sham.hmfomt.core.HarvestMoon;
import net.sham.hmfomt.core.data.enums.EnumHexColor;
import net.sham.hmfomt.core.data.enums.EnumSprites;
import net.sham.hmfomt.core.data.enums.EnumTools;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class PlayerStatsScreen extends AbstractContainerScreen<EmptyContainer> {

    private PageButton nextButton;
    private PageButton previousButton;
    public int pageNumber = 0;
    public Player player;
    private final ResourceLocation STAT_SPRITES = HarvestMoon.rl("textures/gui/stat_sprites.png");
    private final ResourceLocation BACKGROUND = HarvestMoon.rl("textures/gui/player_stats.png");

    public PlayerStatsScreen(Player player) {
        super(new EmptyContainer(), player.getInventory(), Component.empty());
        this.imageWidth = 242;
        this.imageHeight = 200;
        this.player = player;
        this.playerInventoryTitle = Component.empty();
    }

    @Override
    protected void init() {
        super.init();
        int w = (this.width - this.imageWidth) / 2;
        int h = (this.height - this.imageHeight) / 2;
        this.nextButton = this.addRenderableWidget(new PageButton(w + 214, h + 14, true, (button) -> this.flipPage(true), true));
        this.previousButton = this.addRenderableWidget(new PageButton(w + 3, h + 14, false, (button) -> this.flipPage(false), true));
        this.updateButtonVisibility();
    }

    public void drawCenteredStringWithCustomScale(GuiGraphics gui, Font f, FormattedText comp, int x, int y, int z, EnumHexColor colour, float size, int avaliableHeight) {
        gui.pose().pushPose();
        gui.pose().translate(x - (double)f.width(comp) / 2 * size, y + ((double)avaliableHeight / 2) + (f.lineHeight * size > 1 ? -1 * f.lineHeight * size : f.lineHeight * size) * 0.5, z);
        gui.pose().scale(size, size, size);
        gui.drawString(f, comp.getString(), 0, 0, colour.getInt(), false);
        gui.pose().popPose();
    }

    @Override
    public void render(GuiGraphics graphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(graphics, pMouseX, pMouseY, pPartialTick);
        this.updateButtonVisibility();
    }

    @Override
    protected void renderBg(@NotNull GuiGraphics poseStack, float partialTick, int mouseX, int mouseY) {
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        poseStack.pose().pushPose();
        RenderSystem.setShaderTexture(0, this.BACKGROUND);
        poseStack.blit(RenderType::guiTextured, BACKGROUND, x, y, 0.0F, 0.0F, this.imageWidth, this.imageHeight, 256, 256);

        switch(pageNumber) {
            case 0 -> farmPage(poseStack);
            case 1 -> spritePage(poseStack);
            case 2 -> toolPage(poseStack);
            default -> {
            }
        }
        poseStack.pose().popPose();
        RenderSystem.enableDepthTest();
    }

    public void farmPage(GuiGraphics stack) {
        int x = 0;
        int h = 40;
        drawCenteredStringWithCustomScale(stack, font, Component.translatable("Your Property"), x + 320, h + 28, 0, EnumHexColor.BLACK, 1.5F, h);

    }

    public void spritePage(GuiGraphics stack) {
        int height = 40;
        int x = 0;
        int h = 40;
        drawCenteredStringWithCustomScale(stack, font, Component.translatable("Harvest Sprites"), x + 320, h + 28, 0, EnumHexColor.BLACK, 1.5F, h);

        drawHarvestSprite(stack, x, h, EnumSprites.STAID);
        drawHarvestSprite(stack, x + 120, h, EnumSprites.AQUA);
        h += height;
        drawHarvestSprite(stack, x, h, EnumSprites.NAPPY);
        drawHarvestSprite(stack, x + 120, h, EnumSprites.HOGGY);
        h += height;
        drawHarvestSprite(stack, x, h, EnumSprites.BOLD);
        drawHarvestSprite(stack, x + 120, h, EnumSprites.TIMID);
        h += height;
        drawHarvestSprite(stack, x, h, EnumSprites.CHEF);

    }

    public void toolPage(GuiGraphics stack) {
        int height = 25;
        int x = 9;
        int h = 45;

        stack.pose();
        drawCenteredStringWithCustomScale(stack, font, Component.translatable("Tool Level List"), x + 310, h + 20, 0, EnumHexColor.BLACK, 1.5F, h);

        drawToolSprite(stack, x, h, EnumTools.HOE);
        h += height;
        drawToolSprite(stack, x, h, EnumTools.SICKLE);
        h += height;
        drawToolSprite(stack, x, h, EnumTools.AXE);
        h += height;
        drawToolSprite(stack, x, h, EnumTools.HAMMER);
        h += height;
        drawToolSprite(stack, x, h, EnumTools.WATERING_CAN);
        h += height;
        drawToolSprite(stack, x, h, EnumTools.FISHING_ROD);
    }

    public void drawSprite(GuiGraphics matrixStack, int x, int y, int spriteX, int spriteY, String s) {
        int k = (width - imageWidth) / 2;
        int l = (height - imageHeight) / 2;
        RenderSystem.setShaderTexture(0, this.STAT_SPRITES);
        matrixStack.blit(RenderType::guiTextured, STAT_SPRITES, k + x, l + y, spriteX, spriteY, 16, 16, 256, 256);
        RenderSystem.enableDepthTest();
    }

    public void drawHarvestSprite(GuiGraphics matrixStack, int x, int y, EnumSprites type) {
        drawSprite(matrixStack, x, y, type.getSpriteX(), type.getSpriteY(), "");
        int progressBarSize = 100;
        int k = (width - imageWidth) / 2;
        int l = (height - imageHeight) / 2;
        RenderSystem.setShaderTexture(0, this.STAT_SPRITES);
        if(player != null) {
            HarvestSprites sprite = player.getData(HMDataAttachments.HARVEST_SPRITES);
            int waterWidth = (int)sprite.getWaterXP(type);
            int harvestWidth = (int)sprite.getHarvestXP(type);
            int animalWidth = (int)sprite.getAnimalXP(type);

            int progressBarX = k + x + 20, progressBarY = l + y + 10;
            matrixStack.drawString(font, type.getName(), k + x - this.font.width(type.getName()) / 2 + 32, l + y, ArgbColor.from(ChatFormatting.BLACK), false);

            matrixStack.blit(RenderType::guiTextured, STAT_SPRITES, progressBarX, progressBarY, 2, 55, progressBarSize, 7, 256, 256);
            matrixStack.blit(RenderType::guiTextured, STAT_SPRITES, progressBarX, progressBarY, 2, 63, waterWidth, 7, 256, 256);
            matrixStack.blit(RenderType::guiTextured, STAT_SPRITES, progressBarX - 8, progressBarY, 39, 45, 8, 8, 256, 256);

            progressBarY += 10;
            matrixStack.blit(RenderType::guiTextured, STAT_SPRITES, progressBarX, progressBarY, 2, 55, progressBarSize, 7, 256, 256);
            matrixStack.blit(RenderType::guiTextured, STAT_SPRITES, progressBarX, progressBarY, 2, 71, harvestWidth, 7, 256, 256);
            matrixStack.blit(RenderType::guiTextured, STAT_SPRITES, progressBarX - 8, progressBarY, 49, 45, 8, 8, 256, 256);

            progressBarY += 10;
            matrixStack.blit(RenderType::guiTextured, STAT_SPRITES, progressBarX, progressBarY, 2, 55, progressBarSize, 7, 256, 256);
            matrixStack.blit(RenderType::guiTextured, STAT_SPRITES, progressBarX, progressBarY, 2, 79, animalWidth, 7, 256, 256);
            matrixStack.blit(RenderType::guiTextured, STAT_SPRITES, progressBarX - 8, progressBarY, 58, 45, 8, 8, 256, 256);

        }
    }

    public void drawToolSprite(GuiGraphics matrixStack, int x, int y, EnumTools type) {
        drawSprite(matrixStack, x, y, type.getSpriteX(), type.getSpriteY(), "");
        int progressBarSize = 150;
        int k = (width - imageWidth) / 2;
        int l = (height - imageHeight) / 2;
        RenderSystem.setShaderTexture(0, this.STAT_SPRITES);
        if(player != null) {
            PlayerStats stats = player.getData(HMDataAttachments.PLAYER_STATS);
            int copperWidth = (int)stats.getCopperXP(type);
            int silverWidth = (int)stats.getSilverXP(type);
            int goldWidth = (int)stats.getGoldXP(type);
            int mythrilWidth = (int)stats.getMythrilXP(type);

            int progressBarX = k + x + 25, progressBarY = l + y;
            matrixStack.blit(RenderType::guiTextured, STAT_SPRITES, progressBarX, progressBarY, 2, 2, progressBarSize, 7, 256, 256);
            matrixStack.blit(RenderType::guiTextured, STAT_SPRITES, progressBarX, progressBarY, 2, 11, copperWidth, 7, 256, 256);
            matrixStack.blit(RenderType::guiTextured, STAT_SPRITES, progressBarX, progressBarY, 2, 20, silverWidth, 7, 256, 256);
            matrixStack.blit(RenderType::guiTextured, STAT_SPRITES, progressBarX, progressBarY, 2, 28, goldWidth, 7, 256, 256);
            matrixStack.blit(RenderType::guiTextured, STAT_SPRITES, progressBarX, progressBarY, 2, 36, mythrilWidth, 7, 256, 256);

            progressBarY += 8;
            if(copperWidth >= 150) {
                matrixStack.blit(RenderType::guiTextured, STAT_SPRITES, progressBarX, progressBarY, 2, 45, 8, 8, 256, 256);
            }

            if(silverWidth >= 150) {
                matrixStack.blit(RenderType::guiTextured, STAT_SPRITES, progressBarX + 9, progressBarY, 11, 45, 8, 8, 256, 256);
            }

            if(goldWidth >= 150) {
                matrixStack.blit(RenderType::guiTextured, STAT_SPRITES, progressBarX + 18, progressBarY, 20, 45, 8, 8, 256, 256);
            }
        }
    }

    @Override
    protected void containerTick() {
        super.containerTick();
        this.nextButton.active = pageNumber < 2;
        this.previousButton.active = pageNumber > 0;
    }

    private void updateButtonVisibility() {
        this.nextButton.visible = true;
        this.previousButton.visible = true;
        this.nextButton.active = pageNumber != 2;
        this.previousButton.active = pageNumber != 0;
    }

    protected void flipPage(boolean forward) {
        if(forward) this.pageNumber++;
        else this.pageNumber--;
        this.updateButtonVisibility();
    }
}