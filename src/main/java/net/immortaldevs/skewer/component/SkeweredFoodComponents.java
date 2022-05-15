package net.immortaldevs.skewer.component;

import it.unimi.dsi.fastutil.objects.Reference2ReferenceMap;
import it.unimi.dsi.fastutil.objects.Reference2ReferenceOpenHashMap;
import net.immortaldevs.sar.api.Component;
import net.immortaldevs.sar.api.SarRegistries;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.registry.Registry;

import java.util.Objects;

import static net.immortaldevs.skewer.Skewer.locate;

public class SkeweredFoodComponents {
    private static final Reference2ReferenceMap<Item, Component> FOODS =
            new Reference2ReferenceOpenHashMap<>();

    public static final SkeweredFoodComponent SKEWERED_APPLE = add("skewered_apple", Items.APPLE);
    public static final SkeweredFoodComponent SKEWERED_BAKED_POTATO = add("skewered_baked_potato", Items.BAKED_POTATO);
    public static final SkeweredFoodComponent SKEWERED_CARROT = add("skewered_carrot", Items.CARROT);
    public static final SkeweredFoodComponent SKEWERED_CHORUS_FRUIT = add("skewered_chorus_fruit", Items.CHORUS_FRUIT);
    public static final SkeweredFoodComponent SKEWERED_BEETROOT = add("skewered_beetroot", Items.BEETROOT);
    public static final SkeweredFoodComponent SKEWERED_COOKED_BEEF = add("skewered_cooked_beef", Items.COOKED_BEEF);
    public static final SkeweredFoodComponent SKEWERED_COOKED_CHICKEN = add("skewered_cooked_chicken", Items.COOKED_CHICKEN);
    public static final SkeweredFoodComponent SKEWERED_COOKED_COD = add("skewered_cooked_cod", Items.COOKED_COD);
    public static final SkeweredFoodComponent SKEWERED_COOKED_MUTTON = add("skewered_cooked_mutton", Items.COOKED_MUTTON);
    public static final SkeweredFoodComponent SKEWERED_COOKED_PORKCHOP = add("skewered_cooked_porkchop", Items.COOKED_PORKCHOP);
    public static final SkeweredFoodComponent SKEWERED_COOKED_RABBIT = add("skewered_cooked_rabbit", Items.COOKED_RABBIT);
    public static final SkeweredFoodComponent SKEWERED_COOKED_SALMON = add("skewered_cooked_salmon", Items.COOKED_SALMON);
    public static final SkeweredFoodComponent SKEWERED_DRIED_KELP = add("skewered_dried_kelp", Items.DRIED_KELP);
    public static final SkeweredFoodComponent SKEWERED_GOLDEN_APPLE = add("skewered_golden_apple", Items.GOLDEN_APPLE);
    public static final SkeweredFoodComponent SKEWERED_GOLDEN_CARROT = add("skewered_golden_carrot", Items.GOLDEN_CARROT);
    public static final SkeweredFoodComponent SKEWERED_MELON = add("skewered_melon", Items.MELON_SLICE);
    public static final SkeweredFoodComponent SKEWERED_POISONOUS_POTATO = add("skewered_poisonous_potato", Items.POISONOUS_POTATO);
    public static final SkeweredFoodComponent SKEWERED_ROTTEN_FLESH = add("skewered_rotten_flesh", Items.ROTTEN_FLESH);
    public static final SkeweredFoodComponent SKEWERED_SPIDER_EYE = add("skewered_spider_eye", Items.SPIDER_EYE);

    public static Component fromItem(Item item) {
        return FOODS.get(item);
    }

    /**
     * Creates a new SkeweredFoodComponent from a base item.
     */
    private static SkeweredFoodComponent add(String id, Item base) {
        Objects.requireNonNull(base.getFoodComponent());
        Objects.requireNonNull(FOODS);

        SkeweredFoodComponent component = Registry.register(SarRegistries.COMPONENT, locate(id), SkeweredFoodComponent.of(base.getFoodComponent()));
        FOODS.put(base, component);

        return component;
    }

    public static void init() {
    }
}
