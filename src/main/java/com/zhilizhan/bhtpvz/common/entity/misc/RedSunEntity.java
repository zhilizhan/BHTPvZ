package com.zhilizhan.bhtpvz.common.entity.misc;

import com.hungteen.pvz.common.capability.CapabilityHandler;
import com.hungteen.pvz.common.entity.misc.drop.SunEntity;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;


public class RedSunEntity extends SunEntity {
    public RedSunEntity(EntityType<? extends MobEntity> type, World worldIn) {
        super(type, worldIn);
        this.setAmount(this.getDefaultAmount());
        this.setNoGravity(true);
    }
    public Vector3d ColorBase = new Vector3d(240.0, 24.0, 24.0);
    @Override
    protected int getDefaultAmount() {
        return -25;
    }
    public int getIcon() {
        int value = -(this.getAmount());
        return value < 6 ? 0 : (value < 16 ? 1 : (value < 26 ? 2 : 3));
    }
    public void tick() {
        super.tick();
        if ((this.tickCount + this.getId()) % 40 == 0) {
            PlayerEntity following = this.level.getNearestPlayer(this, 32.0);
            if (EntityUtil.isEntityValid(following)) {
                this.giveSunToTarget(following);
            }
        }
    }
    private void giveSunToTarget(PlayerEntity player) {
        if (!level.isClientSide) {
            player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l) -> {
                int amount = this.getAmount();
                {
                    l.getPlayerData().addResource(Resources.SUN_NUM, amount);
                    PlayerUtil.playClientSound(player, SoundEvents.PLAYER_HURT_ON_FIRE);
                }
            });
        }
    }
}