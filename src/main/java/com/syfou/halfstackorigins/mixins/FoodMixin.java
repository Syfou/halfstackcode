package com.syfou.halfstackorigins.mixins;

import com.syfou.halfstackorigins.power.HalfstackPowerTypes;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.PlantBlock;
import net.minecraft.block.TallFlowerBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.tag.TagGroupLoader;
import net.minecraft.text.Text;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Mixin(Item.class)
public abstract class FoodMixin {
    @Shadow
    public abstract Text getName();

    private static final String[] edibleSuperClassesArray = new String[]{"net.minecraft.block.PlantBlock", "net.minecraft.block.GourdBlock"};
    private static final List<String> edibleSuperClasses = Arrays.asList(edibleSuperClassesArray);
    private static final String[] inediblePlantsArray = new String[]{"net.minecraft.block.LilyPadBlock"};
    private static final List<String> inediblePlants = Arrays.asList(inediblePlantsArray);
    private static final String[] edibleClassesArray = new String[]{"net.minecraft.block.TallFlowerBlock", "net.minecraft.block.SporeBlossomBlock", "net.minecraft.block.HayBlock", "net.minecraft.block.KelpBlock", "net.minecraft.block.GlowLichenBlock", "net.minecraft.block.HangingRootsBlock", "net.minecraft.block.BambooBlock", "net.minecraft.block.LeavesBlock", "net.minecraft.block.MossBlock", "net.minecraft.block.CocoaBlock", "net.minecraft.block.BeetrootsBlock", "net.minecraft.block.TurtleEggBlock", "net.minecraft.block.PropaguleBlock"};
    private static final List<String> edibleClasses = Arrays.asList(edibleClassesArray);
    private static final String[] edibleItemsArray = new String[]{"Egg", "Honeycomb", "Blue Orchid", "Glistering Melon Slice", "Dandelion", "Milk Bucket", "Wheat", "Clay Ball", "Sea Pickle", "Bone", "Sugar"};
    private static final List<String> edibleItems = Arrays.asList(edibleItemsArray);
    private static final String[] oneArray = new String[]{"net.minecraft.block.CropBlock", "net.minecraft.block.CocoaBlock", "net.minecraft.block.StemBlock", "net.minecraft.block.BambooBlock", "net.minecraft.block.HangingRootsBlock", "net.minecraft.block.BambooBlock", "net.minecraft.block.DeadBushBlock", "net.minecraft.block.SproutsBlock", "net.minecraft.block.BeetrootsBlock"};
    private static final List<String> one = Arrays.asList(oneArray);
    private static final String[] twoArray = new String[]{"net.minecraft.block.FernBlock", "net.minecraft.block.FlowerBlock", "net.minecraft.block.MossBlock", "net.minecraft.block.SeagrassBlock", "net.minecraft.block.PropaguleBlock"};
    private static final List<String> two = Arrays.asList(twoArray);
    private static final String[] threeArray = new String[]{"net.minecraft.block.RootsBlock", "net.minecraft.block.MushroomPlantBlock", "net.minecraft.block.FungusBlock", "net.minecraft.block.LeavesBlock", "net.minecraft.block.AzaleaBlock", "net.minecraft.block.SaplingBlock", "net.minecraft.block.TallPlantBlock", "net.minecraft.block.NetherWartBlock", "net.minecraft.block.GlowLichenBlock"};
    private static final List<String> three = Arrays.asList(threeArray);
    private static final String[] fourArray = new String[]{"net.minecraft.block.SporeBlossomBlock", "net.minecraft.block.TallFlowerBlock"};
    private static final List<String> four = Arrays.asList(fourArray);
    private static final String[] sixArray = new String[]{"net.minecraft.block.HayBlock", "net.minecraft.block.PumpkinBlock", "net.minecraft.block.MelonBlock", "net.minecraft.block.TurtleEggBlock"};
    private static final List<String> six = Arrays.asList(sixArray);
    private static final String[] itemsNoneArray = new String[]{"Clay Ball"};
    private static final List<String> itemsNone = Arrays.asList(itemsNoneArray);
    private static final String[] itemsTwoArray = new String[]{"Egg", "Milk Bucket", "Wheat", "Bone", "Sugar", "Bucket of Tadpole"};
    private static final List<String> itemsTwo = Arrays.asList(itemsTwoArray);
    private static final String[] itemsThreeArray = new String[]{"Glistering Melon Slice", "Blue Orchid", "Dandelion", "Sea Pickle"};
    private static final List<String> itemsThree = Arrays.asList(itemsThreeArray);
    private static final String[] itemsSixArray = new String[]{"Honeycomb"};
    private static final List<String> itemsSix = Arrays.asList(itemsSixArray);

