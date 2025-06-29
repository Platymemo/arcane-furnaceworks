package io.github.platymemo.arcane_furnaceworks;

import io.github.platymemo.arcane_furnaceworks.menu.client.ArcaneFurnaceworksScreens;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ArcaneFurnaceworksClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ArcaneFurnaceworksScreens.register();
    }
}
