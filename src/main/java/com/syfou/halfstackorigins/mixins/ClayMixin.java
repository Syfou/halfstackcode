package com.syfou.halfstackorigins.mixins;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(PlayerEntity.class)
public abstract class ClayMixin extends LivingEntity {
    private int clayCount = 0;
    protected ClayMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }
    @Shadow public abstract boolean canConsume(boolean ignoreHunger);

//    @Inject(method = "writeCustomDataToNbt", at = @At("HEAD"))
//    private void SaveClayCount(NbtCompound nbt, CallbackInfo ci){
//        nbt.putInt("clayCount", clayCount);
//    }

    @Inject(method = "eatFood", at = @At("HEAD"), cancellable = true)
    private void IfClay(World world, ItemStack stack, CallbackInfoReturnable<ItemStack> cir) {
        PlayerEntity entity = (PlayerEntity) (Object) this;
        if(clayCount <= 0) {
            if (Objects.equals(stack.getName().getString(), "Clay Ball")) {
                entity.clearStatusEffects();
                clayCount = 200;
            }
        } else {
            cir.setReturnValue(stack);
            cir.cancel();
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void UpdateClayCounter(CallbackInfo ci){
        if(clayCount > 0 && this.random.nextInt(20) == 1){
            clayCount -= 1;
        }
    }
}
