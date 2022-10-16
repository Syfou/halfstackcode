package com.syfou.halfstackorigins.mixins;


import com.syfou.halfstackorigins.power.HalfstackPowerTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(BucketItem.class)
public abstract class BucketItemMixin extends ItemMixin{

    @Shadow public abstract TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand);

    boolean failedUse = false;

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    protected void customUseLogic(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        if(HalfstackPowerTypes.TAOTIE_BITE.isActive(user) && user.isSneaking()){
            isTaotieEatingActive = true;
//            System.out.println("taotie ate");
            cir.setReturnValue(ItemUsage.consumeHeldItem(world, user, hand));
        } else {
            isTaotieEatingActive = false;
        }
    }

    @Inject(method = "use", at = @At("RETURN"))
    protected void didFail(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir){
        if(!cir.getReturnValue().getResult().isAccepted()){
//            System.out.println("failed");
            failedUse = true;
        } else {
//            System.out.println("success");
            failedUse = false;
        }
    }
}