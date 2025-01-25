package net.sham.hmfomt.common.block;

public class MineRockBlock extends ModeledBlock {

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
