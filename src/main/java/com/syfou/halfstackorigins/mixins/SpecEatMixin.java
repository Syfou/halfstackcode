package com.syfou.halfstackorigins.mixins;


import com.syfou.halfstackorigins.power.HalfstackPowerTypes;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import org.checkerframework.checker.units.qual.A;
import org.checkerframework.common.value.qual.IntRangeFromNonNegative;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public abstract class SpecEatMixin extends ItemMixin{

    @Mixin(EnderEyeItem.class)
    public static abstract class EyeEating extends ItemMixin{

        @Inject(method = "use", at = @At("HEAD"), cancellable = true)
        private void injected(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
            isTaotieEatingActive = HalfstackPowerTypes.TAOTIE_BITE.isActive(user) && user.isSneaking() && user.canConsume(false);
            if (HalfstackPowerTypes.TAOTIE_BITE.isActive(user) && user.canConsume(false)) {
                user.setCurrentHand(hand);
                cir.setReturnValue(TypedActionResult.consume(user.getStackInHand(hand)));
            }
        }
    }


    @Mixin(WritableBookItem.class)
    public static abstract class BookEating extends ItemMixin{

        @Inject(method = "use", at = @At("HEAD"), cancellable = true)
        private void injected(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
            isTaotieEatingActive = HalfstackPowerTypes.TAOTIE_BITE.isActive(user) && user.isSneaking() && user.canConsume(false);
            if (isTaotieEatingActive) {
                user.setCurrentHand(hand);
                cir.setReturnValue(TypedActionResult.consume(user.getStackInHand(hand)));
            }
        }
    }


    @Mixin(WrittenBookItem.class)
    public static abstract class WrittenBookEating extends ItemMixin{

        @Inject(method = "use", at = @At("HEAD"), cancellable = true)
        private void injected(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
            isTaotieEatingActive = HalfstackPowerTypes.TAOTIE_BITE.isActive(user) && user.isSneaking() && user.canConsume(false);
            if (isTaotieEatingActive) {
                user.setCurrentHand(hand);
                cir.setReturnValue(TypedActionResult.consume(user.getStackInHand(hand)));
            }
        }
    }

    @Mixin(ExperienceBottleItem.class)
    public static abstract class XPBottleEating extends ItemMixin{

        @Inject(method = "use", at = @At("HEAD"), cancellable = true)
        private void injected(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
            isTaotieEatingActive = HalfstackPowerTypes.TAOTIE_BITE.isActive(user) && user.isSneaking() && user.canConsume(false);
            if (isTaotieEatingActive) {
                user.setCurrentHand(hand);
                cir.setReturnValue(TypedActionResult.consume(user.getStackInHand(hand)));
                cir.cancel();
            }
        }
    }

    @Mixin(FireworkRocketItem.class)
    public static abstract class RocketEating  extends ItemMixin{

        @Inject(method = "use", at = @At("HEAD"), cancellable = true)
        private void injected(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
            isTaotieEatingActive = HalfstackPowerTypes.TAOTIE_BITE.isActive(user) && user.isSneaking() && user.canConsume(false);
            if (isTaotieEatingActive) {
                user.setCurrentHand(hand);
                cir.setReturnValue(TypedActionResult.consume(user.getStackInHand(hand)));
            }
        }
    }

    @Mixin(GoatHornItem.class)
    public static abstract class HornEating  extends ItemMixin{

        @Inject(method = "use", at = @At("HEAD"), cancellable = true)
        private void injected(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
            isTaotieEatingActive = HalfstackPowerTypes.TAOTIE_BITE.isActive(user) && user.isSneaking() && user.canConsume(false);
            if (isTaotieEatingActive) {
                user.setCurrentHand(hand);
                cir.setReturnValue(TypedActionResult.consume(user.getStackInHand(hand)));
            }
        }
    }

    @Mixin(SpyglassItem.class)
    public static abstract class GlassEating  extends ItemMixin{

        @Inject(method = "use", at = @At("HEAD"), cancellable = true)
        private void injected(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
            isTaotieEatingActive = HalfstackPowerTypes.TAOTIE_BITE.isActive(user) && user.isSneaking() && user.canConsume(false);
            if (isTaotieEatingActive) {
                user.setCurrentHand(hand);
                cir.setReturnValue(TypedActionResult.consume(user.getStackInHand(hand)));
            }
        }

        @Inject(method = "finishUsing", at = @At("HEAD"))
        private void injected(ItemStack stack, World world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir){
            PlayerEntity player = (PlayerEntity)user;
            player.eatFood(world, stack);
        }

        @Inject(method = "getMaxUseTime", at = @At("HEAD"), cancellable = true)
        private void useinject(ItemStack stack, CallbackInfoReturnable<Integer> cir){
            cir.setReturnValue(32);
            cir.cancel();
        }

        @Inject(method = "getUseAction", at = @At("RETURN"), cancellable = true)
        private void  injected(ItemStack stack, CallbackInfoReturnable<UseAction> cir){
            if(isTaotieEatingActive) {
                cir.setReturnValue(UseAction.EAT);
            }
        }
    }

    @Mixin(ArmorItem.class)
    public static abstract class ArmorEating  extends ItemMixin{

        @Inject(method = "use", at = @At("HEAD"), cancellable = true)
        private void injected(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
            isTaotieEatingActive = HalfstackPowerTypes.TAOTIE_BITE.isActive(user) && user.isSneaking() && user.canConsume(false);
            if (isTaotieEatingActive) {
                user.setCurrentHand(hand);
                cir.setReturnValue(TypedActionResult.consume(user.getStackInHand(hand)));
                cir.cancel();
            }
        }
    }

    @Mixin(ElytraItem.class)
    public static abstract class ElytraEating  extends ItemMixin{

        @Inject(method = "use", at = @At("HEAD"), cancellable = true)
        private void injected(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
            isTaotieEatingActive = HalfstackPowerTypes.TAOTIE_BITE.isActive(user) && user.isSneaking() && user.canConsume(false);
            if (isTaotieEatingActive) {
                user.setCurrentHand(hand);
                cir.setReturnValue(TypedActionResult.consume(user.getStackInHand(hand)));
            }
        }
    }

    @Mixin(ShieldItem.class)
    public static abstract class ShieldEating  extends ItemMixin{

        @Inject(method = "use", at = @At("HEAD"), cancellable = true)
        private void injected(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
            isTaotieEatingActive = HalfstackPowerTypes.TAOTIE_BITE.isActive(user) && user.isSneaking() && user.canConsume(false);
            if (isTaotieEatingActive) {
                user.setCurrentHand(hand);
                cir.setReturnValue(TypedActionResult.consume(user.getStackInHand(hand)));
            }
        }

        @Inject(method = "getUseAction", at = @At("RETURN"), cancellable = true)
        private void  modifyuseaction(ItemStack stack, CallbackInfoReturnable<UseAction> cir){
            if(isTaotieEatingActive) {
                cir.setReturnValue(UseAction.EAT);
            }
        }

        @Inject(method = "getMaxUseTime", at = @At("HEAD"), cancellable = true)
        private void injected(ItemStack stack, CallbackInfoReturnable<Integer> cir){
            if (isTaotieEatingActive) {
                cir.setReturnValue(16);
            }
        }
    }

    @Mixin(CrossbowItem.class)
    public static abstract class CrossbowEating extends ItemMixin{

        @Shadow public abstract UseAction getUseAction(ItemStack stack);

        //        public CrossbowEating(Settings settings) {
//            super(settings);
//        }

            @Inject(method = "use", at = @At("HEAD"), cancellable = true)
            private void injected(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
                isTaotieEatingActive = HalfstackPowerTypes.TAOTIE_BITE.isActive(user) && user.isSneaking() && user.canConsume(false);
                if (isTaotieEatingActive) {
                user.setCurrentHand(hand);
                cir.setReturnValue(TypedActionResult.consume(user.getStackInHand(hand)));
            }
        }

        @Inject(method = "getUseAction", at = @At("HEAD"), cancellable = true)
            protected void TaotieEats(ItemStack stack, CallbackInfoReturnable<UseAction> cir){
            if (isTaotieEatingActive) {
                cir.setReturnValue(UseAction.EAT);
            }
        }

        @Inject(method = "getMaxUseTime", at = @At("HEAD"), cancellable = true)
        private void injected(ItemStack stack, CallbackInfoReturnable<Integer> cir){
            if (isTaotieEatingActive) {
                cir.setReturnValue(16);
            }
        }

        @Inject(method = "isUsedOnRelease", at = @At("HEAD"), cancellable = true)
        private void notUsedOnRelease(ItemStack stack, CallbackInfoReturnable<Boolean> cir){
            if (isTaotieEatingActive) {
                cir.setReturnValue(false);
            }
        }

        @Inject(method = "onStoppedUsing", at = @At("HEAD"), cancellable = true)
        private void download(ItemStack stack, World world, LivingEntity user, int remainingUseTicks, CallbackInfo ci) {
            if (isTaotieEatingActive) {
                ci.cancel();
            }
        }
    }

    @Mixin(EnderPearlItem.class)
    public static abstract class PearlEating  extends ItemMixin {

        @Inject(method = "use", at = @At("HEAD"), cancellable = true)
        private void injected(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        isTaotieEatingActive = HalfstackPowerTypes.TAOTIE_BITE.isActive(user) && user.isSneaking() && user.canConsume(false);
        if (isTaotieEatingActive) {
                user.setCurrentHand(hand);
                cir.setReturnValue(TypedActionResult.consume(user.getStackInHand(hand)));
            }
        }
    }

    @Mixin(GlassBottleItem.class)
    public static abstract class BottleEating  extends ItemMixin{

        @Inject(method = "use", at = @At("HEAD"), cancellable = true)
        private void injected(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
            isTaotieEatingActive = HalfstackPowerTypes.TAOTIE_BITE.isActive(user) && user.isSneaking() && user.canConsume(false);
            if (isTaotieEatingActive) {
                user.setCurrentHand(hand);
                cir.setReturnValue(TypedActionResult.consume(user.getStackInHand(hand)));
            }
        }
    }

    @Mixin(TridentItem.class)
    public static abstract class TridentEating extends ItemMixin{

        @Inject(method = "getUseAction", at = @At("HEAD"), cancellable = true)
        private void injected(ItemStack stack, CallbackInfoReturnable<UseAction> cir){
            if(isTaotieEatingActive){
                cir.setReturnValue(UseAction.EAT);
            }
        }

        @Inject(method = "use", at = @At("HEAD"), cancellable = true)
        private void setTaotieEating(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir){
            isTaotieEatingActive = HalfstackPowerTypes.TAOTIE_BITE.isActive(user);
            if (HalfstackPowerTypes.TAOTIE_BITE.isActive(user) && user.canConsume(false)) {
                user.setCurrentHand(hand);
                cir.setReturnValue(TypedActionResult.consume(user.getStackInHand(hand)));
            }
        }

        @Inject(method = "onStoppedUsing", at = @At("HEAD"), cancellable = true)
        private void dontthrow(ItemStack stack, World world, LivingEntity user, int remainingUseTicks, CallbackInfo ci){
            if(HalfstackPowerTypes.TAOTIE_BITE.isActive(user)){
                ci.cancel();
            }
        }

        @Inject(method = "getMaxUseTime", at = @At("HEAD"), cancellable = true)
        private void shortusetime(ItemStack stack, CallbackInfoReturnable<Integer> cir){
            if(isTaotieEatingActive){
                cir.setReturnValue(16);
            }
        }
    }

    @Mixin(GoatHornItem.class)
    public static abstract class GoatHornMixin extends ItemMixin{
        @Inject(method = "getUseAction", at = @At("HEAD"), cancellable = true)
        private void injected(ItemStack stack, CallbackInfoReturnable<UseAction> cir){
            if(isTaotieEatingActive){
                cir.setReturnValue(UseAction.EAT);
            }
        }

        @Inject(method = "use", at = @At("HEAD"), cancellable = true)
        private void setTaotieEating(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir){
                isTaotieEatingActive = HalfstackPowerTypes.TAOTIE_BITE.isActive(user) && user.isSneaking() && user.canConsume(false);
                if (isTaotieEatingActive) {
                user.setCurrentHand(hand);
                cir.setReturnValue(TypedActionResult.consume(user.getStackInHand(hand)));
            }
        }

        @Inject(method = "getMaxUseTime", at = @At("HEAD"), cancellable = true)
        private void shortusetime(ItemStack stack, CallbackInfoReturnable<Integer> cir){
            if(isTaotieEatingActive){
                cir.setReturnValue(16);
            }
        }
    }
    @Mixin(BowItem.class)
    public static abstract class BowMixin extends ItemMixin{
        //        public CrossbowEating(Settings settings) {
//            super(settings);
//        }
        @Inject(method = "use", at = @At("HEAD"), cancellable = true)
        private void injected(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
            isTaotieEatingActive = HalfstackPowerTypes.TAOTIE_BITE.isActive(user);
            if (HalfstackPowerTypes.TAOTIE_BITE.isActive(user) && user.canConsume(false)) {
                user.setCurrentHand(hand);
                cir.setReturnValue(TypedActionResult.consume(user.getStackInHand(hand)));
            }
        }

        @Inject(method = "getUseAction", at = @At("HEAD"), cancellable = true)
        protected void TaotieEats(ItemStack stack, CallbackInfoReturnable<UseAction> cir){
            if (isTaotieEatingActive) {
                cir.setReturnValue(UseAction.EAT);
            }
        }

        @Inject(method = "getMaxUseTime", at = @At("HEAD"), cancellable = true)
        private void injected(ItemStack stack, CallbackInfoReturnable<Integer> cir){
            if (isTaotieEatingActive) {
                cir.setReturnValue(16);
            }
        }

        @Inject(method = "onStoppedUsing", at = @At("HEAD"), cancellable = true)
        private void download(ItemStack stack, World world, LivingEntity user, int remainingUseTicks, CallbackInfo ci) {
            if (isTaotieEatingActive) {
                ci.cancel();
            }
        }
    }

    @Mixin(ModelPredicateProviderRegistry.class)
    public static abstract class ModelProviderMixin {
        static{
            ModelPredicateProviderRegistry.register(Items.TRIDENT, new Identifier("throwing"), (stack, world, entity, seed) -> {
                if(entity != null && entity.isUsingItem() && entity.getActiveItem() == stack){
                    if (HalfstackPowerTypes.TAOTIE_BITE.isActive(entity)){
                        return 0.0f;
                    } else {
                        return 1.0f;
                    }
                } else {
                    return 0.0f;
                }
            }
            );
            ModelPredicateProviderRegistry.register(Items.CROSSBOW, new Identifier("pull"), (stack, world, entity, seed) -> {
                if (entity == null) {
                    return 0.0f;
                }
                if (CrossbowItem.isCharged(stack)) {
                    return 0.0f;
                } if (HalfstackPowerTypes.TAOTIE_BITE.isActive(entity)){
                    return 0.0f;
                }
                return (float)(stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / (float)CrossbowItem.getPullTime(stack);
            });
//            ModelPredicateProviderRegistry.register(Items.CROSSBOW, new Identifier("pulling"), (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack && !CrossbowItem.isCharged(stack) && !HalfstackPowerTypes.TAOTIE_BITE.isActive(entity) ? 1.0f : 0.0f);
//            ModelPredicateProviderRegistry.register(Items.CROSSBOW, new Identifier("charged"), (stack, world, entity, seed) -> entity != null && CrossbowItem.isCharged(stack) && !HalfstackPowerTypes.TAOTIE_BITE.isActive(entity) ? 1.0f : 0.0f);

            ModelPredicateProviderRegistry.register(Items.BOW, new Identifier("pull"), (stack, world, entity, seed) -> {
                if (entity == null) {
                    return 0.0f;
                }
                if (entity.getActiveItem() != stack) {
                    return 0.0f;
                } if (HalfstackPowerTypes.TAOTIE_BITE.isActive(entity)){
                    return 0.0f;
                }
                return (float)(stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / 20.0f;
            });
            ModelPredicateProviderRegistry.register(Items.FISHING_ROD, new Identifier("cast"), (stack, world, entity, seed) -> {
                boolean bl2;
                if (entity == null) {
                    return 0.0f;
                }
                if (HalfstackPowerTypes.TAOTIE_BITE.isActive(entity)) {
                    return 0.0f;
                }
                boolean bl = entity.getMainHandStack() == stack;
                boolean bl3 = bl2 = entity.getOffHandStack() == stack;
                if (entity.getMainHandStack().getItem() instanceof FishingRodItem) {
                    bl2 = false;
                }
                return (bl || bl2) && entity instanceof PlayerEntity && ((PlayerEntity)entity).fishHook != null ? 1.0f : 0.0f;
            });
        }
    }

    @Mixin(FishingRodItem.class)
    public static abstract class FishingRodMixin extends ItemMixin {
        @Inject(method = "use", at = @At("HEAD"), cancellable = true)
        private void injected(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        isTaotieEatingActive = HalfstackPowerTypes.TAOTIE_BITE.isActive(user) && user.isSneaking() && user.canConsume(false);
        if (isTaotieEatingActive) {
                user.setCurrentHand(hand);
                cir.setReturnValue(TypedActionResult.consume(user.getStackInHand(hand)));
            }
        }
    }
}
