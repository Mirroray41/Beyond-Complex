package net.zappfire.beyond_complex.block.simplealloykiln;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.zappfire.beyond_complex.BeyondComplex;
import org.jetbrains.annotations.Nullable;

public class SimpleAlloyKilnFuelRecipe implements Recipe<SimpleContainer> {
    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItems;
    private final int heat;

    public SimpleAlloyKilnFuelRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> recipeItems, int heat) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
        this.heat = heat;
    }

    @Override
    public boolean matches(SimpleContainer container, Level level) {
        if (level.isClientSide()) return false;
        return recipeItems.get(0).test(container.getItem(2));
    }

    public int getMaxHeat() {return heat;}

    @Override
    public ItemStack assemble(SimpleContainer pContainer) {
        return output;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<SimpleAlloyKilnFuelRecipe> {
        private Type() {}
        public static final Type INSTANCE = new Type();
        public static final String ID = "alloy_t1_fuel";
    }

    public static class Serializer implements RecipeSerializer<SimpleAlloyKilnFuelRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(BeyondComplex.MODID, "alloy_t1_fuel");

        @Override
        public SimpleAlloyKilnFuelRecipe fromJson(ResourceLocation id, JsonObject json) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "output"));

            JsonArray ingredients = GsonHelper.getAsJsonArray(json, "fuel");
            NonNullList<Ingredient> inputs = NonNullList.withSize(1, Ingredient.EMPTY);

            int heat = GsonHelper.getAsInt(json, "max_heat");
            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }
            return new SimpleAlloyKilnFuelRecipe(id, output, inputs, heat);
        }

        @Override
        public @Nullable SimpleAlloyKilnFuelRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);
            ItemStack output = buf.readItem();
            int heat = buf.readInt();
            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buf));
            }
            return new SimpleAlloyKilnFuelRecipe(id, output, inputs, heat);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, SimpleAlloyKilnFuelRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());
            buf.writeInt(recipe.heat);
            for (Ingredient ing : recipe.getIngredients()) {
                ing.toNetwork(buf);
            }
            buf.writeItemStack(recipe.getResultItem(), false);
        }
        @SuppressWarnings("unchecked") // Need this wrapper, because generics
        private static <G> Class<G> castClass(Class<?> cls) {
            return (Class<G>)cls;
        }
    }
}
