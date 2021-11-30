package io.github.racoondog.armainfinitum.util;

import io.github.racoondog.armainfinitum.mixin.CrossbowItemInvoker;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.CrossbowUser;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Quaternion;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.World;

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

    public static void shoot(World world, LivingEntity shooter, Hand hand, ItemStack crossbow, ItemStack projectile, float soundPitch, boolean creative, float speed, float divergence, float simulated, int index) {
        if (!world.isClient) {
            boolean bl = projectile.isOf(Items.FIREWORK_ROCKET);
            Object projectileEntity2;
            if (bl) {
                projectileEntity2 = new FireworkRocketEntity(world, projectile, shooter, shooter.getX(), shooter.getEyeY() - 0.15000000596046448D, shooter.getZ(), true);
            } else {
                projectileEntity2 = CrossbowItemInvoker.invokeCreateArrow(world, shooter, crossbow, projectile);
                if (creative || simulated != 0.0F) {
                    ((PersistentProjectileEntity)projectileEntity2).pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
                }
            }

            if (shooter instanceof CrossbowUser crossbowUser) {
                crossbowUser.shoot(crossbowUser.getTarget(), crossbow, (ProjectileEntity)projectileEntity2, simulated);
            } else {
                Vec3d vec3d = shooter.getOppositeRotationVector(1.0F);
                Quaternion quaternion = new Quaternion(new Vec3f(vec3d), simulated, true);
                Vec3d vec3d2 = shooter.getRotationVec(1.0F);
                Vec3f vec3f = new Vec3f(vec3d2);
                vec3f.rotate(quaternion);
                ((ProjectileEntity)projectileEntity2).setVelocity((double)vec3f.getX(), (double)vec3f.getY(), (double)vec3f.getZ(), speed, divergence);
            }

            if (EnchantmentHelper.getLevel(Enchantments.FLAME, crossbow) > 0) {
                ((Entity)projectileEntity2).setOnFireFor(100 * EnchantmentHelper.getLevel(Enchantments.FLAME, crossbow));
            }

            crossbow.damage(bl ? 3 : 1, shooter, (e) -> {
                e.sendToolBreakStatus(hand);
            });
            world.spawnEntity((Entity)projectileEntity2);
            if (index < 25) world.playSound((PlayerEntity)null, shooter.getX(), shooter.getY(), shooter.getZ(), SoundEvents.ITEM_CROSSBOW_SHOOT, SoundCategory.PLAYERS, 1.0F, soundPitch);
        }
    }
}
