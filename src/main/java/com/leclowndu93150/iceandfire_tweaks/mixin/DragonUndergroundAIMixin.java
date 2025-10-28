package com.leclowndu93150.iceandfire_tweaks.mixin;

import com.github.alexthe666.iceandfire.entity.EntityDragonBase;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = EntityDragonBase.class, remap = false)
public class DragonUndergroundAIMixin {

    @Inject(method = "isAllowedToTriggerFlight", at = @At("HEAD"), cancellable = true)
    private void preventFlightInDen(CallbackInfoReturnable<Boolean> cir) {
        EntityDragonBase dragon = (EntityDragonBase) (Object) this;
        
        if (isInDen(dragon)) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "hasFlightClearance", at = @At("HEAD"), cancellable = true)
    private void checkFlightClearanceInDen(CallbackInfoReturnable<Boolean> cir) {
        EntityDragonBase dragon = (EntityDragonBase) (Object) this;
        
        if (isInDen(dragon)) {
            cir.setReturnValue(false);
        }
    }

    private boolean isInDen(EntityDragonBase dragon) {
        if (!dragon.hasHomePosition) {
            return !dragon.level().canSeeSky(dragon.blockPosition());
        }
        
        BlockPos restrictCenter = dragon.getRestrictCenter();
        if (restrictCenter == null) {
            return !dragon.level().canSeeSky(dragon.blockPosition());
        }
        
        double distanceToHome = dragon.distanceToSqr(Vec3.atCenterOf(restrictCenter));
        double denRadius = dragon.getRestrictRadius();
        
        if (distanceToHome <= denRadius * denRadius) {
            return !dragon.level().canSeeSky(restrictCenter);
        }
        
        return false;
    }
}
