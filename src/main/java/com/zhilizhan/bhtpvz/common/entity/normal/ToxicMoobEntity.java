package com.zhilizhan.bhtpvz.common.entity.normal;

import com.hungteen.pvz.common.item.ItemRegister;
import com.zhilizhan.bhtpvz.common.entity.BHTPvZEntityTypes;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SuspiciousStewItem;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.Effects;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.IForgeShearable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ToxicMoobEntity extends CowEntity implements IForgeShearable {


    public ToxicMoobEntity(EntityType<? extends CowEntity> arg, World arg2) {
        super(arg, arg2);
    }


    public List<ItemStack> onSheared(@Nullable PlayerEntity player, @Nonnull ItemStack item, World world, BlockPos pos, int fortune) {
        world.playSound(null, this, SoundEvents.MOOSHROOM_SHEAR, player == null ? SoundCategory.BLOCKS : SoundCategory.PLAYERS, 1.0F, 1.0F);
        if (world.isClientSide() || this.isBaby()) {
            return Collections.emptyList();
        } else {
            ((ServerWorld)this.level).sendParticles(ParticleTypes.EXPLOSION, this.getX(), this.getY(0.5), this.getZ(), 1, 0.0, 0.0, 0.0, 0.0);
            this.remove();
            CowEntity cowentity = EntityType.COW.create(this.level);
            if (cowentity != null) {

            cowentity.moveTo(this.getX(), this.getY(), this.getZ(), this.yRot, this.xRot);

            cowentity.setHealth(this.getHealth());
            cowentity.yBodyRot = this.yBodyRot;
            if (this.hasCustomName()) {
                cowentity.setCustomName(this.getCustomName());
                cowentity.setCustomNameVisible(this.isCustomNameVisible());
            }

            if (this.isPersistenceRequired()) {
                cowentity.setPersistenceRequired();
            }

            cowentity.setInvulnerable(this.isInvulnerable());

            this.level.addFreshEntity(cowentity);
            }
            List<ItemStack> items = new ArrayList<>();

            for(int i = 0; i < 3; ++i) {
                if(i == 0 )items.add(new ItemStack(ItemRegister.SPORE.get()));
                if(Math.random()<0.25f)items.add(new ItemStack(ItemRegister.SPORE.get()));
            }

            return items;
        }
    }
    public ActionResultType mobInteract(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (itemstack.getItem() == Items.BOWL && !this.isBaby()) {
            ItemStack itemstack1;
            itemstack1 = new ItemStack(Items.SUSPICIOUS_STEW);
            SuspiciousStewItem.saveMobEffect(itemstack1, Effects.POISON, 80+this.random.nextInt(20));

            ItemStack itemstack2 = DrinkHelper.createFilledResult(itemstack, player, itemstack1, false);
            player.setItemInHand(hand, itemstack2);
            SoundEvent soundevent;

            soundevent = SoundEvents.MOOSHROOM_MILK;

            this.playSound(soundevent, 1.0F, 1.0F);
            return ActionResultType.sidedSuccess(this.level.isClientSide);

        } else {
            return super.mobInteract(player, hand);
        }
    }

    public ToxicMoobEntity getBreedOffspring(ServerWorld serverLevel, AgeableEntity mate) {
        return  BHTPvZEntityTypes.TOXIC_MOOB.get().create(serverLevel);
    }


    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 8.0).add(Attributes.MOVEMENT_SPEED, 0.20000000298023224);
    }
}