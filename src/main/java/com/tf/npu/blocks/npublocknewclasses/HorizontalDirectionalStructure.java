package com.tf.npu.blocks.npublocknewclasses;

import com.tf.npu.blocks.dataofnpublocks.ShapeData;
import com.tf.npu.blocks.NpuBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HorizontalDirectionalStructure extends HorizontalDirectionalBlock
{
    //体积映射
    public ArrayList<VoxelShape> shapeList;
    public VoxelShape shape;
    //方向属性
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    private Direction direction;
    //加载方式
    public NpuBlocks.LoadMethod loadMethod;

    //构造
    public HorizontalDirectionalStructure(BlockBehaviour.Properties properties, NpuBlocks.LoadMethod loadMethod)
    {
        super(properties);
        shapeList = new ArrayList<>(0);
        shape = null;
        direction = Direction.NORTH;
        this.loadMethod = loadMethod;
    }
    //与构造并用
    public HorizontalDirectionalStructure setSHAPE(ShapeData shapeData)
    {
        for (List<Double> shape : shapeData.getShapeList())
        {
            shapeList.add(Shapes.box(shape.get(0), shape.get(1), shape.get(2), shape.get(3), shape.get(4), shape.get(5)));
        }

        return this;
    }

    //旋转坐标变换
    protected VoxelShape getShapeByDirection(VoxelShape shape, Direction direction)
    {
        Double[] pos = new Double[6];
        {
            pos[0] = shape.bounds().minX;
            pos[1] = shape.bounds().minY;
            pos[2] = shape.bounds().minZ;
            pos[3] = shape.bounds().maxX;
            pos[4] =  shape.bounds().maxY;
            pos[5] =  shape.bounds().maxZ;
        }

        return switch (direction)
        {
            //坐标变化表
            case NORTH -> Shapes.box(pos[0], pos[1], pos[2], pos[3], pos[4], pos[5]);
            case SOUTH -> Shapes.box(1-pos[3], pos[1], 1-pos[5], 1-pos[0], pos[4], 1-pos[2]);
            case EAST -> Shapes.box(1-pos[5], pos[1], pos[0], 1-pos[2], pos[4], pos[3]);
            case WEST -> Shapes.box(pos[2], pos[1], 1-pos[3], pos[5], pos[4], 1-pos[0]);
            default -> shape;
        };
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context)
    {
        return defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    private void loadShape()
    {
        switch (loadMethod)
        {
            case METICULOUS:
                shape = NpuBlocks.EmunShape.NULL_SHPAE.getShape();
                for (VoxelShape voxelShape : shapeList)
                {
                    shape = Shapes.or(shape, getShapeByDirection(voxelShape, direction));
                }
                break;
            case ROUGH:
                shape = shapeList.get(0);
                for (VoxelShape voxelShape : shapeList)
                {
                    AABB a = shape.bounds();
                    AABB b = voxelShape.bounds();
                    shape = getShapeByDirection(Shapes.box(
                                    Math.min(a.minX, b.minX), Math.min(a.minY, b.minY), Math.min(a.minZ, b.minZ),
                                    Math.max(a.maxX, b.maxX), Math.max(a.maxY, b.maxY), Math.max(a.maxZ, b.maxZ)),
                            direction);
                }
                break;
        }
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState pState, @NotNull BlockGetter pGetter, @NotNull BlockPos pPos, @NotNull CollisionContext pContext)
    {
        if (shape == null || direction != pState.getValue(FACING))
        {
            direction = pState.getValue(FACING);
            loadShape();
        }
        return shape;
    }
}
