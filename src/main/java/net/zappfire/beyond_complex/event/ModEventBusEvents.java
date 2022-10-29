package net.zappfire.beyond_complex.event;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.zappfire.beyond_complex.BeyondComplex;
import net.zappfire.beyond_complex.recipe.SimpleAlloyKilnRecipe;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = BeyondComplex.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerModifierSerializers(@Nonnull final RegisterEvent event) {
        event.register(ForgeRegistries.Keys.RECIPE_TYPES, helper -> {
            helper.register(new ResourceLocation(BeyondComplex.MODID, SimpleAlloyKilnRecipe.Type.ID),
                    SimpleAlloyKilnRecipe.Type.INSTANCE);
        });
    }
}

