package com.tf.npu.entities.npuentitynewclasses.geo.schoolbus;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;
import javax.annotation.Nullable;
import java.util.List;

public class SchoolBus extends Animal implements GeoEntity
{
    private static final int maxPassengers = 8;
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public SchoolBus(EntityType<? extends Animal> entityType, Level level)
    {
        super(entityType, level);
    }
    public static AttributeSupplier.Builder registerAttributes()
    {
        return Chicken.createAttributes()
                .add(Attributes.MAX_HEALTH, 25.0F)
                .add(Attributes.MOVEMENT_SPEED, 0.3F)
                .add(Attributes.JUMP_STRENGTH, 0.0F);
    }

    @Override
    public boolean isPushable()
    {
        return false;
    }

    @Override
    public @NotNull InteractionResult mobInteract(@NotNull Player player, @NotNull InteractionHand hand) {
        if (!this.isVehicle())
        {
            player.startRiding(this);

            Zombie $$3 = EntityType.ZOMBIE.create(this.level());
            if ($$3 != null)
            {
                $$3.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), 0.0F);
                this.level().addFreshEntity($$3);
                $$3.startRiding(this);
            }
        }
        return super.mobInteract(player, hand);
    }

    @Override
    protected void playStepSound(@NotNull BlockPos pos, @NotNull BlockState block)
    {
        //play sound
    }

    @Override
    public void travel(@NotNull Vec3 pos) {
        if (this.isAlive() && this.isVehicle())
        {
            LivingEntity passenger = this.getControllingPassenger();
            if (passenger != null )
            {
                this.yRotO = this.getYRot();
                this.xRotO = this.getXRot();
                this.setYRot(passenger.getYRot());
                this.setXRot(Math.min(passenger.getXRot(), 20.0F));
                this.setRot(this.getYRot(), this.getXRot());
                this.yBodyRot = this.getYRot();
                this.yHeadRot = this.yBodyRot;
                float z = passenger.zza;
                if (z <= 0.0F)
                {
                    z *= 0.25F;
                }

                this.setSpeed(0.3F);
                super.travel(new Vec3(0, pos.y, (double) z));
            }
        }

    }

    @Override
    public boolean isControlledByLocalInstance() {
        return true;
    }

    @Override
    @Nullable
    public LivingEntity getControllingPassenger()
    {
        Entity var2 = this.getFirstPassenger();
        LivingEntity var10000;
        if (var2 instanceof LivingEntity entity) {
            var10000 = entity;
        } else {
            var10000 = null;
        }

        return var10000;
    }

    @Override
    protected void addPassenger(@NotNull Entity entity)
    {
        if (getPassengers().size() < maxPassengers)
            super.addPassenger(entity);
    }

    @Override
    public void positionRider(@NotNull Entity entity, @NotNull Entity.MoveFunction moveFunction)
    {
        if (entity instanceof LivingEntity passenger)
        {
            passenger.xRotO = this.xRotO;
            List<Double> xz = getPosition(getPassengers().size() % 2 == 0 ? -1 : 1, 5 - (int)(getPassengers().size() / 2));
            moveFunction.accept(entity, xz.get(0), this.getY() + 1.2, xz.get(1));
        }

    }
    @Override
    public @NotNull Vec3 getDismountLocationForPassenger(@NotNull LivingEntity passenger)
    {
        List<Double> xz = getPosition(3.5F, 0.0F);
        return new Vec3(xz.get(0), this.getY(), xz.get(1));
    }
    private List<Double> getPosition(double relative_X, double relative_Z)
    {
        double x = this.getX() - relative_Z * Mth.sin(getYRot() * Mth.DEG_TO_RAD)
                + relative_X* Mth.cos(getYRot() * Mth.DEG_TO_RAD);
        double z = this.getZ() + relative_Z * Mth.cos(getYRot() * Mth.DEG_TO_RAD)
                + relative_X * Mth.sin(getYRot() * Mth.DEG_TO_RAD);
        return List.of(x, z);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers)
    {
        controllers.add((new AnimationController<>(this, "controller", 2, this::getAnimation)));
    }

    private PlayState getAnimation(AnimationState<SchoolBus> state)
    {
        if (state.isMoving() && this.getControllingPassenger() != null)
        {
            state.setAnimation(RawAnimation.begin().then("animation.ModelSchoolBus.move", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }
        else
            return PlayState.STOP;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public AgeableMob getBreedOffspring(@NotNull ServerLevel world, @NotNull AgeableMob entity) {
        return null;
    }

    @Override
    protected float getStandingEyeHeight(@NotNull Pose pose, @NotNull EntityDimensions dimensions) {
        return 0.5F;
    }
}