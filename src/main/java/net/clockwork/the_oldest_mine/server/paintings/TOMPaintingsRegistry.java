package net.clockwork.the_oldest_mine.server.paintings;

import net.clockwork.the_oldest_mine.TheOldestMine;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TOMPaintingsRegistry {
    public static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS =
            DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, TheOldestMine.MODID);

    public static final RegistryObject<PaintingVariant> WEREBACKPOSTER = PAINTING_VARIANTS.register("werebackposter",
            () -> new PaintingVariant(16, 32));

    public static final RegistryObject<PaintingVariant> REVERCHON_PORTRAIT = PAINTING_VARIANTS.register("reverchon_portrait",
            () -> new PaintingVariant(16, 16));

    public static void register(IEventBus eventBus) {
        PAINTING_VARIANTS.register(eventBus);
    }
}
