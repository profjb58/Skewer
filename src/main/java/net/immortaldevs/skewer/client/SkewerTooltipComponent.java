package net.immortaldevs.skewer.client;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

public class SkewerTooltipComponent implements TooltipComponent {

    private final DefaultedList<ItemStack> skeweredItems;
    private final int skewerCapacity;

    public SkewerTooltipComponent(SkewerTooltipData skewerData) {
        skeweredItems = skewerData.getSkeweredItems();
        skewerCapacity = skewerData.getSkewerCapacity();
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public int getWidth(TextRenderer textRenderer) {
        return 0;
    }

    @Override
    public void drawItems(TextRenderer textRenderer, int x, int y, MatrixStack matrices, ItemRenderer itemRenderer, int z) {
        TooltipComponent.super.drawItems(textRenderer, x, y, matrices, itemRenderer, z);
    }
}
