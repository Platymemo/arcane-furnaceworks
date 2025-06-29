package io.github.platymemo.arcane_furnaceworks.mixin;

import it.unimi.dsi.fastutil.objects.Reference2IntOpenHashMap;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(AbstractFurnaceBlockEntity.class)
public interface AbstractFurnaceBlockEntityAccessor {
    @Accessor
    Reference2IntOpenHashMap<ResourceKey<Recipe<?>>> getRecipesUsed();

    @Invoker("createExperience")
    static void callCreateExperience(ServerLevel level, Vec3 popVec, int recipeIndex, float experience) {
        throw new UnsupportedOperationException();
    }
}
