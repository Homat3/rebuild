package com.tf.npu.blocks.npublocknewclasses;

import com.tf.npu.blocks.dataofnpublocks.ShapeData;
import com.tf.npu.blocks.NpuBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NormalStructure extends Block
{
    //体积映射
    public static Map<NormalStructure, ArrayList<VoxelShape>> GetSHAPE = new HashMap<>();

    //构造
    public NormalStructure(BlockBehaviour.Properties properties)
    {
        super(properties);
    }
    //与构造并用
    public NormalStructure setSHAPE(ShapeData shapeData)
    {
        for (List<Double> shape : shapeData.getShapeList())
        {
            GetSHAPE.putIfAbsent(this, new ArrayList<>(1));
            GetSHAPE.get(this).add(Shapes.box(shape.get(0), shape.get(1), shape.get(2), shape.get(3), shape.get(4), shape.get(5)));
        }

        return this;
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState pState, @NotNull BlockGetter pGetter, @NotNull BlockPos pPos, @NotNull CollisionContext pContext)
    {
        VoxelShape shape = NpuBlocks.EmunShape.NULL_SHPAE.getShape();
        List<VoxelShape> list = List.copyOf(GetSHAPE.get(this));

        for (VoxelShape voxelShape : list)
        {
            shape = Shapes.or(shape, voxelShape);
        }

        return shape.optimize();
    }
}
