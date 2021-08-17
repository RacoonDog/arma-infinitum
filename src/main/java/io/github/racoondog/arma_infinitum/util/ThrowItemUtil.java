package io.github.racoondog.arma_infinitum.util;

import io.github.racoondog.arma_infinitum.Arma_infinitum;
import io.github.racoondog.arma_infinitum.Configs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.EggEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

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

    public static void shootSnowballs(World world, PlayerEntity user, ItemStack itemStack) {
        int level = EnchantmentHelper.getLevel(Enchantments.MULTISHOT, itemStack);
        int count = countProjectiles(level, Arma_infinitum.config.throwableCountingStyle);
        SnowballEntity entity = new SnowballEntity(world, user);
        entity.setItem(itemStack);
        SnowballEntity entity2;
        Random random = user.getRandom();
        for (int i = 0; i < count; ++i) {
            entity2 = entity;
            if (i == 1) {
                entity2.setProperties(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 1.0F);
                world.spawnEntity(entity2);
            } else {
                entity2.setProperties(user, genRandom(user.getPitch(), random), genRandom(user.getYaw(), random), 0.0F, 1.5F, 1.0F);
                world.spawnEntity(entity2);
            }
        }
    }

    public static void shootEnderPearls(World world, PlayerEntity user, ItemStack itemStack) {
        int level = EnchantmentHelper.getLevel(Enchantments.MULTISHOT, itemStack);
        int count = countProjectiles(level, Arma_infinitum.config.throwableCountingStyle);
        EnderPearlEntity entity = new EnderPearlEntity(world, user);
        entity.setItem(itemStack);
        EnderPearlEntity entity2;
        Random random = user.getRandom();
        for (int i = 0; i < count; ++i) {
            entity2 = entity;
            if (i == 1) {
                entity2.setProperties(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 1.0F);
                world.spawnEntity(entity2);
            } else {
                entity2.setProperties(user, genRandom(user.getPitch(), random), genRandom(user.getYaw(), random), 0.0F, 1.5F, 1.0F);
                world.spawnEntity(entity2);
            }
        }
    }
}
