package satisfy.farm_and_charm.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FarmBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import satisfy.farm_and_charm.registry.ObjectRegistry;

import java.util.Iterator;

@Mixin(FarmBlock.class)
public class FarmlandBlockMixin {
    @Inject(method = "isNearWater", at = @At("HEAD"), cancellable = true)
    private static void injectWaterSprinklerCheck(LevelReader levelReader, BlockPos blockPos, CallbackInfoReturnable<Boolean> cir) {
        Iterator<BlockPos> var2 = BlockPos.betweenClosed(blockPos.offset(-8, 1, -8), blockPos.offset(8, 1, 8)).iterator();

        BlockPos blockPos2;
        do {
            if (!var2.hasNext()) {
                return;
            }
            blockPos2 = var2.next();
        } while(!levelReader.getBlockState(blockPos2).is(ObjectRegistry.WATER_SPRINKLER.get()));
        cir.setReturnValue(true);
    }
}
