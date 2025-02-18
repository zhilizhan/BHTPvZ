package com.zhilizhan.bhtpvz.common.mixin;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.EntityRegister;
import com.hungteen.pvz.common.entity.bullet.StarEntity;
import com.hungteen.pvz.common.entity.bullet.itembullet.PeaEntity;
import com.hungteen.pvz.common.impl.plant.OtherPlants;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.hungteen.pvz.common.item.ItemRegister;
import com.hungteen.pvz.common.item.spawn.card.PlantCardItem;
import com.hungteen.pvz.common.item.tool.plant.PeaGunItem;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.zhilizhan.bhtpvz.common.entity.BHTPvZEntityTypes;
import com.zhilizhan.bhtpvz.common.entity.bullet.BeeEntity;
import com.zhilizhan.bhtpvz.common.entity.bullet.itembullet.GooPeaEntity;
import com.zhilizhan.bhtpvz.common.entity.bullet.itembullet.StonePeaEntity;
import com.zhilizhan.bhtpvz.common.impl.plant.BHTPvZPlants;
import com.zhilizhan.bhtpvz.common.item.BHTPvZItems;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

import static com.hungteen.pvz.common.item.tool.plant.PeaGunItem.getFirstBullets;

@Mixin(value = PeaGunItem.class,remap = false)
public abstract class PeaGunItemMixin {
    @Shadow @Final private static HashSet<IPlantType> SHOOT_MODES;

    @Shadow protected abstract void shrinkItemStack(PlayerEntity player, ItemStack stack);

    @Shadow protected abstract PeaEntity.Type getPeaType(PlayerEntity player);

    @Unique
    private static final HashSet<IPlantType> BHTPVZ_SHOOT_MODES = new HashSet<>(Arrays.asList(BHTPvZPlants.RE_ICEPEA,BHTPvZPlants.PEA_POD,BHTPvZPlants.PRIMAL_PEA_SHOOTER,BHTPvZPlants.FIRE_PEASHOOTER,BHTPvZPlants.GOO_PEA_SHOOTER,BHTPvZPlants.BEE_SHOOTER));
    @Unique
    private static final HashSet<IPlantType> NORMAL_PEA_MODES = new HashSet<>(Arrays.asList(BHTPvZPlants.RE_ICEPEA,BHTPvZPlants.PEA_POD,BHTPvZPlants.FIRE_PEASHOOTER,PVZPlants.SNOW_PEA,PVZPlants.PEA_SHOOTER,PVZPlants.GATLING_PEA,PVZPlants.SPLIT_PEA,PVZPlants.THREE_PEATER));

    @Inject(method = "registerPeaGunShootMode", at = @At("TAIL"))
    private static void registerPeaGunShootMode(IPlantType type, CallbackInfo ci) {
        BHTPVZ_SHOOT_MODES.add(type);
    }
    @Redirect(method = "isValidMode", at = @At(value = "INVOKE",target = "Ljava/util/HashSet;contains(Ljava/lang/Object;)Z"))
    private static boolean isValidMode(HashSet<?> instance, Object o,ItemStack stack) {
        return (SHOOT_MODES.contains(((PlantCardItem)stack.getItem()).plantType)||BHTPVZ_SHOOT_MODES.contains(((PlantCardItem)stack.getItem()).plantType) )&& !((PlantCardItem)stack.getItem()).isEnjoyCard;
    }

