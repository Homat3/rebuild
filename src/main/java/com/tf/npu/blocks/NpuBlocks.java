package com.tf.npu.blocks;

import com.tf.npu.blocks.npublocknewclasses.HorizontalDirectionalStructure;
import com.tf.npu.blocks.npublocknewclasses.NormalStructure;
import com.tf.npu.util.Reference;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.ToIntFunction;

public class NpuBlocks
{
    // Create a Deferred Register to hold Blocks which will all be registered under the "npu" namespace
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Reference.MODID);

    //新方块ID表
    //GrilleCeiling中的类
    public static final String GRILLE_CEILING_ID = "grille_ceiling";
    public static final String GRILLE_CEILING_AUDIO_ID = "grille_ceiling_audio";
    public static final String GRILLE_CEILING_BIGLIGHT_ID = "grille_ceiling_biglight";
    public static final String GRILLE_CEILING_CAMERA_ID = "grille_ceiling_camera";
    public static final String GRILLE_CEILING_SMALLLIGHT_ID = "grille_ceiling_smalllight";

    //Structure中的类
    public static final String BPUP_ID = "bpup";
    public static final String BPDOWN_ID = "bpdown";

    //新方块注册表
/*
    //注册新方块示例

    //注明原路径中的类！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
    public static final RegistryObject<Block> EXAMPLE_BLOCK =
            BLOCKS.register("example_block", () ->
                    new NormalStructure/HorizontalDirectionalStructure(createBlockProperties(NpuBlocks.EnumMaterial.IRON)   //材料设置，自行选择想要的材料即可，材料不够用自己去下面定义
                            .noOcclusion())                                                                                 //属性设置，自行选择属性设置即可，可以查英语词典
                            .addSHAPE(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D));                                                 //添加体积，可以一直添加来细化模型
 */

    //原npu/Blocks/BuildBlocks/Ceiling中的类
    public static final RegistryObject<Block> GRILLE_CEILING =
            BLOCKS.register(GRILLE_CEILING_ID, () ->
                    new NormalStructure(createBlockProperties(NpuBlocks.EnumMaterial.IRON)
                            .noOcclusion())
                            .addSHAPE(0.0D, 0.5D, 0.0D, 1.0D, 0.63D, 1.0D));
    public static final RegistryObject<Block> GRILLE_CEILING_AUDIO =
            BLOCKS.register(GRILLE_CEILING_AUDIO_ID, () ->
                    new NormalStructure(createBlockProperties(NpuBlocks.EnumMaterial.IRON)
                            .noOcclusion())
                            .addSHAPE(0.0D, 0.5D, 0.0D, 1.0D, 0.63D, 1.0D));
    public static final RegistryObject<Block> GRILLE_CEILING_BIGLIGHT =
            BLOCKS.register(GRILLE_CEILING_BIGLIGHT_ID, () ->
                    new NormalStructure(createBlockProperties(NpuBlocks.EnumMaterial.IRON)
                            .noOcclusion())
                            .addSHAPE(0.0D, 0.5D, 0.0D, 1.0D, 0.75D, 1.0D));
    public static final RegistryObject<Block> GRILLE_CEILING_CAMERA =
            BLOCKS.register(GRILLE_CEILING_CAMERA_ID, () ->
                    new NormalStructure(createBlockProperties(NpuBlocks.EnumMaterial.IRON)
                            .noOcclusion())
                            .addSHAPE(0.0D, 0.5D, 0.0D, 1.0D, 0.63D, 1.0D));
    public static final RegistryObject<Block> GRILLE_CEILING_SMALLLIGHT =
            BLOCKS.register(GRILLE_CEILING_SMALLLIGHT_ID, () ->
                    new NormalStructure(createBlockProperties(NpuBlocks.EnumMaterial.IRON)
                            .noOcclusion())
                            .addSHAPE(0.0D, 0.5D, 0.0D, 1.0D, 0.63D, 1.0D));

    //原npu/Blocks/BuildBlocks/Constructions中的类
    public static final RegistryObject<HorizontalDirectionalBlock> BPUP =
            BLOCKS.register(BPUP_ID, () ->
                    new HorizontalDirectionalStructure(createBlockProperties(NpuBlocks.EnumMaterial.IRON)
                            .noOcclusion())
                            .addSHAPE(0.44D, 0.0D, 0.0D, 0.56D, 2.0D, 2.0D));
    public static final RegistryObject<HorizontalDirectionalBlock> BPDOWN =
            BLOCKS.register(BPDOWN_ID, () ->
                    new HorizontalDirectionalStructure(createBlockProperties(NpuBlocks.EnumMaterial.IRON)
                            .noOcclusion())
                            .addSHAPE(-1.0D, 0.0D, 0.0D, 2.0D, 2.0D, 1.0D)
                            .addSHAPE(0.44D, 0.0D, 1.0D, 0.56D, 2.0D, 2.0D));




    //其他的一些玩意

    //一些常用属性
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
    private static BlockBehaviour.Properties createBlockProperties(EnumMaterial material)
    {
        return BlockBehaviour.Properties.of()
                .strength(material.getStrength())
                .sound(material.getSound())
                .lightLevel(material.getLightLevel())
                .friction(material.getFriction());
    }
}
