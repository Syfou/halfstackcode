package com.syfou.halfstackorigins.power;

import com.syfou.halfstackorigins.Halfstackorigins;
import io.github.apace100.apoli.power.PowerType;
import io.github.apace100.apoli.power.PowerTypeReference;
import io.github.apace100.apoli.power.factory.PowerFactory;
import io.github.apace100.apoli.registry.ApoliRegistries;
import io.github.apace100.origins.Origins;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class HalfstackPowerTypes {

    public static final PowerType<?> KELPIE_BREATHING = new PowerTypeReference<>(new Identifier("halfstackorigins","kelpie_breathing"));
    public static final PowerType<?> HORSE_FEAR = new PowerTypeReference<>(new Identifier("halfstackorigins","horse_fear"));
    public static final PowerType<?> NO_SNEAK = new PowerTypeReference<>(new Identifier("halfstackorigins","no_sneak"));
    public static final PowerType<?> NO_SPRINT = new PowerTypeReference<>(new Identifier("halfstackorigins","no_sprint"));
    public static final PowerType<?> UNDISMOUNTEABLE = new PowerTypeReference<>(new Identifier("halfstackorigins","undismountable"));
    public static final PowerType<?> ON_KELPIE = new PowerTypeReference<>(new Identifier("halfstackorigins","on_kelpie"));
    public static final PowerType<?> LOONG_PEARL_SPAWN = new PowerTypeReference<>(new Identifier("halfstackorigins","loong_pearl_spawn"));
    public static final PowerType<?> EATS_EGGS = new PowerTypeReference<>(new Identifier("halfstackorigins","eats_eggs"));
    public static final PowerType<?> DROWN = new PowerTypeReference<>(new Identifier("halfstackorigins","drown"));
    public static final PowerType<?> TAOTIE_BITE = new PowerTypeReference<>(new Identifier("halfstackorigins","taotie_bite"));


}
