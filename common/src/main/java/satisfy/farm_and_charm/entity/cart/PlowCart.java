package satisfy.farm_and_charm.entity.cart;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;
import satisfy.farm_and_charm.registry.ObjectRegistry;
import satisfy.farm_and_charm.registry.TagRegistry;

public class PlowCart extends CartEntity {

    public PlowCart(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void move(MoverType moverType, Vec3 vec3) {
        super.move(moverType, vec3);
        if (this.onGround()) {
            if (this.level() instanceof ServerLevel serverLevel) {
                BlockPos blockPos = this.getOnPos();
                Direction direction = Direction.fromYRot(this.getYRot());
                // Berechnet die Positionen der Blöcke direkt hinter und neben dem Karren
                BlockPos behindPos = blockPos.relative(direction.getOpposite());
                BlockPos leftPos = behindPos.relative(direction.getClockWise());
                BlockPos rightPos = behindPos.relative(direction.getCounterClockWise());
                transformGroundAndDestroyAbove(serverLevel, behindPos);
                transformGroundAndDestroyAbove(serverLevel, leftPos);
                transformGroundAndDestroyAbove(serverLevel, rightPos);
            }
        }
    }

    private void transformGroundAndDestroyAbove(ServerLevel serverLevel, BlockPos pos) {
        serverLevel.setBlockAndUpdate(pos, Blocks.FARMLAND.defaultBlockState());
        BlockPos abovePos = pos.above();
        BlockState aboveBlockState = serverLevel.getBlockState(abovePos);
        if (aboveBlockState.is(BlockTags.SMALL_FLOWERS) || aboveBlockState.is(TagRegistry.WILD_CROPS) ||aboveBlockState.is(BlockTags.TALL_FLOWERS) || aboveBlockState.is(Blocks.GRASS) || aboveBlockState.is(Blocks.TALL_GRASS)) {
            destroyBlockWithParticles(serverLevel, abovePos, aboveBlockState);
        }
    }

    private void destroyBlockWithParticles(ServerLevel serverLevel, BlockPos pos, BlockState blockState) {
        serverLevel.destroyBlock(pos, true);
        BlockParticleOption particleOption = new BlockParticleOption(ParticleTypes.BLOCK, blockState);
        serverLevel.sendParticles(particleOption, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 20, 0.5, 0.5, 0.5, 0.1);
    }

    @Override
    protected void destroy(DamageSource damageSource) {
        this.spawnAtLocation(ObjectRegistry.PLOW.get());
    }

    @Override
    protected float firstPoint() {
        return 3.0F;
    }

    @Override
    protected float lastPoint() {
        return 1.0F;
    }

    @Override
    protected float wheelRadius() {
        return 1.0F;
    }
}
