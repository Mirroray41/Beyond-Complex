package net.zappfire.beyond_complex.item.advanced;


import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;

public class Flint extends Item {

    public Flint(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if(pContext.getLevel().isClientSide()) {
            BlockPos positionClicked = pContext.getClickedPos();
            Player player = pContext.getPlayer();
            ItemStack itemStack = player.getItemInHand(InteractionHand.MAIN_HAND);
            System.out.println(pContext.getLevel().getBlockState(positionClicked).getBlock().toString());
            if (pContext.getLevel().getBlockState(positionClicked).getBlock().toString().equals("Block{minecraft:stone}")) {
                pContext.getPlayer().getCooldowns().addCooldown(this, 10);
                itemStack.shrink(1);

            }
        }
        return super.useOn(pContext);
    }
}

