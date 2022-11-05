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
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.zappfire.beyond_complex.networking.packet.EnergySyncS2CPacket;
import net.zappfire.beyond_complex.registries.ModBlockEntities;
import net.zappfire.beyond_complex.registries.ModPackets;
import net.zappfire.beyond_complex.util.ModEnergyStorage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
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
    private int temperature = 2000;
    private int isHeated = 0;

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    private final ModEnergyStorage ENERGY_STORAGE = new ModEnergyStorage(2000, 0) {
        @Override
        public void onEnergyChanged() {
            setChanged();
            ModPackets.sendToClients(new EnergySyncS2CPacket(this.energy, getBlockPos()));
        }
    };

    private LazyOptional<IEnergyStorage> lazyEnergyHandler = LazyOptional.empty();

    public SimpleAlloyKilnEntity( BlockPos p_155229_, BlockState p_155230_) {
        super(ModBlockEntities.SIMPLE_ALLOY_KILN_ENTITY.get(), p_155229_, p_155230_);
        this.data = new ContainerData() {
            public int get(int index) {
                switch (index) {
                    case 0: return SimpleAlloyKilnEntity.this.progress;
                    case 1: return SimpleAlloyKilnEntity.this.maxProgress;
                    case 2: return SimpleAlloyKilnEntity.this.temperature;
                    case 3: return SimpleAlloyKilnEntity.this.isHeated;
                    default: return 0;
                }
            }

            public void set(int index, int value) {
                switch(index) {
                    case 0: SimpleAlloyKilnEntity.this.progress = value; break;
                    case 1: SimpleAlloyKilnEntity.this.maxProgress = value; break;
                    case 2: SimpleAlloyKilnEntity.this.temperature = value; break;
                    case 3: SimpleAlloyKilnEntity.this.isHeated = value; break;
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
    public IEnergyStorage getEnergyStorage() { return ENERGY_STORAGE; }
    public void setEnergyLevel(int energy) { this.ENERGY_STORAGE.setEnergy(energy); }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @javax.annotation.Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return lazyItemHandler.cast();
        }
        if (cap == ForgeCapabilities.ENERGY) {
            return lazyEnergyHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
        lazyEnergyHandler = LazyOptional.of(() -> ENERGY_STORAGE);
    }

    @Override
    public void invalidateCaps()  {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
        lazyEnergyHandler.invalidate();
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        tag.put("inventory", itemHandler.serializeNBT());
        tag.putInt("simple_alloy_kiln.progress", progress);
        tag.putInt("simple_alloy_kiln.energy", ENERGY_STORAGE.getEnergyStored());
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        progress = nbt.getInt("simple_alloy_kiln.progress");
        ENERGY_STORAGE.setEnergy(nbt.getInt("simple_alloy_kiln.energy"));
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, SimpleAlloyKilnEntity pBlockEntity) {
        if(hasRecipe(pBlockEntity)) {

            pBlockEntity.progress++;
            setChanged(pLevel, pPos, pState);

            if(pBlockEntity.progress >= pBlockEntity.maxProgress) {
                craftItem(pBlockEntity);
            }
        } else {
            pBlockEntity.resetProgress();
            setChanged(pLevel, pPos, pState);
        }
        if (hasHeatRecipe(pBlockEntity) && pBlockEntity.ENERGY_STORAGE.getEnergyStored() < getMaxHeatForFuel(pBlockEntity)) {
            pBlockEntity.ENERGY_STORAGE.receiveEnergy(5, false);
        } else {
            pBlockEntity.ENERGY_STORAGE.receiveEnergy(5, false);
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
        match.ifPresent(simpleAlloyKilnRecipe -> System.out.println(simpleAlloyKilnRecipe.getHeatReq()));
        return match.isPresent() && canInsertAmountIntoOutputSlot(inventory)
                && canInsertItemIntoOutputSlot(inventory, match.get().getResultItem()) && entity.ENERGY_STORAGE.getEnergyStored() >= match.get().getHeatReq();
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
            entity.itemHandler.extractItem(2,1, false);
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
}