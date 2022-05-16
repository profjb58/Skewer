package net.immortaldevs.skewer.item;

import net.fabricmc.fabric.api.client.rendering.v1.TooltipComponentCallback;
import net.immortaldevs.sar.api.SkeletalComponentData;
import net.minecraft.block.Block;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.client.item.BundleTooltipData;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.item.TooltipData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

import static net.immortaldevs.skewer.Skewer.SKEWER;

public class SkewerItem extends Item {
    public final int maxCapacity;
    public final boolean breaks;

    public SkewerItem(Settings settings, int maxCapacity, boolean breaks) {
        super(settings);
        this.maxCapacity = maxCapacity;
        this.breaks = breaks;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        ItemStack result = super.finishUsing(stack, world, user);
        if(user instanceof PlayerEntity player && player.getAbilities().creativeMode) {
            return result;
        }
        else {
            return breaks ? result : new ItemStack(this);
        }


    }

    /**
     * No, you don't get to chew on the stick.
     */
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (stack.hasComponent("kebab")) {
            user.setCurrentHand(hand);
            return TypedActionResult.consume(stack);
        } else {
            return TypedActionResult.fail(stack);
        }
    }

    @Override
    public Optional<TooltipData> getTooltipData(ItemStack stack) {
        DefaultedList<ItemStack> skewerStacks = DefaultedList.of();
        SkeletalComponentData kebab = stack.getComponent("kebab");
        if (kebab == null) return Optional.empty();

        SkeletalComponentData.Children foods = kebab.getChildren("foods");
        for (int i = 0; i < foods.size(); i++) {


            TooltipC

            Item foodItem = Registry..get(new Identifier(foods.get(i).getComponent().getId().toString()));
            skewerStacks.add(foodItem.getDefaultStack());
        }
        return Optional.of(new BundleTooltipData(skewerStacks, 12));
    }

    /**
     * Display skewered items on the tooltip
     */
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        //if (!context.isAdvanced()) return;
        SkeletalComponentData kebab = stack.getComponent("kebab");
        if (kebab == null) return;

        SkeletalComponentData.Children foods = kebab.getChildren("foods");
        for (int i = 0; i < foods.size(); i++) {
            tooltip.add(new LiteralText(foods.get(i).getComponent().getId().toString()));
        }

        SkeletalComponentData.Children condiments = kebab.getChildren("condiments");
        for (int i = 0; i < condiments.size(); i++) {
            tooltip.add(new LiteralText(condiments.get(i).getComponent().getId().toString()));
        }
    }
}
