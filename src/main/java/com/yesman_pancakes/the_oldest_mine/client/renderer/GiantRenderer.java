package com.yesman_pancakes.the_oldest_mine.client.renderer;

import com.yesman_pancakes.the_oldest_mine.TheOldestMine;
import com.yesman_pancakes.the_oldest_mine.client.layers.TOMModelLayers;
import com.yesman_pancakes.the_oldest_mine.client.model.GiantModel;
import com.yesman_pancakes.the_oldest_mine.server.entities.GiantEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class GiantRenderer extends MobRenderer<GiantEntity, GiantModel<GiantEntity>> {
    public GiantRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new GiantModel<>(pContext.bakeLayer(TOMModelLayers.GIANT_LAYER)), 2f);
        this.shadowRadius = 0.6F;
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull GiantEntity pEntity) {
        return new ResourceLocation(TheOldestMine.MODID, "textures/entity/rolling_giant.png");
    }
}
