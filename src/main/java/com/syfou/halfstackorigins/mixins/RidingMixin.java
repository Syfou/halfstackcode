package com.syfou.halfstackorigins.mixins;


import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.HuskEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class RidingMixin {

    @Inject(method = "getHeightOffset", at = @At("RETURN"), cancellable = true)
    private void FloatPlayers(CallbackInfoReturnable<Double> cir){
        Entity entity = (Entity)(Object)this;
        if(entity instanceof PlayerEntity) {
            cir.setReturnValue(cir.getReturnValue() * -1.1);
        }
    }
}
