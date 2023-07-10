package com.zhilizhan.bhtpvz.common.event;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.enchantment.EnchantmentRegister;
import com.hungteen.pvz.common.entity.AbstractPAZEntity;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.potion.EffectRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;
import com.zhilizhan.bhtpvz.common.item.BHTPvZItems;
import com.zhilizhan.bhtpvz.config.BHTPvZConfig;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PVZMod.MOD_ID)
public class LivingEvents {

	@SubscribeEvent
	public static void onLivingHurt(LivingHurtEvent event) {
		if(event.getSource().getEntity() instanceof AbstractPAZEntity &&!(event.getEntity() instanceof AbstractPAZEntity)) {
			if (!(event.getEntityLiving() instanceof Player)) {
				event.setAmount((float) (event.getAmount() * BHTPvZConfig.COMMON_CONFIG.EntitySettings.EntityLiveTick.PVZDamageAmount.get()));
			}
		}
	}
	@SubscribeEvent
	public static void onZombieHurt(LivingHurtEvent event) {
		if((event.getEntity() instanceof PVZZombieEntity)) {
				event.setAmount((float) (event.getAmount() * BHTPvZConfig.COMMON_CONFIG.EntitySettings.ZombieSetting.ZombieHurtAmount.get()));
		}
	}
	@SubscribeEvent
	public static void PlayerRightClickItem(PlayerInteractEvent.EntityInteractSpecific event) {
		Entity entity = event.getTarget();
		Player player = event.getPlayer();
		Item item = event.getPlayer().getMainHandItem().getItem();
		if(! event.getWorld().isClientSide&&event.getHand() == InteractionHand.MAIN_HAND){
			if (entity instanceof PVZPlantEntity && EntityUtil.isEntityValid(entity) && (item.equals(BHTPvZItems.DAMSON_CRYSTAL_SWORD.get()) || EnchantmentHelper.getItemEnchantmentLevel((Enchantment) EnchantmentRegister.ENERGY_TRANSFER.get(), item.getDefaultInstance()) > 0) && ((PVZPlantEntity)entity).canStartSuperMode() && (!PlayerUtil.isPlayerSurvival(player) || PlayerUtil.getResource(player, Resources.ENERGY_NUM) > 0)) {
			if (PlayerUtil.isPlayerSurvival(player)) {
				PlayerUtil.addResource(player, Resources.ENERGY_NUM, -1);
			}
			((PVZPlantEntity)entity).startSuperMode(true);
			int treeLevel = PlayerUtil.getResource(player, Resources.TREE_LVL);
			player.addEffect(new MobEffectInstance((MobEffect) EffectRegister.ENERGETIC_EFFECT.get(), 100 + (treeLevel + 1) / 2, 0));
		}

	}
	}

}
