package net.sham.hmfomt.common.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class HMItem extends Item {

    public HMItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext pContext, List<Component> tooltip, TooltipFlag pTooltipFlag) {
        //addItemDesc(HMItems.CHERRY_CANDY_CANE.get(), tooltip, "jitl.item.desc.regen");
    }

    public void addItemDesc(Item item, List<Component> tooltip, String descLoc) {
        if(this == item) {
            tooltip.add((Component.translatable(descLoc)));
        }
    }
}
