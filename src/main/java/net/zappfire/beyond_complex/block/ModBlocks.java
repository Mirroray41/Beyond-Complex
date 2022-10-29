package net.zappfire.beyond_complex.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zappfire.beyond_complex.BeyondComplex;
import net.zappfire.beyond_complex.block.advanced.Pebble;
import net.zappfire.beyond_complex.block.advanced.SimpleAlloyKiln;
import net.zappfire.beyond_complex.item.ModItems;
import net.zappfire.beyond_complex.tabs.ModCreativeModeTab;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, BeyondComplex.MODID);

    //MISC BLOCKS
    public static final RegistryObject<Block> SIMPLE_ALLOY_KILN = registerBlock("simple_alloy_kiln", () -> new SimpleAlloyKiln(BlockBehaviour.Properties.of(Material.STONE).strength(2f).noOcclusion().lightLevel(state -> state.getValue(SimpleAlloyKiln.LIT) ? 15 : 0)) {
    }, ModCreativeModeTab.BEYOND_COMPLEX_TAB);

    //TIN
    public static final RegistryObject<Block> TIN_ORE = registerBlock("tin_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(2f)) {
    }, ModCreativeModeTab.BEYOND_COMPLEX_TAB);
    public static final RegistryObject<Block> DEEPSLATE_TIN_ORE = registerBlock("deepslate_tin_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(2f)) {
    }, ModCreativeModeTab.BEYOND_COMPLEX_TAB);
    public static final RegistryObject<Block> TIN_BLOCK = registerBlock("tin_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(2f)) {
    }, ModCreativeModeTab.BEYOND_COMPLEX_TAB);
    public static final RegistryObject<Block> RAW_TIN_BLOCK = registerBlock("raw_tin_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(2f)) {
    }, ModCreativeModeTab.BEYOND_COMPLEX_TAB);
    public static final RegistryObject<Block> TIN_PLATES = registerBlock("tin_plates", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(2f)) {
    }, ModCreativeModeTab.BEYOND_COMPLEX_TAB);

    //LEAD
    public static final RegistryObject<Block> LEAD_ORE = registerBlock("lead_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(2f)) {
    }, ModCreativeModeTab.BEYOND_COMPLEX_TAB);
    public static final RegistryObject<Block> DEEPSLATE_LEAD_ORE = registerBlock("deepslate_lead_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(2f)) {
    }, ModCreativeModeTab.BEYOND_COMPLEX_TAB);
    public static final RegistryObject<Block> LEAD_BLOCK = registerBlock("lead_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(2f)) {
    }, ModCreativeModeTab.BEYOND_COMPLEX_TAB);
    public static final RegistryObject<Block> RAW_LEAD_BLOCK = registerBlock("raw_lead_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(2f)) {
    }, ModCreativeModeTab.BEYOND_COMPLEX_TAB);
    public static final RegistryObject<Block> LEAD_PLATES = registerBlock("lead_plates", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(2f)) {
    }, ModCreativeModeTab.BEYOND_COMPLEX_TAB);

    //ZINC
    public static final RegistryObject<Block> ZINC_ORE = registerBlock("zinc_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(2f)) {
    }, ModCreativeModeTab.BEYOND_COMPLEX_TAB);
    public static final RegistryObject<Block> DEEPSLATE_ZINC_ORE = registerBlock("deepslate_zinc_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(2f)) {
    }, ModCreativeModeTab.BEYOND_COMPLEX_TAB);
    public static final RegistryObject<Block> ZINC_BLOCK = registerBlock("zinc_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(2f)) {
    }, ModCreativeModeTab.BEYOND_COMPLEX_TAB);
    public static final RegistryObject<Block> RAW_ZINC_BLOCK = registerBlock("raw_zinc_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(2f)) {
    }, ModCreativeModeTab.BEYOND_COMPLEX_TAB);
    public static final RegistryObject<Block> ZINC_PLATES = registerBlock("zinc_plates", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(2f)) {
    }, ModCreativeModeTab.BEYOND_COMPLEX_TAB);

    //LITHIUM
    public static final RegistryObject<Block> LITHIUM_ORE = registerBlock("lithium_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(2f)) {
    }, ModCreativeModeTab.BEYOND_COMPLEX_TAB);
    public static final RegistryObject<Block> DEEPSLATE_LITHIUM_ORE = registerBlock("deepslate_lithium_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(2f)) {
    }, ModCreativeModeTab.BEYOND_COMPLEX_TAB);
    public static final RegistryObject<Block> LITHIUM_BLOCK = registerBlock("lithium_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(2f)) {
    }, ModCreativeModeTab.BEYOND_COMPLEX_TAB);
    public static final RegistryObject<Block> RAW_LITHIUM_BLOCK = registerBlock("raw_lithium_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(2f)) {
    }, ModCreativeModeTab.BEYOND_COMPLEX_TAB);
    public static final RegistryObject<Block> LITHIUM_PLATES = registerBlock("lithium_plates", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(2f)) {
    }, ModCreativeModeTab.BEYOND_COMPLEX_TAB);

    //BRONZE
    public static final RegistryObject<Block> BRONZE_BLOCK = registerBlock("bronze_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(2f)) {
    }, ModCreativeModeTab.BEYOND_COMPLEX_TAB);
    public static final RegistryObject<Block> BRONZE_PLATES = registerBlock("bronze_plates", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(2f)) {
    }, ModCreativeModeTab.BEYOND_COMPLEX_TAB);

    //BRASS
    public static final RegistryObject<Block> BRASS_BLOCK = registerBlock("brass_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(2f)) {
    }, ModCreativeModeTab.BEYOND_COMPLEX_TAB);
    public static final RegistryObject<Block> BRASS_PLATES = registerBlock("brass_plates", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(2f)) {
    }, ModCreativeModeTab.BEYOND_COMPLEX_TAB);

    //STEEL
    public static final RegistryObject<Block> STEEL_BLOCK = registerBlock("steel_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(2f)) {
    }, ModCreativeModeTab.BEYOND_COMPLEX_TAB);
    public static final RegistryObject<Block> STEEL_PLATES = registerBlock("steel_plates", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(2f)) {
    }, ModCreativeModeTab.BEYOND_COMPLEX_TAB);

    //HARDENED STEEL
    public static final RegistryObject<Block> HARDENED_STEEL_BLOCK = registerBlock("hardened_steel_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(2f)) {
    }, ModCreativeModeTab.BEYOND_COMPLEX_TAB);
    public static final RegistryObject<Block> HARDENED_STEEL_PLATES = registerBlock("hardened_steel_plates", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(2f)) {
    }, ModCreativeModeTab.BEYOND_COMPLEX_TAB);

    //STONE PEBBLES
    public static final RegistryObject<Block> STONE_PEBBLE = registerBlock("stone_pebble", () -> new Pebble(BlockBehaviour.Properties.of(Material.STONE).instabreak().noOcclusion().requiresCorrectToolForDrops()) {
    }, ModCreativeModeTab.BEYOND_COMPLEX_TAB);
    public static final RegistryObject<Block> GRANITE_PEBBLE = registerBlock("granite_pebble", () -> new Pebble(BlockBehaviour.Properties.of(Material.STONE).instabreak().noOcclusion().requiresCorrectToolForDrops()) {
    }, ModCreativeModeTab.BEYOND_COMPLEX_TAB);
    public static final RegistryObject<Block> DIORITE_PEBBLE = registerBlock("diorite_pebble", () -> new Pebble(BlockBehaviour.Properties.of(Material.STONE).instabreak().noOcclusion().requiresCorrectToolForDrops()) {
    }, ModCreativeModeTab.BEYOND_COMPLEX_TAB);
    public static final RegistryObject<Block> ANDESITE_PEBBLE = registerBlock("andesite_pebble", () -> new Pebble(BlockBehaviour.Properties.of(Material.STONE).instabreak().noOcclusion().requiresCorrectToolForDrops()) {
    }, ModCreativeModeTab.BEYOND_COMPLEX_TAB);
    //METAL PEBBLES
    public static final RegistryObject<Block> COPPER_PEBBLE = registerBlock("copper_pebble", () -> new Pebble(BlockBehaviour.Properties.of(Material.STONE).instabreak().noOcclusion().requiresCorrectToolForDrops()) {
    }, ModCreativeModeTab.BEYOND_COMPLEX_TAB);
    public static final RegistryObject<Block> TIN_PEBBLE = registerBlock("tin_pebble", () -> new Pebble(BlockBehaviour.Properties.of(Material.STONE).instabreak().noOcclusion().requiresCorrectToolForDrops()) {
    }, ModCreativeModeTab.BEYOND_COMPLEX_TAB);

    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
