package io.github.platymemo.arcane_furnaceworks.menu;

import net.minecraft.tags.ItemTags;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

// A slot for the rock input to a Volcanic Crucible
public class RockSlagSlot extends Slot {
    public RockSlagSlot(Container furnaceContainer, int slot, int xPosition, int yPosition) {
        super(furnaceContainer, slot, xPosition, yPosition);
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack stack) {
        return isRock(stack);
    }

    public static boolean isRock(ItemStack stack) {
        return stack.is(ItemTags.STONE_TOOL_MATERIALS);
    }
}
