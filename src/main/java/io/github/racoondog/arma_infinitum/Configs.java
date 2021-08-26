package io.github.racoondog.arma_infinitum;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = Arma_infinitum.modid)
public class Configs implements ConfigData {
    @Comment(value = "https://github.com/RacoonDog/arma-infinitum/wiki/Config")
    public final int projectileSpread = 20;
    public final int soundEffects = 225;

    //public final MultishotStyle crossbowMultishotStyle = MultishotStyle.ANGLE;
    //public final MultishotStyle bowMultishotStyle = MultishotStyle.RANDOM;
    public final MultishotStyle throwableMultishotStyle = MultishotStyle.RANDOM;

    public final ProjectileCountingStyle crossbowCountingStyle = ProjectileCountingStyle.VANILLA;
    //public final ProjectileCountingStyle bowCountingStyle = ProjectileCountingStyle.VANILLA;
    public final ProjectileCountingStyle throwableCountingStyle = ProjectileCountingStyle.VANILLA;

    //public final boolean dispenserCompatible = false;

    public enum MultishotStyle {
        ANGLE,
        RANDOM;
    }

    public enum ProjectileCountingStyle {
        VANILLA,
        INCREMENTAL;
    }
}
