package io.github.racoondog.armainfinitum.mixin;

import io.github.racoondog.armainfinitum.ArmaInfinitum;
import io.github.racoondog.armainfinitum.util.BowUtil;
import io.github.racoondog.armainfinitum.util.ThrowItemUtil;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(BowItem.class)
public abstract class BowItemMixin {
    @Inject(method = "onStoppedUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;spawnEntity(Lnet/minecraft/entity/Entity;)Z", shift = At.Shift.AFTER))
    private void multishot(ItemStack stack, World world, LivingEntity user, int remainingUseTicks, CallbackInfo ci) {
        int level = EnchantmentHelper.getLevel(Enchantments.MULTISHOT, stack);
        if (level > 0) {
            int count = ThrowItemUtil.countProjectiles(level) - 1;
            Random random = user.getRandom();
            int spread = world.getGameRules().getInt(ArmaInfinitum.PROJECTILE_SPREAD);
            float f = BowItem.getPullProgress(stack.getItem().getMaxUseTime(stack) - remainingUseTicks);
            for (int i = 0; i < count; i++) {
                if (world.getGameRules().getBoolean(ArmaInfinitum.BOW_MULTISHOT_RANDOM)) {
                    //Random
                    BowUtil.shoot(world, user, stack, ThrowItemUtil.genRandom(user.getPitch(), random, spread), ThrowItemUtil.genRandom(user.getYaw(), random, spread), f);
                } else {
                    //Not Random
                    BowUtil.shoot(world, user, stack, user.getPitch(), ThrowItemUtil.genAngle(user.getYaw(), count, i, spread), f);
                }
            }
        }
    }
}
