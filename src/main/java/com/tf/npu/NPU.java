package com.tf.npu;

import com.tf.npu.creativemodtab.CreativeModeTab;
import com.tf.npu.entities.NpuEntities;
import com.tf.npu.entities.npuentitynewclasses.normal.GoldenChicken.GoldenChickenRenderer;
import com.tf.npu.entities.npuentitynewclasses.geo.schoolbus.SchoolBusRenderer;
import com.tf.npu.util.Logger;
import com.tf.npu.util.Reference;
import com.tf.npu.util.Register;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Reference.MODID)
public class NPU
{

    public static final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    /*
    // Creates grey_brick.json new BlockItem with the id "npu:example_block", combining the namespace and path
    public static final RegistryObject<items> EXAMPLE_BLOCK_ITEM = ITEMS.register("example_block", () -> new BlockItem(EXAMPLE_BLOCK.get(), new items.Properties()));

    // Creates grey_brick.json new food item with the id "npu:example_id", nutrition 1 and saturation 2
    public static final RegistryObject<items> EXAMPLE_ITEM = ITEMS.register("example_item", () -> new items(new items.Properties().food(new FoodProperties.Builder()
            .alwaysEat().nutrition(1).saturationMod(2f).build())));

    // Creates grey_brick.json creative tab with the id "npu:example_tab" for the example item, that is placed after the combat tab
    public static final RegistryObject<CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("example_tab", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> EXAMPLE_ITEM.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(EXAMPLE_ITEM.get()); // Add the example item to the tab. For your own tabs, this method is preferred over the event
            }).build());
    */
    public NPU()
    {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register the Deferred Register to the mod event bus so new things of the mod get registered
        Register.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        //将物品注册到创造模式物品栏
        modEventBus.addListener(this::addCreative);
        //将模组渲染方式注册到模组实体
        modEventBus.addListener(this::registerRenderers);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        Logger.LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.logDirtBlock)
            Logger.LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));

        Logger.LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        Config.items.forEach((item) -> Logger.LOGGER.info("ITEM >> {}", item.toString()));
    }

    //将物品注册到创造模式物品栏
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        CreativeModeTab.addCreative(event);
    }

    //将模组渲染方式注册到模组实体
    //新的实体方块和新的实体都需要自己的 Renderer 并把它们加到这里
    private void registerRenderers(EntityRenderersEvent.RegisterRenderers register)
    {
        register.registerEntityRenderer(NpuEntities.GOLDEN_CHICKEN.get(), GoldenChickenRenderer::new);
        register.registerEntityRenderer(NpuEntities.SCHOOL_BUS.get(), SchoolBusRenderer::new);
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        Logger.LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            Logger.LOGGER.info("HELLO FROM CLIENT SETUP");
            Logger.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
