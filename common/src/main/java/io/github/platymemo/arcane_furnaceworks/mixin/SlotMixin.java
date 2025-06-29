package io.github.platymemo.arcane_furnaceworks.mixin;

import io.github.platymemo.arcane_furnaceworks.menu.SlotExtensions;
import net.minecraft.world.inventory.Slot;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Slot.class)
public class SlotMixin implements SlotExtensions {
    @Shadow
    @Final
    @Mutable
    public int x;
    @Shadow
    @Final
    @Mutable
    public int y;

    @Override
    public void arcaneFurnaceworks$move(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
