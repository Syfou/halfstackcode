package com.syfou.halfstackorigins.mixins;

import com.syfou.halfstackorigins.Halfstackorigins;
import com.syfou.halfstackorigins.power.HalfstackPowerTypes;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.input.Input;
import net.minecraft.client.input.KeyboardInput;
import net.minecraft.client.option.GameOptions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.w3c.dom.Entity;


@Mixin(KeyboardInput.class)
public abstract class NoSneakMixin extends Input {
    @Inject(method = "tick", at = @At("TAIL"))
    private void tick(boolean slowDown, float f, CallbackInfo ci) {
        if (HalfstackPowerTypes.UNDISMOUNTEABLE.isActive(MinecraftClient.getInstance().player.getVehicle())) {
            this.sneaking = false;
        } else if (HalfstackPowerTypes.NO_SNEAK.isActive(MinecraftClient.getInstance().player)) {
            if (!(MinecraftClient.getInstance().player.hasVehicle())) {
                this.sneaking = false;
            }
        }
    }
}