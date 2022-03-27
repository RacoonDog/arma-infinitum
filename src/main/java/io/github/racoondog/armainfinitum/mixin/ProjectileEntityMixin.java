package io.github.racoondog.armainfinitum.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.hit.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ProjectileEntity.class)
public abstract class ProjectileEntityMixin {
    @Inject(method = "onEntityHit", at = @At("HEAD"))
    protected void onEntityHit(EntityHitResult entityHitResult, CallbackInfo info) {
        if (!(((ProjectileEntity) (Object) this) instanceof PersistentProjectileEntity)) {
            Entity entity = entityHitResult.getEntity();
            boolean bl = entity.getType() == EntityType.ENDERMAN;
            if (((ProjectileEntity) (Object) this).isOnFire() && !bl) {
                entity.setOnFireFor(5);
            }
        }
    }
}
