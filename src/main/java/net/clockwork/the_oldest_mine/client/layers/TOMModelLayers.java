package net.clockwork.the_oldest_mine.client.layers;

import net.clockwork.the_oldest_mine.TheOldestMine;
import net.clockwork.the_oldest_mine.client.model.CardboardMaskModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.EntityRenderersEvent;

public class TOMModelLayers {
    public static final ModelLayerLocation GIANT_LAYER = new ModelLayerLocation(
            new ResourceLocation(TheOldestMine.MODID, "giant_layer"), "main");

    public static final ModelLayerLocation CARDBOARD_MASK_LAYER = new ModelLayerLocation(
            new ResourceLocation(TheOldestMine.MODID, "cardboard_mask_layer"), "main");

    public static void register(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(CARDBOARD_MASK_LAYER, () -> CardboardMaskModel.createArmorLayer(new CubeDeformation(0)));
    }

}
