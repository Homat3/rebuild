package com.tf.npu.entities.npuentitynewclasses.geo.schoolbus;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.GeckoLib;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

//SchoolBus的Renderer
public class SchoolBusRenderer extends GeoEntityRenderer<SchoolBus>
{
    public SchoolBusRenderer(EntityRendererProvider.Context context)
    {
        super(context, new SchoolBusModel());
        this.shadowRadius *= 0.6F;
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull SchoolBus schoolbus)
    {
        return new ResourceLocation(GeckoLib.MOD_ID, SchoolBusModel.texturePath);
    }

    @Override
    public RenderType getRenderType(SchoolBus animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick)
    {
        return super.getRenderType(animatable, texture, bufferSource, partialTick);
    }
}

