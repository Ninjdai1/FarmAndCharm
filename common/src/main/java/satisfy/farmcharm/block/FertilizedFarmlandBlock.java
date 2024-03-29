package satisfy.farmcharm.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.Nullable;
import satisfy.farmcharm.registry.ObjectRegistry;

public class FertilizedFarmlandBlock extends FarmBlock {
    public FertilizedFarmlandBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        super.randomTick(blockState, serverLevel, blockPos, randomSource);
        if (randomSource.nextFloat() < 0.005) {
            BlockPos.betweenClosedStream(blockPos.offset(-1, -1, -1), blockPos.offset(1, 1, 1))
                    .forEach(pos -> {
                        BlockState state = serverLevel.getBlockState(pos);
                        if (state.getBlock() instanceof BonemealableBlock bonemealableBlock) {
                            if (bonemealableBlock.isValidBonemealTarget(serverLevel, pos, state, serverLevel.isClientSide)) {
                                bonemealableBlock.performBonemeal(serverLevel, randomSource, pos, state);
                            }
                        }
                    });
        }


        // override dirt to soil
        if (serverLevel.getBlockState(blockPos).getBlock().equals(Blocks.DIRT)) { //!serverLevel.getBlockState(blockPos).getBlock().equals(ObjectRegistry.FERTILIZED_SOIL_BLOCK.get())
            turnToSoil(null, blockState, serverLevel, blockPos);
        }
    }

    public static void turnToSoil(@Nullable Entity entity, BlockState blockState, Level level, BlockPos blockPos) {
        BlockState blockState2 = pushEntitiesUp(blockState, ObjectRegistry.FERTILIZED_SOIL_BLOCK.get().defaultBlockState(), level, blockPos);
        level.setBlockAndUpdate(blockPos, blockState2);
        level.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(entity, blockState2));
    }


    @Override
    public void tick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        if (!blockState.canSurvive(serverLevel, blockPos)) {
            turnToSoil(null, blockState, serverLevel, blockPos);
        }
    }
}
