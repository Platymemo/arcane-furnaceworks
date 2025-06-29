package io.github.platymemo.arcane_furnaceworks.stats;

import io.github.platymemo.arcane_furnaceworks.Constants;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.Stats;

public class ArcaneFurnaceworksStats {
    public static final ResourceLocation INTERACT_WITH_VOLCANIC_CRUCIBLE = createInteractionStat("interact_with_" + Constants.FurnaceType.VOLCANIC_CORE.getPath());

    public static void register() {}

    private static ResourceLocation createInteractionStat(String key) {
        ResourceLocation resourceLocation = Constants.id(key);
        Registry.register(BuiltInRegistries.CUSTOM_STAT, resourceLocation, resourceLocation);
        Stats.CUSTOM.get(resourceLocation, StatFormatter.DEFAULT);
        return resourceLocation;
    }
}
