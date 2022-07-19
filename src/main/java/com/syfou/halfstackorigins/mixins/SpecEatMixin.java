package com.syfou.halfstackorigins.mixins;


import com.syfou.halfstackorigins.power.HalfstackPowerTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

public class SpecEatMixin {


    @Mixin(EnderEyeItem.class)
    public static abstract class EyeEating {

        @Inject(method = "use", at = @At("HEAD"), cancellable = true)
        private void injected(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
            if (HalfstackPowerTypes.TAOTIE_BITE.isActive(user) && user.canConsume(false)) {
                user.setCurrentHand(hand);
                cir.setReturnValue(TypedActionResult.consume(user.getStackInHand(hand)));
            }
        }
    }

    @Mixin(ExperienceBottleItem.class)
    public static abstract class XPBottleEating {

        @Inject(method = "use", at = @At("HEAD"), cancellable = true)
        private void injected(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
            if (HalfstackPowerTypes.TAOTIE_BITE.isActive(user) && user.canConsume(false)) {
                user.setCurrentHand(hand);
                cir.setReturnValue(TypedActionResult.consume(user.getStackInHand(hand)));
            }
        }
    }

    @Mixin(FireworkRocketItem.class)
    public static abstract class RocketEating {

        @Inject(method = "use", at = @At("HEAD"), cancellable = true)
        private void injected(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
            if (HalfstackPowerTypes.TAOTIE_BITE.isActive(user) && user.canConsume(false)) {
                user.setCurrentHand(hand);
                cir.setReturnValue(TypedActionResult.consume(user.getStackInHand(hand)));
            }
        }
    }

    @Mixin(GoatHornItem.class)
    public static abstract class HornEating {

        @Inject(method = "use", at = @At("HEAD"), cancellable = true)
        private void injected(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
            if (HalfstackPowerTypes.TAOTIE_BITE.isActive(user) && user.canConsume(false)) {
                user.setCurrentHand(hand);
                cir.setReturnValue(TypedActionResult.consume(user.getStackInHand(hand)));
            }
        }
    }

    @Mixin(SpyglassItem.class)
    public static abstract class GlassEating {

        @Inject(method = "use", at = @At("HEAD"), cancellable = true)
        private void injected(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
            if (HalfstackPowerTypes.TAOTIE_BITE.isActive(user) && user.canConsume(false)) {
                user.setCurrentHand(hand);
                cir.setReturnValue(TypedActionResult.consume(user.getStackInHand(hand)));
            }
        }
    }

    @Mixin(ArmorItem.class)
    public static abstract class ArmorEating {

        @Inject(method = "use", at = @At("HEAD"), cancellable = true)
        private void injected(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
            if (HalfstackPowerTypes.TAOTIE_BITE.isActive(user) && user.canConsume(false)) {
                user.setCurrentHand(hand);
                cir.setReturnValue(TypedActionResult.consume(user.getStackInHand(hand)));
            }
        }
    }

    @Mixin(ElytraItem.class)
    public static abstract class ElytraEating {

        @Inject(method = "use", at = @At("HEAD"), cancellable = true)
        private void injected(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
            if (HalfstackPowerTypes.TAOTIE_BITE.isActive(user) && user.canConsume(false)) {
                user.setCurrentHand(hand);
                cir.setReturnValue(TypedActionResult.consume(user.getStackInHand(hand)));
            }
        }
    }

    @Mixin(ShieldItem.class)
    public static abstract class ShieldEating {

        @Inject(method = "use", at = @At("HEAD"), cancellable = true)
        private void injected(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
            if (HalfstackPowerTypes.TAOTIE_BITE.isActive(user) && user.canConsume(false)) {
                user.setCurrentHand(hand);
                cir.setReturnValue(TypedActionResult.consume(user.getStackInHand(hand)));
            }
        }
    }

    @Mixin(BowItem.class)
    public static abstract class BowEating {

        @Inject(method = "use", at = @At("HEAD"), cancellable = true)
        private void injected(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
            if (HalfstackPowerTypes.TAOTIE_BITE.isActive(user) && user.canConsume(false)) {
                user.setCurrentHand(hand);
                cir.setReturnValue(TypedActionResult.consume(user.getStackInHand(hand)));
            }
        }
    }

    @Mixin(CrossbowItem.class)
    public static abstract class CrossbowEating {

        @Inject(method = "use", at = @At("HEAD"), cancellable = true)
        private void injected(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
//            if (HalfstackPowerTypes.TAOTIE_BITE.isActive(user) && user.canConsume(false)) {
//                user.setCurrentHand(hand);
//                cir.setReturnValue(TypedActionResult.consume(user.getStackInHand(hand)));
//            }
            cir.setReturnValue(TypedActionResult.fail(user.getStackInHand(hand)));
        }
    }

    @Mixin(TridentItem.class)
    public static abstract class TridentEating {

        @Inject(method = "use", at = @At("HEAD"), cancellable = true)
        private void injected(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
            if (HalfstackPowerTypes.TAOTIE_BITE.isActive(user) && user.canConsume(false)) {
                user.setCurrentHand(hand);
                cir.setReturnValue(TypedActionResult.consume(user.getStackInHand(hand)));
            }
        }
    }

    @Mixin(EnderPearlItem.class)
    public static abstract class PearlEating {

        @Inject(method = "use", at = @At("HEAD"), cancellable = true)
        private void injected(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
            if (HalfstackPowerTypes.TAOTIE_BITE.isActive(user) && user.canConsume(false)) {
                user.setCurrentHand(hand);
                cir.setReturnValue(TypedActionResult.consume(user.getStackInHand(hand)));
            }
        }
    }

    @Mixin(GlassBottleItem.class)
    public static abstract class BottleEating {

        @Inject(method = "use", at = @At("HEAD"), cancellable = true)
        private void injected(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
            if (HalfstackPowerTypes.TAOTIE_BITE.isActive(user) && user.canConsume(false)) {
                user.setCurrentHand(hand);
                cir.setReturnValue(TypedActionResult.consume(user.getStackInHand(hand)));
            }
        }
    }
}
