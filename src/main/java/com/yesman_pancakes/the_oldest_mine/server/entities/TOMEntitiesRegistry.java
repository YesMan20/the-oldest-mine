package com.yesman_pancakes.the_oldest_mine.server.entities;

import com.yesman_pancakes.the_oldest_mine.TheOldestMine;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TOMEntitiesRegistry {
    public static final DeferredRegister<EntityType<?>> ENTITES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, TheOldestMine.MODID);

    public static final RegistryObject<EntityType<GiantEntity>> GIANT = register("rolling_giant", EntityType.Builder.of(GiantEntity::new, MobCategory.MISC)
            .updateInterval(1)
            .sized(1.4F, 2.7F));

    private static<E extends Entity> RegistryObject<EntityType<E>> register(String id, EntityType.Builder<E> builder) {
        return ENTITES.register(id, () -> builder.build(id));
    }
}
