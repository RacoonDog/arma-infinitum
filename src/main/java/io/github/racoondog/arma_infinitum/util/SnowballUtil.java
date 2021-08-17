package io.github.racoondog.arma_infinitum.util;

import io.github.racoondog.arma_infinitum.Arma_infinitum;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Random;

public class SnowballUtil {
    public static void shootSnowballs(World world, PlayerEntity user, ItemStack itemStack) {
        int level = EnchantmentHelper.getLevel(Enchantments.MULTISHOT, itemStack);
        int count = ThrowItemUtil.countProjectiles(level, Arma_infinitum.config.throwableCountingStyle);
        Random random = user.getRandom();
        for (int i = 0; i < count; ++i) {
            if (i == 0) {
                shoot(world, user, itemStack, user.getPitch(), user.getYaw());
            } else {
                switch (Arma_infinitum.config.throwableMultishotStyle) {
                    case ANGLE: { //temp
                        shoot(world, user, itemStack, ThrowItemUtil.genRandom(user.getPitch(), random), ThrowItemUtil.genRandom(user.getYaw(), random));
                    }
                    default: { //case RANDOM
                        shoot(world, user, itemStack, ThrowItemUtil.genRandom(user.getPitch(), random), ThrowItemUtil.genRandom(user.getYaw(), random));
                    }
                }
            }
        }
    }

    private static void shoot(World world, PlayerEntity user, ItemStack itemStack, float pitch, float yaw) {
        SnowballEntity entity = new SnowballEntity(world, user);
        entity.setItem(itemStack);
        entity.setProperties(user, pitch, yaw, 0.0f, 1.5f, 1.0f);
        world.spawnEntity(entity);
    }
}
