package io.github.platymemo.arcane_furnaceworks.stats;

import io.github.platymemo.arcane_furnaceworks.Constants;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ArcaneFurnaceworksStats {
    public static final DeferredRegister<ResourceLocation> STATS = DeferredRegister.create(BuiltInRegistries.CUSTOM_STAT, Constants.MOD_ID);

    static {
        STATS.register(Constants.FurnaceType.VOLCANIC_CORE.getInteractionStatId().getPath(), Constants.FurnaceType.VOLCANIC_CORE::getInteractionStatId);
    }
}
