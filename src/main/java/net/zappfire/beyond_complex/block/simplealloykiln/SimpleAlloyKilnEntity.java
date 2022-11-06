package net.zappfire.beyond_complex.block.simplealloykiln;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.zappfire.beyond_complex.block.WrappedHandler;
import net.zappfire.beyond_complex.networking.packet.EnergySyncS2CPacket;
import net.zappfire.beyond_complex.registries.ModBlockEntities;
import net.zappfire.beyond_complex.registries.ModPackets;
import net.zappfire.beyond_complex.util.ModEnergyStorage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Optional;

public class SimpleAlloyKilnEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(4) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 144;
    private int temperature = 0;
    private int maxTemp = 2000;

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    public SimpleAlloyKilnEntity( BlockPos p_155229_, BlockState p_155230_) {
        super(ModBlockEntities.SIMPLE_ALLOY_KILN_ENTITY.get(), p_155229_, p_155230_);
        this.data = new ContainerData() {
            public int get(int index) {
                switch (index) {
                    case 0: return SimpleAlloyKilnEntity.this.progress;
                    case 1: return SimpleAlloyKilnEntity.this.maxProgress;
                    case 2: return SimpleAlloyKilnEntity.this.temperature;
                    case 3: return SimpleAlloyKilnEntity.this.maxTemp;
                    default: return 0;
                }
            }

            public void set(int index, int value) {
                switch(index) {
                    case 0: SimpleAlloyKilnEntity.this.progress = value; break;
                    case 1: SimpleAlloyKilnEntity.this.maxProgress = value; break;
                    case 2: SimpleAlloyKilnEntity.this.temperature = value; break;
                    case 3: SimpleAlloyKilnEntity.this.maxTemp = value; break;
                }
            }

            public int getCount() {
                return 4;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Simple Alloying Kiln");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new SimpleAlloyKilnMenu(i ,inventory , this, this.data);
    }


    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            if (side == null) { return lazyItemHandler.cast(); }
            if (directionWrappedHandlerMap.containsKey(side)) {
                Direction localDir = this.getBlockState().getValue(SimpleAlloyKiln.FACING);

                if (side == Direction.UP || side == Direction.DOWN) { return directionWrappedHandlerMap.get(side).cast(); }
                return switch (localDir) {
                    case EAST -> directionWrappedHandlerMap.get(side.getClockWise()).cast();
                    case SOUTH -> directionWrappedHandlerMap.get(side).cast();
                    case WEST -> directionWrappedHandlerMap.get(side.getCounterClockWise()).cast();
                    default -> directionWrappedHandlerMap.get(side.getOpposite()).cast();
                };
            }
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps()  {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        tag.put("inventory", itemHandler.serializeNBT());
        tag.putInt("simple_alloy_kiln.progress", progress);
        tag.putInt("simple_alloy_kiln.temp", temperature);
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        progress = nbt.getInt("simple_alloy_kiln.progress");
        temperature = (nbt.getInt("simple_alloy_kiln.temp"));
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, SimpleAlloyKilnEntity pBlockEntity) {
        if (pLevel.isClientSide()) return;

        if(hasRecipe(pBlockEntity) && pBlockEntity.temperature >= getRecipeHeatReq(pBlockEntity)) {
            pBlockEntity.progress++;
            setChanged(pLevel, pPos, pState);

            if(pBlockEntity.progress >= pBlockEntity.maxProgress) {
                craftItem(pBlockEntity);
            }
        } else {
            pBlockEntity.resetProgress();
            setChanged(pLevel, pPos, pState);
        }

        if (hasHeatRecipe(pBlockEntity) && pBlockEntity.temperature <= getMaxHeatForFuel(pBlockEntity)) {
            pBlockEntity.temperature += 5;
        } else {
            if (pBlockEntity.temperature > 0) pBlockEntity.temperature--;
        }
    }

    public static boolean hasRecipe(SimpleAlloyKilnEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<SimpleAlloyKilnRecipe> match = level.getRecipeManager()
                .getRecipeFor(SimpleAlloyKilnRecipe.Type.INSTANCE, inventory, level);
        return match.isPresent() && canInsertAmountIntoOutputSlot(inventory)
                && canInsertItemIntoOutputSlot(inventory, match.get().getResultItem());
    }

    private static int getRecipeHeatReq(SimpleAlloyKilnEntity entity) {
        Level level = entity.level;
        SimpleContainer inv = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inv.setItem(i, entity.itemHandler.getStackInSlot(i));
        }
        Optional<SimpleAlloyKilnRecipe> match = level.getRecipeManager().getRecipeFor(SimpleAlloyKilnRecipe.Type.INSTANCE, inv, level);
        return match.get().getHeatReq();
    }

    private static boolean hasHeatRecipe(SimpleAlloyKilnEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }
        Optional<SimpleAlloyKilnFuelRecipe> match = level.getRecipeManager().getRecipeFor(SimpleAlloyKilnFuelRecipe.Type.INSTANCE, inventory, level);

        return match.isPresent();
    }
    // Gets the max heat for the inputted fuel.
    private static int getMaxHeatForFuel(SimpleAlloyKilnEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }
        Optional<SimpleAlloyKilnFuelRecipe> match = level.getRecipeManager().getRecipeFor(SimpleAlloyKilnFuelRecipe.Type.INSTANCE, inventory, level);
        return match.map(SimpleAlloyKilnFuelRecipe::getMaxHeat).orElse(0);
    }

    private static void craftItem(SimpleAlloyKilnEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<SimpleAlloyKilnRecipe> match = level.getRecipeManager()
                .getRecipeFor(SimpleAlloyKilnRecipe.Type.INSTANCE, inventory, level);

        if(match.isPresent()) {
            entity.itemHandler.extractItem(0,1, false);
            entity.itemHandler.extractItem(1,1, false);
            entity.itemHandler.setStackInSlot(3, new ItemStack(match.get().getResultItem().getItem(),
                    entity.itemHandler.getStackInSlot(3).getCount() + 1));

            entity.resetProgress();
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack output) {
        return inventory.getItem(3).getItem() == output.getItem() || inventory.getItem(3).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(3).getMaxStackSize() > inventory.getItem(3).getCount();
    }
    private final Map<Direction, LazyOptional<WrappedHandler>> directionWrappedHandlerMap =
            Map.of(Direction.DOWN, LazyOptional.of(() -> new WrappedHandler(itemHandler, (i) -> i == 2, (i, s) -> false)),
                    Direction.NORTH, LazyOptional.of(() -> new WrappedHandler(itemHandler, (index) -> index == 1,
                            (index, stack) -> itemHandler.isItemValid(1, stack))),
                    Direction.SOUTH, LazyOptional.of(() -> new WrappedHandler(itemHandler, (i) -> i == 2, (i, s) -> false)),
                    Direction.EAST, LazyOptional.of(() -> new WrappedHandler(itemHandler, (i) -> i == 1,
                            (index, stack) -> itemHandler.isItemValid(1, stack))),
                    Direction.WEST, LazyOptional.of(() -> new WrappedHandler(itemHandler, (index) -> index == 0 || index == 1,
                            (index, stack) -> itemHandler.isItemValid(0, stack) || itemHandler.isItemValid(1, stack))));
}