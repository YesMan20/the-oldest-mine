package net.clockwork.the_oldest_mine.client.renderer;

import net.clockwork.the_oldest_mine.client.layers.TOMModelLayers;
import net.clockwork.the_oldest_mine.client.model.CardboardMaskModel;
import net.clockwork.the_oldest_mine.server.items.CardboardMaskArmorItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;

public class TOMArmorRenderProperties implements IClientItemExtensions {
    private static boolean init;
    public static CardboardMaskModel CARDBOARD_MASK;


    public static void initializeModels() {
        init = true;
        CARDBOARD_MASK = new CardboardMaskModel(Minecraft.getInstance().getEntityModels().bakeLayer(TOMModelLayers.CARDBOARD_MASK_LAYER));
    }

    @Override
    public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> _default) {
        if (!init) {
            initializeModels();
        }
        if (itemStack.getItem() instanceof CardboardMaskArmorItem) {
            return CARDBOARD_MASK;
        }
        return _default;
    }
}
