package com.syfou.halfstackorigins.items;


import com.sun.jdi.Location;
import com.syfou.halfstackorigins.power.HalfstackPowerTypes;
//import net.minecraft.data.client.model.VariantSettings;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TridentItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class loong_pearl extends TridentItem {

    public loong_pearl(Settings settings) {
        super(settings);

    }
    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
            if (!(user instanceof PlayerEntity)) {
                return;
            }
            PlayerEntity playerEntity = (PlayerEntity) user;
            int i = this.getMaxUseTime(stack) - remainingUseTicks;
            if (i < 10) {
                return;
            }
            int j = 1;
            if (!world.isClient) {
                stack.damage(1, playerEntity, p -> p.sendToolBreakStatus(user.getActiveHand()));
                if (j == 0) {
                    TridentEntity tridentEntity = new TridentEntity(world, (LivingEntity) playerEntity, stack);
                    tridentEntity.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0f, 2.5f + (float) j * 0.5f, 1.0f);
                    if (playerEntity.getAbilities().creativeMode) {
                        tridentEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
                    } else {

                    }
                    world.spawnEntity(tridentEntity);
                    world.playSoundFromEntity(null, tridentEntity, SoundEvents.ITEM_TRIDENT_THROW, SoundCategory.PLAYERS, 1.0f, 1.0f);
                    if (!playerEntity.getAbilities().creativeMode) {
                        playerEntity.getInventory().removeOne(stack);
                    }
                }
            }
            playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
            if (j > 0) {
                float f = playerEntity.getYaw();
                float g = playerEntity.getPitch();
                float h = -MathHelper.sin(f * ((float) Math.PI / 180)) * MathHelper.cos(g * ((float) Math.PI / 180));
                float k = -MathHelper.sin(g * ((float) Math.PI / 180));
                float l = MathHelper.cos(f * ((float) Math.PI / 180)) * MathHelper.cos(g * ((float) Math.PI / 180));
                float m = MathHelper.sqrt(h * h + k * k + l * l);
                float n = 3.0f * ((1.0f + (float) j) / 4.0f);
                playerEntity.addVelocity(h *= n / m, k *= n / m, l *= n / m);
                playerEntity.useRiptide(20);
                if (playerEntity.isOnGround()) {
                    float o = 1.1999999f;
                    playerEntity.move(MovementType.SELF, new Vec3d(0.0, 1.1999999284744263, 0.0));
                }
//            SoundEvent soundEvent = j >= 3 ? SoundEvents.ITEM_TRIDENT_RIPTIDE_3 : (j == 2 ? SoundEvents.ITEM_TRIDENT_RIPTIDE_2 : SoundEvents.ITEM_TRIDENT_RIPTIDE_1);
//            world.playSoundFromEntity(null, playerEntity, soundEvent, SoundCategory.PLAYERS, 1.0f, 1.0f);

        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (HalfstackPowerTypes.LOONG_PEARL_SPAWN.isActive(user)) {

//        if (itemStack.getDamage() >= itemStack.getMaxDamage() - 1) {
//            return TypedActionResult.fail(itemStack);
//        }
//        if (EnchantmentHelper.getRiptide(itemStack) > 0 ) {
//            return TypedActionResult.fail(itemStack);
//        }
            user.setCurrentHand(hand);
            return TypedActionResult.consume(itemStack);
        } else {
            return TypedActionResult.fail(itemStack);
        }
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return false;
    }


}
