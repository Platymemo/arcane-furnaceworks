package io.github.platymemo.arcane_furnaceworks.block;

import io.github.platymemo.arcane_furnaceworks.Constants;
import io.github.platymemo.arcane_furnaceworks.block.blockentity.VolcanicCrucibleBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ArcaneFurnaceworksBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(BuiltInRegistries.BLOCK, Constants.MOD_ID);
    public static final DeferredHolder<Block, VolcanicCrucibleBlock> VOLCANIC_CRUCIBLE = BLOCKS.register(Constants.FurnaceType.VOLCANIC_CORE.getPath(), () -> new VolcanicCrucibleBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.5F).lightLevel((state) -> state.getValue(BlockStateProperties.LIT) ? 15 : 0)));

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Constants.MOD_ID);
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<VolcanicCrucibleBlockEntity>> VOLCANIC_CRUCIBLE_BLOCK_ENTITY_TYPE = BLOCK_ENTITY_TYPES.register(Constants.FurnaceType.VOLCANIC_CORE.getPath(), () -> new BlockEntityType<>(VolcanicCrucibleBlockEntity::new, VOLCANIC_CRUCIBLE.value()));
}
