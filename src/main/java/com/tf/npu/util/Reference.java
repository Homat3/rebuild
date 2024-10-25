package com.tf.npu.util;

import com.mojang.logging.LogUtils;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

public class Reference
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "npu";
    public static final String MODNAME = "Build The World For NPU";
    public static final String VERSION = "2.24";
    // Create a Deferred Register to hold Blocks which will all be registered under the "npu" namespace
}
