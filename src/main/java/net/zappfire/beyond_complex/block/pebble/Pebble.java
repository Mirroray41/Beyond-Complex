package net.zappfire.beyond_complex.block.pebble;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Pebble extends HorizontalDirectionalBlock {
    public Pebble(Properties p_49795_) {
        super(p_49795_);
    }
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    private static final VoxelShape SHAPE_N =
            Block.box(6, 0, 5, 11, 2, 10);
    private static final VoxelShape SHAPE_E =
            Block.box(6, 0, 6, 11, 2, 11);
    private static final VoxelShape SHAPE_S =
            Block.box(5, 0, 6, 10, 2, 11);
    private static final VoxelShape SHAPE_W =
            Block.box(5, 0, 5, 10, 2, 10);


    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        switch(p_60555_.getValue(FACING)) {
            case NORTH:
                return SHAPE_N;
            case SOUTH:
                return SHAPE_S;
            case WEST:
                return SHAPE_W;
            default:
                return SHAPE_E;
        }
    }
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext p53087) {
        Direction direction = p53087.getHorizontalDirection().getOpposite();
        return defaultBlockState().setValue(FACING, direction);
    }

    @Override
    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p53105) {
        p53105.add(FACING);
    }
}
