package net.zappfire.beyond_complex.tabs;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.zappfire.beyond_complex.item.ModItems;

public class ModCreativeModeTab {
    public static final CreativeModeTab BEYOND_COMPLEX_TAB = new CreativeModeTab("beyond_complex_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.STRAW.get());
        }
    };
}
