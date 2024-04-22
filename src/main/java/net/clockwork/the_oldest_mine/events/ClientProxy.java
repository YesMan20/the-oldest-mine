package net.clockwork.the_oldest_mine.events;

import net.clockwork.the_oldest_mine.client.renderer.TOMArmorRenderProperties;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class ClientProxy extends CommonProxy {

    private final TOMArmorRenderProperties armorProperties = new TOMArmorRenderProperties();

    public void commonInit() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
    }

    @Override
    public Object getArmorProperties() {
        return armorProperties;
    }
}
