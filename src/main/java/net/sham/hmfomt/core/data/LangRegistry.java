package net.sham.hmfomt.core.data;

import net.sham.hmfomt.core.HMBlocks;
import net.sham.hmfomt.core.HMItems;

import java.io.*;
import java.util.ArrayList;

public class LangRegistry {

    protected BufferedWriter langWriter;

    public void generate() {
        String langDir = "../src/main/resources/assets/hmfomt/lang/en_us.json";

        File en_us = new File(langDir);
        
        ArrayList<String> blockList = new ArrayList<>(HMBlocks.normalBlockName);
        ArrayList<String> crossList = new ArrayList<>(HMBlocks.crossBlockName);
        ArrayList<String> logList = new ArrayList<>(HMBlocks.logBlockName);
        ArrayList<String> doorList = new ArrayList<>(HMBlocks.doorBlockName);
        ArrayList<String> trapDoorList = new ArrayList<>(HMBlocks.trapDoorBlockName);
        ArrayList<String> stairList = new ArrayList<>(HMBlocks.stairsBlockName);
        ArrayList<String> slabList = new ArrayList<>(HMBlocks.slabBlockName);
        ArrayList<String> gateList = new ArrayList<>(HMBlocks.gateBlockName);
        ArrayList<String> fenceList = new ArrayList<>(HMBlocks.fenceBlockName);
        ArrayList<String> modelBlockList = new ArrayList<>(HMBlocks.modelBlockName);
        ArrayList<String> chestBlockList = new ArrayList<>(HMBlocks.chestBlockName);
        ArrayList<String> pathBlockList = new ArrayList<>(HMBlocks.pathBlockName);
        ArrayList<String> grassBlockList = new ArrayList<>(HMBlocks.grassBlockName);
        ArrayList<String> farmBlockList = new ArrayList<>(HMBlocks.farmlandBlockName);
        ArrayList<String> cropBlockList = new ArrayList<>(HMBlocks.cropBlockName);

        ArrayList<String> itemList = new ArrayList<>(HMItems.itemName);
        ArrayList<String> modelItemList = new ArrayList<>(HMItems.modelName);

        try {
            if(en_us.exists()) en_us.delete();
            en_us.createNewFile();
            langWriter = new BufferedWriter(new FileWriter(en_us));
        } catch (IOException e) {
            e.printStackTrace();
        }
        writeToFile("{");
        /* --------------------- Add manual lines --------------------- */

        writeToFile("\"itemGroup.hmfomt\" : \"Harvest Moon - FOMT\",");

        writeToFile("\"hmfomt.message.no_internet\" : \"Update checker failed, Please check your internet connection.\",");
        writeToFile("\"hmfomt.message.thank_you\" : \"Thank you %s, for downloading and playing!\",");
        writeToFile("\"hmfomt.message.update_available\" : \"Update available! Latest version: %s\",");
        writeToFile("\"hmfomt.message.current_version\" : \"Current Version: %s\",");
        writeToFile("\"hmfomt.message.up_to_date\" : \"Your version is up to date. Enjoy!\",");

        /* --------------------- Finish manual lines --------------------- */
        
        for(int i = 0; i < blockList.size(); i++)
            writeToFile("\"block.hmfomt." + blockList.get(i) + "\": \"" + HMBlocks.normalLangName.get(i) + "\"" + ",");
       
        for(int i = 0; i < crossList.size(); i++)
            writeToFile("\"block.hmfomt." + crossList.get(i) + "\": \"" + HMBlocks.crossLangName.get(i) + "\"" + ",");

        for(int i = 0; i < grassBlockList.size(); i++)
            writeToFile("\"block.hmfomt." + grassBlockList.get(i) + "\": \"" + HMBlocks.grassLangName.get(i) + "\"" + ",");

        for(int i = 0; i < chestBlockList.size(); i++)
            writeToFile("\"block.hmfomt." + chestBlockList.get(i) + "\": \"" + HMBlocks.chestLangName.get(i) + "\"" + ",");
        
        for(int i = 0; i < doorList.size(); i++)
            writeToFile("\"block.hmfomt." + doorList.get(i) + "\": \"" + HMBlocks.doorLangName.get(i) + "\"" + ",");

        for(int i = 0; i < trapDoorList.size(); i++)
            writeToFile("\"block.hmfomt." + trapDoorList.get(i) + "\": \"" + HMBlocks.trapDoorLangName.get(i) + "\"" + ",");

        for(int i = 0; i < stairList.size(); i++)
            writeToFile("\"block.hmfomt." + stairList.get(i) + "\": \"" + HMBlocks.stairsLangName.get(i) + "\"" + ",");

        for(int i = 0; i < slabList.size(); i++)
            writeToFile("\"block.hmfomt." + slabList.get(i) + "\": \"" + HMBlocks.slabLangName.get(i) + "\"" + ",");
        
        for(int i = 0; i < fenceList.size(); i++)
            writeToFile("\"block.hmfomt." + fenceList.get(i) + "\": \"" + HMBlocks.fenceLangName.get(i) + "\"" + ",");

        for(int i = 0; i < gateList.size(); i++)
            writeToFile("\"block.hmfomt." + gateList.get(i) + "\": \"" + HMBlocks.gateLangName.get(i) + "\"" + ",");

        for(int i = 0; i < logList.size(); i++)
            writeToFile("\"block.jitl." + logList.get(i) + "\": \"" + HMBlocks.logLangName.get(i) + "\"" + ",");

        for(int i = 0; i < modelBlockList.size(); i++)
            writeToFile("\"block.hmfomt." + modelBlockList.get(i) + "\": \"" + HMBlocks.modelLangName.get(i) + "\"" + ",");
        
        for(int i = 0; i < pathBlockList.size(); i++)
            writeToFile("\"block.hmfomt." + pathBlockList.get(i) + "\": \"" + HMBlocks.pathLangName.get(i) + "\"" + ",");

        for(int i = 0; i < farmBlockList.size(); i++)
            writeToFile("\"block.hmfomt." + farmBlockList.get(i) + "\": \"" + HMBlocks.farmlandLangName.get(i) + "\"" + ",");

        for(int i = 0; i < cropBlockList.size(); i++)
            writeToFile("\"block.hmfomt." + cropBlockList.get(i) + "\": \"" + HMBlocks.cropLangName.get(i) + "\"" + ",");
        
        for(int i = 0; i < modelItemList.size(); i++)
            writeToFile("\"item.hmfomt." + modelItemList.get(i) + "\": \"" + HMItems.modelLangName.get(i) + "\"" + ",");

        int j = 0;
        for(int i = 0; i < itemList.size(); i++) {
            j++;
            String end = j == itemList.size() ? "" : ",";
            writeToFile("\"item.hmfomt." + itemList.get(i) + "\": \"" + HMItems.langName.get(i) + "\"" + end);
        }

        writeToFile("}");

        writerInit();
    }

    public void writerInit() {
        try {
            langWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToFile(String text){
        try {
            langWriter.write(text + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}