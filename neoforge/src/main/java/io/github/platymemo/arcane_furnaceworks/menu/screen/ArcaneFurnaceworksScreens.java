package io.github.platymemo.arcane_furnaceworks.menu.screen;

import io.github.platymemo.arcane_furnaceworks.Constants;
import io.github.platymemo.arcane_furnaceworks.menu.ArcaneFurnaceworksMenus;
import io.github.platymemo.arcane_furnaceworks.menu.client.VolcanicCrucibleScreen;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = Constants.MOD_ID)
public class ArcaneFurnaceworksScreens {
    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(ArcaneFurnaceworksMenus.VOLCANIC_CRUCIBLE.get(), VolcanicCrucibleScreen::new);
    }
}
