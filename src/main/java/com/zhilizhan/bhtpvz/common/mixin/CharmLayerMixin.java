package com.zhilizhan.bhtpvz.common.mixin;

import com.hungteen.pvz.client.render.layer.fullskin.CharmLayer;
import com.hungteen.pvz.client.render.layer.fullskin.PVZFullSkinLayer;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.plant.magic.HypnoShroomEntity;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.zhilizhan.bhtpvz.common.entity.plant.appease.GrassCarpEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = CharmLayer.class,remap = false)
public abstract class CharmLayerMixin <T extends LivingEntity, M extends EntityModel<T>> extends PVZFullSkinLayer<T, M> {
    public CharmLayerMixin(RenderLayerParent<T, M> entityRendererIn) {
        super(entityRendererIn);
    }

    @Overwrite
    protected boolean canRender(T entity) {
        if (entity.isInvisible()||entity instanceof GrassCarpEntity) {
            return false;
        } else if (entity instanceof HypnoShroomEntity) {
            return ((HypnoShroomEntity)entity).isPlantInSuperMode();
        } else if (entity instanceof PVZZombieEntity) {
            return ((PVZZombieEntity)entity).isCharmed();
        } else {
            return entity instanceof PVZPlantEntity ? ((PVZPlantEntity)entity).isCharmed() : false;
        }
    }
}
