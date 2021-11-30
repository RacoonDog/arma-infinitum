package io.github.racoondog.armainfinitum.util;

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
    public static float genRandom(float start, Random random, int spread) {
        float randomFloat = random.nextFloat() * spread;
        randomFloat = randomFloat - ((float)spread) / 2;
        return start + randomFloat;
    }

    public static int countProjectiles(int level) {
        if (level == 1 || level == 2) {
            return 3;
        } else {
            return level;
        }
    }

    public static void shootSnowball(World world, PlayerEntity user, ItemStack itemStack, float pitch, float yaw) {
        SnowballEntity entity = new SnowballEntity(world, user);
        entity.setItem(itemStack);
        entity.setProperties(user, pitch, yaw, 0.0f, 1.5f, 1.0f);
        if (EnchantmentHelper.getLevel(Enchantments.FLAME, itemStack) > 0) {
            entity.setOnFireFor(100 * EnchantmentHelper.getLevel(Enchantments.FLAME, itemStack));
        }
        world.spawnEntity(entity);
    }

    public static void shootEgg(World world, PlayerEntity user, ItemStack itemStack, float pitch, float yaw) {
        EggEntity entity = new EggEntity(world, user);
        entity.setItem(itemStack);
        entity.setProperties(user, pitch, yaw, 0.0f, 1.5f, 1.0f);
        if (EnchantmentHelper.getLevel(Enchantments.FLAME, itemStack) > 0) {
            entity.setOnFireFor(100 * EnchantmentHelper.getLevel(Enchantments.FLAME, itemStack));
        }
        world.spawnEntity(entity);
    }

    public static void shootEnderPearl(World world, PlayerEntity user, ItemStack itemStack, float pitch, float yaw) {
        EnderPearlEntity entity = new EnderPearlEntity(world, user);
        entity.setItem(itemStack);
        entity.setProperties(user, pitch, yaw, 0.0f, 1.5f, 1.0f);
        if (EnchantmentHelper.getLevel(Enchantments.FLAME, itemStack) > 0) {
            entity.setOnFireFor(100 * EnchantmentHelper.getLevel(Enchantments.FLAME, itemStack));
        }
        world.spawnEntity(entity);
    }
}
