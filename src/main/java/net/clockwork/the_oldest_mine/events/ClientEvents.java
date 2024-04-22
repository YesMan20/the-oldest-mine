package net.clockwork.the_oldest_mine.events;

import net.clockwork.the_oldest_mine.TheOldestMine;
import net.clockwork.the_oldest_mine.server.effects.TOMEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TheOldestMine.MODID, value = Dist.CLIENT)
public class ClientEvents {

    private static boolean markedAmnesia = false;
    @SubscribeEvent
    public static void clientTick(TickEvent.ClientTickEvent event) {
        Player player = Minecraft.getInstance().player;
        if (player != null) {
            if (player.hasEffect(TOMEffects.BOTANY.get())) {
                if (!markedAmnesia) {
                    Minecraft.getInstance().gameRenderer.checkEntityPostEffect(null);
                    markedAmnesia = true;
                    Minecraft.getInstance().gameRenderer.loadEffect(new ResourceLocation(TheOldestMine.MODID, "shaders/botany.json"));
                }
            } else if (markedAmnesia) {
                markedAmnesia = false;
                Minecraft.getInstance().gameRenderer.checkEntityPostEffect(null);
            }
        }
    }
}
