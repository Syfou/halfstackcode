package com.syfou.halfstackorigins.mixins;

import com.syfou.halfstackorigins.power.HalfstackPowerTypes;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AirBlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.lang.reflect.Array;

import static net.minecraft.util.math.MathHelper.ceil;
import static net.minecraft.util.math.MathHelper.floor;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {

    @Shadow public abstract void addExperience(int experience);

    @Shadow public abstract HungerManager getHungerManager();

    @Shadow public abstract boolean damage(DamageSource source, float amount);

    @Shadow public int totalExperience;

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

//    @Inject(method = "canConsume", at = @At("HEAD"), cancellable = true)
//    public void injected(boolean ignoreHunger, CallbackInfoReturnable<Boolean> cir){
//        if(!HalfstackPowerTypes.TAOTIE_BITE.isActive(this) && HalfstackPowerTypes.FOOD_ON_HIT.isActive(this) && !ignoreHunger){
//            System.out.println("cant comsume");
//            cir.setReturnValue(false);
//        }
//    }
    @Inject(method = "attack", at = @At("TAIL"))
    public void steal(Entity target, CallbackInfo ci) {
        if (HalfstackPowerTypes.XP_HIT.isActive(this)) {
            float f = (float) this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
            float g = target instanceof LivingEntity ? EnchantmentHelper.getAttackDamage(this.getMainHandStack(), ((LivingEntity) target).getGroup()) : EnchantmentHelper.getAttackDamage(this.getMainHandStack(), EntityGroup.DEFAULT);
            int dmg = ceil((f + g)/2);
            if (target instanceof PlayerEntity) {
                PlayerEntity t = (PlayerEntity) target;
                if (t.totalExperience > dmg) {
                    t.addExperience(-dmg);
                    this.addExperience(dmg);
                }
            } else {
                this.addExperience(dmg);
            }
        } else if (HalfstackPowerTypes.FOOD_ON_HIT.isActive(this)){
            float f = (float) this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
            float g = target instanceof LivingEntity ? EnchantmentHelper.getAttackDamage(this.getMainHandStack(), ((LivingEntity) target).getGroup()) : EnchantmentHelper.getAttackDamage(this.getMainHandStack(), EntityGroup.DEFAULT);
            Integer dmg = floor(f+g);
            Integer armorcount = 0;
            Iterable<ItemStack> armor = target.getArmorItems();
            for(ItemStack m: armor) {
                if (!(m.getItem() instanceof AirBlockItem)){
                    armorcount++;
                }
            }
            if(armorcount == 0) {
                this.getHungerManager().add(dmg, 0);
            } else {
                for(int m=0; m < armorcount; m++){
                    if(this.random.nextInt(4) == 3){
                        System.out.println("failed");
                        return;
                    }
                }
                System.out.println("success");
                this.getHungerManager().add(dmg, 0);
            }
        }
    }
}
