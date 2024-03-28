package com.yesman_pancakes.the_oldest_mine.events;

import com.yesman_pancakes.the_oldest_mine.TheOldestMine;
import com.yesman_pancakes.the_oldest_mine.client.layers.TOMModelLayers;
import com.yesman_pancakes.the_oldest_mine.client.model.GiantModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TheOldestMine.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(TOMModelLayers.GIANT_LAYER, GiantModel::createBodyLayer);


    }
}
