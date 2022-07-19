package com.syfou.halfstackorigins.entities;


import com.syfou.halfstackorigins.Halfstackorigins;
import com.syfou.halfstackorigins.client.HalfstackoriginsClient;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.projectile.thrown.ExperienceBottleEntity;
import net.minecraft.item.Item;
import net.minecraft.network.Packet;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.minecraft.entity.TntEntity;


public class fairyflossentity extends ExperienceBottleEntity{
    public fairyflossentity(EntityType<? extends fairyflossentity> entityType, World world) {
        super(entityType, world);
    }

    public fairyflossentity(World world, LivingEntity owner) {
        super(world, owner );
    }

    public fairyflossentity(World world, double x, double y, double z) {
        super(world, x, y, z );
    }

    @Override
    protected Item getDefaultItem() {return null; }

    @Override
    protected void onCollision(HitResult hitResult) {
//        super.onCollision(hitResult);
        if (this.world instanceof ServerWorld) {
            this.world.syncWorldEvent(WorldEvents.SPLASH_POTION_SPLASHED, this.getBlockPos(), PotionUtil.getColor(Potions.WATER));
            int i = 16;
            ExperienceOrbEntity.spawn((ServerWorld)this.world, this.getPos(), i);
            this.discard();
        }
    }
    @Override
    public Packet createSpawnPacket() {
        return EntitySpawnPacket.create(this, Halfstackorigins.PacketID);
    }
}