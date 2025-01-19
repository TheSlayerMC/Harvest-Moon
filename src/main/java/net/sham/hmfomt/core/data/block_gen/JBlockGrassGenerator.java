package net.sham.hmfomt.core.data.block_gen;

import net.sham.hmfomt.core.HMBlocks;
import net.sham.hmfomt.core.HarvestMoon;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JBlockGrassGenerator extends BasicFileGenerator {

    protected BufferedWriter blockModelWriter, blockstateWriter, itemModelWriter;

    public void generate() {
        for(String name : HMBlocks.grassBlockName) {
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
    }

    public void getBlockItem(String modID, String name) {
        writeToItemModelFile("{");
        writeToItemModelFile("  \"parent\": \"" + modID + ":block/" + name + "\"");
        writeToItemModelFile("}");
    }

    public void getBlockModel(String modID, String name) {
        writeToBlockModelFile("{");
        writeToBlockModelFile("  \"parent\": \"minecraft:block/cube\",");
        writeToBlockModelFile("  \"textures\": {");
        writeToBlockModelFile("    \"up\": \"" + modID + ":" + "block/" + name + "_top\",");
        writeToBlockModelFile("    \"north\": \"" + modID + ":" + "block/" + name + "_side\",");
        writeToBlockModelFile("    \"east\": \"" + modID + ":" + "block/" + name + "_side\",");
        writeToBlockModelFile("    \"south\": \"" + modID + ":" + "block/" + name + "_side\",");
        writeToBlockModelFile("    \"west\": \"" + modID + ":" + "block/" + name + "_side\",");
        writeToBlockModelFile("    \"down\": \"" + modID + ":" + "block/" + name + "_bottom\",");
        writeToBlockModelFile("    \"particle\": \"" + modID + ":" + "block/" + name + "_bottom\"");
        writeToBlockModelFile("  }");
        writeToBlockModelFile("}");

    }

    public void getBlockstate(String modID, String name) {
        writeToBlockstateFile("{");
        writeToBlockstateFile("  \"variants\": {");
        writeToBlockstateFile("    \"\": [");
        writeToBlockstateFile("      {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "\"");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("      {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "\",");
        writeToBlockstateFile("         \"y\": 90");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("      {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "\",");
        writeToBlockstateFile("         \"y\": 180");
        writeToBlockstateFile("      },");
        writeToBlockstateFile("      {");
        writeToBlockstateFile("        \"model\": \"" + modID + ":" + "block/" + name + "\",");
        writeToBlockstateFile("         \"y\": 270");
        writeToBlockstateFile("      }");
        writeToBlockstateFile("    ]");
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