package io.github.racoondog.arma_infinitum.util;

import io.github.racoondog.arma_infinitum.mixin.CrossbowItemInvoker;

import java.util.Random;

public class CrossbowUtil {
    public static float[] getSoundPitches(Random random, int number) {
        boolean bl = random.nextBoolean();
        float[] output = new float[number];
        for (int i = 0; i < number; ++i) {
            if (i == 0) {
                output[i] = 1.0f;
            } else if (i % 2 == 1) {
                output[i] = CrossbowItemInvoker.invokeGetSoundPitch(bl, random);
            } else {
                output[i] = CrossbowItemInvoker.invokeGetSoundPitch(!bl, random);
            }
        }
        return output;
    }
}
