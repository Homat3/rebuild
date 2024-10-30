package com.tf.npu.entities.npuentitynewclasses.geo.schoolbus;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.GeckoLib;
import software.bernie.geckolib.model.GeoModel;

public class SchoolBusModel extends GeoModel<SchoolBus>
{
    public static final String modelPath = "geo/school_bus.geo.json";
    public static final String texturePath = "textures/school_bus.png";
    public static final String animationPath = "animations/school_bus.animation.json";

    @Override
    public ResourceLocation getModelResource(SchoolBus schoolBus)
    {
        return new ResourceLocation(GeckoLib.MOD_ID, modelPath);
    }

    @Override
    public ResourceLocation getTextureResource(SchoolBus schoolBus)
    {
        return new ResourceLocation(GeckoLib.MOD_ID, texturePath);
    }

    @Override
    public ResourceLocation getAnimationResource(SchoolBus schoolBus)
    {
        return new ResourceLocation(GeckoLib.MOD_ID, animationPath);
    }
}
