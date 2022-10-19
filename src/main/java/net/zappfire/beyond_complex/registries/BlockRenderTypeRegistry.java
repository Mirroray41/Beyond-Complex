package net.zappfire.beyond_complex.registries;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.zappfire.beyond_complex.BeyondComplex;
import net.zappfire.beyond_complex.block.ModBlocks;

@Mod.EventBusSubscriber(modid = BeyondComplex.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class BlockRenderTypeRegistry {

    @SubscribeEvent
    public static void register(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.SIMPLE_ALLOY_KILN.get(), RenderType.translucent());
    }
}