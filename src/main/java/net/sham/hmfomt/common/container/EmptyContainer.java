package net.sham.hmfomt.common.container;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.IContainerFactory;
import net.sham.hmfomt.core.HMContainers;

public class EmptyContainer extends AbstractContainerMenu implements IContainerFactory<EmptyContainer> {

    public EmptyContainer() {
        super(HMContainers.EMPTY_CONTAINER.get(), 200);
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        return null;
    }

    @Override
    public boolean stillValid(Player playerIn) {
        return false;
    }
    
    @Override
    public EmptyContainer create(int windowId, Inventory inv, RegistryFriendlyByteBuf data) {
        try {
            return new EmptyContainer();
        } catch (IllegalArgumentException iae) {

        }
        return null;
    }
}
