package net.sham.hmfomt.common.block;

import net.minecraft.world.level.block.Block;

public class MineRockBlock extends Block {

    private final MineRockBlock.Tier tier;

    public MineRockBlock(Properties p, MineRockBlock.Tier tier) {
        super(p);
        this.tier = tier;
    }

    public enum Tier {
        LOW,
        COPPER,
        SILVER,
        GOLD,
        MYTHRIL
    }
}
