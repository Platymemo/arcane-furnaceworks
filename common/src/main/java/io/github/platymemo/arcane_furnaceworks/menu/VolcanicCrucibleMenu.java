package io.github.platymemo.arcane_furnaceworks.menu;

import io.github.platymemo.arcane_furnaceworks.Constants;
import io.github.platymemo.arcane_furnaceworks.platform.Services;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipePropertySet;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.NotNull;

public class VolcanicCrucibleMenu extends AbstractFurnaceMenu {
    public VolcanicCrucibleMenu(int containerId, Inventory playerInventory) {
        this(containerId, playerInventory, new SimpleContainer(4), new SimpleContainerData(10));
    }

    public VolcanicCrucibleMenu(int containerId, Inventory playerInventory, Container furnaceContainer, ContainerData furnaceData) {
        super(Services.PLATFORM.getMenuType(Constants.FurnaceType.VOLCANIC_CORE), RecipeType.BLASTING, RecipePropertySet.BLAST_FURNACE_INPUT, RecipeBookType.BLAST_FURNACE, containerId, playerInventory, furnaceContainer, furnaceData);
        ((SlotExtensions) this.slots.get(1)).arcaneFurnaceworks$move(44, 53);
        this.addSlot(new RockSlagSlot(furnaceContainer, 3, 68, 53));
    }

    public boolean isRock(ItemStack stack) {
        return RockSlagSlot.isRock(stack);
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack movedStack = slot.getItem();
            itemstack = movedStack.copy();
            if (index == 2) {
                if (!this.moveItemStackTo(movedStack, 3, 39, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(movedStack, itemstack);
            } else if (index != 1 && index != 0 && index != 39) {
                if (this.canSmelt(movedStack)) {
                    if (!this.moveItemStackTo(movedStack, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (this.isFuel(movedStack)) {
                    if (!this.moveItemStackTo(movedStack, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (this.isRock(movedStack)) {
                    if (!this.moveItemStackTo(movedStack, 39, 40, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 3 && index < 30) {
                    if (!this.moveItemStackTo(movedStack, 30, 39, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 30 && index < 39 && !this.moveItemStackTo(movedStack, 3, 30, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(movedStack, 3, 39, false)) {
                return ItemStack.EMPTY;
            }

            if (movedStack.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (movedStack.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, movedStack);
        }

        return itemstack;
    }
}
