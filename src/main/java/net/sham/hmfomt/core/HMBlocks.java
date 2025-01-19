package net.sham.hmfomt.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.FuelValues;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.sham.hmfomt.common.block.*;
import net.sham.hmfomt.core.data.block_gen.JBlockCropGenerator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.function.Function;

public class HMBlocks {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(HarvestMoon.MOD_ID);

    public static final ArrayList<String> normalBlockName = new ArrayList<>();
    public static final ArrayList<String> normalLangName = new ArrayList<>();
    public static final ArrayList<String> modelBlockName = new ArrayList<>();
    public static final ArrayList<String> modelLangName = new ArrayList<>();
    public static final ArrayList<String> logBlockName = new ArrayList<>();
    public static final ArrayList<String> logLangName = new ArrayList<>();
    public static final ArrayList<String> doorBlockName = new ArrayList<>();
    public static final ArrayList<String> doorLangName = new ArrayList<>();
    public static final ArrayList<String> trapDoorBlockName = new ArrayList<>();
    public static final ArrayList<String> trapDoorLangName = new ArrayList<>();
    public static final ArrayList<String> stairsBlockName = new ArrayList<>();
    public static final ArrayList<String> stairsLangName = new ArrayList<>();
    public static final ArrayList<String> slabBlockName = new ArrayList<>();
    public static final ArrayList<String> slabLangName = new ArrayList<>();
    public static final ArrayList<String> crossBlockName = new ArrayList<>();
    public static final ArrayList<String> crossLangName = new ArrayList<>();
    public static final ArrayList<String> gateBlockName = new ArrayList<>();
    public static final ArrayList<String> gateLangName = new ArrayList<>();
    public static final ArrayList<String> fenceBlockName = new ArrayList<>();
    public static final ArrayList<String> fenceLangName = new ArrayList<>();
    public static final ArrayList<String> chestBlockName = new ArrayList<>();
    public static final ArrayList<String> chestLangName = new ArrayList<>();
    public static final ArrayList<String> pathBlockName = new ArrayList<>();
    public static final ArrayList<String> pathLangName = new ArrayList<>();
    public static final ArrayList<String> cropBlockName = new ArrayList<>();
    public static final ArrayList<String> cropLangName = new ArrayList<>();
    public static final ArrayList<String> grassBlockName = new ArrayList<>();
    public static final ArrayList<String> grassLangName = new ArrayList<>();
    public static final ArrayList<String> farmlandBlockName = new ArrayList<>();
    public static final ArrayList<String> farmlandLangName = new ArrayList<>();

    public static final DeferredBlock<Block> TOWN_BRICKS = register("town_bricks", "Town Bricks", HMBlockProps.STONE);

    public static final DeferredBlock<Block> BASIC_MINE_ROCK = registerModeledBlock("mine_rock_basic", "Mine Rock", (p) -> new MineRockBlock(p, MineRockBlock.Tier.LOW), HMBlockProps.STONE);
    public static final DeferredBlock<Block> COPPER_MINE_ROCK = registerModeledBlock("mine_rock_copper", "Mine Rock", (p) -> new MineRockBlock(p, MineRockBlock.Tier.LOW), HMBlockProps.STONE);
    public static final DeferredBlock<Block> SILVER_MINE_ROCK = registerModeledBlock("mine_rock_silver", "Mine Rock", (p) -> new MineRockBlock(p, MineRockBlock.Tier.LOW), HMBlockProps.STONE);
    public static final DeferredBlock<Block> GOLD_MINE_ROCK = registerModeledBlock("mine_rock_gold", "Mine Rock", (p) -> new MineRockBlock(p, MineRockBlock.Tier.LOW), HMBlockProps.STONE);
    public static final DeferredBlock<Block> MYTHRIL_MINE_ROCK = registerModeledBlock("mine_rock_mythril", "Mine Rock", (p) -> new MineRockBlock(p, MineRockBlock.Tier.LOW), HMBlockProps.STONE);

