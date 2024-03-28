package com.yesman_pancakes.the_oldest_mine.events;

import com.yesman_pancakes.the_oldest_mine.TheOldestMine;
import com.yesman_pancakes.the_oldest_mine.server.entities.GiantEntity;
import com.yesman_pancakes.the_oldest_mine.server.entities.TOMEntitiesRegistry;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TheOldestMine.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonModEvents {
    @SubscribeEvent
    public static void entityAttributeCreation(EntityAttributeCreationEvent event) {
        event.put(TOMEntitiesRegistry.GIANT.get(), GiantEntity.createAttributes().build());
    }
}
