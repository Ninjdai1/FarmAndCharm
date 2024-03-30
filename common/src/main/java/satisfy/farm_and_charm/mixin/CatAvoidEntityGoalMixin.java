package satisfy.farm_and_charm.mixin;

import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import satisfy.farm_and_charm.registry.ObjectRegistry;

@Mixin(Cat.CatAvoidEntityGoal.class)
public class CatAvoidEntityGoalMixin {
    @Inject(method = "canUse", at = @At("HEAD"), cancellable = true)
    private void modifyCanUse(CallbackInfoReturnable<Boolean> cir) {
        Cat.CatAvoidEntityGoal<?> goal = (Cat.CatAvoidEntityGoal<?>) (Object) this;
        if(goal.toAvoid instanceof Player p && p.isHolding(ObjectRegistry.CAT_FOOD.get().asItem())) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "canContinueToUse", at = @At("HEAD"), cancellable = true)
    private void modifyCanContinueToUse(CallbackInfoReturnable<Boolean> cir) {
        Cat.CatAvoidEntityGoal<?> goal = (Cat.CatAvoidEntityGoal<?>) (Object) this;
        if(goal.toAvoid instanceof Player p && p.isHolding(ObjectRegistry.CAT_FOOD.get().asItem())) {
            cir.setReturnValue(false);
        }
    }
}
