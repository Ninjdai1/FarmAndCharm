package satisfy.farm_and_charm.mixin;

import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import satisfy.farm_and_charm.registry.ObjectRegistry;

@Mixin(Cat.class)
public class CatMixin {
    @Inject(method = "isFood", at = @At("HEAD"), cancellable = true)
    private void modifyCanScare(ItemStack itemStack, CallbackInfoReturnable<Boolean> cir) {
        if(itemStack.is(ObjectRegistry.CAT_FOOD.get().asItem())) cir.setReturnValue(true);
    }
}
