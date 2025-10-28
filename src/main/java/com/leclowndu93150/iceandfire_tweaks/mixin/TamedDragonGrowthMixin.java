package com.leclowndu93150.iceandfire_tweaks.mixin;

import com.github.alexthe666.iceandfire.entity.EntityDragonBase;
import com.github.alexthe666.iceandfire.entity.IafDragonLogic;
import com.leclowndu93150.iceandfire_tweaks.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = IafDragonLogic.class, remap = false)
public class TamedDragonGrowthMixin {

    @Shadow
    private EntityDragonBase dragon;

    @Unique
    private int iceandfire_tweaks$growthCounter = 0;

    @Inject(method = "updateDragonServer", at = @At(value = "INVOKE", target = "Lcom/github/alexthe666/iceandfire/entity/EntityDragonBase;setAgeInTicks(I)V"), cancellable = true)
    private void modifyGrowthRate(CallbackInfo ci) {
        if (dragon.isTame() && !dragon.isAgingDisabled()) {
            if (Config.tamedDragonMaxStage > 0) {
                int maxAgeForStage = iceandfire_tweaks$getMaxAgeForStage(Config.tamedDragonMaxStage);
                int currentAge = dragon.getAgeInTicks() / 24000;
                
                if (currentAge >= maxAgeForStage) {
                    ci.cancel();
                    return;
                }
            }
            
            if (Config.tamedDragonGrowthRate != 1.0) {
                iceandfire_tweaks$growthCounter++;
                int ticksNeeded = (int) Math.round(1.0 / Config.tamedDragonGrowthRate);
                
                if (ticksNeeded <= 1 || iceandfire_tweaks$growthCounter >= ticksNeeded) {
                    iceandfire_tweaks$growthCounter = 0;
                } else {
                    ci.cancel();
                }
            }
        }
    }

    @Unique
    private int iceandfire_tweaks$getMaxAgeForStage(int stage) {
        switch (stage) {
            case 1: return 24;
            case 2: return 49;
            case 3: return 74;
            case 4: return 99;
            case 5: return Integer.MAX_VALUE;
            default: return Integer.MAX_VALUE;
        }
    }
}
