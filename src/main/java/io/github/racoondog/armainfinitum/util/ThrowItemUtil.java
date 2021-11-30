package io.github.racoondog.armainfinitum.util;

import io.github.racoondog.armainfinitum.ArmaInfinitum;
import net.minecraft.world.World;

import java.util.Random;

public class ThrowItemUtil {
    public static float genRandom(float start, Random random, World world) {
        final int spread = world.getGameRules().getInt(ArmaInfinitum.PROJECTILE_SPREAD);
        float randomFloat = random.nextFloat() * spread;
        randomFloat = randomFloat - ((float)spread) / 2;
        return start + randomFloat;
    }

    public static int countProjectiles(int level) {
        if (level == 0) {
            return 1;
        } else if (level == 1 || level == 2) {
            return 3;
        } else {
            return level;
        }
    }
}
