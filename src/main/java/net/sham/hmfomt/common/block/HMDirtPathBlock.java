package net.sham.hmfomt.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class HMDirtPathBlock extends Block {

    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D);

    public HMDirtPathBlock(BlockBehaviour.Properties p) {
        super(p);
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState p_153159_) {
        return true;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext b) {

        return !this.defaultBlockState().canSurvive(b.getLevel(), b.getClickedPos()) ? Block.pushEntitiesUp(this.defaultBlockState(), getDirt().defaultBlockState(), b.getLevel(), b.getClickedPos()) : super.getStateForPlacement(b);
    }

    private Block getDirt() {
        Block dirt = Blocks.DIRT;
        return dirt;
    }

    @Override
    public BlockState updateShape(BlockState stateIn, LevelReader reader, ScheduledTickAccess tick, BlockPos currentPos, Direction dir, BlockPos facingPos, BlockState state, RandomSource source) {
        if(dir == Direction.UP && !stateIn.canSurvive(reader, currentPos)) {
            tick.scheduleTick(currentPos, this, 1);
        }
        return super.updateShape(stateIn, reader, tick,currentPos, dir, facingPos, state, source);
    }

    @Override
    public void tick(BlockState s, ServerLevel l, BlockPos p, RandomSource r) {
        l.setBlockAndUpdate(p, pushEntitiesUp(s, getDirt().defaultBlockState(), l, p));
    }

    @Override
    public boolean canSurvive(BlockState s, LevelReader l, BlockPos p) {
        BlockState blockstate = l.getBlockState(p.above());
        return !blockstate.isSolid() || blockstate.getBlock() instanceof FenceGateBlock;
    }

    @Override
    public VoxelShape getShape(BlockState s, BlockGetter b, BlockPos p, CollisionContext c) {
        return SHAPE;
    }

    @Override
    protected boolean isPathfindable(BlockState pState, PathComputationType pPathComputationType) {
        return false;
    }
}