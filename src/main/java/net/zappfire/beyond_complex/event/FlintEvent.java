package net.zappfire.beyond_complex.event;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.zappfire.beyond_complex.BeyondComplex;
import net.zappfire.beyond_complex.item.ModItems;

@Mod.EventBusSubscriber(modid = BeyondComplex.MODID)
public class FlintEvent {
    @SubscribeEvent
    public static void useOn(PlayerInteractEvent.RightClickBlock event) {
        if(event.getLevel().getBlockState(event.getPos()) == Blocks.STONE.defaultBlockState()) {
            if(event.getEntity().getItemInHand(InteractionHand.MAIN_HAND).getItem() == Items.FLINT) {
                Player player = event.getEntity();
                ItemStack itemStack = player.getItemInHand(InteractionHand.MAIN_HAND);
                if(!player.getCooldowns().isOnCooldown(itemStack.getItem())) {
                    ItemStack itemStack2 = new ItemStack(ModItems.SHARPENED_FLINT.get());
                    ItemEntity itemEntity = new ItemEntity(event.getLevel(), player.position().x, player.position().y, player.position().z, itemStack2);
                    if(event.getLevel().isClientSide()) {
                        player.getCooldowns().addCooldown(itemStack.getItem(), 10);
                        itemStack.shrink(1);
                    }
                    else if(!event.getLevel().isClientSide) {
                        player.getLevel().addFreshEntity(itemEntity);
                    }
                }
            }
        }
    }
}
