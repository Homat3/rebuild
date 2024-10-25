package com.tf.npu.blocks.npublocknewclasses;

import com.mojang.realmsclient.client.Request;
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

    //材料
    public NpuBlocks.EnumMaterial material;

    //构造
    public NormalStructure(BlockBehaviour.Properties properties)
    {
        super(properties);
    }
    //与构造并用
    public NormalStructure addSHAPE(double a, double b, double c, double d, double e, double f)
    {
        GetSHAPE.putIfAbsent(this, new ArrayList<VoxelShape>(1));
        GetSHAPE.get(this).add(Shapes.box(a, b, c, d, e, f));
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