    @Inject(method = "performShoot", at = @At(value = "HEAD"), cancellable = true)
    public void performShoot(World world, PlayerEntity player, ItemStack itemStack, IPlantType mode, CallbackInfo ci) {
        ItemStack stack = getFirstBullets(itemStack);
        if (mode == PVZPlants.PEA_SHOOTER||mode==BHTPvZPlants.FIRE_PEASHOOTER||mode==BHTPvZPlants.BEE_SHOOTER||mode == PVZPlants.SNOW_PEA||mode==BHTPvZPlants.GOO_PEA_SHOOTER||mode==BHTPvZPlants.PRIMAL_PEA_SHOOTER) {
            this.shootPea(world, player, mode, stack, 0.5, 0.0,0.0, 0.0F);
        } else if (mode == PVZPlants.REPEATER || mode == BHTPvZPlants.RE_ICEPEA) {
            this.shootPea(world, player, mode, stack, 0.5, 0.0,0.0, 0.0F);
            this.shootPea(world, player, mode, stack, 0.0, 0.0,0.0, 0.0F);
        } else if (mode == PVZPlants.THREE_PEATER) {
            this.shootPea(world, player, mode, stack, 0.25, 0.0,-0.25, -15.0F);
            this.shootPea(world, player, mode, stack, 0.25, 0.0,0.0, 0.0F);
            this.shootPea(world, player, mode, stack, 0.25, 0.0,0.25, 15.0F);
        } else if (mode == PVZPlants.SPLIT_PEA) {
            this.shootPea(world, player, mode, stack, 0.25, 0.0,0.0, 0.0F);
            this.shootPea(world, player, mode, stack, 0.0, 0.0,0.0, 180.0F);
            this.shootPea(world, player, mode, stack, -0.5, 0.0,0.0, 180.0F);
        } else {
            if (mode == PVZPlants.STAR_FRUIT) {
                for(int i = 0; i < 5; ++i) {
                    this.shootPea(world, player, mode, stack, 0.25, 0.0,0.0, (float)(i + 72 * i));
                }
            } else if (mode == OtherPlants.ANGEL_STAR_FRUIT) {
                for(int i = 0; i < 5; ++i) {
                    this.shootPea(world, player, mode, stack, 0.25, 0.0,0.0, (float)(72 * i));
                }
            } else if (mode == PVZPlants.GATLING_PEA) {
                this.shootPea(world, player, mode, stack, 1.5, 0.0,0.0, 0.0F);
                this.shootPea(world, player, mode, stack, 1.0, 0.0,0.0, 0.0F);
                this.shootPea(world, player, mode, stack, 0.5, 0.0,0.0, 0.0F);
                this.shootPea(world, player, mode, stack, 0.0, 0.0,0.0, 0.0F);
            }
            else if (mode == BHTPvZPlants.PEA_POD) {
                this.shootPea(world, player, mode, stack, 0.25, 0.0,-0.25, -15.0F);
                this.shootPea(world, player, mode, stack, 0.25, 0.0,0.0, 0.0F);
                this.shootPea(world, player, mode, stack, 0.25, 0.2,0.0, 0.0F);
                this.shootPea(world, player, mode, stack, 0.25, 0.4,0.0, 0.0F);
                this.shootPea(world, player, mode, stack, 0.25, 0.0,0.25, 15.0F);
            }
        }

        SoundEvent sound = mode != PVZPlants.SNOW_PEA && !stack.getItem().equals(ItemRegister.SNOW_PEA.get()) ? SoundEvents.SNOW_GOLEM_SHOOT : SoundRegister.SNOW_SHOOT.get();
        EntityUtil.playSound(player, sound);
        this.shrinkItemStack(player, itemStack);
        if (PlayerUtil.isPlayerSurvival(player)) {
            itemStack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(Hand.MAIN_HAND));
        }
        ci.cancel();
    }
    @Unique
    public void shootPea(World world, PlayerEntity player, IPlantType mode, ItemStack stack, double forwardOffset, double heightOffset , double rightOffset, float angle) {
        Vector3d vec = player.getLookAngle();
        double deltaX = forwardOffset * vec.x - rightOffset * vec.z;
        double deltaY = heightOffset - 0.4;
        double deltaZ = forwardOffset * vec.z + rightOffset * vec.x;
        if (NORMAL_PEA_MODES.contains(mode)) {
            PeaEntity pea = EntityRegister.PEA.get().create(world);
            if (pea != null) {
                pea.setPeaType(this.getPeaType(player));
            pea.setPeaState(this.getPeaState(mode, stack.getItem()));
            pea.setPower(EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, player.getMainHandItem()));
            pea.setPos(player.getX() + deltaX, player.getEyeY() + deltaY, player.getZ() + deltaZ);
            pea.shootPea(player.getLookAngle(), 1.2999999523162842, angle);
            pea.summonByOwner(player);
            pea.setAttackDamage(1.5F);

            world.addFreshEntity(pea);
            }
        } else if (mode ==BHTPvZPlants.GOO_PEA_SHOOTER||stack.getItem()==BHTPvZItems.GOO_PEA.get()) {
            GooPeaEntity pea = BHTPvZEntityTypes.GOO_PEA.get().create(world);
            if (pea != null) {
                pea.setPos(player.getX() + deltaX, player.getEyeY() + deltaY, player.getZ() + deltaZ);

            pea.shootPea(player.getLookAngle(), 1.2999999523162842, angle);
            pea.setPower(EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, player.getMainHandItem()));
            pea.summonByOwner(player);
            pea.setAttackDamage(1.5F);

            world.addFreshEntity(pea);
            }
        } else if (mode ==BHTPvZPlants.PRIMAL_PEA_SHOOTER||stack.getItem()==BHTPvZItems.PEA_BLOCK.get()) {
            if (Math.random() <= 0.5F||stack.getItem()==BHTPvZItems.PEA_BLOCK.get()) {
                StonePeaEntity pea =  BHTPvZEntityTypes.STONE_PEA.get().create(world);
                if (pea != null) {
                    pea.setPos(player.getX() + deltaX, player.getEyeY() + deltaY, player.getZ() + deltaZ);
                pea.shootPea(player.getLookAngle(), 1.2999999523162842, angle);
                pea.setPower(EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, player.getMainHandItem()));
                pea.summonByOwner(player);
                pea.setAttackDamage(1.5F);

                world.addFreshEntity(pea);
                }

            } else {
                PeaEntity pea = EntityRegister.PEA.get().create(world);
                if (pea != null) {
                    pea.setPeaType(this.getPeaType(player));

                pea.setPeaState(this.getPeaState(mode, stack.getItem()));
                pea.setPower(EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, player.getMainHandItem()));
                pea.setPos(player.getX() + deltaX, player.getEyeY() + deltaY, player.getZ() + deltaZ);
                pea.shootPea(player.getLookAngle(), 1.2999999523162842, angle);
                pea.summonByOwner(player);
                pea.setAttackDamage(1.5F);

                world.addFreshEntity(pea);
                }
            }
        } else if (mode == BHTPvZPlants.BEE_SHOOTER) {
            BeeEntity bee = BHTPvZEntityTypes.BEE.get().create(world);

            float power = 1 + (float) (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, player.getMainHandItem())) / 2;
            if (bee != null) {
                bee.setPos(player.getX() + deltaX, player.getEyeY() + deltaY, player.getZ() + deltaZ);

            bee.shootBee(player.getLookAngle(), 1.2999999523162842, angle);
            bee.summonByOwner(player);
            Objects.requireNonNull(bee.getAttribute(Attributes.MAX_HEALTH)).setBaseValue(power);
            Objects.requireNonNull(bee.getAttribute(Attributes.ATTACK_DAMAGE)).setBaseValue((power / 2));

                world.addFreshEntity(bee);
            }
        } else if (mode == PVZPlants.STAR_FRUIT || mode == OtherPlants.ANGEL_STAR_FRUIT){
              StarEntity star = EntityRegister.STAR.get().create(world);
            if (star != null) {
                star.setStarState(this.getStarState(mode, stack.getItem()));

            float power = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, player.getMainHandItem());
                star.setPos(player.getX() + deltaX, player.getEyeY() + deltaY, player.getZ() + deltaZ);
                star.setPos(player.getX() + deltaX, player.getEyeY() + deltaY, player.getZ() + deltaZ);
                star.shootPea(player.getLookAngle(), 1.2999999523162842, angle);
                star.summonByOwner(player);
                star.setAttackDamage(1.5F + power);

                world.addFreshEntity(star);
            }
        }else if (stack.getItem()==BHTPvZItems.STARFRUIT.get()||stack.getItem()==BHTPvZItems.ANGEL_STARFRUIT.get()) {
            Item item = stack.getItem();
            StarEntity star = EntityRegister.STAR.get().create(world);
            if (star != null) {
                star.setStarState(item==BHTPvZItems.STARFRUIT.get()? StarEntity.StarStates.YELLOW: StarEntity.StarStates.PINK);
            }

            float power = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, player.getMainHandItem());
            if (star != null) {
                star.setPos(player.getX() + deltaX, player.getEyeY() + deltaY, player.getZ() + deltaZ);
            star.setPos(player.getX() + deltaX, player.getEyeY() + deltaY, player.getZ() + deltaZ);
            star.shootPea(player.getLookAngle(), 1.2999999523162842, angle);
            star.summonByOwner(player);
            star.setAttackDamage(1.5F + power);

            world.addFreshEntity(star);
            }
        }

    }
    @Unique
    private PeaEntity.State getPeaState(IPlantType plant, Item item) {
        if (plant == PVZPlants.SNOW_PEA || plant == BHTPvZPlants.RE_ICEPEA) {
            return PeaEntity.State.ICE;
        } else if (plant == BHTPvZPlants.FIRE_PEASHOOTER) {
            return PeaEntity.State.FIRE;
        } else if (item == ItemRegister.SNOW_PEA.get()) {
            return PeaEntity.State.ICE;
        } else {
            return item == ItemRegister.FLAME_PEA.get() ? PeaEntity.State.FIRE : PeaEntity.State.NORMAL;
        }
    }
    @Unique
    private StarEntity.StarStates getStarState(IPlantType plant, Item item) {
        if (plant == PVZPlants.STAR_FRUIT) {
            return StarEntity.StarStates.YELLOW;
        } else if (plant == OtherPlants.ANGEL_STAR_FRUIT) {
            return StarEntity.StarStates.PINK;
        } else if (item == BHTPvZItems.STARFRUIT.get()) {
            return StarEntity.StarStates.YELLOW;
        } else if (item == BHTPvZItems.ANGEL_STARFRUIT.get()){
            return  StarEntity.StarStates.PINK; }
        return StarEntity.StarStates.YELLOW;
    }

}
