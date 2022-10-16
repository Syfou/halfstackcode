package com.syfou.halfstackorigins.mixins;

import com.syfou.halfstackorigins.power.HalfstackPowerTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.EggItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EggItem.class)
public abstract class EggMixin extends Item{
    public EggMixin(Item.Settings settings) {
        super(settings);
    }
    @Shadow public abstract TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand);

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void tick (World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        ItemStack itemStack = user.getStackInHand(hand);
        if(HalfstackPowerTypes.EATS_EGGS.isActive(user) || HalfstackPowerTypes.TAOTIE_BITE.isActive(user) ) {
            if (user.getHungerManager().isNotFull()) {
                super.use(world, user, hand);
                cir.setReturnValue(TypedActionResult.success(itemStack, world.isClient()));
                cir.cancel();
            }
        }
    }
}
