package com.tf.npu.blocks;

import com.tf.npu.blocks.dataofnpublocks.DataOfNpuBlocks;
import com.tf.npu.blocks.dataofnpublocks.ShapeData;
import com.tf.npu.blocks.npublocknewclasses.HorizontalDirectionalHalfSlab;
import com.tf.npu.blocks.npublocknewclasses.HorizontalDirectionalStructure;
import com.tf.npu.blocks.npublocknewclasses.NormalHalfSlab;
import com.tf.npu.blocks.npublocknewclasses.NormalStructure;
import com.tf.npu.util.FileDataGetter;
import com.tf.npu.util.FolderDataGetter;
import com.tf.npu.util.Reference;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.ToIntFunction;

public class NpuBlocks
{
    // Create a Deferred Register to hold Blocks which will all be registered under the "npu" namespace
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Reference.MODID);
    public static final String dataPath = "blockdata/";

    //新方块注册
    static
    {
        for (TabType tabType : TabType.values())
            tabType.registerBlocks();
    }

    //其他的一些有用玩意
    //注册模板

    //一个构造方法
    public static BlockBehaviour.Properties createBlockProperties(EnumMaterial material)
    {
        return BlockBehaviour.Properties.of()
                .strength(material.getStrength())
                .sound(material.getSound())
                .lightLevel(material.getLightLevel())
                .friction(material.getFriction());
    }


    //一些常用属性
    public static enum TabType
    {
        AreaBlock(
                new FolderDataGetter<>(dataPath + "area_block", DataOfNpuBlocks.class).getList()),
        ConstructionBlock(
                new FolderDataGetter<>(dataPath + "construction_block", DataOfNpuBlocks.class).getList()),
        RoadBlock(
                new FolderDataGetter<>(dataPath + "road_block", DataOfNpuBlocks.class).getList()),
        PlaygroundBlock(
                new FolderDataGetter<>(dataPath + "playground_block", DataOfNpuBlocks.class).getList());

        //新方块属性表
        final List<DataOfNpuBlocks> dataList;
        //新方块表
        public final ArrayList<RegistryObject<Block>> blockList;
        //新方块ID映射表
        public final Map<RegistryObject<Block>, String> IDMap;


        TabType(List<DataOfNpuBlocks> dataList)
        {
            this.dataList = dataList;
            this.blockList = new ArrayList<>(0);
            this.IDMap = new HashMap<>(0);
        }


        public void registerBlocks()
        {
            for (DataOfNpuBlocks data : dataList)
            {
                RegistryObject<Block> BLOCK;

                BLOCK = switch (StructureType.valueOf(data.StructureType))
                {
                    case NORMAL_STRUCTURE ->
                    {
                        ShapeData shapeData =
                                new FileDataGetter<ShapeData>("../src/main/resources/assets/npu/" + data.modelPath, ShapeData.class).getData();
                        yield BLOCKS.register(data.ID, () ->
                                new NormalStructure(data.createBlockProperties(), LoadMethod.valueOf(data.loadMethod)).setSHAPE(shapeData));
                    }
                    case HORIZONTAL_DIRECTIONAL_STRUCTURE ->
                    {
                        ShapeData shapeData =
                                new FileDataGetter<ShapeData>("../src/main/resources/assets/npu/" + data.modelPath, ShapeData.class).getData();
                        yield BLOCKS.register(data.ID, () ->
                                new HorizontalDirectionalStructure(data.createBlockProperties(), LoadMethod.valueOf(data.loadMethod)).setSHAPE(shapeData));
                    }
                    case HORIZONTAL_DIRECTIONAL_HALF_SLAB -> BLOCKS.register(data.ID, () ->
                            new HorizontalDirectionalHalfSlab(data.createBlockProperties()).setCanBeDouble(data.double_enable));
                    case NORMAL_HALF_SLAB -> BLOCKS.register(data.ID, () ->
                            new NormalHalfSlab(data.createBlockProperties()).setCanBeDouble(data.double_enable));
                };

                blockList.add(BLOCK);
                IDMap.put(BLOCK, data.ID);
            }
        }
    }
    public static enum StructureType
    {
        NORMAL_STRUCTURE,
        HORIZONTAL_DIRECTIONAL_STRUCTURE,
        NORMAL_HALF_SLAB,
        HORIZONTAL_DIRECTIONAL_HALF_SLAB;
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
    public static enum LoadMethod
    {
        METICULOUS, ROUGH;
    }
}
