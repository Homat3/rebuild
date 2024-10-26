package com.tf.npu.blocks.dataofnpublocks;

import com.tf.npu.blocks.NpuBlocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

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
