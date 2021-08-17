package io.github.racoondog.arma_infinitum;


import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;

public class Arma_infinitum implements ModInitializer {
    public static final String modid = "arma_infinitum";
    public static Configs config;

    @Override
    public void onInitialize() {
        AutoConfig.register(Configs.class, JanksonConfigSerializer::new);
        config = AutoConfig.getConfigHolder(Configs.class).getConfig();
    }
}
