package net.zappfire.beyond_complex.block.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zappfire.beyond_complex.BeyondComplex;
import net.zappfire.beyond_complex.block.ModBlocks;
import net.zappfire.beyond_complex.block.entity.advanced.SimpleAlloyKilnEntity;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, BeyondComplex.MODID);
    
    public static final RegistryObject<BlockEntityType<SimpleAlloyKilnEntity>> SIMPLE_ALLOY_KILN_ENTITY =
            BLOCK_ENTITIES.register("simple_alloy_kiln_entity", () ->
                    BlockEntityType.Builder.of(SimpleAlloyKilnEntity::new,
                            ModBlocks.SIMPLE_ALLOY_KILN.get()).build(null));
    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
