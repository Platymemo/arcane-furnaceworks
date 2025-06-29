package io.github.platymemo.arcane_furnaceworks;

import io.github.platymemo.arcane_furnaceworks.stats.ArcaneFurnaceworksStats;

// This class is part of the common project meaning it is shared between all supported loaders. Code written here can only
// import and access the vanilla codebase, libraries used by vanilla, and optionally third party libraries that provide
// common compatible binaries. This means common code can not directly use loader specific concepts such as Forge events
// however it will be compatible with all supported mod loaders.
public class CommonClass {
    public static void init() {
        ArcaneFurnaceworksStats.register();
    }
}
