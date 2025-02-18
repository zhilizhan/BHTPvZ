package com.zhilizhan.bhtpvz.common.entity.zombie;

import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.impl.zombie.ZombieType;
import com.hungteen.pvz.utils.EntityUtil;
import com.zhilizhan.bhtpvz.common.impl.zombie.BHTPvZZombies;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;

public class RedEdgeZombieEntity extends PVZZombieEntity {
    private final ServerBossInfo bossInfo;
    private static final DataParameter<Boolean> STEEL_AND_FIRE = EntityDataManager.defineId(RedEdgeZombieEntity.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> SHARP_EDGE = EntityDataManager.defineId(RedEdgeZombieEntity.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> IS_ORIGIN = EntityDataManager.defineId(RedEdgeZombieEntity.class, DataSerializers.BOOLEAN);
    private int noTargetTick;

    public RedEdgeZombieEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
        this.bossInfo = (ServerBossInfo)(new ServerBossInfo(this.getDisplayName(), ServerBossInfo.Color.RED, ServerBossInfo.Overlay.PROGRESS)).setDarkenScreen(true);
        this.noTargetTick = 0;
        this.canBeCharm = false;
        this.canBeMini = false;
    }

    @Override
    public void zombieTick() {
        super.zombieTick();
        float percent = EntityUtil.getCurrentHealth(this) / EntityUtil.getCurrentMaxHealth(this);

        if(this.isOrigin()){
         this.bossInfo.setPercent(percent);
         if(this.canBeCold || this.canBeButtered ||this.canBeFrozen)
            this.setImmuneAllEffects();
        }
        if (!this.level.isClientSide) {
            this.checkAndHeal();
        }
    }
    @Override
    protected float getLife() {
        int sharpEdge = this.hasSharpEdge()?247:0;
        int steelAndFire = this.hasSteelAndFire()?274:0;
        int isOrigin = this.isOrigin()?800:0;
        return 80+steelAndFire+sharpEdge+isOrigin;
    }
    public float getKBValue() {
        return this.isOrigin()?4.92F:1.23F;
    }
    public boolean onFire() {
        return this.canNormalUpdate() && this.getHealth() / this.getMaxHealth() < 0.5F || this.hasSteelAndFire();
    }
    @Override
    public boolean hurt(DamageSource source, float amount) {
        int steelAndFire = this.hasSteelAndFire()?2:1;
        return super.hurt(source,amount/steelAndFire);
    }

    @Override
    public float getWalkSpeed() {
        float baseSpeed = 0.205F;
        float isOrigin = this.isOrigin()?0.015F:0;
        float isFire = this.onFire()?0.005F:0;
        return baseSpeed+isOrigin+isFire;
    }
    @Override
    public boolean doHurtTarget(Entity target) {
        int isOrigin = this.isOrigin()?20:5;
        if (!super.doHurtTarget(target)) {
            return false;
        } else {
            if (target instanceof LivingEntity) {
               if(this.onFire()){
                  target.setSecondsOnFire(isOrigin);
            }}

            return true;
        }
    }
    public void checkAndHeal() {
        if (this.getTarget() == null && ++this.noTargetTick >= 160 && this.isOrigin()) {
            this.heal(1.0F);
        }
    }
    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(STEEL_AND_FIRE, false);
        this.entityData.define(SHARP_EDGE, false);
        this.entityData.define(IS_ORIGIN, false);
    }
    @Override
    public void startSeenByPlayer(ServerPlayerEntity player) {
        super.startSeenByPlayer(player);
        if(this.isOrigin()) this.bossInfo.addPlayer(player);
    }
    @Override
    public void stopSeenByPlayer(ServerPlayerEntity player) {
        super.stopSeenByPlayer(player);
        if(this.isOrigin()) this.bossInfo.removePlayer(player);
    }
    @Override
    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        if(compound.contains("steel_and_fire")) {
            this.setSteelAndFire(compound.getBoolean("steel_and_fire"));
        }
        if(compound.contains("sharp_edge")) {
            this.setSharpEdge(compound.getBoolean("sharp_edge"));
        }
        if(compound.contains("is_origin")) {
            this.setIsOrigin(compound.getBoolean("is_origin"));
        }
    }
    public void setSteelAndFire(boolean has) {
        this.entityData.set(STEEL_AND_FIRE, has);
    }
    public boolean hasSteelAndFire() {
        return this.entityData.get(STEEL_AND_FIRE);
    }
    public void setSharpEdge(boolean has) {
        this.entityData.set(SHARP_EDGE, has);
    }
    public boolean hasSharpEdge() {
        return this.entityData.get(SHARP_EDGE);
    }
    public void setIsOrigin(boolean has) {
        this.entityData.set(IS_ORIGIN, has);
    }
    public boolean isOrigin() {
        return this.entityData.get(IS_ORIGIN);
    }
    @Override
    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("steel_and_fire", this.hasSteelAndFire());
        compound.putBoolean("sharp_edge", this.hasSharpEdge());
        compound.putBoolean("is_origin", this.isOrigin());
    }
    protected float getModifyAttackDamage(Entity entity, float f) {
        int steelAndFire = this.hasSteelAndFire()?4:2;//倍率
        return this.onFire() ? this.getEatDamage()*steelAndFire : this.getEatDamage();
    }
    public float getEatDamage() {
        int baseDamage = 12;
        int steelAndFire = this.hasSteelAndFire()?12:0;
        int sharpEdge = this.hasSharpEdge()?18:0;
        int isOrigin = this.isOrigin()?3:0;
        return (baseDamage+steelAndFire+sharpEdge)*isOrigin;
    }
    public int getArmorToughness() {
        int steelAndFire = 10;
        int sharpEdge = 8;
        int baseArmor = 2;
        return baseArmor+steelAndFire+sharpEdge;
    }
    @Override
    public ZombieType getZombieType() {
        return BHTPvZZombies.RED_EDGE_ZOMBIE;
    }
}