    @Inject(method = "isFood", at = @At("HEAD"), cancellable = true)
    private void InsertFood(CallbackInfoReturnable<Boolean> cir) {
//        if (((Item)(Object) this) instanceof BlockItem) {
//            BlockItem item = ((BlockItem) (Object) this);
//            Block block = item.getBlock();
//            if (edibleSuperClasses.contains(block.getClass().getSuperclass().getName()) && !(inediblePlants.contains(block.getClass().getName()))) {
//                cir.setReturnValue(true);
//            } else if(edibleClasses.contains(block.getClass().getName())) {
//                cir.setReturnValue(true);
//            }
//        } else if (((Item)(Object) this) instanceof Item) {
//            Item item = ((Item)(Object) this);
//            if (edibleItems.contains(item.getName().getString())) {
//                cir.setReturnValue(true);
//            }
//        }
        if (!Objects.equals(this.getName().getString(), "Bucket of Axolotl") && !Objects.equals(this.getName().getString(), "Lily Pad") && !Objects.equals(this.getName().getString(), "Frogspawn")) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "getFoodComponent", at = @At("HEAD"), cancellable = true)
    private void addComponents(CallbackInfoReturnable<FoodComponent> cir) {
        if ((edibleItems.contains(((Item) (Object) this).getName().getString()))) {
            Item item = ((Item) (Object) this);
            FoodComponent.Builder component = new FoodComponent.Builder();
            if (itemsNone.contains(item.getName().getString())) {
                cir.setReturnValue(component.hunger(0).saturationModifier(0).snack().build());
            }
            if (itemsTwo.contains(item.getName().getString())) {
                cir.setReturnValue(component.hunger(2).saturationModifier(1).snack().build());
            } else if (itemsThree.contains(item.getName().getString())) {
                cir.setReturnValue(component.hunger(3).saturationModifier(3).build());
            } else if (itemsSix.contains(item.getName().getString())) {
                cir.setReturnValue(component.hunger(6).saturationModifier(5).build());
            } else {
                cir.setReturnValue(component.hunger(2).saturationModifier(1).build());
            }
        } else if (((Item) (Object) this) instanceof BlockItem || ((Item) (Object) this) instanceof PlaceableOnWaterItem) {
            BlockItem item = ((BlockItem) (Object) this);
            Block block = item.getBlock();
            FoodComponent.Builder component = new FoodComponent.Builder();
            if (one.contains(block.getClass().getName())) {
                cir.setReturnValue(component.hunger(1).saturationModifier(1).snack().build());
            } else if (two.contains(block.getClass().getName())) {
                cir.setReturnValue(component.hunger(2).saturationModifier(1).snack().build());
            } else if (three.contains(block.getClass().getName())) {
                cir.setReturnValue(component.hunger(3).saturationModifier(3).build());
            } else if (four.contains(block.getClass().getName())) {
                cir.setReturnValue(component.hunger(4).saturationModifier(3).build());
            } else if (six.contains(block.getClass().getName())) {
                cir.setReturnValue(component.hunger(6).saturationModifier(5).build());
            } else {
                cir.setReturnValue(component.hunger(2).saturationModifier(1).build());
            }
        } else {
            FoodComponent.Builder component = new FoodComponent.Builder();
            cir.setReturnValue(component.hunger(2).saturationModifier(1).snack().build());
        }
    }

    @Inject(method = "finishUsing", at = @At("HEAD"), cancellable = true)
    private void injected(ItemStack stack, World world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir) {
        if (Objects.equals(stack.getItem().getName().getString(), "Bucket of Tadpole")) {
            System.out.println("ran");
            if (user instanceof ServerPlayerEntity) {
                ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) user;
                Criteria.CONSUME_ITEM.trigger(serverPlayerEntity, stack);
                serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(stack.getItem()));
            }
            if (user instanceof PlayerEntity && !((PlayerEntity) user).getAbilities().creativeMode) {
                stack.decrement(1);
            }
            if (stack.isEmpty()) {
                System.out.println("gave bucket");
                cir.setReturnValue(new ItemStack(Items.BUCKET));
            }
            cir.setReturnValue(stack);
        }
    }
}
