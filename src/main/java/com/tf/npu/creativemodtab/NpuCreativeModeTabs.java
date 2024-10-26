package com.tf.npu.creativemodtab;

import com.tf.npu.util.Logger;
import com.tf.npu.util.Reference;
import com.tf.npu.items.NpuItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;


//用于注册新物品栏并向其中添加物品

public class NpuCreativeModeTabs
{

    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "npu" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Reference.MODID);


    //新物品栏ID表
    //public static final String EXAMPLE_BLOCK_TAB_ID = "example_block_tab";
    public static final String CONSTRUCTION_BLOCK_TAB_ID = "construction_block_tab";
    public static final String ENTITY_ITEM_TAB_ID = "entity_item_tab";


    //注册新物品栏示例
    /*
    public static final RegistryObject<CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register(EXAMPLE_TAB_ID, () -> CreativeModeTab.builder()
            .title(Component.translatable("creativemodetab.npu" + EXAMPLE_TAB_ID))
            .withTabsBefore(CreativeModeTabs.COMBAT)                        //设置物品栏位置
            .icon(() -> new ItemStack(NpuItems.TabType.EXAMPLE.itemList.get(0).get()))   //设置物品栏图标为WHAT_YOU_WANT_ITEM的图标
            .displayItems(((itemDisplayParameters, output) ->
                {
                    Logger.LOGGER.info("Adding NPU items to NpuCreativeModeTab >> {}", EXAMPLE_BLOCK_TAB_ID);

                    //加物品
                    for (var BLOCK_ITEM : NpuItems.TabType.ExampleBlock.blockItemList)
                        output.accept(BLOCK_ITEM.get());
                    for (var ITEM : NpuItems.TabType.ExampleBlock.itemList)
                        output.accept(ITEM.get());
                }))
            .build());
     */

    public static final RegistryObject<CreativeModeTab> NPU_CONSTRUCTION_BLOCK_TAB = CREATIVE_MODE_TABS.register(CONSTRUCTION_BLOCK_TAB_ID, () -> CreativeModeTab.builder()
            .title(Component.translatable("creativemodetab.npu." + CONSTRUCTION_BLOCK_TAB_ID))
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> new ItemStack(NpuItems.TabType.ConstructionBlock.blockItemList.get(0).get()))
            .displayItems((itemDisplayParameters, output) ->
                {
                    Logger.LOGGER.info("Adding NPU items to NpuCreativeModeTab >> {}", CONSTRUCTION_BLOCK_TAB_ID);

                    //加物品
                    for (var BLOCK_ITEM : NpuItems.TabType.ConstructionBlock.blockItemList)
                        output.accept(BLOCK_ITEM.get());
                    for (var ITEM : NpuItems.TabType.ConstructionBlock.itemList)
                        output.accept(ITEM.get());
                }
                )
            .build());

    public static final RegistryObject<CreativeModeTab> NPU_ENTITY_ITEM_TAB = CREATIVE_MODE_TABS.register(ENTITY_ITEM_TAB_ID, () -> CreativeModeTab.builder()
            .title(Component.translatable("creativemodetab.npu." + ENTITY_ITEM_TAB_ID))
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> new ItemStack(NpuItems.TabType.EntityItem.itemList.get(0).get()))
            .displayItems((itemDisplayParameters, output) ->
                {
                    Logger.LOGGER.info("Adding NPU items to NpuCreativeModeTab >> {}", ENTITY_ITEM_TAB_ID);

                    //加物品
                    for (var BLOCK_ITEM : NpuItems.TabType.EntityItem.blockItemList)
                        output.accept(BLOCK_ITEM.get());
                    for (var ITEM : NpuItems.TabType.EntityItem.itemList)
                        output.accept(ITEM.get());
                }
                )
            .build());

}
