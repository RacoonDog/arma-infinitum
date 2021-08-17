package io.github.racoondog.arma_infinitum;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = Arma_infinitum.modid)
public class Configs implements ConfigData {
    public final int projectileSpread = 20;

    public final MultishotStyle crossbowMultishotStyle = MultishotStyle.HYBRID;
    public final MultishotStyle bowMultishotStyle = MultishotStyle.RANDOM;
    public final MultishotStyle throwableMultishotStyle = MultishotStyle.RANDOM;

    public final ProjectileCountingStyle crossbowCountingStyle = ProjectileCountingStyle.VANILLA;
    public final ProjectileCountingStyle bowCountingStyle = ProjectileCountingStyle.VANILLA;
    public final ProjectileCountingStyle throwableCountingStyle = ProjectileCountingStyle.VANILLA;

    public final boolean dispenserCompatible = false;

    public enum MultishotStyle {
        ANGLE,
        RANDOM,
        HYBRID;
    }

    public enum ProjectileCountingStyle {
        VANILLA,
        INCREMENTAL;
    }
}
