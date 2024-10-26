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

import java.util.ArrayList;

public class NpuItems
{
    // Create a Deferred Register to hold items which will all be registered under the "npu" namespace
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MODID);


    //////////////////////////////////////////////BlockItem/////////////////////////////////////////////////////////

    public static final ArrayList<RegistryObject<BlockItem>> construction_block_item_List = new ArrayList<>(0);
    static
    {
        for (var BLOCK : NpuBlocks.ConstructionBlock_List)
        {
            construction_block_item_List.add(
                    ITEMS.register(NpuBlocks.ConstructionBlockID_Map.get(BLOCK), () -> new BlockItem(BLOCK.get(), new Item.Properties())));
        }
    }

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
