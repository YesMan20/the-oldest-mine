package net.clockwork.the_oldest_mine;

import com.mojang.logging.LogUtils;
import net.clockwork.the_oldest_mine.client.layers.TOMModelLayers;
import net.clockwork.the_oldest_mine.client.sounds.TOMSoundsRegistry;
import net.clockwork.the_oldest_mine.events.ClientProxy;
import net.clockwork.the_oldest_mine.events.CommonProxy;
import net.clockwork.the_oldest_mine.server.effects.TOMEffects;
import net.clockwork.the_oldest_mine.server.entities.TOMEntitiesRegistry;
import net.clockwork.the_oldest_mine.server.items.TOMItemTags;
import net.clockwork.the_oldest_mine.server.items.TOMItemsRegistry;
import net.clockwork.the_oldest_mine.server.paintings.TOMPaintingsRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(TheOldestMine.MODID)
public class TheOldestMine {
    public static CommonProxy PROXY = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);
    public static final String MODID = "tom";
    public static final Logger LOGGER = LogUtils.getLogger();

    public TheOldestMine() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);

        TOMEntitiesRegistry.ENTITES.register(modEventBus);
        TOMSoundsRegistry.SOUND_EVENTS.register(modEventBus);
        TOMPaintingsRegistry.PAINTING_VARIANTS.register(modEventBus);
        TOMItemsRegistry.ITEMS.register(modEventBus);
        TOMEffects.MOB_EFFECTS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
        modEventBus.addListener(this::registerLayerDefinitions);
    }

    private void registerLayerDefinitions(final EntityRenderersEvent.RegisterLayerDefinitions event) {
        TOMModelLayers.register(event);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {}

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(TOMItemsRegistry.UNNAMED_SHOPPING_MUZAK_MUSIC_DISC);
            event.accept(TOMItemsRegistry.STAR_EYES_MUSIC_DISC);
            event.accept(TOMItemsRegistry.CARDBOARD_MASK);
        }
    }

    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(MODID, path);
    }
}
