package io.github.racoondog.arma_infinitum.util;

import io.github.racoondog.arma_infinitum.Arma_infinitum;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.EggEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Random;

public class EggUtil {
    public static void shootEggs(World world, PlayerEntity user, ItemStack itemStack) {
        int level = EnchantmentHelper.getLevel(Enchantments.MULTISHOT, itemStack);
        int count = ThrowItemUtil.countProjectiles(level, Arma_infinitum.config.throwableCountingStyle);
        Random random = user.getRandom();
        for (int i = 0; i < count; ++i) {
            if (i <= 1) {
                shoot(world, user, itemStack);
            } else {
                switch (Arma_infinitum.config.throwableMultishotStyle) {
                    case ANGLE: { //temp
                        shoot(world, user, itemStack, ThrowItemUtil.genRandom(user.getPitch(), random), ThrowItemUtil.genRandom(user.getYaw(), random));
                    }
                    case HYBRID: { //temp
                        shoot(world, user, itemStack, ThrowItemUtil.genRandom(user.getPitch(), random), ThrowItemUtil.genRandom(user.getYaw(), random));
                    }
                    default: { //case RANDOM
                        shoot(world, user, itemStack, ThrowItemUtil.genRandom(user.getPitch(), random), ThrowItemUtil.genRandom(user.getYaw(), random));
                    }
                }
            }
        }
    }

    public static void shoot(World world, PlayerEntity user, ItemStack itemStack, float pitch, float yaw) {
        EggEntity entity = new EggEntity(world, user);
        entity.setItem(itemStack);
        entity.setProperties(user, pitch, yaw, 0.0f, 1.5f, 1.0f);
        world.spawnEntity(entity);
    }
    public static void shoot(World world, PlayerEntity user, ItemStack itemStack) {
        EggEntity entity = new EggEntity(world, user);
        entity.setItem(itemStack);
        entity.setProperties(user, user.getPitch(), user.getYaw(), 0.0f, 1.5f, 1.0f);
        world.spawnEntity(entity);
    }
}
