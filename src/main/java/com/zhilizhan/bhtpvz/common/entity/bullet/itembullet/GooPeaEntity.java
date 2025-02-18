package com.zhilizhan.bhtpvz.common.entity.bullet.itembullet;

import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.zhilizhan.bhtpvz.common.damagesource.BHTPvZEntityDamageSource;
import com.zhilizhan.bhtpvz.common.effect.BHTPvZMobEffects;
import com.zhilizhan.bhtpvz.common.entity.BHTPvZEntityTypes;
import com.zhilizhan.bhtpvz.common.item.BHTPvZItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import java.util.Optional;

@OnlyIn(value = Dist.CLIENT, _interface = IRendersAsItem.class)
public class GooPeaEntity extends BHTPvZPeaEntity{
    public GooPeaEntity(EntityType<?> type, World worldIn) {
        super(type, worldIn);
    }
    public GooPeaEntity(World worldIn, LivingEntity shooter) {
        super(BHTPvZEntityTypes.GOO_PEA.get(), worldIn, shooter);
    }

    protected void dealPeaDamage(Entity target) {
        float damage = this.getAttackDamage();
        PVZEntityDamageSource source = BHTPvZEntityDamageSource.gooPea(this, this.getThrower());
        source.addEffect(this.getPoisonEffect().orElse(null));
        target.hurt(source, damage);
    }
    public Optional<EffectInstance> getPoisonEffect() {
        return Optional.of(new EffectInstance(BHTPvZMobEffects.GOO_POISON.get(), 100, 0, false, false));
    }
    @Nonnull
    @Override
    public ItemStack getItem() {
        return  new ItemStack(BHTPvZItems.GOO_PEA.get());
    }
}
