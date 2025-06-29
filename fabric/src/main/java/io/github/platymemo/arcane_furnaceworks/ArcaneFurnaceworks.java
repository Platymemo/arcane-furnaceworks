package io.github.platymemo.arcane_furnaceworks;

import io.github.platymemo.arcane_furnaceworks.block.ArcaneFurnaceworksBlocks;
import io.github.platymemo.arcane_furnaceworks.menu.ArcaneFurnaceworksMenus;
import net.fabricmc.api.ModInitializer;

public class ArcaneFurnaceworks implements ModInitializer {
    @Override
    public void onInitialize() {
        // Use Fabric to bootstrap the Common mod.
        Constants.LOG.info("Hello Fabric world!");
        CommonClass.init();

        ArcaneFurnaceworksBlocks.register();
        ArcaneFurnaceworksMenus.register();
    }
}
