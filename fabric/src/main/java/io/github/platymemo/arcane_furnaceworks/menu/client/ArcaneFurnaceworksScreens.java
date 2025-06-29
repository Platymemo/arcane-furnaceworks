package io.github.platymemo.arcane_furnaceworks.menu.client;

import io.github.platymemo.arcane_furnaceworks.menu.ArcaneFurnaceworksMenus;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screens.MenuScreens;

@Environment(EnvType.CLIENT)
public class ArcaneFurnaceworksScreens {
    public static void register() {
        MenuScreens.register(ArcaneFurnaceworksMenus.VOLCANIC_CRUCIBLE, VolcanicCrucibleScreen::new);
    }
}
