package net.clockwork.the_oldest_mine.mixin;

import net.clockwork.the_oldest_mine.server.effects.TOMEffects;
import net.clockwork.the_oldest_mine.server.items.TOMItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public abstract class ItemMixin {
    @Inject(at = @At("HEAD"), method = "use", cancellable = true)
    public void useInject(Level pLevel, Player pPlayer, InteractionHand pUsedHand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        if (stack.isEdible() && pPlayer.hasEffect(TOMEffects.BOTANY.get()) && stack.is(TOMItemTags.Items.MEAT_FOODS)) {
            cir.setReturnValue(InteractionResultHolder.fail(stack));
        }
    }

}