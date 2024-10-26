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
    public static final String NPU_CONSTRUCTION_BLOCK_TAB_ID = "npu_construction_block_tab";

    //注册新物品栏示例
    /*
    public static final RegistryObject<CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register(EXAMPLE_TAB_ID, () -> CreativeModeTab.builder()
            .title(Component.translatable("creativemodetab.npu" + EXAMPLE_TAB_ID))
            .withTabsBefore(CreativeModeTabs.COMBAT)                        //设置物品栏位置
            .icon(() -> new ItemStack(NpuItems.WHAT_YOU_WANT_ITEM.get()))   //设置物品栏图标为WHAT_YOU_WANT_ITEM的图标
            .displayItems(((itemDisplayParameters, output) ->
                {
                    Logger.LOGGER.info("Adding NPU items to NpuCreativeModeTab >> {}", EXAMPLE_TAB_ID);
                    output.accept(NpuItems.WHAT_YOU_WANT_ITEM.get());       //设置物品栏内容为WHAT_YOU_WANT_ITEM
                }))
            .build());
     */

    public static final RegistryObject<CreativeModeTab> NPU_CONSTRUCTION_BLOCK_TAB = CREATIVE_MODE_TABS.register(NPU_CONSTRUCTION_BLOCK_TAB_ID, () -> CreativeModeTab.builder()
            .title(Component.translatable("creativemodetab.npu." + NPU_CONSTRUCTION_BLOCK_TAB_ID))
            .withTabsBefore(CreativeModeTabs.COMBAT)
            //.icon(() -> new ItemStack(NpuItems.GRILLE_CEILING_ITEM.get()))
            .displayItems(((itemDisplayParameters, output) ->
                {
                    Logger.LOGGER.info("Adding NPU items to NpuCreativeModeTab >> {}", NPU_CONSTRUCTION_BLOCK_TAB_ID);

                    for (var ITEM : NpuItems.construction_block_item_List)
                        output.accept(ITEM.get());
                }
                ))
            .build());


}
