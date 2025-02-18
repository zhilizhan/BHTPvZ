package com.zhilizhan.bhtpvz.common.entity.zombie;

import com.hungteen.pvz.api.interfaces.IAlmanacEntry;
import com.hungteen.pvz.api.types.IZombieType;
import com.hungteen.pvz.common.entity.zombie.zombotany.AbstractZombotanyEntity;
import com.hungteen.pvz.common.potion.EffectRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.PAZAlmanacs;
import com.mojang.datafixers.util.Pair;
import com.zhilizhan.bhtpvz.common.entity.BHTPvZEntityTypes;
import com.zhilizhan.bhtpvz.common.entity.misc.RedSunEntity;
import com.zhilizhan.bhtpvz.common.impl.zombie.BHTPvZZombies;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

import java.util.List;
import java.util.Objects;

public class SunFlowerZombieEntity extends AbstractZombotanyEntity {
    public SunFlowerZombieEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
    }
    private int producTick = 0;
    @Override
    protected void initAttributes() {
        super.initAttributes();
        Objects.requireNonNull(this.getAttribute(Attributes.MOVEMENT_SPEED)).setBaseValue(0.25);
    }
    public void normalZombieTick() {
        super.normalZombieTick();
        if (!this.level.isClientSide) {
            ++this.producTick;
            if (this.producTick >= this.getFixedProducCD()) {
                this.setAttackTime(1);
                this.producTick = 0;
            }

            if (this.getAttackTime() > 0) {
                this.setAttackTime(this.getAttackTime() - 1);
                this.genSomething();
            }
        }

    }


    public float getAttackDamage() {
        return 2.0F;
    }

    public float getLife() {
        return 20.0F;
    }

    protected int getFixedProducCD() {
        int now = this.getShootCD();
        if (this.hasEffect(EffectRegister.COLD_EFFECT.get())) {
            int lvl = Objects.requireNonNull(this.getEffect(EffectRegister.COLD_EFFECT.get())).getAmplifier();
            now += 3 * lvl;
        }

        return now;
    }

    protected int getShootCD() {
        return 120;
    }



    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("produc_tick")) {
            this.producTick = compound.getInt("produc_tick");
        }

    }

    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("produc_tick", this.producTick);}
    public int getGenCD() {
        return this.level.isDay() ? (this.level.isRaining() ? 500 : 250) : 750;
    }
    public void addAlmanacEntries(List<Pair<IAlmanacEntry, Number>> list) {
        super.addAlmanacEntries(list);
        list.add(Pair.of(PAZAlmanacs.GEN_CD, this.getGenCD()));
    }

    protected void genSun(int num, int cnt) {
        RedSunEntity.spawnSunsByAmount(this.level, this.blockPosition(), num, num / cnt, 2.0);
        EntityUtil.playSound(this, SoundEvents.EXPERIENCE_ORB_PICKUP);
    }

    public void genSomething() {
        RedSunEntity sun = (RedSunEntity)((EntityType<?>) BHTPvZEntityTypes.RED_SUN.get()).create(this.level);
        if (sun != null) {
            sun.setAmount(sun.getAmount());
            EntityUtil.onEntityRandomPosSpawn(this.level, sun, this.blockPosition(), 1);
        }
        EntityUtil.playSound(this, SoundEvents.EXPERIENCE_ORB_PICKUP);
    }


    @Override
    public IZombieType getZombieType() {
        return BHTPvZZombies.SUN_FLOWER_ZOMBIE;
    }
}
