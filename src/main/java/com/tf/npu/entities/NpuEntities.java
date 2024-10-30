package com.tf.npu.entities;

import com.tf.npu.entities.npuentitynewclasses.normal.GoldenChicken.GoldenChicken;
import com.tf.npu.entities.npuentitynewclasses.geo.schoolbus.SchoolBus;
import com.tf.npu.util.Reference;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class NpuEntities
{
    // Create a Deferred Register to hold items which will all be registered under the "npu" namespace
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Reference.MODID);

    //新实体ID表
    public static final String GOLDEN_CHICKEN_ID = "golden_chicken";
    public static final String SCHOOL_BUS_ID = "school_bus";
    //注册新实体示例

    /*
    public static final RegistryObject<EntityType<YOURTYPE>> EXAMPLE_ENTITY =
            ENTITYTYPES.register(EXAMPLE_ENTITY_ID, () ->
                    EntityType.Builder.of(YOURTYPE::new, MobCategory.YOU_KNOW)                              //设置生物大类
                            .sized(Width, Height)                                                           //设置大小
                            .build(new ResourceLocation(Reference.MODID, EXAMPLE_ENTITY_ID).toString()));
     */
    public static final RegistryObject<EntityType<GoldenChicken>> GOLDEN_CHICKEN =
            ENTITY_TYPES.register(GOLDEN_CHICKEN_ID, () ->
                    EntityType.Builder.of(GoldenChicken::new, MobCategory.CREATURE)
                            .sized(1.0F, 1.0F)
                            .build(new ResourceLocation(Reference.MODID, GOLDEN_CHICKEN_ID).toString()));
    public static final RegistryObject<EntityType<SchoolBus>> SCHOOL_BUS =
            ENTITY_TYPES.register(SCHOOL_BUS_ID, () ->
                    EntityType.Builder.of(SchoolBus::new, MobCategory.CREATURE)
                            .sized(2.0F, 2.0F)
                            .build(new ResourceLocation(Reference.MODID, SCHOOL_BUS_ID).toString()));
}
