package com.zhilizhan.bhtpvz.common.entity.plant.appease;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.plant.appease.PeaShooterEntity;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.MathUtil;
import com.zhilizhan.bhtpvz.common.entity.BHTPvZEntityTypes;
import com.zhilizhan.bhtpvz.common.entity.bullet.BeeEntity;
import com.zhilizhan.bhtpvz.common.impl.plant.BHTPvZPlants;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import java.util.List;
import java.util.Optional;

import static com.hungteen.pvz.utils.EntityUtil.*;

public class BeeShooterEntity extends PeaShooterEntity {
    public BeeShooterEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
    }


    public void performShoot(double forwardOffset, double rightOffset, double heightOffset, boolean needSound, double angleOffset) {
        Optional.ofNullable(this.getTarget()).ifPresent((target) -> {
            Vector3d vec = EntityUtil.getNormalisedVector2d(this, target);
            double deltaY = (double)(this.getDimensions(this.getPose()).height * 0.7F) + heightOffset;
            double deltaX = forwardOffset * vec.x - rightOffset * vec.z;
            double deltaZ = forwardOffset * vec.z + rightOffset * vec.x;
            BeeEntity bee = BHTPvZEntityTypes.BEE.get().create(level);
            if (bee != null) {
                bee.setPos(this.getX() + deltaX, this.getY() + deltaY, this.getZ() + deltaZ);

            double dx = target.getX() - bee.getX();
            double dy = target.getY() + (double)target.getBbHeight() - bee.getY();
            double dz = target.getZ() - bee.getZ();

            double down = this.getMaxShootAngle();
            double dxz = Math.sqrt(dx * dx + dz * dz);
            if (down != 0.0) {
                dy = MathHelper.clamp(dy, -dxz / down, dxz / down);
            }

            double degree = MathHelper.atan2(dz, dx) + Math.toRadians(angleOffset);
            dx = Math.cos(degree) * dxz;
            dz = Math.sin(degree) * dxz;
            double totSpeed = Math.sqrt(dxz * dxz + dy * dy);
            double speed = 0.9f;

            bee.setDeltaMovement((new Vector3d(dx / totSpeed, dy / totSpeed, dz / totSpeed)).scale(speed));
            bee.summonByOwner(this);
            if(bee.getAttribute(Attributes.MAX_HEALTH)!=null) bee.getAttribute(Attributes.MAX_HEALTH).setBaseValue(this.getAttackDamage());
            if(bee.getAttribute(Attributes.ATTACK_DAMAGE)!=null)bee.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue((this.getAttackDamage()/2));
            if (needSound) {
                EntityUtil.playSound(this, this.getShootSound());
            }
            this.level.addFreshEntity(bee);
        }});

    }
   @Override
    public void shootBullet() {
        if (this.isPlantInSuperMode()) {
            int cnt = this.getSuperShootCount();

            for(int i = 0; i < cnt; ++i) {
                float offset = MathUtil.getRandomFloat(this.getRandom()) / 3.0F;
                float offsetH = MathUtil.getRandomFloat(this.getRandom()) / 3.0F;
                this.performShoot(0.2, offset, offsetH, this.getExistTick() % 10 == 0, 0.0);
            }
        } else {
            this.performShoot(0.2, 0.0, 0.0, this.getAttackTime() == 1, 0.0);
        }

    }
    public List<BeeEntity> giveBeeAmount(){
        int range = 60;
        List<BeeEntity> bee = getPredicateEntities(this, getEntityAABB(this,range,range), BeeEntity.class, (target) -> {
            return isFriendly(this, target) && target.getOwner()==this;
        });
        return bee;
    }
    public int getShootCD() {
        return giveBeeAmount().size()>=2?160:60;
    }
    public int getSuperShootCount() {
        return 1;
    }
    public int getSuperTimeLength() {
        return 40;
    }
    @Override
    public IPlantType getPlantType() {
        return BHTPvZPlants.BEE_SHOOTER;
    }

}
