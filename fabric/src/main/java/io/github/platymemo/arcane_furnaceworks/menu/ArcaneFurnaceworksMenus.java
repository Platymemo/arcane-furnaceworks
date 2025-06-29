package io.github.platymemo.arcane_furnaceworks.menu;

import io.github.platymemo.arcane_furnaceworks.Constants;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;

public class ArcaneFurnaceworksMenus {
    public static final MenuType<VolcanicCrucibleMenu> VOLCANIC_CRUCIBLE = new MenuType<>(VolcanicCrucibleMenu::new, FeatureFlags.VANILLA_SET);

    public static void register() {
        Registry.register(BuiltInRegistries.MENU, Constants.FurnaceType.VOLCANIC_CORE.getId(), VOLCANIC_CRUCIBLE);
    }
}
