package io.github.platymemo.arcane_furnaceworks.block.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.state.BlockState;

public interface FurnaceExtensions {
    void arcaneFurnaceworks$onSmelted(ServerLevel level, BlockPos pos, BlockState state);
    void arcaneFurnaceworks$onFuelBurn(ServerLevel level, BlockPos pos, BlockState state);
}
