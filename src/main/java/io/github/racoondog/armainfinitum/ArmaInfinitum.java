package io.github.racoondog.armainfinitum;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;

public class ArmaInfinitum implements ModInitializer {
    public static final GameRules.Key<GameRules.IntRule> PROJECTILE_SPREAD = GameRuleRegistry.register("projectileSpread", GameRules.Category.MISC, GameRuleFactory.createIntRule(20));
    public static final GameRules.Key<GameRules.BooleanRule> THROWABLE_MULTISHOT_RANDOM = GameRuleRegistry.register("throwableMultishotRandom", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(true));
    public static final GameRules.Key<GameRules.BooleanRule> BOW_MULTISHOT_RANDOM = GameRuleRegistry.register("bowMultishotRandom", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(true)); //unused
    public static final GameRules.Key<GameRules.BooleanRule> CROSSBOW_MULTISHOT_RANDOM = GameRuleRegistry.register("crossbowMultishotRandom", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(true)); //unused
    public static final GameRules.Key<GameRules.BooleanRule> DISPENSER_COMPATIBLE = GameRuleRegistry.register("dispenserCompatible", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(false)); //unused

    @Override
    public void onInitialize() {
    }
}
