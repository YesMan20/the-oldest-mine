package net.clockwork.the_oldest_mine.events;

import net.clockwork.the_oldest_mine.TheOldestMine;
import net.clockwork.the_oldest_mine.server.entities.GiantEntity;
import net.clockwork.the_oldest_mine.server.entities.TOMEntitiesRegistry;
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
