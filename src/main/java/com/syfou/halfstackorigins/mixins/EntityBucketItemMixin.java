package com.syfou.halfstackorigins.mixins;

import net.fabricmc.fabric.mixin.transfer.BucketItemMixin;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.EntityBucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityBucketItem.class)
public class EntityBucketItemMixin extends BucketItemMixin {
    @Shadow @Final private EntityType<?> entityType;

    @Override
    protected void customUseLogic(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        if (entityType == EntityType.TADPOLE) {
            cir.setReturnValue(ItemUsage.consumeHeldItem(world, user, hand));
        }
    }

    @Override
    protected void customMaxUseTimeLogic(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        if (entityType == EntityType.TADPOLE) {
            cir.setReturnValue(32);
        }
    }

    @Override
    protected void customFinishUsingLogic(ItemStack stack, World world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir) {
        if (entityType == EntityType.TADPOLE) {
            cir.setReturnValue(new ItemStack(Items.BUCKET));
        }
    }

    @Override
    public void customUseActionLogic(ItemStack stack, CallbackInfoReturnable<UseAction> cir) {
        if (entityType == EntityType.TADPOLE) {
            cir.setReturnValue(UseAction.EAT);
        }
    }
}