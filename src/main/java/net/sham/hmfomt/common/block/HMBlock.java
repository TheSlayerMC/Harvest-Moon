package net.sham.hmfomt.common.block;

import net.minecraft.world.level.block.Block;

public class HMBlock extends Block {

    private final Properties props;

    public HMBlock(Properties properties) {
        super(properties);
        this.props = properties;
    }
}
