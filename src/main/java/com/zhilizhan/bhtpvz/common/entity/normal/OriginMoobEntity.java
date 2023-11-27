package com.zhilizhan.bhtpvz.common.entity.normal;

import com.zhilizhan.bhtpvz.common.entity.BHTPvZEntityTypes;
import com.zhilizhan.bhtpvz.common.item.BHTPvZItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.AgableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraftforge.common.IForgeShearable;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class OriginMoobEntity extends Cow implements IForgeShearable {

    private MobEffect effect;
    private int effectDuration;

    public OriginMoobEntity(EntityType<? extends Cow> arg, Level arg2) {
        super(arg, arg2);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0).add(Attributes.MOVEMENT_SPEED, 0.20000000298023224);
    }

    public List<ItemStack> onSheared(@Nullable Player player, @Nonnull ItemStack item, Level world, BlockPos pos, int fortune) {
        world.playSound((Player)null, this, SoundEvents.MOOSHROOM_SHEAR, player == null ? SoundSource.BLOCKS : SoundSource.PLAYERS, 1.0F, 1.0F);
        if (world.isClientSide() || this.isBaby()) {
            return Collections.emptyList();
        } else {
            ((ServerLevel)this.level).sendParticles(ParticleTypes.EXPLOSION, this.getX(), this.getY(0.5), this.getZ(), 1, 0.0, 0.0, 0.0, 0.0);
            this.remove();
            Cow cowentity = (Cow)EntityType.COW.create(this.level);
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
            List<ItemStack> items = new ArrayList();

            for(int i = 0; i < 3; ++i) {
                items.add(new ItemStack(BHTPvZItems.ORIGIN_MUSHROOM.get()));
                if(Math.random()<0.2f)items.add(new ItemStack(BHTPvZItems.CHLOROPHYLL.get()));
            }

            return items;
        }
    }
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (itemstack.getItem() == Items.BOWL && !this.isBaby()) {
            boolean flag = false;
            ItemStack itemstack1;
            if (this.effect != null) {
                flag = true;
                itemstack1 = new ItemStack(Items.SUSPICIOUS_STEW);
                SuspiciousStewItem.saveMobEffect(itemstack1, this.effect, this.effectDuration);
                this.effect = null;
                this.effectDuration = 0;
            } else {
                itemstack1 = new ItemStack(Items.MUSHROOM_STEW);
            }

            ItemStack itemstack2 = ItemUtils.createFilledResult(itemstack, player, itemstack1, false);
            player.setItemInHand(hand, itemstack2);
            SoundEvent soundevent;
            if (flag) {
                soundevent = SoundEvents.MOOSHROOM_MILK_SUSPICIOUSLY;
            } else {
                soundevent = SoundEvents.MOOSHROOM_MILK;
            }

            this.playSound(soundevent, 1.0F, 1.0F);
            return InteractionResult.sidedSuccess(this.level.isClientSide);
        }else if (itemstack.getItem().is(ItemTags.SMALL_FLOWERS)) {
            if (this.effect != null) {
                for(int i = 0; i < 2; ++i) {
                    this.level.addParticle(ParticleTypes.SMOKE, this.getX() + this.random.nextDouble() / 2.0, this.getY(0.5), this.getZ() + this.random.nextDouble() / 2.0, 0.0, this.random.nextDouble() / 5.0, 0.0);
                }
            } else {
                Optional<Pair<MobEffect, Integer>> optional = this.getEffectFromItemStack(itemstack);
                if (!optional.isPresent()) {
                    return InteractionResult.PASS;
                }

                Pair<MobEffect, Integer> pair = (Pair)optional.get();
                if (!player.abilities.instabuild) {
                    itemstack.shrink(1);
                }

                for(int j = 0; j < 4; ++j) {
                    this.level.addParticle(ParticleTypes.EFFECT, this.getX() + this.random.nextDouble() / 2.0, this.getY(0.5), this.getZ() + this.random.nextDouble() / 2.0, 0.0, this.random.nextDouble() / 5.0, 0.0);
                }

                this.effect = (MobEffect)pair.getLeft();
                this.effectDuration = (Integer)pair.getRight();
                this.playSound(SoundEvents.MOOSHROOM_EAT, 2.0F, 1.0F);
            }

            return InteractionResult.sidedSuccess(this.level.isClientSide);
        } else {
            return super.mobInteract(player, hand);
        }
    }

    public OriginMoobEntity getBreedOffspring(ServerLevel serverLevel, AgableMob mate) {
        return  BHTPvZEntityTypes.ORIGIN_MOOB.get().create(serverLevel);
    }
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        if (this.effect != null) {
            compound.putByte("EffectId", (byte)MobEffect.getId(this.effect));
            compound.putInt("EffectDuration", this.effectDuration);
        }

    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("EffectId", 1)) {
            this.effect = MobEffect.byId(compound.getByte("EffectId"));
        }

        if (compound.contains("EffectDuration", 3)) {
            this.effectDuration = compound.getInt("EffectDuration");
        }

    }
    private Optional<Pair<MobEffect, Integer>> getEffectFromItemStack(ItemStack stack) {
        Item item = stack.getItem();
        if (item instanceof BlockItem) {
            Block block = ((BlockItem)item).getBlock();
            if (block instanceof FlowerBlock) {
                FlowerBlock flowerblock = (FlowerBlock)block;
                return Optional.of(Pair.of(flowerblock.getSuspiciousStewEffect(), flowerblock.getEffectDuration()));
            }
        }

        return Optional.empty();
    }
}