    public static final DeferredBlock<Block> BLUE_GRASS = registerCrossBlock("blue_grass", "Blue Grass", TallGrassBlock::new, HMBlockProps.FLOWER);
    public static final DeferredBlock<Block> YELLOW_GRASS = registerCrossBlock("yellow_grass", "Yellow Grass", TallGrassBlock::new, HMBlockProps.FLOWER);
    public static final DeferredBlock<Block> ORANGE_GRASS = registerCrossBlock("orange_grass", "Orange Grass", TallGrassBlock::new, HMBlockProps.FLOWER);
    public static final DeferredBlock<Block> GREEN_GRASS = registerCrossBlock("green_grass", "Green Grass", TallGrassBlock::new, HMBlockProps.FLOWER);
    public static final DeferredBlock<Block> WEEDS = registerCrossBlock("weeds", "Weeds", TallGrassBlock::new, HMBlockProps.FLOWER);

    public static final DeferredBlock<Block> FARMLAND = registerFarmlandBlock("farmland", "Farmland", FarmlandBlock::new, HMBlockProps.FARMLAND);
    public static final DeferredBlock<Block> SOIL = register("soil", "Soil", Block::new, HMBlockProps.DIRT);


    public static DeferredBlock<Block> registerGrassBlock(String name, String translatedName, Function<BlockBehaviour.Properties, ? extends Block> block, BlockBehaviour.Properties props) {
        grassBlockName.add(name);
        grassLangName.add(translatedName);
        DeferredBlock<Block> block1 = BLOCKS.registerBlock(name, block, props);
        HMItems.registerBlockItem(name, () -> new BlockItem(block1.get(), HMItems.itemProps(name).useBlockDescriptionPrefix()));
        return block1;
    }

    public static DeferredBlock<Block> registerGrassBlock(String name, String translatedName, Function<BlockBehaviour.Properties, ? extends Block> block) {
        return registerGrassBlock(name, translatedName, block, HMBlockProps.GRASS);
    }

    public static DeferredBlock<Block> registerChestBlock(String name, String translatedName, Function<BlockBehaviour.Properties, ? extends Block> block, BlockBehaviour.Properties props) {
        chestBlockName.add(name);
        chestLangName.add(translatedName);
        DeferredBlock<Block> block1 = BLOCKS.registerBlock(name, block, props);
        HMItems.registerBlockItem(name, () -> new BlockItem(block1.get(), HMItems.itemProps(name).useBlockDescriptionPrefix()));
        return block1;
    }

    public static DeferredBlock<Block> registerModeledBlock(String name, String translatedName, Function<BlockBehaviour.Properties, ? extends Block> block, BlockBehaviour.Properties props) {
        modelBlockName.add(name);
        modelLangName.add(translatedName);
        DeferredBlock<Block> block1 = BLOCKS.registerBlock(name, block, props);
        HMItems.registerBlockItem(name, () -> new BlockItem(block1.get(), HMItems.itemProps(name).useBlockDescriptionPrefix()));
        return block1;
    }

    public static DeferredBlock<DoorBlock> registerDoor(String name, String translatedName, boolean wood, BlockBehaviour.Properties p) {
        doorBlockName.add(name);
        doorLangName.add(translatedName);
        DeferredBlock<DoorBlock> block1 = BLOCKS.register(name, () -> new DoorBlock(BlockSetType.OAK, p.setId(ResourceKey.create(Registries.BLOCK, HarvestMoon.rl(name)))));
        HMItems.registerBlockItem(name, () -> new BlockItem(block1.get(), HMItems.itemProps(name).useBlockDescriptionPrefix()) {
            @Override
            public int getBurnTime(@NotNull ItemStack itemStack, @Nullable RecipeType<?> recipeType, @NotNull FuelValues fuelValues) {
                return wood ? 300 : 0;
            }
        });
        return block1;
    }

