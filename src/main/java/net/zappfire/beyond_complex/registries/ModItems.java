package net.zappfire.beyond_complex.registries;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zappfire.beyond_complex.BeyondComplex;
import net.zappfire.beyond_complex.tabs.ModCreativeModeTab;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, BeyondComplex.MODID);

    //MISC
    public static final RegistryObject<Item> STRAW = ITEMS.register("straw",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> STRAW_STRING = ITEMS.register("straw_string",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));

    //FLINT
    public static final RegistryObject<Item> SHARPENED_FLINT = ITEMS.register("sharpened_flint",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));

    //FLINT TOOLS
    public static final RegistryObject<Item> FLINT_SHOVEL = ITEMS.register("flint_shovel",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> FLINT_AXE = ITEMS.register("flint_axe",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> FLINT_KNIFE = ITEMS.register("flint_knife",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    //TIN
    public static final RegistryObject<Item> TIN_INGOT = ITEMS.register("tin_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> TIN_NUGGET = ITEMS.register("tin_nugget",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> RAW_TIN = ITEMS.register("raw_tin",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));

    //ZINC
    public static final RegistryObject<Item> ZINC_INGOT = ITEMS.register("zinc_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> ZINC_NUGGET = ITEMS.register("zinc_nugget",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> RAW_ZINC = ITEMS.register("raw_zinc",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));

    //LEAD
    public static final RegistryObject<Item> LEAD_INGOT = ITEMS.register("lead_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> LEAD_NUGGET = ITEMS.register("lead_nugget",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> RAW_LEAD = ITEMS.register("raw_lead",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));

    //LITHIUM
    public static final RegistryObject<Item> LITHIUM_INGOT = ITEMS.register("lithium_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> LITHIUM_NUGGET = ITEMS.register("lithium_nugget",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> RAW_LITHIUM = ITEMS.register("raw_lithium",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));

    //BRONZE
    public static final RegistryObject<Item> BRONZE_INGOT = ITEMS.register("bronze_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> BRONZE_NUGGET = ITEMS.register("bronze_nugget",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));

    //BRASS
    public static final RegistryObject<Item> BRASS_INGOT = ITEMS.register("brass_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> BRASS_NUGGET = ITEMS.register("brass_nugget",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));

    //STEEL
    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> STEEL_NUGGET = ITEMS.register("steel_nugget",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));

    //HARDENED STEEL
    public static final RegistryObject<Item> HARDENED_STEEL_INGOT = ITEMS.register("hardened_steel_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> HARDENED_STEEL_NUGGET = ITEMS.register("hardened_steel_nugget",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));

    //PLATES
    public static final RegistryObject<Item> BRASS_PLATE = ITEMS.register("brass_plate",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> BRONZE_PLATE = ITEMS.register("bronze_plate",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> LITHIUM_PLATE = ITEMS.register("lithium_plate",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> LEAD_PLATE = ITEMS.register("lead_plate",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> ZINC_PLATE = ITEMS.register("zinc_plate",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> TIN_PLATE = ITEMS.register("tin_plate",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> IRON_PLATE = ITEMS.register("iron_plate",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> COPPER_PLATE = ITEMS.register("copper_plate",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> GOLD_PLATE = ITEMS.register("gold_plate",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> STEEL_PLATE = ITEMS.register("steel_plate",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> HARDENED_STEEL_PLATE = ITEMS.register("hardened_steel_plate",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));

    //DUSTS
    public static final RegistryObject<Item> LITHIUM_DUST = ITEMS.register("lithium_dust",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> TIN_DUST = ITEMS.register("tin_dust",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> ZINC_DUST = ITEMS.register("zinc_dust",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> LEAD_DUST = ITEMS.register("lead_dust",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> BRONZE_DUST = ITEMS.register("bronze_dust",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> BRASS_DUST = ITEMS.register("brass_dust",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> IRON_DUST = ITEMS.register("iron_dust",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> COPPER_DUST = ITEMS.register("copper_dust",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> GOLD_DUST = ITEMS.register("gold_dust",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> AMETHYST_DUST = ITEMS.register("amethyst_dust",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> DIAMOND_DUST = ITEMS.register("diamond_dust",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> EMERALD_DUST = ITEMS.register("emerald_dust",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> OBSIDIAN_DUST = ITEMS.register("obsidian_dust",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> STEEL_DUST = ITEMS.register("steel_dust",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> HARDENED_STEEL_DUST = ITEMS.register("hardened_steel_dust",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));

    //COMPRESSED
    public static final RegistryObject<Item> COMPRESSED_LITHIUM = ITEMS.register("compressed_lithium",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> COMPRESSED_TIN = ITEMS.register("compressed_tin",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> COMPRESSED_ZINC = ITEMS.register("compressed_zinc",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> COMPRESSED_LEAD = ITEMS.register("compressed_lead",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> COMPRESSED_BRONZE = ITEMS.register("compressed_bronze",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> COMPRESSED_BRASS = ITEMS.register("compressed_brass",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> COMPRESSED_IRON = ITEMS.register("compressed_iron",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> COMPRESSED_COPPER = ITEMS.register("compressed_copper",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> COMPRESSED_GOLD = ITEMS.register("compressed_gold",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> COMPRESSED_AMETHYST = ITEMS.register("compressed_amethyst",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> COMPRESSED_DIAMOND = ITEMS.register("compressed_diamond",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> COMPRESSED_EMERALD = ITEMS.register("compressed_emerald",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> COMPRESSED_OBSIDIAN = ITEMS.register("compressed_obsidian",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> COMPRESSED_STEEL = ITEMS.register("compressed_steel",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));
    public static final RegistryObject<Item> COMPRESSED_HARDENED_STEEL = ITEMS.register("compressed_hardened_steel",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BEYOND_COMPLEX_TAB)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
