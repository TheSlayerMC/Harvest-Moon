package net.sham.hmfomt.core.data.block_gen;

import net.sham.hmfomt.core.HMBlocks;
import net.sham.hmfomt.core.HarvestMoon;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JBlockModelGenerator extends BasicFileGenerator {

    protected BufferedWriter blockstateWriter, itemModelWriter;

    public void generate() {
        for(String name : HMBlocks.modelBlockName) {
            String itemModelDir = "../src/main/resources/assets/hmfomt/models/item/" + name + ".json";
            String blockstateDir = "../src/main/resources/assets/hmfomt/blockstates/" + name + ".json";

            File itemModel = new File(itemModelDir);
            File blockstateModel = new File(blockstateDir);

            try {
                if (itemModel.exists()) itemModel.delete();
                itemModel.createNewFile();
                itemModelWriter = new BufferedWriter(new FileWriter(itemModel));

                if (blockstateModel.exists()) blockstateModel.delete();
                blockstateModel.createNewFile();
                blockstateWriter = new BufferedWriter(new FileWriter(blockstateModel));
            } catch (IOException e) {
                e.printStackTrace();
            }

            getBlockstate(HarvestMoon.MOD_ID, name);
            getBlockItem(HarvestMoon.MOD_ID, name);
            generateBasicFile(true, name);

            writerInit();
        }
    }

    public void getBlockItem(String modID, String name) {
        writeToItemModelFile("{");
        writeToItemModelFile("  \"parent\": \"minecraft:item/generated\",");
        writeToItemModelFile("  \"textures\": {");
        writeToItemModelFile("    \"layer0\": \"" + modID + ":" + "item/" + name + "\"");
        writeToItemModelFile("  }");
        writeToItemModelFile("}");
    }

    public void getBlockstate(String modID, String name) {
        writeToBlockstateFile("{");
        writeToBlockstateFile("  \"variants\": {");
        writeToBlockstateFile("  \"\": {");
        writeToBlockstateFile("      \"model\": \"" + modID + ":" + "block/" + name + "\"");
        writeToBlockstateFile("     }");
        writeToBlockstateFile("  }");
        writeToBlockstateFile("}");
    }

    public void writerInit() {
        try {
            itemModelWriter.close();
            blockstateWriter.close();
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

    public void writeToBlockstateFile(String text){
        try {
            blockstateWriter.write(text + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}