    public static DeferredBlock<TrapDoorBlock> registerTrapDoor(String name, String translatedName, boolean wood, BlockBehaviour.Properties p) {
        trapDoorBlockName.add(name);
        trapDoorLangName.add(translatedName);
        DeferredBlock<TrapDoorBlock> block1 = BLOCKS.register(name, () -> new TrapDoorBlock(BlockSetType.OAK, p.setId(ResourceKey.create(Registries.BLOCK, HarvestMoon.rl(name)))));
        HMItems.registerBlockItem(name, () -> new BlockItem(block1.get(), HMItems.itemProps(name).useBlockDescriptionPrefix()) {
            @Override
            public int getBurnTime(@NotNull ItemStack itemStack, @Nullable RecipeType<?> recipeType, @NotNull FuelValues fuelValues) {
                return wood ? 300 : 0;
            }
        });
        return block1;
    }

    public static DeferredBlock<Block> registerCrossBlock(String name, String translatedName, Function<BlockBehaviour.Properties, ? extends Block> block, BlockBehaviour.Properties props) {
        crossBlockName.add(name);
        crossLangName.add(translatedName);
        DeferredBlock<Block> block1 = BLOCKS.registerBlock(name, block, props);
        HMItems.registerBlockItem(name, () -> new BlockItem(block1.get(), HMItems.itemProps(name).useBlockDescriptionPrefix()));
        return block1;
    }

    public static DeferredBlock<HMFenceBlock> registerFence(String name, String translatedName, boolean wood, BlockBehaviour.Properties p) {
        fenceBlockName.add(name);
        fenceLangName.add(translatedName);
        DeferredBlock<HMFenceBlock> block1 = BLOCKS.register(name, () -> new HMFenceBlock(p.setId(ResourceKey.create(Registries.BLOCK, HarvestMoon.rl(name)))));
        HMItems.registerBlockItem(name, () -> new BlockItem(block1.get(), HMItems.itemProps(name).useBlockDescriptionPrefix()) {
            @Override
            public int getBurnTime(@NotNull ItemStack itemStack, @Nullable RecipeType<?> recipeType, @NotNull FuelValues fuelValues) {
                return wood ? 300 : 0;
            }
        });
        return block1;
    }

    public static DeferredBlock<FenceGateBlock> registerFenceGate(String name, String translatedName, boolean wood, BlockBehaviour.Properties p) {
        gateBlockName.add(name);
        gateLangName.add(translatedName);
        DeferredBlock<FenceGateBlock> block1 = BLOCKS.register(name, () -> new FenceGateBlock(WoodType.OAK, p.setId(ResourceKey.create(Registries.BLOCK, HarvestMoon.rl(name)))));
        HMItems.registerBlockItem(name, () -> new BlockItem(block1.get(), HMItems.itemProps(name).useBlockDescriptionPrefix()) {
            @Override
            public int getBurnTime(@NotNull ItemStack itemStack, @Nullable RecipeType<?> recipeType, @NotNull FuelValues fuelValues) {
                return wood ? 300 : 0;
            }
        });
        return block1;
    }

    public static DeferredBlock<StairBlock> registerStairs(String name, String translatedName, DeferredBlock<Block> plank, boolean wood, BlockBehaviour.Properties p) {
        stairsBlockName.add(name);
        stairsLangName.add(translatedName);
        DeferredBlock<StairBlock> block1 = BLOCKS.register(name, () -> new StairBlock(plank.get().defaultBlockState(), p.setId(ResourceKey.create(Registries.BLOCK, HarvestMoon.rl(name)))));
        HMItems.registerBlockItem(name, () -> new BlockItem(block1.get(), HMItems.itemProps(name).useBlockDescriptionPrefix()) {
            @Override
            public int getBurnTime(@NotNull ItemStack itemStack, @Nullable RecipeType<?> recipeType, @NotNull FuelValues fuelValues) {
                return wood ? 300 : 0;
            }
        });
        return block1;
    }

