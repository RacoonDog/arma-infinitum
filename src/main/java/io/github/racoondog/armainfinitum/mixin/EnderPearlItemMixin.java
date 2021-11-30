package io.github.racoondog.armainfinitum.mixin;

import io.github.racoondog.armainfinitum.ArmaInfinitum;
import io.github.racoondog.armainfinitum.util.ThrowItemUtil;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.EnderPearlItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(EnderPearlItem.class)
public abstract class EnderPearlItemMixin {
    @Inject(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;spawnEntity(Lnet/minecraft/entity/Entity;)Z", shift = At.Shift.AFTER))
    private void multishot(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        ItemStack stack = user.getStackInHand(hand);
        int level = EnchantmentHelper.getLevel(Enchantments.MULTISHOT, stack);
        if (level > 0) {
            int count = ThrowItemUtil.countProjectiles(level) - 1;
            Random random = user.getRandom();
            int spread = world.getGameRules().getInt(ArmaInfinitum.PROJECTILE_SPREAD);
            for (int i = 0; i < count; i++) {
                if (world.getGameRules().getBoolean(ArmaInfinitum.THROWABLE_MULTISHOT_RANDOM)) {
                    //Random
                    ThrowItemUtil.shootEnderPearl(world, user, stack, ThrowItemUtil.genRandom(user.getPitch(), random, spread), ThrowItemUtil.genRandom(user.getYaw(), random, spread));
                } else {
                    //Not Random
                    //Temp
                    ThrowItemUtil.shootEnderPearl(world, user, stack, ThrowItemUtil.genRandom(user.getPitch(), random, spread), ThrowItemUtil.genRandom(user.getYaw(), random, spread));
                }
            }
        }
    }
}
