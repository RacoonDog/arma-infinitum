package io.github.racoondog.arma_infinitum.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;
import java.util.Random;

@Mixin(CrossbowItem.class)
public interface CrossbowItemInvoker {
    @Invoker
    static float[] invokeGetSoundPitches(Random random) {
        throw new AssertionError();
    }

    @Invoker
    static List<ItemStack> invokeGetProjectiles(ItemStack crossbow) {
        throw new AssertionError();
    }

    @Invoker
    static void invokeShoot(World world, LivingEntity shooter, Hand hand, ItemStack crossbow, ItemStack projectile, float soundPitch, boolean creative, float speed, float divergence, float simulated) {
        throw new AssertionError();
    }

    @Invoker
    static void invokePostShoot(World world, LivingEntity entity, ItemStack stack) {
        throw new AssertionError();
    }

    @Invoker
    static boolean invokeLoadProjectile(LivingEntity shooter, ItemStack crossbow, ItemStack projectile, boolean simulated, boolean creative) {
        throw new AssertionError();
    }
}
