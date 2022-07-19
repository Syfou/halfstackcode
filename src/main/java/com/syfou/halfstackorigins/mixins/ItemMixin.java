package com.syfou.halfstackorigins.mixins;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(Item.class)
public class ItemMixin {
    @Inject(method = "getUseAction", at = @At("HEAD"), cancellable = true)
    public void customUseActionLogic(ItemStack stack, CallbackInfoReturnable<UseAction> cir) {
    }

    @Inject(method = "getMaxUseTime", at = @At("HEAD"), cancellable = true)
    protected void customMaxUseTimeLogic(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
    }

    @Inject(method = "finishUsing", at = @At("HEAD"), cancellable = true)
    protected void customFinishUsingLogic(ItemStack stack, World world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir) {
    }
}