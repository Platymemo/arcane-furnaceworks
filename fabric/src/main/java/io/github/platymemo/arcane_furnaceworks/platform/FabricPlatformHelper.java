package io.github.platymemo.arcane_furnaceworks.platform;

import io.github.platymemo.arcane_furnaceworks.Constants;
import io.github.platymemo.arcane_furnaceworks.block.ArcaneFurnaceworksBlocks;
import io.github.platymemo.arcane_furnaceworks.menu.ArcaneFurnaceworksMenus;
import io.github.platymemo.arcane_furnaceworks.platform.services.IPlatformHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.apache.commons.lang3.NotImplementedException;

public class FabricPlatformHelper implements IPlatformHelper {
    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @Override
    public MenuType<? extends AbstractFurnaceMenu> getMenuType(Constants.FurnaceType furnaceType) {
        return switch (furnaceType) {
            case VOLCANIC_CORE -> ArcaneFurnaceworksMenus.VOLCANIC_CRUCIBLE;
            case FIRE_BREATHER -> throw new NotImplementedException();
            case LIGHTNING_FURNACE -> throw new NotImplementedException();
            case FURNACE_OF_THE_MOUNTAIN_KING -> throw new NotImplementedException();
            case CLOCKWORK_FURNACE -> throw new NotImplementedException();
            case CALCIFER -> throw new NotImplementedException();
            case TOASTER -> throw new NotImplementedException();
            case SOLAR_TOWER -> throw new NotImplementedException();
            case INFERNAL_ALTAR -> throw new NotImplementedException();
            case HEART_FURNACE -> throw new NotImplementedException();
        };
    }

    @Override
    public BlockEntityType<? extends AbstractFurnaceBlockEntity> getBlockEntityType(Constants.FurnaceType furnaceType) {
        return switch (furnaceType) {
            case VOLCANIC_CORE -> ArcaneFurnaceworksBlocks.VOLCANIC_CRUCIBLE_BLOCK_ENTITY_TYPE;
            case FIRE_BREATHER -> throw new NotImplementedException();
            case LIGHTNING_FURNACE -> throw new NotImplementedException();
            case FURNACE_OF_THE_MOUNTAIN_KING -> throw new NotImplementedException();
            case CLOCKWORK_FURNACE -> throw new NotImplementedException();
            case CALCIFER -> throw new NotImplementedException();
            case TOASTER -> throw new NotImplementedException();
            case SOLAR_TOWER -> throw new NotImplementedException();
            case INFERNAL_ALTAR -> throw new NotImplementedException();
            case HEART_FURNACE -> throw new NotImplementedException();
        };
    }
}
