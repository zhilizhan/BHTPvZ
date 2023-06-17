package com.zhilizhan.bhtpvz.common.entity.zombie.bhtpvz;

import com.hungteen.pvz.api.interfaces.IAlmanacEntry;
import com.hungteen.pvz.api.types.IZombieType;
import com.hungteen.pvz.common.entity.bullet.itembullet.PeaEntity;
import com.hungteen.pvz.common.entity.zombie.zombotany.AbstractZombotanyEntity;
import com.hungteen.pvz.common.potion.EffectRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.PAZAlmanacs;
import com.mojang.datafixers.util.Pair;
import com.zhilizhan.bhtpvz.common.entity.BHTPvZEntityTypes;
import com.zhilizhan.bhtpvz.common.entity.misc.RedSunEntity;
import com.zhilizhan.bhtpvz.common.impl.zombie.BHTPvZZombies;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;

import java.util.Arrays;
import java.util.List;

public class SunFlowerZombieEntity extends AbstractZombotanyEntity {
    public SunFlowerZombieEntity(EntityType<? extends PathfinderMob> type, Level worldIn) {
        super(type, worldIn);
    }
    private int producTick = 0;
    @Override
    protected void initAttributes() {
        super.initAttributes();
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.25);
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

    private void shootPea() {
        LivingEntity target = this.getTarget();
        if (target != null && !this.isAlive()) {
            PeaEntity pea = new PeaEntity(this.level, this, PeaEntity.Type.NORMAL, PeaEntity.State.NORMAL);
            pea.setPos(this.getX(), this.getY() + (double)this.getEyeHeight(), this.getZ());
            pea.shootToTarget(target, 1.5);
            pea.summonByOwner(this);
            pea.setAttackDamage(this.getAttackDamage());
            this.level.addFreshEntity(pea);
            EntityUtil.playSound(this, SoundEvents.SNOW_GOLEM_SHOOT);
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
        if (this.hasEffect((MobEffect) EffectRegister.COLD_EFFECT.get())) {
            int lvl = this.getEffect((MobEffect)EffectRegister.COLD_EFFECT.get()).getAmplifier();
            now += 3 * lvl;
        }

        return now;
    }

    protected int getShootCD() {
        return 120;
    }



    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("produc_tick")) {
            this.producTick = compound.getInt("produc_tick");
        }

    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("produc_tick", this.producTick);}
    public int getGenCD() {
        boolean time = true;
        return this.level.isDay() ? (this.level.isRaining() ? 500 : 250) : 750;
    }
    public void addAlmanacEntries(List<Pair<IAlmanacEntry, Number>> list) {
        super.addAlmanacEntries(list);
        list.addAll(Arrays.asList(Pair.of(PAZAlmanacs.GEN_CD, this.getGenCD())));
    }

    protected void genSun(int num, int cnt) {
        RedSunEntity.spawnSunsByAmount(this.level, this.blockPosition(), num, num / cnt, 2.0);
        EntityUtil.playSound(this, SoundEvents.EXPERIENCE_ORB_PICKUP);
    }

    public void genSomething() {
        RedSunEntity sun = (RedSunEntity)((EntityType) BHTPvZEntityTypes.RED_SUN.get()).create(this.level);
        sun.setAmount(sun.getAmount());
        EntityUtil.onEntityRandomPosSpawn(this.level, sun, this.blockPosition(),1);
        EntityUtil.playSound(this, SoundEvents.EXPERIENCE_ORB_PICKUP);
    }


    @Override
    public IZombieType getZombieType() {
        return BHTPvZZombies.SUN_FLOWER_ZOMBIE;
    }
}
