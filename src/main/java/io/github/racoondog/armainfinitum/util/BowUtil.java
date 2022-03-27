package io.github.racoondog.armainfinitum.util;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class BowUtil {
    public static void shoot(World world, LivingEntity user, ItemStack stack, float pitch, float yaw, float pullProgress) {
        ArrowItem arrowItem = (ArrowItem)(stack.getItem() instanceof ArrowItem ? stack.getItem() : Items.ARROW);
        PersistentProjectileEntity persistentProjectileEntity = arrowItem.createArrow(world, stack, user);
        persistentProjectileEntity.setVelocity(user, pitch, yaw, 0.0f, pullProgress * 3.0f, 1.0f);

        if (pullProgress == 1.0f) {
            persistentProjectileEntity.setCritical(true);
        }
        int power;
        if ((power = EnchantmentHelper.getLevel(Enchantments.POWER, stack)) > 0) {
            persistentProjectileEntity.setDamage(persistentProjectileEntity.getDamage() + (double)power * 0.5 + 0.5);
        }
        int punch;
        if ((punch = EnchantmentHelper.getLevel(Enchantments.PUNCH, stack)) > 0) {
            persistentProjectileEntity.setPunch(punch);
        }
        if (EnchantmentHelper.getLevel(Enchantments.FLAME, stack) > 0) {
            persistentProjectileEntity.setOnFireFor(100);
        }

        world.spawnEntity(persistentProjectileEntity);
    }
}
