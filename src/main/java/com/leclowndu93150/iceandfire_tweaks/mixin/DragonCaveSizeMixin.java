package com.leclowndu93150.iceandfire_tweaks.mixin;

import com.github.alexthe666.iceandfire.world.gen.WorldGenDragonCave;
import com.leclowndu93150.iceandfire_tweaks.Config;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(value = WorldGenDragonCave.class, remap = false)
public class DragonCaveSizeMixin {

    @WrapOperation(
            method = "place",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/RandomSource;nextInt(I)I",
                    ordinal = 2,
                    remap = true
            )
    )
    private int scaleInitialRadiusRandom(RandomSource instance, int bound, Operation<Integer> original) {
        int scaledBound = (int) Math.round(bound * Config.dragonCaveSizeMultiplier);
        return original.call(instance, scaledBound);
    }

    @ModifyVariable(
            method = "place",
            at = @At(value = "STORE"),
            ordinal = 1
    )
    private int scaleInitialRadius(int radius) {
        int scaled = (int) Math.round(radius * Config.dragonCaveSizeMultiplier);
        return Math.min(scaled, 48);
    }

    @WrapOperation(
            method = "generateCave",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/core/BlockPos;relative(Lnet/minecraft/core/Direction;I)Lnet/minecraft/core/BlockPos;",
                    remap = true
            )
    )
    private BlockPos scaleSecondaryChamberOffset(BlockPos instance, Direction direction, int distance, Operation<BlockPos> original) {
        int scaledDistance = (int) Math.round(distance * Config.dragonCaveSizeMultiplier);
        return original.call(instance, direction, scaledDistance);
    }
}
