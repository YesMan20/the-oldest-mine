package net.clockwork.the_oldest_mine.server.items;

import net.clockwork.the_oldest_mine.TheOldestMine;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class TOMItemTags {
    public static class Items {
        public static final TagKey<Item> MEAT_FOODS = tag("meat");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(TheOldestMine.MODID, name));
        }
    }
}
