package net.clockwork.the_oldest_mine.client.sounds;

import net.clockwork.the_oldest_mine.TheOldestMine;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TOMSoundsRegistry {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, TheOldestMine.MODID);

    public static final RegistryObject<SoundEvent> ROLLING = registerSoundEvent("rolling");

    public static final RegistryObject<SoundEvent> UNNAMED_SHOPPING_MUZAK = registerSoundEvent("music_disc.unnamed_shopping_muzak");
    public static final RegistryObject<SoundEvent> STAR_EYES = registerSoundEvent("music_disc.star_eyes");

    public static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = new ResourceLocation(TheOldestMine.MODID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
