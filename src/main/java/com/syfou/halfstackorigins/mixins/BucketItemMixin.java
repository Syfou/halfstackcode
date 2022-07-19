package com.syfou.halfstackorigins.mixins;


import com.syfou.halfstackorigins.power.HalfstackPowerTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(BucketItem.class)
public class BucketItemMixin extends ItemMixin{

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    protected void customUseLogic(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
    }

//    @Inject(method = "use", at = @At("RETURN"), cancellable = true)
//    private void injected(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
//        if (HalfstackPowerTypes.TAOTIE_BITE.isActive(user) && user.canConsume(false)) {
//            System.out.println("taotie tadpole eat");
//            user.setCurrentHand(hand);
//            cir.setReturnValue(TypedActionResult.consume(user.getStackInHand(hand)));
//        } else if ((cir.getReturnValue().getResult() == ActionResult.PASS) && (Objects.equals(user.getStackInHand(hand).getName().getString(), "Bucket of Tadpole"))) {
//            System.out.println("standard tadpole eat");
//            user.setCurrentHand(hand);
//            cir.setReturnValue(TypedActionResult.consume(user.getStackInHand(hand)));
//        }
//    }
}