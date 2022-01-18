package net.id.skewer.sar;

import net.id.skewer.condiments.Condiment;
import net.immortaldevs.sar.api.Component;
import net.immortaldevs.sar.api.ComponentData;
import net.immortaldevs.sar.base.client.modifier.BakedModelModifier;
import net.id.skewer.client.sar.SkeweredFoodBakedModel;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtInt;
import net.minecraft.nbt.NbtList;

import java.util.Random;

public class SkewerComponent extends Component {
    @Override
    public void init(ComponentData data) {
        NbtList foods = data.nbt().getList("items", NbtElement.COMPOUND_TYPE);
        NbtList condiments = data.nbt().getList("condiments", NbtElement.COMPOUND_TYPE);
        Random random = new Random();

        for (int i = 0; i < foods.size(); i++) {
            NbtCompound food = foods.getCompound(i);
            food.put("Count", NbtInt.of(1));
            ItemStack stack = ItemStack.fromNbt(food);

            data.addModifier((FoodModifier) consumer -> {
                FoodComponent foodComponent = stack.getItem().getFoodComponent();
                if (foodComponent != null) consumer.accept(foodComponent);
            });

            if (data.onClient()) {
                SkeweredFoodBakedModel model = new SkeweredFoodBakedModel(MinecraftClient.getInstance()
                        .getItemRenderer()
                        .getModel(stack, null, null, 0), i, random);

                data.addModifier((BakedModelModifier) consumer -> consumer.accept(model));
            }
        }

        // TODO this adds condiment info, but they don't render.
        for (int i = 0; i < condiments.size(); i++) {
            Condiment condiment = Condiment.fromNbt(condiments.getCompound(i));
            data.addModifier((FoodModifier) consumer ->{
                FoodComponent foodComponent = condiment.getFoodComponent();
                consumer.accept(foodComponent);
            });
        }
    }
}
