package io.github.platymemo.arcane_furnaceworks.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import io.github.platymemo.arcane_furnaceworks.block.blockentity.AbstractArcaneFurnaceworkBlockEntity;
import io.github.platymemo.arcane_furnaceworks.block.blockentity.FurnaceExtensions;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractFurnaceBlockEntity.class)
public class AbstractFurnaceBlockEntityMixin implements FurnaceExtensions {
    @Override
    public void arcaneFurnaceworks$onSmelted(ServerLevel level, BlockPos pos, BlockState state) {
        // To be overridden
    }

    @Override
    public void arcaneFurnaceworks$onFuelBurn(ServerLevel level, BlockPos pos, BlockState state) {
        // To be overridden
    }

    @Inject(method = "serverTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/entity/AbstractFurnaceBlockEntity;setRecipeUsed(Lnet/minecraft/world/item/crafting/RecipeHolder;)V"))
    private static void callOnSmelted(ServerLevel level, BlockPos pos, BlockState state, AbstractFurnaceBlockEntity furnace, CallbackInfo ci) {
        ((AbstractFurnaceBlockEntityMixin) (Object) furnace).arcaneFurnaceworks$onSmelted(level, pos, state);
    }

    @Inject(method = "serverTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;getItem()Lnet/minecraft/world/item/Item;"))
    private static void callOnFuelBurned(ServerLevel level, BlockPos pos, BlockState state, AbstractFurnaceBlockEntity furnace, CallbackInfo ci) {
        ((AbstractFurnaceBlockEntityMixin) (Object) furnace).arcaneFurnaceworks$onFuelBurn(level, pos, state);
    }

    @ModifyReturnValue(method = "getTotalCookTime", at = @At("RETURN"))
    private static int modifyCookTime(int original, ServerLevel level, AbstractFurnaceBlockEntity furnace) {
        if (furnace instanceof AbstractArcaneFurnaceworkBlockEntity arcaneFurnace) {
            return (int) (original / arcaneFurnace.getSpeed());
        }

        return original;
    }
}
