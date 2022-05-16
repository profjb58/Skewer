package net.immortaldevs.skewer.client;

import net.minecraft.client.item.TooltipData;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

public class SkewerTooltipData implements TooltipData {

    private final DefaultedList<ItemStack> componentFoodStacks;
    private final DefaultedList<ItemStack> condimentStacks;
    private final int skewerCapacity;

    public SkewerTooltipData(DefaultedList<ItemStack> componentFoodStacks, DefaultedList<ItemStack> condimentStacks, int skewerCapacity) {
        this.componentFoodStacks = componentFoodStacks;
        this.condimentStacks = condimentStacks;
        this.skewerCapacity = skewerCapacity;
    }

    public DefaultedList<ItemStack> getComponentFood() {
        return this.componentFoodStacks;
    }

    public DefaultedList<ItemStack> getCondiments() { return this.condimentStacks; }

    public int getSkewerCapacity() {
        return this.skewerCapacity;
    }
}
