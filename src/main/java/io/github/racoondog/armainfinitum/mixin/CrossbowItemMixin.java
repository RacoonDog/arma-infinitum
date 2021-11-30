package io.github.racoondog.armainfinitum.mixin;

import io.github.racoondog.armainfinitum.util.CrossbowUtil;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(CrossbowItem.class)
public abstract class CrossbowItemMixin {
    @Shadow private static void postShoot(World world, LivingEntity entity, ItemStack stack) {}

    @Shadow private static boolean loadProjectile(LivingEntity shooter, ItemStack crossbow, ItemStack projectile, boolean simulated, boolean creative) { throw new AssertionError(); }

    @Shadow private static List<ItemStack> getProjectiles(ItemStack crossbow) { throw new AssertionError(); }

    /**
     * @author crosby
     * @reason
     */
    @Overwrite
    public static void shootAll(World world, LivingEntity entity, Hand hand, ItemStack stack, float speed, float divergence) {
        List<ItemStack> list = getProjectiles(stack);
        float[] fs = CrossbowUtil.getSoundPitches(entity.getRandom(), list.size());

        for (int i = 0; i < list.size(); ++i) {
            ItemStack itemStack = (ItemStack) list.get(i);
            boolean bl = entity instanceof PlayerEntity && ((PlayerEntity) entity).getAbilities().creativeMode;
            if (!itemStack.isEmpty()) {
                if (list.size() > 3) {
                    if (i == 0) {
                        CrossbowUtil.shoot(world, entity, hand, stack, itemStack, fs[i], bl, speed, divergence, 0.0F, i);
                    } else {
                        float r = entity.getRandom().nextFloat() * 20 - 10;
                        CrossbowUtil.shoot(world, entity, hand, stack, itemStack, fs[i], bl, speed, divergence, r, i);
                    }
                } else {
                    if (i == 0) {
                        CrossbowUtil.shoot(world, entity, hand, stack, itemStack, fs[i], bl, speed, divergence, 0.0F, i);
                    } else if (i == 1) {
                        CrossbowUtil.shoot(world, entity, hand, stack, itemStack, fs[i], bl, speed, divergence, -10.0F, i);
                    } else if (i == 2) {
                        CrossbowUtil.shoot(world, entity, hand, stack, itemStack, fs[i], bl, speed, divergence, 10.0F, i);
                    }
                }
            }
        }

        postShoot(world, entity, stack);
    }

    /**
     * @author crosby
     * @reason
     * @todo replace by modifying the value of 'j' in the original method
     */
    @Overwrite
    private static boolean loadProjectiles(LivingEntity shooter, ItemStack projectile) {
        int i = EnchantmentHelper.getLevel(Enchantments.MULTISHOT, projectile);
        int j;
        if (i == 0) { //count projectiles
            j = 1;
        } else if (i == 1 || i == 2) {
            j = 3;
        } else {
            j = i;
        }
        boolean bl = shooter instanceof PlayerEntity && ((PlayerEntity) shooter).getAbilities().creativeMode;
        ItemStack itemStack = shooter.getArrowType(projectile);
        ItemStack itemStack2 = itemStack.copy();

        for (int k = 0; k < j; ++k) {
            if (k > 0) {
                itemStack = itemStack2.copy();
            }

            if (itemStack.isEmpty() && bl) {
                itemStack = new ItemStack(Items.ARROW);
                itemStack2 = itemStack.copy();
            }

            if (!loadProjectile(shooter, projectile, itemStack, k > 0, bl)) {
                return false;
            }
        }

        return true;
    }
}