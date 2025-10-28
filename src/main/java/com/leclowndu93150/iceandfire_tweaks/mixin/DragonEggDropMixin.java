package com.leclowndu93150.iceandfire_tweaks.mixin;

import com.github.alexthe666.iceandfire.entity.EntityDragonBase;
import com.leclowndu93150.iceandfire_tweaks.Config;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityDragonBase.class)
public class DragonEggDropMixin {

    @Redirect(
            method = "interactAt",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/github/alexthe666/iceandfire/entity/EntityDragonBase;spawnAtLocation(Lnet/minecraft/world/item/ItemStack;F)Lnet/minecraft/world/entity/item/ItemEntity;",
                    ordinal = 1
            )
    )
    private ItemEntity modifyEggDrop(EntityDragonBase dragon, ItemStack stack, float offset) {
        if (dragon.level().getRandom().nextDouble() < Config.dragonEggDropRate) {
            return dragon.spawnAtLocation(stack, offset);
        }
        return null;
    }
}
