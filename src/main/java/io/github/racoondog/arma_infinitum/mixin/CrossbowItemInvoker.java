package io.github.racoondog.arma_infinitum.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
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
    static List<ItemStack> invokeGetProjectiles(ItemStack crossbow) {
        throw new AssertionError();
    }

    @Invoker
    static PersistentProjectileEntity invokeCreateArrow(World world, LivingEntity entity, ItemStack crossbow, ItemStack arrow) {
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

    @Invoker
    static float invokeGetSoundPitch(boolean flag, Random random) {
        throw new AssertionError();
    }
}
