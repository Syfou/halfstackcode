package com.syfou.halfstackorigins.mixins;

import com.mojang.datafixers.util.Pair;
import com.syfou.halfstackorigins.power.HalfstackPowerTypes;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.item.BucketItem;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.gen.feature.util.CaveSurface;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(EntityBucketItem.class)
public abstract class EntityBucketItemMixin extends BucketItemMixin {

    private static BlockHitResult mixinraycast(World world, PlayerEntity player, RaycastContext.FluidHandling fluidHandling) {
        float f = player.getPitch();
        float g = player.getYaw();
        Vec3d vec3d = player.getEyePos();
        float h = MathHelper.cos(-g * ((float)Math.PI / 180) - (float)Math.PI);
        float i = MathHelper.sin(-g * ((float)Math.PI / 180) - (float)Math.PI);
        float j = -MathHelper.cos(-f * ((float)Math.PI / 180));
        float k = MathHelper.sin(-f * ((float)Math.PI / 180));
        float l = i * j;
        float m = k;
        float n = h * j;
        double d = 5.0;
        Vec3d vec3d2 = vec3d.add((double)l * 5.0, (double)m * 5.0, (double)n * 5.0);
        return world.raycast(new RaycastContext(vec3d, vec3d2, RaycastContext.ShapeType.OUTLINE, fluidHandling, player));
    }

    @Shadow @Final private EntityType<?> entityType;
//    public boolean isTaotieEatingActive = false;

    @Override
    protected void customUseLogic(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        BlockHitResult blockHitResult = this.mixinraycast(world, user, RaycastContext.FluidHandling.NONE);
        if(HalfstackPowerTypes.TAOTIE_BITE.isActive(user) && user.isSneaking()){
            isTaotieEatingActive = true;
            if (blockHitResult.getType() == HitResult.Type.MISS) {
//                System.out.println("entitybucketmixin ran møönch");
                cir.setReturnValue(ItemUsage.consumeHeldItem(world, user, hand));
            }
        } else {
            isTaotieEatingActive = false;
        }
        if (entityType == EntityType.TADPOLE) {
            if (blockHitResult.getType() == HitResult.Type.MISS) {
//                System.out.println("entitybucketmixin ran møönch");
                cir.setReturnValue(ItemUsage.consumeHeldItem(world, user, hand));
            }
        }
    }

    @Override
    protected void customMaxUseTimeLogic(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
//        System.out.println(stack.isFood());
        if (entityType == EntityType.TADPOLE || isTaotieEatingActive) {
            cir.setReturnValue(32);
        }
    }

    @Override
    protected void customFinishUsingLogic(ItemStack stack, World world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir) {
        PlayerEntity player = (PlayerEntity)user;
        if (entityType == EntityType.TADPOLE) {

            //istaotieeatingactive isn't being set to true seen in getFoodComponent

//            System.out.println(stack.isFood());
            if (HalfstackPowerTypes.TAOTIE_BITE.isActive(user)) {
                cir.setReturnValue(player.eatFood(world, stack));
                cir.setReturnValue(stack);
            } else {
                cir.setReturnValue(player.eatFood(world, stack));
                cir.setReturnValue(new ItemStack(Items.BUCKET));
            }
        } else {
            if (HalfstackPowerTypes.TAOTIE_BITE.isActive(user)) {
                cir.setReturnValue(player.eatFood(world, stack));
                cir.setReturnValue(stack);
            } else {
                cir.setReturnValue(player.eatFood(world, stack));
                cir.setReturnValue(new ItemStack(Items.BUCKET));
            }
        }
    }

    @Override
    public void customUseActionLogic(ItemStack stack, CallbackInfoReturnable<UseAction> cir) {
        if (entityType == EntityType.TADPOLE || isTaotieEatingActive) {
            cir.setReturnValue(UseAction.EAT);
        }
    }
}