    public static DeferredBlock<SlabBlock> registerSlab(String name, String translatedName, boolean wood, BlockBehaviour.Properties p) {
        slabBlockName.add(name);
        slabLangName.add(translatedName);
        DeferredBlock<SlabBlock> block1 = BLOCKS.register(name, () -> new SlabBlock(p.setId(ResourceKey.create(Registries.BLOCK, HarvestMoon.rl(name)))));
        HMItems.registerBlockItem(name, () -> new BlockItem(block1.get(), HMItems.itemProps(name).useBlockDescriptionPrefix()) {
            @Override
            public int getBurnTime(@NotNull ItemStack itemStack, @Nullable RecipeType<?> recipeType, @NotNull FuelValues fuelValues) {
                return wood ? 300 : 0;
            }
        });
        return block1;
    }

    public static DeferredBlock<RotatedPillarBlock> addLogBlock(String name, String translatedName) {
        logBlockName.add(name);
        logLangName.add(translatedName);
        DeferredBlock<RotatedPillarBlock> block1 = BLOCKS.registerBlock(name, RotatedPillarBlock::new, HMBlockProps.WOOD);
        HMItems.registerBlockItem(name, () -> new BlockItem(block1.get(), HMItems.itemProps(name).useBlockDescriptionPrefix()) {
            @Override
            public int getBurnTime(@NotNull ItemStack itemStack, @Nullable RecipeType<?> recipeType, @NotNull FuelValues fuelValues) {
                return 300;
            }
        });
        return block1;
    }
    
    public static DeferredBlock<Block> registerCropBlock(String name, String translatedName, int maxStages, Function<BlockBehaviour.Properties, ? extends Block> block, BlockBehaviour.Properties props) {
        cropBlockName.add(name);
        cropLangName.add(translatedName);
        DeferredBlock<Block> block1 = BLOCKS.registerBlock(name, block, props);
        HMItems.registerBlockItem(name, () -> new BlockItem(block1.get(), HMItems.itemProps(name).useBlockDescriptionPrefix()));
        if(HarvestMoon.DEV_MODE)
            new JBlockCropGenerator().generate(name, maxStages);
        return block1;
    }

    public static DeferredBlock<Block> registerPathBlock(String name, String translatedName) {
        pathBlockName.add(name);
        pathLangName.add(translatedName);
        DeferredBlock<Block> block1 = BLOCKS.register(name, () -> new HMDirtPathBlock(HMBlockProps.PATH.setId(ResourceKey.create(Registries.BLOCK, HarvestMoon.rl(name)))));
        HMItems.registerBlockItem(name, () -> new BlockItem(block1.get(), HMItems.itemProps(name).useBlockDescriptionPrefix()));
        return block1;
    }


    public static DeferredBlock<Block> registerFarmlandBlock(String name, String translatedName, Function<BlockBehaviour.Properties, ? extends Block> block, BlockBehaviour.Properties props) {
        farmlandBlockName.add(name);
        farmlandLangName.add(translatedName);
        DeferredBlock<Block> block1 = BLOCKS.registerBlock(name, block, props);
        HMItems.registerBlockItem(name, () -> new BlockItem(block1.get(), HMItems.itemProps(name).useBlockDescriptionPrefix()));
        return block1;
    }
    
    public static DeferredBlock<Block> register(String name, String translatedName, BlockBehaviour.Properties props) {
        normalBlockName.add(name);
        return register(name, translatedName, HMBlock::new, props, false);
    }

    public static DeferredBlock<Block> register(String name, String translatedName, Function<BlockBehaviour.Properties, ? extends Block> block, BlockBehaviour.Properties props) {
        normalBlockName.add(name);
        return register(name, translatedName, block, props, false);
    }

    public static DeferredBlock<Block> register(String name, String translatedName, Function<BlockBehaviour.Properties, ? extends Block> block, BlockBehaviour.Properties props, boolean addName) {
        normalLangName.add(translatedName);
        if(addName)
            normalBlockName.add(name);
        DeferredBlock<Block> block1 = BLOCKS.registerBlock(name, block, props);
        HMItems.registerBlockItem(name, () -> new BlockItem(block1.get(), HMItems.itemProps(name).useBlockDescriptionPrefix()));
        return block1;
    }
}
