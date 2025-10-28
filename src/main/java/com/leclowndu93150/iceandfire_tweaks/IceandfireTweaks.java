package com.leclowndu93150.iceandfire_tweaks;

import com.github.alexthe666.iceandfire.mixin.gen.WorldGenRegionMixin;
import com.mojang.logging.LogUtils;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.util.profiling.jfr.event.ChunkGenerationEvent;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(IceandfireTweaks.MODID)
public class IceandfireTweaks {

    public static final String MODID = "iceandfire_tweaks";
    private static final Logger LOGGER = LogUtils.getLogger();

    public IceandfireTweaks() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
}
