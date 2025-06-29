package io.github.platymemo.arcane_furnaceworks.block.blockentity;

import io.github.platymemo.arcane_furnaceworks.Constants;
import io.github.platymemo.arcane_furnaceworks.menu.VolcanicCrucibleMenu;
import io.github.platymemo.arcane_furnaceworks.platform.Services;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.FuelValues;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class VolcanicCrucibleBlockEntity extends AbstractArcaneFurnaceworkBlockEntity {
    public static final int[] DOWN_SLOT_SET = {1, 2};
    public static final int[] SIDE_SLOT_SET = {1, 3};
    public static final int[] UP_SLOT_SET = {0};
    static final int FUEL_BURN_SPEEDUP = 4;
    int slagTimeRemaining;
    int slagTimeTotal;
    protected final ContainerData volcanicCrucibleData = new ContainerData() {
        @Override
        public int get(int i) {
            if (i == 0) {
                return slagTimeRemaining;
            } else if (i == 1) {
                return slagTimeTotal;
            }
            return 0;
        }

        @Override
        public void set(int i, int value) {
            if (i == 0) {
                slagTimeRemaining = value;
            } else if (i == 1) {
                slagTimeTotal = value;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    };

    public VolcanicCrucibleBlockEntity(BlockPos pos, BlockState state) {
        super(Services.PLATFORM.getBlockEntityType(Constants.FurnaceType.VOLCANIC_CORE), pos, state, RecipeType.BLASTING);
        // Increase list size to account for the rock slot
        this.items = NonNullList.withSize(4, ItemStack.EMPTY);
    }

    @Override
    public ContainerData getAdditionalData() {
        return volcanicCrucibleData;
    }

    @Override
    protected int getBurnDuration(@NotNull FuelValues fuelValues, @NotNull ItemStack stack) {
        return this.items.get(3).isEmpty() ? 0 : super.getBurnDuration(fuelValues, stack) / FUEL_BURN_SPEEDUP;
    }

    @Override
    public void arcaneFurnaceworks$onFuelBurn(ServerLevel level, BlockPos pos, BlockState state) {
        this.items.get(3).shrink(1);
    }

    @Override
    protected @NotNull Component getDefaultName() {
        return Component.translatable("container." + Constants.FurnaceType.VOLCANIC_CORE.getPath());
    }

    @Override
    protected @NotNull AbstractContainerMenu createMenu(int id, @NotNull Inventory player) {
        return new VolcanicCrucibleMenu(id, player, this, this.arcaneFurnaceworksData);
    }

    @Override
    public int @NotNull [] getSlotsForFace(@NotNull Direction side) {
        return switch (side) {
            case UP -> UP_SLOT_SET;
            case DOWN -> DOWN_SLOT_SET;
            default -> SIDE_SLOT_SET;
        };
    }
}
