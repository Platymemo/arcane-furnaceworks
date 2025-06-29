package io.github.platymemo.arcane_furnaceworks;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.Stat;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.Stats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Constants {
	public static final String MOD_ID = "arcane_furnaceworks";
	public static final String MOD_NAME = "Arcane Furnaceworks";
	public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    public static ResourceLocation id(String path) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
	}

	public enum FurnaceType {
		VOLCANIC_CORE("volcanic_core"),
		FIRE_BREATHER("fire_breather"),
		LIGHTNING_FURNACE("lightning_furnace"),
		FURNACE_OF_THE_MOUNTAIN_KING("furnace_of_the_mountain_king"),
		CLOCKWORK_FURNACE("clockwork_furnace"),
		CALCIFER("calcifer"),
		TOASTER("toaster"),
		SOLAR_TOWER("solar_tower"),
		INFERNAL_ALTAR("infernal_altar"),
		HEART_FURNACE("heart_furnace");

		private final String path;
		private final ResourceLocation stat;

		FurnaceType(String path) {
			this.path = path;
			this.stat = id("interact_with_" + path);
		}

		public String getPath() {
			return this.path;
		}

		public ResourceLocation getId() {
			return id(this.path);
		}

		public ResourceLocation getInteractionStatId() {
			return stat;
		}

		public Stat<ResourceLocation> getInteractionStat() {
			return Stats.CUSTOM.get(this.stat, StatFormatter.DEFAULT);
		}
	}
}
