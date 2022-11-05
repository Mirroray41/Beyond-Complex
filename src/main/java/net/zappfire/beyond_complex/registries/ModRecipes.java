package net.zappfire.beyond_complex.registries;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zappfire.beyond_complex.BeyondComplex;
import net.zappfire.beyond_complex.block.simplealloykiln.SimpleAlloyKilnFuelRecipe;
import net.zappfire.beyond_complex.block.simplealloykiln.SimpleAlloyKilnRecipe;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZER =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, BeyondComplex.MODID);

    public static final RegistryObject<RecipeSerializer<SimpleAlloyKilnRecipe>> ALLOYING_T1 =
            SERIALIZER.register("alloying_t1", () -> SimpleAlloyKilnRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<SimpleAlloyKilnFuelRecipe>> ALLOY_T1_FUEL =
            SERIALIZER.register("alloy_t1_fuel", () -> SimpleAlloyKilnFuelRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZER.register(eventBus);
    }
}
