package io.github.racoondog.arma_infinitum;

public class Configs {
    public static final int projectileSpread = 20;

    public static final MultishotStyle crossbowMultishotStyle = MultishotStyle.HYBRID;
    public static final MultishotStyle bowMultishotStyle = MultishotStyle.RANDOM;
    public static final MultishotStyle throwableMultishotStyle = MultishotStyle.RANDOM;

    public static final ProjectileCountingStyle crossbowCountingStyle = ProjectileCountingStyle.VANILLA;
    public static final ProjectileCountingStyle bowCountingStyle = ProjectileCountingStyle.VANILLA;
    public static final ProjectileCountingStyle throwableCountingStyle = ProjectileCountingStyle.VANILLA;

    public static final boolean dispenserCompatible = false;

    public enum MultishotStyle {
        ANGLE,
        RANDOM,
        HYBRID;
        MultishotStyle() {}
    }

    public enum ProjectileCountingStyle {
        VANILLA,
        INCREMENTAL;
        ProjectileCountingStyle() {}
    }
}
