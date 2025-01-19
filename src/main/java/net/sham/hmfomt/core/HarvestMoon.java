package net.sham.hmfomt.core;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.sham.hmfomt.client.event.KeyUsedEvent;
import net.sham.hmfomt.core.data.Config;
import net.sham.hmfomt.core.data.HMNetworkRegistry;
import net.sham.hmfomt.core.data.JItemGenerator;
import net.sham.hmfomt.core.data.LangRegistry;
import net.sham.hmfomt.core.data.block_gen.*;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(HarvestMoon.MOD_ID)
public class HarvestMoon {
    public static final String MOD_ID = "hmfomt", MOD_VERSION = "1.0.0", MOD_NAME = "Harvest Moon - Friends of Mineral Town";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final boolean DEV_MODE = true;

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> HM_TAB = CREATIVE_MODE_TABS.register("hm_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.hmfomt"))
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> HMItems.WATERING_CAN.get().getDefaultInstance()).build());

    public HarvestMoon(IEventBus modEventBus, ModContainer modContainer) {

        HMItems.ITEMS.register(modEventBus);
        HMBlocks.BLOCKS.register(modEventBus);
        HMItems.BLOCK_ITEMS.register(modEventBus);
        HMDataAttachments.REGISTRY.register(modEventBus);
        HMNetworkRegistry.init(modEventBus);
        HMContainers.REGISTRY.register(modEventBus);

        if(DEV_MODE) {
            new LangRegistry().generate();
            new JBlockGenerator().generate();
            new JBlockChestGenerator().generate();
            new JBlockCrossGenerator().generate();
            new JBlockDoorGenerator().generate();
            new JBlockFarmGenerator().generate();
            new JBlockGrassGenerator().generate();
            new JBlockPathGenerator().generate();
            new JBlockSlabGenerator().generate();
            new JBlockStairsGenerator().generate();
            new JItemGenerator().generate();
        }

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        CREATIVE_MODE_TABS.register(modEventBus);
        NeoForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::registerTabs);
        modContainer.registerConfig(ModConfig.Type.CLIENT, Config.SPEC, "harvest-moon.toml");
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void clientSetup(final FMLClientSetupEvent event) {
        NeoForge.EVENT_BUS.register(KeyUsedEvent.class);
    }

    public void registerTabs(BuildCreativeModeTabContentsEvent event){
        if(event.getTab() == HM_TAB.get()) {
            for(DeferredHolder<Block, ? extends Block> block : HMBlocks.BLOCKS.getEntries()){
                    event.accept(block.get());
            }
            for(DeferredHolder<Item, ? extends Item> item : HMItems.ITEMS.getEntries()){
                event.accept(item.get());
            }
        };
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    public static ResourceLocation rl(String r) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, r);
    }
}
