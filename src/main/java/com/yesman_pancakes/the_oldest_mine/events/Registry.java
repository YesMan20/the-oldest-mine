package com.yesman_pancakes.the_oldest_mine.events;

import com.yesman_pancakes.the_oldest_mine.TheOldestMine;
import com.yesman_pancakes.the_oldest_mine.client.renderer.GiantRenderer;
import com.yesman_pancakes.the_oldest_mine.server.entities.TOMEntitiesRegistry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TheOldestMine.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class Registry {
    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(TOMEntitiesRegistry.GIANT.get(), GiantRenderer::new);
    }
}
