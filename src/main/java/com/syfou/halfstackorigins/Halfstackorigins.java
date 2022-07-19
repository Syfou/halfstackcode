package com.syfou.halfstackorigins;


import com.syfou.halfstackorigins.entities.fairyflossentity;
import com.syfou.halfstackorigins.items.fairy_floss;
import com.syfou.halfstackorigins.items.loong_pearl;
import io.github.apace100.origins.Origins;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Halfstackorigins implements ModInitializer {
    public static final String MOD_ID = "halfstackorigins";

    public static final Identifier PacketID = new Identifier(Halfstackorigins.MOD_ID, "spawn_packet");


    public static final Item LOONG_PEARL = new loong_pearl(new FabricItemSettings().group(ItemGroup.MISC));
    public static final Item FAIRY_FLOSS = new fairy_floss(new FabricItemSettings().group(ItemGroup.MISC));
    public static final EntityType<fairyflossentity> fairyflossentityEntityType = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID,"fairy_floss_entity"),
            FabricEntityTypeBuilder.<fairyflossentity>create(SpawnGroup.MISC, fairyflossentity::new)
                    .dimensions(EntityDimensions.fixed(0.25f, 0.25f))
                    .trackRangeBlocks(4).trackedUpdateRate(10)
                    .build()

    );

    public static final Logger LOGGER = LoggerFactory.getLogger("halfstackorigins");

    @Override
    public void onInitialize() {

        Registry.register(Registry.ITEM, new Identifier("halfstackorigins", "loong_pearl"), LOONG_PEARL);
        Registry.register(Registry.ITEM, new Identifier("halfstackorigins", "fairy_floss"), FAIRY_FLOSS);

    }
    public static Identifier identifier(String path) {
        return new Identifier(Origins.MODID, path);
    }

    @Config(name = Origins.MODID + "_server")
    public static class ServerConfig implements ConfigData {

        public boolean performVersionCheck = true;
    }
}
