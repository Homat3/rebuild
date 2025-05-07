package com.tf.npu.entities.npuentitynewclasses.normal.GoldenChicken;

import com.mojang.blaze3d.vertex.PoseStack;
import com.tf.npu.util.Reference;
import net.minecraft.client.renderer.entity.ChickenRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Chicken;
import org.jetbrains.annotations.NotNull;


//GoldenChicken的Renderer
public class GoldenChickenRenderer extends ChickenRenderer
{
    private static final ResourceLocation GOLDEN_CHICKEN_TEXTURES =
            new ResourceLocation(Reference.MODID, "textures/entity/g/golden_chicken.png");


    public GoldenChickenRenderer(EntityRendererProvider.Context context)
    {
        super(context);
        this.shadowRadius *= 0.6F;
    }

    //放缩方式
    @Override
    protected void scale(@NotNull Chicken chicken, PoseStack stack, float value)
    {
        stack.scale(1.5F, 1.5F, 1.5F);
    }

    //获取字段
    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull Chicken chicken)
    {
        return GOLDEN_CHICKEN_TEXTURES;
    }
}
