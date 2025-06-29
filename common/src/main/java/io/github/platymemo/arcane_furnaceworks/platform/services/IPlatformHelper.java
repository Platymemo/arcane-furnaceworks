package io.github.platymemo.arcane_furnaceworks.platform.services;

import io.github.platymemo.arcane_furnaceworks.Constants;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public interface IPlatformHelper {

    /**
     * Gets the name of the current platform
     *
     * @return The name of the current platform.
     */
    String getPlatformName();

    /**
     * Checks if a mod with the given id is loaded.
     *
     * @param modId The mod to check if it is loaded.
     * @return True if the mod is loaded, false otherwise.
     */
    boolean isModLoaded(String modId);

    /**
     * Check if the game is currently in a development environment.
     *
     * @return True if in a development environment, false otherwise.
     */
    boolean isDevelopmentEnvironment();

    /**
     * Gets the name of the environment type as a string.
     *
     * @return The name of the environment type.
     */
    default String getEnvironmentName() {
        return isDevelopmentEnvironment() ? "development" : "production";
    }

    /**
     * MenuType registration is platform specific, so this helper allows the menus in common code reference their
     * registered menu type.
     *
     * @param furnaceType The type of furnace.
     * @return The menu type for the provided furnace type.
     */
    MenuType<? extends AbstractFurnaceMenu> getMenuType(Constants.FurnaceType furnaceType);

    /**
     * BlockEntityType registration is platform specific, so this helper allows the menus in common code reference their
     * registered block entity type.
     *
     * @param furnaceType The type of furnace.
     * @return The block entity type for the provided furnace type.
     */
    BlockEntityType<? extends AbstractFurnaceBlockEntity> getBlockEntityType(Constants.FurnaceType furnaceType);
}
