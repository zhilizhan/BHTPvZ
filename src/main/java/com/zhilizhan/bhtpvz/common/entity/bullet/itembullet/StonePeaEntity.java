package com.zhilizhan.bhtpvz.common.entity.bullet.itembullet;

import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.plant.flame.TorchWoodEntity;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.zhilizhan.bhtpvz.common.misc.BHTPvZEntityDamageSource;
import com.zhilizhan.bhtpvz.common.entity.BHTPvZEntityTypes;
import com.zhilizhan.bhtpvz.common.item.BHTPvZItems;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.entity.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value = Dist.CLIENT, _interface = IRendersAsItem.class)
public class StonePeaEntity  extends BHTPvZPeaEntity {
    private static final DataParameter<Integer> PEA_STATE = EntityDataManager.defineId(StonePeaEntity.class, DataSerializers.INT);
    public TorchWoodEntity torchWood = null;
    private int power = 0;
    private int extraHitCount = 0;
    private int maxHitCount = 2;
    private IntOpenHashSet set = new IntOpenHashSet();
    public StonePeaEntity(EntityType<?> type, World worldIn) {
        super(type, worldIn);
    }

    public StonePeaEntity(World worldIn, LivingEntity shooter, StonePeaEntity.State peaState) {
        super( BHTPvZEntityTypes.STONE_PEA.get(), worldIn, shooter);
        this.setPeaState(peaState);
    }

    protected void defineSynchedData() {
        this.entityData.define(PEA_STATE, StonePeaEntity.State.NORMAL.ordinal());
    }

    protected boolean shouldHit(Entity target) {
        if (!super.shouldHit(target)) {
            return false;
        } else {
            return !this.set.contains(target.getId());
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

    protected void dealPeaDamage(Entity target) {
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
        if(target instanceof PVZZombieEntity) {
            if (random.nextInt(2) == 0) {
                ((LivingEntity) target).knockback(5, this.getX() - target.getX(), this.getZ() - target.getZ());
            } else {
                ((LivingEntity) target).addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 40, 8, false, false, false));
            }
        }
    }

    protected void onImpact(RayTraceResult result) {
        if (result.getType() == RayTraceResult.Type.ENTITY) {
            Entity target = ((EntityRayTraceResult)result).getEntity();
            if (this.shouldHit(target)) {
                target.invulnerableTime = 0;
                this.dealPeaDamage(target);
                this.set.add(target.getId());
               ++this.extraHitCount;
            }
            this.level.broadcastEntityEvent(this, (byte)3);
            if (this.extraHitCount >= this.maxHitCount) {
                this.remove();
            }
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
    @Override
    public EntitySize getDimensions(Pose poseIn) {
        return new EntitySize(0.4F, 0.4F, false);
    }

    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("peaState", this.getPeaState().ordinal());
        compound.putInt("extra_hit_count", this.extraHitCount);
    }

    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("peaState")) {
            this.setPeaState(StonePeaEntity.State.values()[compound.getInt("peaState")]);
        }
        if (compound.contains("extra_hit_count")) {
            this.extraHitCount = compound.getInt("extra_hit_count");
        }
    }

    public StonePeaEntity.State getPeaState() {
        return StonePeaEntity.State.values()[this.entityData.get(PEA_STATE)];
    }

    public void setPeaState(StonePeaEntity.State state) {
        this.entityData.set(PEA_STATE, state.ordinal());
    }

    public void setExtraHitCount(int cnt) {
        this.extraHitCount = cnt;
    }

    public ItemStack getItem() {
        if (this.getPeaState() == StonePeaEntity.State.NORMAL) {
            return new ItemStack(BHTPvZItems.PEA_BLOCK.get());
        } else if (this.getPeaState() == State.STONE) {
            return new ItemStack(Items.COBBLESTONE);
        } else {
            return this.getPeaState() == StonePeaEntity.State.FIRE ? new ItemStack(Items.MAGMA_BLOCK) : new ItemStack(BHTPvZItems.PEA_BLOCK.get());
        }
    }

    @Override
    public void setPower(int lvl) {
        this.power = lvl;
    }


    public enum State {
        STONE,
        NORMAL,
        FIRE;

        State() {
        }
    }

    
}
