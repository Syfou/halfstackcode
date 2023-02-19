package com.syfou.halfstackorigins.mixins;


import com.syfou.halfstackorigins.power.HalfstackPowerTypes;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(LivingEntity.class)
public abstract class NoSprintMixin extends Entity{
    public NoSprintMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "setSprinting", at = @At("HEAD"), cancellable = true)
    public void customSprinting(boolean sprinting, CallbackInfo ci){
        if(HalfstackPowerTypes.NO_SPRINT.isActive(this)){
            super.setSprinting(false);
            ci.cancel();
        }
    }
//    @Shadow protected abstract void setFlag(int index, boolean value);
//
//    @Shadow @Final private static int SPRINTING_FLAG_INDEX;

//    @Inject(method = "setSprinting", at = @At("HEAD"), cancellable = true)
//    private void setNoSprint (boolean sprint, CallbackInfo ci) {
//        if(HalfstackPowerTypes.NO_SPRINT.isActive(this) ) {
//            ci.cancel();
//        }
//    }
//        if (HalfstackPowerTypes.NO_SPRINT.isActive(player)) {
//            ci.cancel();
//        } else if(HalfstackPowerTypes.KELPIE_BREATHING.isActive(player)){
//            if(sprint && player.isSubmergedInWater() && player.isTouchingWater()){
//                System.out.println("set sprinting false");
//                this.setFlag(SPRINTING_FLAG_INDEX, false);
//                ci.cancel();
//            } else if(!sprint && !player.isSubmergedInWater() && player.isTouchingWater()){
//                this.setFlag(SPRINTING_FLAG_INDEX, true);
//                System.out.println("set sprinting true");
//                ci.cancel();
//            }
//        }
//    }
}