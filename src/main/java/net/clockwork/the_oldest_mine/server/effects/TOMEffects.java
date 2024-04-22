package net.clockwork.the_oldest_mine.server.effects;

import net.clockwork.the_oldest_mine.TheOldestMine;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TOMEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, TheOldestMine.MODID);

    public static final RegistryObject<MobEffect> BOTANY = MOB_EFFECTS.register("botany", () -> new BotanyEffect(MobEffectCategory.NEUTRAL, 0));
}
