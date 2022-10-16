package com.syfou.halfstackorigins.mixins;

import com.syfou.halfstackorigins.power.HalfstackPowerTypes;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public abstract class BlockMixin{
    @Inject(method = "onEntityLand", at = @At("HEAD"), cancellable = true)
    private void bounce(BlockView world, Entity entity, CallbackInfo ci){
        if(HalfstackPowerTypes.BOUNCE.isActive(entity) && !entity.bypassesLandingEffects()){
            Vec3d vec3d = entity.getVelocity();
            if (vec3d.y < 0.0) {
                double d = entity instanceof LivingEntity ? 1.0 : 0.8;
                entity.setVelocity(vec3d.x, -vec3d.y * d, vec3d.z);
            }
            ci.cancel();
        }
    }
}