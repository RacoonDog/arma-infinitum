package io.github.racoondog.armainfinitum.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.Random;

@Mixin(CrossbowItem.class)
public interface CrossbowItemInvoker {
    @Invoker
    static PersistentProjectileEntity invokeCreateArrow(World world, LivingEntity entity, ItemStack crossbow, ItemStack arrow) {
        throw new AssertionError();
    }

    @Invoker
    static float invokeGetSoundPitch(boolean flag, Random random) {
        throw new AssertionError();
    }
}
