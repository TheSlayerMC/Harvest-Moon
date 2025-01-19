package net.sham.hmfomt.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.Consumable;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.sham.hmfomt.common.item.*;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.Supplier;

public class HMItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(HarvestMoon.MOD_ID);
    public static final DeferredRegister.Items BLOCK_ITEMS = DeferredRegister.createItems(HarvestMoon.MOD_ID);

    public static final ArrayList<String> itemName = new ArrayList<>();
    public static final ArrayList<String> langName = new ArrayList<>();
    public static final ArrayList<String> modelName = new ArrayList<>();
    public static final ArrayList<String> modelLangName = new ArrayList<>();

    public static final DeferredItem<Item> WATERING_CAN = registerNormalItem("watering_can", "Watering Can", WateringCanItem::new);
    public static final DeferredItem<Item> HAMMER = registerNormalItem("hammer", "Hammer", HammerItem::new);
    public static final DeferredItem<Item> HOE = registerNormalItem("hoe", "Hoe", HoeItem::new);
    public static final DeferredItem<Item> SICKLE = registerNormalItem("sickle", "Sickle", SickleItem::new);

    public static final DeferredItem<Item> BREAD = registerNormalItem("bread", "Bread", HMItem::new);
    public static final DeferredItem<Item> RICE_BALLS = registerNormalItem("rice_balls", "Rice Balls", HMItem::new);
    public static final DeferredItem<Item> CURRY_POWDER = registerNormalItem("curry_powder", "Curry Powder", HMItem::new);
    public static final DeferredItem<Item> FLOUR = registerNormalItem("flour", "Flour", HMItem::new);
    public static final DeferredItem<Item> OIL = registerNormalItem("oil", "Oil", HMItem::new);
    public static final DeferredItem<Item> CHOCOLATE = registerNormalItem("chocolate", "Chocolate", HMItem::new);
    public static final DeferredItem<Item> DUMPLING_POWDER = registerNormalItem("dumpling_powder", "Dumpling Powder", HMItem::new);

    public static final DeferredItem<Item> TURNIP_SEEDS = registerNormalItem("turnip_seeds", "Turnip Seeds", HMItem::new);
    public static final DeferredItem<Item> POTATO_SEEDS = registerNormalItem("potato_seeds", "Potato Seeds", HMItem::new);
    public static final DeferredItem<Item> CUCUMBER_SEEDS = registerNormalItem("cucumber_seeds", "Cucumber Seeds", HMItem::new);
    public static final DeferredItem<Item> GRASS_SEEDS = registerNormalItem("grass_seeds", "Grass Seeds", HMItem::new);
    public static final DeferredItem<Item> CABBAGE_SEEDS = registerNormalItem("cabbage_seeds", "Cabbage Seeds", HMItem::new);
    public static final DeferredItem<Item> PINEAPPLE_SEEDS = registerNormalItem("pineapple_seeds", "Pineapple Seeds", HMItem::new);
    public static final DeferredItem<Item> GREEN_PEPPER_SEEDS = registerNormalItem("green_pepper_seeds", "Green Pepper Seeds", HMItem::new);


    public static Item.Properties itemProps(String name) {
        return new Item.Properties().setId(ResourceKey.create(Registries.ITEM, HarvestMoon.rl(name)));
    }

    public static void registerBlockItem(String name, Supplier<Item> item) {
        BLOCK_ITEMS.register(name, item);
    }

    private static DeferredItem<Item> registerNormalItem(String name, String translatedName, Function<Item.Properties, ? extends Item> item) {
        return register(name, translatedName, item, ItemType.ITEM);
    }

    public static DeferredItem<Item> register(String name, String translatedName, Function<Item.Properties, ? extends Item> item, ItemType type) {
        if(type == ItemType.ITEM) {
            itemName.add(name);
            langName.add(translatedName);
        }
        if(type == ItemType.MODEL) {
            modelName.add(name);
            modelLangName.add(translatedName);
        }
        return register(name, item);
    }

    private static DeferredItem<Item> registerFoodItem(String name, String translatedName, boolean foil, FoodProperties props) {
        return register(name, translatedName, (p) -> new HMItem(HMItems.foodProps(name, props)), ItemType.ITEM);
    }

    private static Item.Properties foodProps(String name, FoodProperties props, Consumable con) {
        return new Item.Properties().food(props, con).setId(ResourceKey.create(Registries.ITEM, HarvestMoon.rl(name)));
    }

    private static Item.Properties foodProps(String name, FoodProperties props) {
        return new Item.Properties().food(props).setId(ResourceKey.create(Registries.ITEM, HarvestMoon.rl(name)));
    }

    private static DeferredItem<Item> register(String name, Function<Item.Properties, ? extends Item> item) {
        return ITEMS.registerItem(name, item, itemProps(name));
    }

    public enum ItemType {
        ITEM,
        MODEL
    }
}
