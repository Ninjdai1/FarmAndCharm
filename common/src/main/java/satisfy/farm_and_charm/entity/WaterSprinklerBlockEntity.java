package satisfy.farm_and_charm.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import satisfy.farm_and_charm.registry.BlockEntityTypeRegistry;

public class WaterSprinklerBlockEntity extends BlockEntity {
    public WaterSprinklerBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityTypeRegistry.SPRINKLER_BLOCK_ENTITY.get(), pos, state);
    }
}