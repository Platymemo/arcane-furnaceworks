package io.github.platymemo.arcane_furnaceworks;

import io.github.platymemo.arcane_furnaceworks.block.ArcaneFurnaceworksBlocks;
import io.github.platymemo.arcane_furnaceworks.menu.ArcaneFurnaceworksMenus;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class ArcaneFurnaceworks {
    public ArcaneFurnaceworks(IEventBus eventBus) {
        // Use NeoForge to bootstrap the Common mod.
        Constants.LOG.info("Hello NeoForge world!");
        CommonClass.init();

        ArcaneFurnaceworksBlocks.BLOCKS.register(eventBus);
        ArcaneFurnaceworksBlocks.BLOCK_ENTITY_TYPES.register(eventBus);
        ArcaneFurnaceworksMenus.MENUS.register(eventBus);
    }
}
