package net.sham.hmfomt.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.sham.hmfomt.common.block.entity.PlayerBedBlockEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerBedBlock extends BedBlock {

    public static final EnumProperty<BedPart> PART;
    public static final BooleanProperty OCCUPIED;
    protected static final VoxelShape BASE;
    protected static final VoxelShape LEG_NORTH_WEST;
    protected static final VoxelShape LEG_SOUTH_WEST;
    protected static final VoxelShape LEG_NORTH_EAST;
    protected static final VoxelShape LEG_SOUTH_EAST;
    protected static final VoxelShape NORTH_SHAPE;
    protected static final VoxelShape SOUTH_SHAPE;
    protected static final VoxelShape WEST_SHAPE;
    protected static final VoxelShape EAST_SHAPE;

    public PlayerBedBlock(BlockBehaviour.Properties properties) {
        super(DyeColor.BLACK, properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(PART, BedPart.FOOT).setValue(OCCUPIED, false));
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(@NotNull BlockState state, Level lv, @NotNull BlockPos pos, @NotNull Player player, @NotNull BlockHitResult result) {
        if(!lv.isClientSide) {
            if (state.getValue(PART) != BedPart.HEAD) {
                pos = pos.relative(state.getValue(FACING));
                state = lv.getBlockState(pos);
                if (!state.is(this)) {
                    return InteractionResult.CONSUME;
                }
            }
            if (!state.getValue(OCCUPIED)) {
                player.startSleepInBed(pos).ifLeft((p) -> {
                    if (p.getMessage() != null) {
                        player.displayClientMessage(p.getMessage(), true);
                    }
                });
            }
        }
        return InteractionResult.SUCCESS_SERVER;
    }

    @Override
    public void fallOn(@NotNull Level l, @NotNull BlockState s, @NotNull BlockPos p, @NotNull Entity e, float f) {
        super.fallOn(l, s, p, e, f * 0.5F);
    }

    @Override
    public void updateEntityMovementAfterFallOn(@NotNull BlockGetter b, Entity e) {
        if (e.isSuppressingBounce()) {
            super.updateEntityMovementAfterFallOn(b, e);
        } else {
            this.bounceUp(e);
        }
    }

    private void bounceUp(Entity entity) {
        Vec3 vec3 = entity.getDeltaMovement();
        if(vec3.y < 0.0) {
            double d0 = entity instanceof LivingEntity ? 1.0 : 0.8;
            entity.setDeltaMovement(vec3.x, -vec3.y * 0.6600000262260437 * d0, vec3.z);
        }
    }

    @Override
    protected @NotNull BlockState updateShape(BlockState state, @NotNull LevelReader lv, @NotNull ScheduledTickAccess ta, @NotNull BlockPos pos, @NotNull Direction dir, @NotNull BlockPos nPos, @NotNull BlockState nState, @NotNull RandomSource random) {
        if (dir != getNeighbourDirection(state.getValue(PART), state.getValue(FACING))) {
            return super.updateShape(state, lv, ta, pos, dir, nPos, nState, random);
        } else {
            return nState.is(this) && nState.getValue(PART) != state.getValue(PART) ? state.setValue(OCCUPIED, nState.getValue(OCCUPIED)) : Blocks.AIR.defaultBlockState();
        }
    }

    private static Direction getNeighbourDirection(BedPart part, Direction direction) {
        return part == BedPart.FOOT ? direction : direction.getOpposite();
    }

    @Override
    public @NotNull BlockState playerWillDestroy(Level l, @NotNull BlockPos p, @NotNull BlockState s, @NotNull Player player) {
        if (!l.isClientSide && player.isCreative()) {
            BedPart bedpart = s.getValue(PART);
            if (bedpart == BedPart.FOOT) {
                BlockPos blockpos = p.relative(getNeighbourDirection(bedpart, s.getValue(FACING)));
                BlockState blockstate = l.getBlockState(blockpos);
                if (blockstate.is(this) && blockstate.getValue(PART) == BedPart.HEAD) {
                    l.setBlock(blockpos, Blocks.AIR.defaultBlockState(), 35);
                    l.levelEvent(player, 2001, blockpos, Block.getId(blockstate));
                }
            }
        }

        return super.playerWillDestroy(l, p, s, player);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction direction = context.getHorizontalDirection();
        BlockPos blockpos = context.getClickedPos();
        BlockPos blockpos1 = blockpos.relative(direction);
        Level level = context.getLevel();
        return level.getBlockState(blockpos1).canBeReplaced(context) && level.getWorldBorder().isWithinBounds(blockpos1) ? this.defaultBlockState().setValue(FACING, direction) : null;
    }
    
    @Override
    protected @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        Direction direction = getConnectedDirection(state).getOpposite();
        switch (direction) {
            case NORTH -> {
                return NORTH_SHAPE;
            }
            case SOUTH -> {
                return SOUTH_SHAPE;
            }
            case WEST -> {
                return WEST_SHAPE;
            }
            default -> {
                return EAST_SHAPE;
            }
        }
    }

    public static @NotNull Direction getConnectedDirection(BlockState state) {
        Direction direction = state.getValue(FACING);
        return state.getValue(PART) == BedPart.HEAD ? direction.getOpposite() : direction;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, PART, OCCUPIED);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos p, BlockState s) {
        return new PlayerBedBlockEntity(p, s);
    }

    @Override
    public void setPlacedBy(@NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state, @Nullable LivingEntity placer, @NotNull ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        if (!level.isClientSide) {
            BlockPos blockpos = pos.relative(state.getValue(FACING));
            level.setBlock(blockpos, state.setValue(PART, BedPart.HEAD), 3);
            level.blockUpdated(pos, Blocks.AIR);
            state.updateNeighbourShapes(level, pos, 3);
        }
    }

    @Override
    protected long getSeed(BlockState state, BlockPos pos) {
        BlockPos blockpos = pos.relative(state.getValue(FACING), state.getValue(PART) == BedPart.HEAD ? 0 : 1);
        return Mth.getSeed(blockpos.getX(), pos.getY(), blockpos.getZ());
    }

    @Override
    protected boolean isPathfindable(@NotNull BlockState s, @NotNull PathComputationType p) {
        return false;
    }

    static {
        PART = BlockStateProperties.BED_PART;
        OCCUPIED = BlockStateProperties.OCCUPIED;
        BASE = Block.box(0.0, 3.0, 0.0, 16.0, 9.0, 16.0);
        LEG_NORTH_WEST = Block.box(0.0, 0.0, 0.0, 3.0, 3.0, 3.0);
        LEG_SOUTH_WEST = Block.box(0.0, 0.0, 13.0, 3.0, 3.0, 16.0);
        LEG_NORTH_EAST = Block.box(13.0, 0.0, 0.0, 16.0, 3.0, 3.0);
        LEG_SOUTH_EAST = Block.box(13.0, 0.0, 13.0, 16.0, 3.0, 16.0);
        NORTH_SHAPE = Shapes.or(BASE, LEG_NORTH_WEST, LEG_NORTH_EAST);
        SOUTH_SHAPE = Shapes.or(BASE, LEG_SOUTH_WEST, LEG_SOUTH_EAST);
        WEST_SHAPE = Shapes.or(BASE, LEG_NORTH_WEST, LEG_SOUTH_WEST);
        EAST_SHAPE = Shapes.or(BASE, LEG_NORTH_EAST, LEG_SOUTH_EAST);
    }
}
