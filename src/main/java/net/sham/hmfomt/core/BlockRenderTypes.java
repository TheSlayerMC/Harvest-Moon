package net.sham.hmfomt.core;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.ArrayList;

@EventBusSubscriber(modid = HarvestMoon.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class BlockRenderTypes {

    @SubscribeEvent
    public static void registerRenderTypes(FMLClientSetupEvent event) {
        ArrayList<Block> cutout = new ArrayList<>();
        ArrayList<Block> translucent = new ArrayList<>();

        cutout.add(HMBlocks.GREEN_GRASS.get());
        cutout.add(HMBlocks.BLUE_GRASS.get());
        cutout.add(HMBlocks.YELLOW_GRASS.get());
        cutout.add(HMBlocks.ORANGE_GRASS.get());
        cutout.add(HMBlocks.WEEDS.get());

        for(Block b : cutout) {
            ItemBlockRenderTypes.setRenderLayer(b, RenderType.cutout());
        }
    }
}