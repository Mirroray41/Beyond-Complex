package net.zappfire.beyond_complex.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zappfire.beyond_complex.BeyondComplex;
import net.zappfire.beyond_complex.item.advanced.Flint;
import net.zappfire.beyond_complex.tabs.ModCreativeModeTab;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, BeyondComplex.MODID);

    public static final RegistryObject<Item> STRAW = ITEMS.register("straw",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> STRAW_STRING = ITEMS.register("straw_string",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> SHARPENED_FLINT = ITEMS.register("sharpened_flint",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> FLINT_SHOVEL = ITEMS.register("flint_shovel",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> FLINT_AXE = ITEMS.register("flint_axe",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> FLINT_KNIFE = ITEMS.register("flint_knife",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> FLINT = ITEMS.register("flint",
            () -> new Flint((new Item.Properties()).tab(CreativeModeTab.TAB_MATERIALS)));
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
