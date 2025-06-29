package io.github.platymemo.arcane_furnaceworks.block;

import io.github.platymemo.arcane_furnaceworks.Constants;
import io.github.platymemo.arcane_furnaceworks.block.blockentity.VolcanicCrucibleBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

public class ArcaneFurnaceworksBlocks {
    public static final Block VOLCANIC_CRUCIBLE = new VolcanicCrucibleBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.5F).lightLevel(Blocks.litBlockEmission(15)).setId(ResourceKey.create(Registries.BLOCK, Constants.FurnaceType.VOLCANIC_CORE.getId())));
    public static final BlockEntityType<VolcanicCrucibleBlockEntity> VOLCANIC_CRUCIBLE_BLOCK_ENTITY_TYPE = FabricBlockEntityTypeBuilder.create(VolcanicCrucibleBlockEntity::new, VOLCANIC_CRUCIBLE).build();

    public static void register() {
        Registry.register(BuiltInRegistries.BLOCK, Constants.FurnaceType.VOLCANIC_CORE.getId(), VOLCANIC_CRUCIBLE);
        Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, Constants.FurnaceType.VOLCANIC_CORE.getId(), VOLCANIC_CRUCIBLE_BLOCK_ENTITY_TYPE);
    }
}
