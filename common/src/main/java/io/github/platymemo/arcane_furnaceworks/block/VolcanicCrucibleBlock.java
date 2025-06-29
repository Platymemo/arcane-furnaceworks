package io.github.platymemo.arcane_furnaceworks.block;

import com.mojang.serialization.MapCodec;
import io.github.platymemo.arcane_furnaceworks.Constants;
import io.github.platymemo.arcane_furnaceworks.block.blockentity.VolcanicCrucibleBlockEntity;
import io.github.platymemo.arcane_furnaceworks.platform.Services;
import io.github.platymemo.arcane_furnaceworks.stats.ArcaneFurnaceworksStats;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class VolcanicCrucibleBlock extends AbstractFurnaceBlock {
    public static final MapCodec<VolcanicCrucibleBlock> CODEC = simpleCodec(VolcanicCrucibleBlock::new);

    public @NotNull MapCodec<VolcanicCrucibleBlock> codec() {
        return CODEC;
    }

    protected VolcanicCrucibleBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new VolcanicCrucibleBlockEntity(pos, state);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> type) {
        return createFurnaceTicker(level, type, Services.PLATFORM.getBlockEntityType(Constants.FurnaceType.VOLCANIC_CORE));
    }

    protected void openContainer(Level level, @NotNull BlockPos pos, @NotNull Player player) {
        BlockEntity blockentity = level.getBlockEntity(pos);
        if (blockentity instanceof VolcanicCrucibleBlockEntity) {
            player.openMenu((MenuProvider) blockentity);
            player.awardStat(ArcaneFurnaceworksStats.INTERACT_WITH_VOLCANIC_CRUCIBLE);
        }

    }

    public void animateTick(BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull RandomSource source) {
        if (state.getValue(LIT)) {
            double centerX = (double) pos.getX() + 0.5;
            double bottomY = pos.getY();
            double centerZ = (double) pos.getZ() + 0.5;
            if (source.nextDouble() < 0.1) {
                level.playLocalSound(centerX, bottomY, centerZ, SoundEvents.LAVA_POP, SoundSource.BLOCKS, 1.0F, 1.0F, false);
            }

            Direction direction = state.getValue(FACING);
            Direction.Axis axis = direction.getAxis();
            double facingDirectionOffsetScale = 0.52;
            double nonFacingOffset = source.nextDouble() * 0.6 - 0.3;
            double offsetX = axis == Direction.Axis.X ? (double) direction.getStepX() * facingDirectionOffsetScale : nonFacingOffset;
            double offsetY = source.nextDouble() * 9.0 / 16.0;
            double offsetZ = axis == Direction.Axis.Z ? (double) direction.getStepZ() * facingDirectionOffsetScale : nonFacingOffset;
            level.addParticle(ParticleTypes.SMOKE, centerX + offsetX, bottomY + offsetY, centerZ + offsetZ, 0.0, 0.0, 0.0);
        }

    }
}
