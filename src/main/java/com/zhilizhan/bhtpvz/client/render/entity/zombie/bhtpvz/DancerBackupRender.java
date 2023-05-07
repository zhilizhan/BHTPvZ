package com.zhilizhan.bhtpvz.client.render.entity.zombie.bhtpvz;

import com.hungteen.pvz.client.render.entity.zombie.PVZZombieRender;
import com.zhilizhan.bhtpvz.client.model.entity.zombie.bhtpvz.DancerBackupModel;
import com.zhilizhan.bhtpvz.common.entity.zombie.bhtpvz.DancerBackupEntity;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DancerBackupRender extends PVZZombieRender<DancerBackupEntity> {
    public DancerBackupRender(EntityRenderDispatcher rendererManager) {
        super(rendererManager, new DancerBackupModel(), 0.5F);
    }
}
