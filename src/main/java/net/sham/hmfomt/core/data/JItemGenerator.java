package net.sham.hmfomt.core.data;

import net.sham.hmfomt.core.HMItems;
import net.sham.hmfomt.core.HarvestMoon;
import net.sham.hmfomt.core.data.block_gen.BasicFileGenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JItemGenerator extends BasicFileGenerator {

    protected BufferedWriter itemModelWriter;

    public void generate(){
        for(String name : HMItems.itemName) {
            String itemModelDir = "../src/main/resources/assets/hmfomt/models/item/" + name + ".json";

            File itemModel = new File(itemModelDir);
            itemModel.mkdirs();
            try {
                if(itemModel.exists()) itemModel.delete();
                itemModel.createNewFile();
                itemModelWriter = new BufferedWriter(new FileWriter(itemModel));
            } catch (IOException e) {
                e.printStackTrace();
            }

            getNormalItem(HarvestMoon.MOD_ID, name, HMItems.ItemType.ITEM);
            itemModelInit();
        }
//
//        for(int i = 0; i < JEntities.entityName.size(); i++) {
//            String name = JEntities.entityName.get(i) + "_spawn_egg";
//            String itemModelDir = "../src/main/resources/assets/jitl/models/item/" + name + ".json";
//
//            File itemModel = new File(itemModelDir);
//            itemModel.mkdirs();
//            try {
//                if(itemModel.exists()) itemModel.delete();
//                itemModel.createNewFile();
//                itemModelWriter = new BufferedWriter(new FileWriter(itemModel));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            getSpawnEggItem();
//            generateSpawnEggFile(name, JEntities.COLOUR1.get(i), JEntities.COLOUR2.get(i));
//            itemModelInit();
//        }

        //generateBasicFile(true, "broken_okoloo_club"); use for model

    }

    public void itemModelInit() {
        try {
            itemModelWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToItemModelFile(String text){
        try {
            itemModelWriter.write(text + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getNormalItem(String modID, String name, HMItems.ItemType type) {
        writeToItemModelFile("{");
        writeToItemModelFile("  \"parent\": \"minecraft:item/generated\",");
        writeToItemModelFile("  \"textures\": {");
        writeToItemModelFile("    \"layer0\": \"" + modID + ":" + "item/" + name + "\"");
        writeToItemModelFile("  }");
        writeToItemModelFile("}");
        generateBasicFile(true, name);
    }

    public void getSpawnEggItem() {
        writeToItemModelFile("{");
        writeToItemModelFile("  \"parent\": \"minecraft:item/template_spawn_egg\"");
        writeToItemModelFile("}");
    }
}