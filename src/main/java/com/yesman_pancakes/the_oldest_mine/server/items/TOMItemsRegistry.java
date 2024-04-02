package com.yesman_pancakes.the_oldest_mine.server.items;

import com.yesman_pancakes.the_oldest_mine.TheOldestMine;
import com.yesman_pancakes.the_oldest_mine.client.sounds.TOMSoundsRegistry;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.RecordItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TOMItemsRegistry {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TheOldestMine.MODID);

    public static final RegistryObject<Item> UNNAMED_SHOPPING_MUZAK_MUSIC_DISC = ITEMS.register("unnamed_shopping_muzak_music_disc",
            () -> new RecordItem(6, TOMSoundsRegistry.UNNAMED_SHOPPING_MUZAK, new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 3220));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
