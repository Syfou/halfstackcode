package com.syfou.halfstackorigins.mixins;

import com.syfou.halfstackorigins.power.HalfstackPowerTypes;
import io.github.apace100.apoli.mixin.EntityAccessor;
import io.github.apace100.origins.power.OriginsPowerTypes;
import io.github.apace100.origins.registry.ModDamageSources;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.tag.FluidTags;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public final class KelpieBreathingMixin {

    @Mixin(LivingEntity.class)
    public static abstract class CanBreatheInWater extends Entity {

        @Shadow
        protected abstract void initDataTracker();

        public CanBreatheInWater(EntityType<?> type, World world) {
            super(type, world);
        }

        @Inject(at = @At("HEAD"), method = "canBreatheInWater", cancellable = true)
        public void doWaterBreathing(CallbackInfoReturnable<Boolean> info) {
            if (HalfstackPowerTypes.KELPIE_BREATHING.isActive(this)) {
                info.setReturnValue(true);
                info.cancel();
            } else if (HalfstackPowerTypes.DROWN.isActive(this)) {
                info.setReturnValue(false);
                info.cancel();
            }
        }
    }

    @Mixin(PlayerEntity.class)
    public static abstract class UpdateAir extends LivingEntity {

        @Shadow
        protected boolean isSubmergedInWater;

        @Shadow
        public abstract boolean isSwimming();

        protected UpdateAir(EntityType<? extends LivingEntity> entityType, World world) {
            super(entityType, world);
        }

        @Inject(at = @At("TAIL"), method = "tick")
        private void tick(CallbackInfo info) {
            if (HalfstackPowerTypes.KELPIE_BREATHING.isActive(this)) {
                if (!this.isSubmergedIn(FluidTags.WATER) && !this.hasStatusEffect(StatusEffects.WATER_BREATHING) && !this.hasStatusEffect(StatusEffects.CONDUIT_POWER)) {
                    if (!((EntityAccessor) this).callIsBeingRainedOn()) {
                        if (this.getAir() > 4) {
                            int landGain = this.getNextAirOnLand(0);
                            if (this.random.nextInt(20) == 1) {
                                this.setAir(this.getNextAirUnderwater(this.getAir()) - landGain);
                            } else {
                                this.setAir(this.getAir() - landGain);
                            }
                        } else {
                            int landGain = this.getNextAirOnLand(0);
                            this.setAir(this.getNextAirUnderwater(this.getAir()) - landGain);

                            if (this.getAir() <= -20) {
                                this.setAir(0);
                                for (int i = 0; i < 8; ++i) {
                                    double f = this.random.nextDouble() - this.random.nextDouble();
                                    double g = this.random.nextDouble() - this.random.nextDouble();
                                    double h = this.random.nextDouble() - this.random.nextDouble();
                                    this.world.addParticle(ParticleTypes.BUBBLE, this.getParticleX(0.5), this.getEyeY() + this.random.nextGaussian() * 0.08D, this.getParticleZ(0.5), f * 0.5F, g * 0.5F + 0.25F, h * 0.5F);
                                }

                                this.damage(ModDamageSources.NO_WATER_FOR_GILLS, 2.0F);
                            }
                        }
                    }
                } else {
                    if (this.random.nextInt(10) == 1) {
                        this.setAir(this.getNextAirOnLand(this.getAir()));
                    }
                }
            }
        }

//
//        @Inject(method = "updateWaterSubmersionState()Z", at = @At("RETURN"), cancellable = true)
//        private void injected(CallbackInfoReturnable<Boolean> inv) {
//            if (HalfstackPowerTypes.KELPIE_BREATHING.isActive(this)) {
//                this.isSubmergedInWater = !this.isSubmergedIn(FluidTags.WATER);
//                inv.setReturnValue(!this.isSubmergedIn(FluidTags.WATER));
//            } else {
//                this.isSubmergedInWater = this.isSubmergedIn(FluidTags.WATER);
//                inv.setReturnValue(this.isSubmergedIn(FluidTags.WATER));
//            }
//        }

        @Inject(method = "isUsingSpyglass", at = @At("HEAD"), cancellable = true)
        private void cancelSpyglass(CallbackInfoReturnable<Boolean> cir) {
            if (HalfstackPowerTypes.TAOTIE_BITE.isActive(this)) {
                cir.setReturnValue(false);
            }
        }
    }
}

