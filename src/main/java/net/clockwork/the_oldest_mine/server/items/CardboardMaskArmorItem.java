package net.clockwork.the_oldest_mine.server.items;

import net.clockwork.the_oldest_mine.TheOldestMine;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class CardboardMaskArmorItem extends ArmorItem {
    public CardboardMaskArmorItem(ArmorMaterial material, Type type, Properties properties) {
        super(material, type, properties);
    }

    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept((IClientItemExtensions) TheOldestMine.PROXY.getArmorProperties());
    }

    @Nullable
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return TheOldestMine.MODID + ":textures/armor/giant_mask.png";
    }
}
