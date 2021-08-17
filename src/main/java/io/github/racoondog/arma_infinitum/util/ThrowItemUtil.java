package io.github.racoondog.arma_infinitum.util;

import io.github.racoondog.arma_infinitum.Arma_infinitum;
import io.github.racoondog.arma_infinitum.Configs;

import java.util.Random;

public class ThrowItemUtil {
    public static int spread = Arma_infinitum.config.projectileSpread;

    public static float genRandom(float start, Random random) {
        float randomFloat = random.nextFloat() * spread;
        randomFloat = randomFloat - spread / 2;
        return start + randomFloat;
    }

    public static int countProjectiles(int level, Configs.ProjectileCountingStyle style) {
        if (style == Configs.ProjectileCountingStyle.INCREMENTAL) {
            return level + 1;
        } else {
            if (level == 0) {
                return 1;
            } else if (level == 1 || level == 2) {
                return 3;
            } else {
                return level;
            }
        }
    }
}
