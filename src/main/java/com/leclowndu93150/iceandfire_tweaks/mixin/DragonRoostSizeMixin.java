package com.leclowndu93150.iceandfire_tweaks.mixin;

import com.github.alexthe666.iceandfire.world.gen.WorldGenDragonRoosts;
import com.leclowndu93150.iceandfire_tweaks.Config;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.util.RandomSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(value = WorldGenDragonRoosts.class, remap = false)
public class DragonRoostSizeMixin {

    @WrapOperation(
            method = "place(Lnet/minecraft/world/level/levelgen/feature/FeaturePlaceContext;)Z",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/RandomSource;nextInt(I)I",
                    ordinal = 0,
                    remap = true
            )
    )
    private int scaleRadiusRandomOffset(RandomSource instance, int bound, Operation<Integer> original) {
        int scaledBound = (int) Math.round(bound * Config.dragonRoostSizeMultiplier);
        return original.call(instance, scaledBound);
    }

    @ModifyVariable(
            method = "place(Lnet/minecraft/world/level/levelgen/feature/FeaturePlaceContext;)Z",
            at = @At(value = "STORE"),
            ordinal = 0
    )
    private int scaleBaseRadius(int radius) {
        return (int) Math.round(radius * Config.dragonRoostSizeMultiplier);
    }
}
