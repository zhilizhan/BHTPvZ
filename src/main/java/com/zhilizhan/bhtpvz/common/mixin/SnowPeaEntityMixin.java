package com.zhilizhan.bhtpvz.common.mixin;


import com.hungteen.pvz.common.entity.bullet.itembullet.PeaEntity;
import com.hungteen.pvz.common.entity.plant.ice.SnowPeaEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = SnowPeaEntity.class,remap = false)
public class SnowPeaEntityMixin {


    @Overwrite
    protected PeaEntity.State getShootState() {
        return PeaEntity.State.FIRE;
    }


}
