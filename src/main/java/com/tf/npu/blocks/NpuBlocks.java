package com.tf.npu.blocks;

import com.tf.npu.blocks.DataOfNpuBlocks.DataOfNpuBlocks;
import com.tf.npu.blocks.DataOfNpuBlocks.ShapeData;
import com.tf.npu.blocks.npublocknewclasses.HorizontalDirectionalStructure;
import com.tf.npu.blocks.npublocknewclasses.NormalStructure;
import com.tf.npu.util.FileDataGetter;
import com.tf.npu.util.FolderDataGetter;
import com.tf.npu.util.Reference;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.ToIntFunction;

public class NpuBlocks
{
    // Create a Deferred Register to hold Blocks which will all be registered under the "npu" namespace
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Reference.MODID);

    public static final String dataPath = "blockstates/data/construction_block";
    public static final List<DataOfNpuBlocks> dataListForConstructionBlock = new FolderDataGetter<>(dataPath, DataOfNpuBlocks.class).getList();

    //新方块表
    public static final ArrayList<RegistryObject<Block>> ConstructionBlock_List = new ArrayList<>(0);
    //新方块与ID映射表
    public static final Map<RegistryObject<Block>, String> ConstructionBlockID_Map = new HashMap<>();


    static
    {
        for (DataOfNpuBlocks data : dataListForConstructionBlock)
        {
            ShapeData shapeData =
                    new FileDataGetter<ShapeData>("../src/main/resources/assets/npu/" + data.modelPath, ShapeData.class).getData();
            Class<?> tClass = StructureType.valueOf(data.StructureType).getStructureType();

            RegistryObject<Block> BLOCK;

            BLOCK = switch (StructureType.valueOf(data.StructureType))
            {
                case NORMAL_STRUCTURE -> BLOCKS.register(data.ID, () ->
                        new NormalStructure(data.createBlockProperties()).setSHAPE(shapeData));
                case HORIZONTAL_DIRECTIONAL_STRUCTURE ->  BLOCKS.register(data.ID, () ->
                        new HorizontalDirectionalStructure(data.createBlockProperties()).setSHAPE(shapeData));
            };

            ConstructionBlock_List.add(BLOCK);
            ConstructionBlockID_Map.put(BLOCK, data.ID);
        }
    }



    //其他的一些玩意

    //一些常用属性
    public static enum StructureType
    {
        NORMAL_STRUCTURE(NormalStructure.class),
        HORIZONTAL_DIRECTIONAL_STRUCTURE(HorizontalDirectionalStructure.class);

        private Class<?> tClass;

        StructureType(Class<?> tClass)
        {
            this.tClass = tClass;
        }

        public Class<?> getStructureType()
        {
            return tClass;
        }
    }
    public static enum EnumMaterial
    {
        //EXAMPLE("example", 硬度, 音效包, (BlockState state) ->{根据不同的blockstate返回不同的亮度值}, 阻力系数，即站在上面的移速),
        IRON("iron", 5.0F, SoundType.METAL, (BlockState state) ->{return 0;},0.6F),
        ROCK("rock", 2.5F, SoundType.STONE, (BlockState state) ->{return 0;},0.6F);


        private final String name;
        private final float strength;
        private final SoundType sound;
        private final ToIntFunction<BlockState> lightLevel;
        private final float friction;

        EnumMaterial(String name, float strength, SoundType sound, ToIntFunction<BlockState> lightLevel, float friction)
        {
            this.name = name;
            this.strength = strength;
            this.sound = sound;
            this.lightLevel = lightLevel;
            this.friction = friction;
        }

        public String getName() {
            return this.name;
        }

        public float getStrength()
        {
            return strength;
        }

        public SoundType getSound()
        {
            return sound;
        }

        public ToIntFunction<BlockState> getLightLevel()
        {
            return lightLevel;
        }

        public float getFriction()
        {
            return friction;
        }
    }
    public static enum EmunShape
    {
        FULL_SHPAE(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D),
        NULL_SHPAE(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D),
        HALF_SHPAE_BOTTOM(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D),
        HALF_SHPAE_TOP(0.0D, 0.5D, 0.0D, 1.0D, 1.0D, 1.0D);

        final VoxelShape shape;
        
        EmunShape(double minX, double minY, double minZ, double maxX, double maxY, double maxZ)
        {
            shape = Shapes.box(minX, minY, minZ, maxX, maxY, maxZ);
        }

        public VoxelShape getShape()
        {
            return shape;
        }
    }

    //一些构造方法
    public static BlockBehaviour.Properties createBlockProperties(EnumMaterial material)
    {
        return BlockBehaviour.Properties.of()
                .strength(material.getStrength())
                .sound(material.getSound())
                .lightLevel(material.getLightLevel())
                .friction(material.getFriction());
    }
}
