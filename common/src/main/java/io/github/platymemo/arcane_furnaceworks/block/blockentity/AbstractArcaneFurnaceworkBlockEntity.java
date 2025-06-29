package io.github.platymemo.arcane_furnaceworks.block.blockentity;

import com.google.common.collect.Lists;
import io.github.platymemo.arcane_furnaceworks.mixin.AbstractFurnaceBlockEntityAccessor;
import it.unimi.dsi.fastutil.objects.Reference2IntMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.FuelValues;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class AbstractArcaneFurnaceworkBlockEntity extends AbstractFurnaceBlockEntity implements FurnaceExtensions {
    protected final ContainerData arcaneFurnaceworksData;
    protected int dupeChance = 0; // Represents a percentage
    protected int xpMult = 100; // Represents a multiplier of 1.0
    protected int fuelEfficiency = 100; // Represents a multiplier of 1.0
    protected int speed = 100; // Represents a multiplier of 1.0
    protected int concurrentSlots = 1; // TODO
    protected int autoOutputSpeed = 0; // TODO

    protected AbstractArcaneFurnaceworkBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState, RecipeType<? extends AbstractCookingRecipe> recipeType) {
        super(type, pos, blockState, recipeType);
        arcaneFurnaceworksData = new ContainerData() {
            private static final int COUNT = 6;

            public int get(int i) {
                if (i < AbstractArcaneFurnaceworkBlockEntity.this.dataAccess.getCount()) {
                    return AbstractArcaneFurnaceworkBlockEntity.this.dataAccess.get(i);
                }

                // Shift so our stats are 0-indexed
                i -= AbstractArcaneFurnaceworkBlockEntity.this.dataAccess.getCount();

                return switch (i) {
                    case 0 -> AbstractArcaneFurnaceworkBlockEntity.this.dupeChance;
                    case 1 -> AbstractArcaneFurnaceworkBlockEntity.this.xpMult;
                    case 2 -> AbstractArcaneFurnaceworkBlockEntity.this.fuelEfficiency;
                    case 3 -> AbstractArcaneFurnaceworkBlockEntity.this.speed;
                    case 4 -> AbstractArcaneFurnaceworkBlockEntity.this.concurrentSlots;
                    case 5 -> AbstractArcaneFurnaceworkBlockEntity.this.autoOutputSpeed;
                    default -> AbstractArcaneFurnaceworkBlockEntity.this.getAdditionalData().get(i - COUNT);
                };
            }

            public void set(int i, int value) {
                if (i < AbstractArcaneFurnaceworkBlockEntity.this.dataAccess.getCount()) {
                    AbstractArcaneFurnaceworkBlockEntity.this.dataAccess.set(i, value);
                    return;
                }

                // Shift so our stats are 0-indexed
                i -= AbstractArcaneFurnaceworkBlockEntity.this.dataAccess.getCount();

                switch (i) {
                    case 0 -> AbstractArcaneFurnaceworkBlockEntity.this.dupeChance      = value;
                    case 1 -> AbstractArcaneFurnaceworkBlockEntity.this.xpMult          = value;
                    case 2 -> AbstractArcaneFurnaceworkBlockEntity.this.fuelEfficiency  = value;
                    case 3 -> AbstractArcaneFurnaceworkBlockEntity.this.speed           = value;
                    case 4 -> AbstractArcaneFurnaceworkBlockEntity.this.concurrentSlots = value;
                    case 5 -> AbstractArcaneFurnaceworkBlockEntity.this.autoOutputSpeed = value;
                    default -> {
                        ContainerData data = AbstractArcaneFurnaceworkBlockEntity.this.getAdditionalData();
                        data.set(i - COUNT, value);
                    }
                }

            }

            public int getCount() {
                return COUNT + AbstractArcaneFurnaceworkBlockEntity.this.dataAccess.getCount();
            }
        };
    }

    /**
     * Gets the additional container data.
     *
     * @return the additional data.
     */
    public ContainerData getAdditionalData() {
        return new SimpleContainerData(0);
    }

    public float getSpeed() {
        return this.speed / 100f;
    }

    @Override
    public void arcaneFurnaceworks$onSmelted(ServerLevel level, BlockPos pos, BlockState state) {
        int dupes = this.dupeChance / 100;
        int addtlChance = this.dupeChance % 100;

        if (Math.random() < addtlChance / 100f) {
            dupes++;
        }

        ItemStack stack = this.items.get(1);
        stack.grow(dupes);
        stack.limitSize(stack.getMaxStackSize());
    }

    public @NotNull List<RecipeHolder<?>> getRecipesToAwardAndPopExperience(@NotNull ServerLevel level, @NotNull Vec3 popVec) {
        List<RecipeHolder<?>> list = Lists.newArrayList();
        for (Reference2IntMap.Entry<ResourceKey<Recipe<?>>> resourceKeyEntry : ((AbstractFurnaceBlockEntityAccessor) this).getRecipesUsed().reference2IntEntrySet()) {
            level.recipeAccess().byKey(resourceKeyEntry.getKey()).ifPresent((recipe) -> {
                list.add(recipe);
                AbstractFurnaceBlockEntityAccessor.callCreateExperience(level, popVec, resourceKeyEntry.getIntValue(), ((AbstractCookingRecipe) recipe.value()).experience() * (this.xpMult / 100f));
            });
        }

        return list;
    }

    @Override
    protected int getBurnDuration(@NotNull FuelValues fuelValues, @NotNull ItemStack stack) {
        return (int) (super.getBurnDuration(fuelValues, stack) * (this.fuelEfficiency / 100f));
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag nbt, HolderLookup.@NotNull Provider registry) {
        super.saveAdditional(nbt, registry);
        nbt.putShort("duplication_chance", (short) this.dupeChance);
        nbt.putShort("xp_multiplier", (short) this.xpMult);
        nbt.putShort("fuel_efficiency", (short) this.fuelEfficiency);
        nbt.putShort("speed", (short) this.speed);
        nbt.putShort("concurrent_slots", (short) this.concurrentSlots);
        nbt.putShort("auto_output_speed", (short) this.autoOutputSpeed);
    }

    @Override
    protected void loadAdditional(@NotNull CompoundTag nbt, HolderLookup.@NotNull Provider registry) {
        super.loadAdditional(nbt, registry);
        this.dupeChance = nbt.getShort("duplication_chance");
        this.xpMult = nbt.getShort("xp_multiplier");
        this.fuelEfficiency = nbt.getShort("fuel_efficiency");
        this.speed = nbt.getShort("speed");
        this.concurrentSlots = nbt.getShort("concurrent_slots");
        this.autoOutputSpeed = nbt.getShort("auto_output_speed");
    }
}
