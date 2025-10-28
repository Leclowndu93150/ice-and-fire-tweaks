package com.leclowndu93150.iceandfire_tweaks;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = IceandfireTweaks.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.DoubleValue DRAGON_EGG_DROP_RATE = BUILDER
            .comment("Dragon egg drop rate from female Stage 4+ dragons (0.0 = 0%, 1.0 = 100%)")
            .defineInRange("dragonEggDropRate", 1.0, 0.0, 1.0);

    private static final ForgeConfigSpec.DoubleValue TAMED_DRAGON_HEALTH_MULTIPLIER = BUILDER
            .comment("Health multiplier for tamed dragons (0.01 = 1% health, 1.0 = 100% health, 10.0 = 1000% health)")
            .defineInRange("tamedDragonHealthMultiplier", 1.0, 0.01, 100.0);

    private static final ForgeConfigSpec.DoubleValue TAMED_DRAGON_ATTACK_MULTIPLIER = BUILDER
            .comment("Attack damage multiplier for tamed dragons (0.01 = 1% damage, 1.0 = 100% damage, 10.0 = 1000% damage)")
            .defineInRange("tamedDragonAttackMultiplier", 1.0, 0.01, 100.0);

    private static final ForgeConfigSpec.DoubleValue TAMED_DRAGON_GROWTH_RATE = BUILDER
            .comment("Growth rate multiplier for tamed dragons (0.0 = no growth, 0.5 = 50% speed, 1.0 = normal, 10.0 = 10x speed)")
            .defineInRange("tamedDragonGrowthRate", 1.0, 0.0, 100.0);

    private static final ForgeConfigSpec.IntValue TAMED_DRAGON_MAX_STAGE = BUILDER
            .comment("Maximum stage for tamed dragons (1-5, 0 = no limit)")
            .defineInRange("tamedDragonMaxStage", 0, 0, 5);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static double dragonEggDropRate;
    public static double tamedDragonHealthMultiplier;
    public static double tamedDragonAttackMultiplier;
    public static double tamedDragonGrowthRate;
    public static int tamedDragonMaxStage;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        dragonEggDropRate = DRAGON_EGG_DROP_RATE.get();
        tamedDragonHealthMultiplier = TAMED_DRAGON_HEALTH_MULTIPLIER.get();
        tamedDragonAttackMultiplier = TAMED_DRAGON_ATTACK_MULTIPLIER.get();
        tamedDragonGrowthRate = TAMED_DRAGON_GROWTH_RATE.get();
        tamedDragonMaxStage = TAMED_DRAGON_MAX_STAGE.get();
    }
}
