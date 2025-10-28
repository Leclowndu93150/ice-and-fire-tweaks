package com.leclowndu93150.iceandfire_tweaks.mixin;

import com.github.alexthe666.iceandfire.entity.EntityDragonBase;
import com.leclowndu93150.iceandfire_tweaks.Config;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityDragonBase.class)
public class TamedDragonStatsMixin {

    @Inject(method = "updateAttributes", at = @At("RETURN"), remap = false)
    private void applyTamedDragonMultipliers(CallbackInfo ci) {
        EntityDragonBase dragon = (EntityDragonBase) (Object) this;
        
        if (dragon.isTame()) {
            double currentHealth = dragon.getAttribute(Attributes.MAX_HEALTH).getBaseValue();
            double currentAttack = dragon.getAttribute(Attributes.ATTACK_DAMAGE).getBaseValue();
            
            dragon.getAttribute(Attributes.MAX_HEALTH).setBaseValue(currentHealth * Config.tamedDragonHealthMultiplier);
            dragon.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(currentAttack * Config.tamedDragonAttackMultiplier);
            
            if (dragon.getHealth() > dragon.getMaxHealth()) {
                dragon.setHealth(dragon.getMaxHealth());
            }
        }
    }
}
