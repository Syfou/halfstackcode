package com.syfou.halfstackorigins.mixins;


import com.syfou.halfstackorigins.power.HalfstackPowerTypes;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(Entity.class)
public abstract class NoBoatEntryMixin {
    @Inject(method = "canStartRiding", at = @At("RETURN"), cancellable = true)
    private void KeepInBoat(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (HalfstackPowerTypes.ON_KELPIE.isActive(MinecraftClient.getInstance().player)) {
            cir.setReturnValue(false);
        }
    }
}
