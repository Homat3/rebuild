package com.tf.npu.items;

import com.tf.npu.blocks.NpuBlocks;
import com.tf.npu.entities.NpuEntities;
import com.tf.npu.util.Reference;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class NpuItems
{
    // Create a Deferred Register to hold items which will all be registered under the "npu" namespace
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MODID);


    //////////////////////////////////////////////BlockItem/////////////////////////////////////////////////////////

    //新方块物品注册表

    //原npu/Blocks/BuildBlocks/Ceiling中的类
    public static final RegistryObject<BlockItem> GRILLE_CEILING_ITEM =
            ITEMS.register(NpuBlocks.GRILLE_CEILING_ID, () -> new BlockItem(NpuBlocks.GRILLE_CEILING.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> GRILLE_CEILING_AUDIO_ITEM =
            ITEMS.register(NpuBlocks.GRILLE_CEILING_AUDIO_ID, () -> new BlockItem(NpuBlocks.GRILLE_CEILING_AUDIO.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> GRILLE_CEILING_BIGLIGHT_ITEM =
            ITEMS.register(NpuBlocks.GRILLE_CEILING_BIGLIGHT_ID, () -> new BlockItem(NpuBlocks.GRILLE_CEILING_BIGLIGHT.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> GRILLE_CEILING_CAMERA_ITEM =
            ITEMS.register(NpuBlocks.GRILLE_CEILING_CAMERA_ID, () -> new BlockItem(NpuBlocks.GRILLE_CEILING_CAMERA.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> GRILLE_CEILING_SMALLLIGHT_ITEM =
            ITEMS.register(NpuBlocks.GRILLE_CEILING_SMALLLIGHT_ID, () -> new BlockItem(NpuBlocks.GRILLE_CEILING_SMALLLIGHT.get(), new Item.Properties()));

    //原npu/Blocks/BuildBlocks/Constructions中的类
    public static final RegistryObject<BlockItem> BPUP_ITEM =
            ITEMS.register(NpuBlocks.BPUP_ID, () -> new BlockItem(NpuBlocks.BPUP.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> BPDOWN_ITEM =
            ITEMS.register(NpuBlocks.BPDOWN_ID, () -> new BlockItem(NpuBlocks.BPDOWN.get(), new Item.Properties()));

    //////////////////////////////////////////////////Item///////////////////////////////////////////////////////////

    /*
    //注册新物品示例
    public static final RegistryObject<items> EXAMPLE_ITEM =
            ITEMS.register(EXAMPLE_ITEM_ID, () -> new Item(new Item.Properties()));
    */

    //新物品ID表
    public static String GOLDEN_EGG_ID = "golden_egg";
    public static String GOLDEN_CHICKEN_SPAWN_EGG_ID = "golden_chicken_spawn_egg";

    //新物品注册表

    public static final RegistryObject<Item> GOLDEN_EGG_ITEM =                              //尚未加入物品栏
            ITEMS.register(GOLDEN_EGG_ID, () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GOLDEN_CHICKEN_SPAWN_EGG_ITEM =                //尚未加入物品栏
            ITEMS.register(GOLDEN_CHICKEN_SPAWN_EGG_ID, () -> new ForgeSpawnEggItem(NpuEntities.GOLDEN_CHICKEN, 0xFFD700, 0xFF8C00, new Item.Properties()));
}
