package com.tf.npu.blocks.DataOfNpuBlocks;

import com.tf.npu.blocks.NpuBlocks;
import com.tf.npu.util.FileDataGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Supplier;

import static com.tf.npu.blocks.NpuBlocks.createBlockProperties;

public class DataOfNpuBlocks
{
    public String StructureType;
    public String ID;
    public String modelPath;
    public String Material;
    public boolean noOcclusion;
    public boolean noCollision;
    public boolean noLootTable;
    public boolean noParticlesOnBreak;

    public BlockBehaviour.Properties createBlockProperties()
    {

        BlockBehaviour.Properties properties = NpuBlocks.createBlockProperties(NpuBlocks.EnumMaterial.valueOf(Material));
        if (noOcclusion) properties.noOcclusion();
        if (noCollision) properties.noCollission();
        if (noLootTable) properties.noLootTable();
        if (noParticlesOnBreak) properties.noParticlesOnBreak();

        return properties;
    }
}
