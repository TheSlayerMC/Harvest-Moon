package net.sham.hmfomt.core.data.block_gen;

import net.sham.hmfomt.core.HMBlocks;
import net.sham.hmfomt.core.HarvestMoon;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JBlockGenerator extends BasicFileGenerator {

    protected BufferedWriter blockModelWriter, blockstateWriter, itemModelWriter;

    public void generate() {
        for(String name : HMBlocks.normalBlockName) {
            String itemModelDir = "../src/main/resources/assets/hmfomt/models/item/" + name + ".json";
            String blockModelDir = "../src/main/resources/assets/hmfomt/models/block/" + name + ".json";
            String blockstateDir = "../src/main/resources/assets/hmfomt/blockstates/" + name + ".json";

            File itemModel = new File(itemModelDir);
            File blockModel = new File(blockModelDir);
            File blockstateModel = new File(blockstateDir);

            try {
                if (itemModel.exists()) itemModel.delete();
                itemModel.createNewFile();
                itemModelWriter = new BufferedWriter(new FileWriter(itemModel));

                if (blockModel.exists()) blockModel.delete();
                blockModel.createNewFile();
                blockModelWriter = new BufferedWriter(new FileWriter(blockModel));

                if (blockstateModel.exists()) blockstateModel.delete();
                blockstateModel.createNewFile();
                blockstateWriter = new BufferedWriter(new FileWriter(blockstateModel));
            } catch (IOException e) {
                e.printStackTrace();
            }

            getBlockItem(HarvestMoon.MOD_ID, name);
            getBlockModel(HarvestMoon.MOD_ID, name);
            getBlockstate(HarvestMoon.MOD_ID, name);
            generateBasicFile(name);

            writerInit();
        }

        for(String name : HMBlocks.modelBlockName) {
            generateBasicFile(name);
        }
    }

    public void getBlockItem(String modID, String name) {
        writeToItemModelFile("{");
        writeToItemModelFile("  \"parent\": \"" + modID + ":block/" + name + "\"");
        writeToItemModelFile("}");
    }

    public void getBlockModel(String modID, String name) {
        writeToBlockModelFile("{");
        writeToBlockModelFile("  \"parent\": \"minecraft:block/cube_all\",");
        writeToBlockModelFile("  \"textures\": {");
        writeToBlockModelFile("    \"all\": \"" + modID + ":" + "block/" + name + "\"");
        writeToBlockModelFile("  }");
        writeToBlockModelFile("}");
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
            blockModelWriter.close();
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

    public void writeToBlockModelFile(String text){
        try {
            blockModelWriter.write(text + "\n");
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