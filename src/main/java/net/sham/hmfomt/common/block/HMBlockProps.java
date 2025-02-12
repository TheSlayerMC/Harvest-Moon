package net.sham.hmfomt.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.MapColor;

import java.util.function.ToIntFunction;

public class HMBlockProps {

    public static BlockBehaviour.Properties STONE = BlockBehaviour.Properties.of().mapColor(MapColor.STONE)
            .strength(-1F, 3600000.0F)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops();

    public static BlockBehaviour.Properties MODELED_STONE = BlockBehaviour.Properties.of().mapColor(MapColor.STONE)
            .strength(-1F, 3600000.0F)
            .sound(SoundType.STONE)
            .isViewBlocking(HMBlockProps::never)
            .isSuffocating(HMBlockProps::never)
            .isValidSpawn(HMBlockProps::never)
            .noOcclusion()
            .requiresCorrectToolForDrops();

    public static BlockBehaviour.Properties WOOL = BlockBehaviour.Properties.of().mapColor(MapColor.WOOL)
            .strength(-1F, 3600000.0F)
            .sound(SoundType.WOOL)
            .requiresCorrectToolForDrops();

    public static BlockBehaviour.Properties GLASS = BlockBehaviour.Properties.of()
            .strength(-1F, 3600000.0F)
            .sound(SoundType.GLASS)
            .noOcclusion()
            .isViewBlocking(HMBlockProps::never)
            .isSuffocating(HMBlockProps::never)
            .isValidSpawn(HMBlockProps::never)
            .isRedstoneConductor(HMBlockProps::never)
            .requiresCorrectToolForDrops();

    public static BlockBehaviour.Properties MUSHROOM_BLOCK = BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_BROWN)
            .strength(-1F, 3600000.0F)
            .sound(SoundType.WOOD)
            .requiresCorrectToolForDrops();

    public static BlockBehaviour.Properties CHEST = BlockBehaviour.Properties.of()
            .strength(-1F, 3600000.0F)
            .sound(SoundType.STONE)
            .noOcclusion();

    public static BlockBehaviour.Properties GLOW_BLOCK = BlockBehaviour.Properties.of()
            .strength(-1F, 3600000.0F)
            .lightLevel((level) -> 15)
            .sound(SoundType.GLASS);

    public static BlockBehaviour.Properties DIRT = BlockBehaviour.Properties.of()
            .strength(-1F, 3600000.0F)
            .sound(SoundType.GRAVEL);

    public static BlockBehaviour.Properties FARMLAND = BlockBehaviour.Properties.of()
            .randomTicks()
            .strength(-1F, 3600000.0F)
            .sound(SoundType.GRAVEL)
            .isViewBlocking((state, getter, pos) -> true)
            .isSuffocating((state, getter, pos) -> true);

    public static final BlockBehaviour.Properties PATH = BlockBehaviour.Properties.of()
            .strength(-1F, 3600000.0F)
            .sound(SoundType.GRASS)
            .isViewBlocking((state, getter, pos) -> true)
            .isSuffocating((state, getter, pos) -> true);

    public static BlockBehaviour.Properties SAND = BlockBehaviour.Properties.of()
            .strength(-1F, 3600000.0F)
            .sound(SoundType.SAND);

    public static BlockBehaviour.Properties LEAVES = BlockBehaviour.Properties.of()
            .strength(-1F, 3600000.0F)
            .sound(SoundType.GRASS)
            .noOcclusion()
            .isValidSpawn(HMBlockProps::never)
            .requiresCorrectToolForDrops();

    public static BlockBehaviour.Properties CROP = BlockBehaviour.Properties.of()
            .sound(SoundType.CROP)
            .strength(-1F, 3600000.0F)
            .noCollission()
            .noOcclusion()
            .randomTicks();

    public static BlockBehaviour.Properties LUMINESCENT_LEAVES = BlockBehaviour.Properties.of()
            .strength(-1F, 3600000.0F)
            .sound(SoundType.GRASS)
            .noOcclusion()
            .lightLevel((state) -> 4)
            .requiresCorrectToolForDrops();

    public static BlockBehaviour.Properties ICE = BlockBehaviour.Properties.of()
            .strength(-1F, 3600000.0F)
            .sound(SoundType.GLASS)
            .noOcclusion()
            .requiresCorrectToolForDrops();

    public static BlockBehaviour.Properties PLANT = BlockBehaviour.Properties.of()
            .strength(-1F, 3600000.0F)
            .sound(SoundType.GRASS)
            .noCollission()
            .noOcclusion()
            .offsetType(BlockBehaviour.OffsetType.XZ);

    public static BlockBehaviour.Properties FLOWER = BlockBehaviour.Properties.of()
            .instabreak()
            .sound(SoundType.GRASS)
            .noCollission()
            .noOcclusion()
            .offsetType(BlockBehaviour.OffsetType.XZ);

    public static BlockBehaviour.Properties GRASS = BlockBehaviour.Properties.of()
            .randomTicks()
            .strength(-1F, 3600000.0F)
            .strength(0.75F)
            .sound(SoundType.GRASS);

    public static BlockBehaviour.Properties WOOD = BlockBehaviour.Properties.of()
            .strength(-1F, 3600000.0F)
            .sound(SoundType.WOOD);

    public static BlockBehaviour.Properties MODELED_WOOD = BlockBehaviour.Properties.of()
            .strength(-1F, 3600000.0F)
            .sound(SoundType.WOOD)
            .isViewBlocking(HMBlockProps::never)
            .isSuffocating(HMBlockProps::never)
            .isValidSpawn(HMBlockProps::never)
            .noOcclusion();

    public static BlockBehaviour.Properties LADDER = BlockBehaviour.Properties.of()
            .strength(-1F, 3600000.0F)
            .noOcclusion()
            .dynamicShape()
            .sound(SoundType.WOOD);

    public static BlockBehaviour.Properties BUTTON = BlockBehaviour.Properties.of()
            .strength(-1F, 3600000.0F)
            .noOcclusion()
            .noCollission()
            .sound(SoundType.WOOD);

    public static BlockBehaviour.Properties DOOR = BlockBehaviour.Properties.of()
            .strength(-1F, 3600000.0F)
            .noOcclusion()
            .dynamicShape()
            .sound(SoundType.WOOD);

    public static final BlockBehaviour.Properties FURNACE = BlockBehaviour.Properties.of()
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()
            .lightLevel(litBlockEmission(13))
            .strength(-1F, 3600000.0F);

    public static String getTextureFromName(String name) {
        String texName = "";
        if(name.contains("euca_brown")) {
            texName = "euca_brown_planks";
        }
        return texName;
    }

    private static ToIntFunction<BlockState> litBlockEmission(int lightValue) {
        return (state) -> state.getValue(BlockStateProperties.LIT) ? lightValue : 0;
    }

    private static boolean never(BlockState s, BlockGetter g, BlockPos p) {
        return false;
    }

    private static Boolean never(BlockState s, BlockGetter g, BlockPos p, EntityType<?> e) {
        return false;
    }

}