package io.github.platymemo.arcane_furnaceworks.menu;

import io.github.platymemo.arcane_furnaceworks.Constants;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ArcaneFurnaceworksMenus {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(BuiltInRegistries.MENU, Constants.MOD_ID);
    public static final DeferredHolder<MenuType<?>, MenuType<VolcanicCrucibleMenu>> VOLCANIC_CRUCIBLE = MENUS.register(Constants.FurnaceType.VOLCANIC_CORE.getPath(), () -> new MenuType<>(VolcanicCrucibleMenu::new, FeatureFlags.VANILLA_SET));
}
