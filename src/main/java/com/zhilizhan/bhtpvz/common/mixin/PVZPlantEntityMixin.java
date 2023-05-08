package com.zhilizhan.bhtpvz.common.mixin;

import com.hungteen.pvz.api.paz.IPlantEntity;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.AbstractPAZEntity;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.zhilizhan.bhtpvz.common.block.BHTPvZBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = PVZPlantEntity.class,remap = false)
public abstract  class PVZPlantEntityMixin extends AbstractPAZEntity implements IPlantEntity {

    public PVZPlantEntityMixin(EntityType<?> arg, Level arg2) {
        super((EntityType<? extends PathfinderMob>) arg, arg2);
    }

    @Shadow
    protected boolean isImmuneToWeak = false;

    @Shadow
    public boolean isImmuneToWeak() {
        return this.isImmuneToWeak;
    }

    @Shadow
    public abstract IPlantType getPlantType();

    @Shadow public abstract boolean hasMetal();

    @Overwrite
    public boolean shouldWilt() {
        if (!this.isImmuneToWeak() && this.getVehicle() == null) {
            if (!this.getPlantType().isWaterPlant()) {
                if (this.isInWaterOrBubble()) {
                    return true;
                } else {
                    BlockPos pos = Math.abs(this.getY() - (double) this.blockPosition().getY()) <= 0.01 ? this.blockPosition().below() : this.blockPosition();
                    return !this.getPlantType().getPlacement().canPlaceOnBlock(this.level.getBlockState(pos).getBlock());
                }
            } else {
                BlockPos pos = Math.abs(this.getY() - (double) this.blockPosition().getY()) <= 0.01 ? this.blockPosition().below() : this.blockPosition();
                return !this.isOnGround() && !this.level.getBlockState(pos).is(BHTPvZBlocks.WATER_POT.get());
            }
        } else {
            return false;
        }
    }
   @Overwrite
    public boolean canBeTargetBy(LivingEntity living) {
        return !(this.getOuterDefenceLife() > 800.0) && !this.hasMetal();
    }



}

