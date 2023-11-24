package com.zhilizhan.bhtpvz.common.entity.bullet;

import com.hungteen.pvz.common.entity.bullet.AbstractBulletEntity;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.plant.flame.TorchWoodEntity;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.zhilizhan.bhtpvz.common.damagesource.BHTPvZEntityDamageSource;
import com.zhilizhan.bhtpvz.common.entity.BHTPvZEntityTypes;
import com.zhilizhan.bhtpvz.common.item.BHTPvZItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class StonePeaEntity  extends AbstractBulletEntity {
    public StonePeaEntity(EntityType<?> type, Level worldIn) {
        super(type, worldIn);
    }

    private static final EntityDataAccessor<Integer> PEA_STATE;
    public TorchWoodEntity torchWood = null;
    private int power = 0;
    
    public StonePeaEntity(Level worldIn, LivingEntity shooter, StonePeaEntity.State peaState) {
        super((EntityType) BHTPvZEntityTypes.STONE_PEA.get(), worldIn, shooter);
        this.setPeaState(peaState);
    }

    protected void defineSynchedData() {
        this.entityData.define(PEA_STATE, StonePeaEntity.State.NORMAL.ordinal());
    }

    protected void onImpact(HitResult result) {
        boolean flag = false;
        if (result.getType() == net.minecraft.world.phys.HitResult.Type.ENTITY) {
            Entity target = ((EntityHitResult)result).getEntity();
            if (this.shouldHit(target)) {
                target.invulnerableTime = 0;
                this.dealPeaDamage(target);
                flag = true;
            }
        }

        this.level.broadcastEntityEvent(this, (byte)3);
        if (flag || !this.checkLive(result)) {
            this.remove();
        }

    }

    public void heatBy(PVZPlantEntity wood) {
        if (this.torchWood == null || !this.torchWood.is(wood)) {
            this.torchWood = (TorchWoodEntity) wood;
           if (this.getPeaState() == State.STONE) {
                this.setPeaState(State.FIRE);
            } else if (this.getPeaState() == StonePeaEntity.State.NORMAL) {
                this.setPeaState(StonePeaEntity.State.FIRE);
            }
        }

    }

    private void dealPeaDamage(Entity target) {
        float damage = this.getAttackDamage();
        if (this.getPeaState() == StonePeaEntity.State.NORMAL || this.getPeaState() == State.STONE) {
            target.hurt(BHTPvZEntityDamageSource.stonePea(this, this.getThrower()), damage);
            peaDamageEffect(target);
        }  else if (this.getPeaState() == StonePeaEntity.State.FIRE) {
            target.hurt(BHTPvZEntityDamageSource.magmaPea(this, this.getThrower()), damage);
            peaDamageEffect(target);
        }

    }
    private void peaDamageEffect(Entity target) {
       if(random.nextInt(2)==0){
           if(target instanceof PVZZombieEntity){
           ((LivingEntity)target).knockback(5,this.getX()-target.getX(),this.getZ()-target.getZ());
          }
       }else {
           ((LivingEntity)target).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,40,8,false,false,false));
       }
    }
    public float getAttackDamage() {
        float damage = this.attackDamage;
        damage *= 1.0F + (float) this.power;
        if (this.getPeaState() == State.STONE) {
            damage *= 2.0F;
        } else if (this.getPeaState() == State.FIRE) {
            damage *= 2.5F;
        }

        return damage;
    }

    protected int getMaxLiveTick() {
        return 40;
    }

    public EntityDimensions getDimensions(Pose poseIn) {
        return new EntityDimensions(0.4F, 0.4F, false);
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("peaState", this.getPeaState().ordinal());
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("peaState")) {
            this.setPeaState(StonePeaEntity.State.values()[compound.getInt("peaState")]);
        }
    }

    public StonePeaEntity.State getPeaState() {
        return StonePeaEntity.State.values()[(Integer)this.entityData.get(PEA_STATE)];
    }

    public void setPeaState(StonePeaEntity.State state) {
        this.entityData.set(PEA_STATE, state.ordinal());
    }


    public ItemStack getItem() {
        if (this.getPeaState() == StonePeaEntity.State.NORMAL) {
            return new ItemStack((ItemLike) BHTPvZItems.PEA_BLOCK.get());
        } else if (this.getPeaState() == State.STONE) {
            return new ItemStack((ItemLike) Items.COBBLESTONE);
        } else {
            return this.getPeaState() == StonePeaEntity.State.FIRE ? new ItemStack((ItemLike)Items.MAGMA_BLOCK) : new ItemStack((ItemLike)BHTPvZItems.PEA_BLOCK.get());
        }
    }

    public void setPower(int lvl) {
        this.power = lvl;
    }

    static {
        PEA_STATE = SynchedEntityData.defineId(StonePeaEntity.class, EntityDataSerializers.INT);
    }

    public static enum State {
        STONE,
        NORMAL,
        FIRE;

        private State() {
        }
    }

    
}
