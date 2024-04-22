package net.clockwork.the_oldest_mine.events;

import net.clockwork.the_oldest_mine.Components;
import net.clockwork.the_oldest_mine.ModFilePackResources;
import net.clockwork.the_oldest_mine.TheOldestMine;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forgespi.language.IModFileInfo;
import net.minecraftforge.forgespi.locating.IModFile;

public class CommonEvents {
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    static
    class ModBusEvents {
        @SubscribeEvent
        public static void addPackFinders(AddPackFindersEvent event) {
            if (event.getPackType() == PackType.CLIENT_RESOURCES) {
                IModFileInfo modFileInfo = ModList.get().getModFileById(TheOldestMine.MODID);
                if (modFileInfo == null) {
                    TheOldestMine.LOGGER.error("Could not find TOM mod file info; built-in resource packs will be missing!");
                    return;
                }
                IModFile modFile = modFileInfo.getFile();
                event.addRepositorySource(consumer -> {
                    Pack pack = Pack.readMetaAndCreate(TheOldestMine.asResource("programmer_view").toString(), Components.literal("Remodeled Mobs"), false, id -> new ModFilePackResources(id, modFile, "resourcepacks/programmer_view"), PackType.CLIENT_RESOURCES, Pack.Position.TOP, PackSource.BUILT_IN);
                    if (pack != null) {
                        consumer.accept(pack);
                    }
                });
            }
        }
    }
}
