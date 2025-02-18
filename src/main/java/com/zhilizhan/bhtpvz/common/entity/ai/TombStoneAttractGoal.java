package com.zhilizhan.bhtpvz.common.entity.ai;

import com.hungteen.pvz.common.entity.zombie.grass.AbstractTombStoneEntity;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.interfaces.ICanAttract;
import net.minecraft.entity.ai.goal.Goal;

public class TombStoneAttractGoal  extends Goal {
    private final ICanAttract attracter;
    private final AbstractTombStoneEntity plantEntity;
    private final int attractCD;
    private int attractTick;

    public TombStoneAttractGoal(AbstractTombStoneEntity tombStoneEntity, ICanAttract attracter, int cd) {
        this.attracter = attracter;
        this.plantEntity = tombStoneEntity;
        this.attractCD = cd;
    }

    public boolean canUse() {
        return this.plantEntity.canNormalUpdate();
    }

    public boolean canContinueToUse() {
        return this.canUse();
    }

    public void tick() {
        ++this.attractTick;
        if (this.attractTick >= this.attractCD) {
            this.attract();
            this.attractTick = 0;
        }

    }

    protected void attract() {
        float range = this.attracter.getAttractRange();
        EntityUtil.getTargetableLivings(this.plantEntity, EntityUtil.getEntityAABB(this.plantEntity, (double)range, (double)range)).stream().filter((target) -> {
            return this.attracter.canAttract(target);
        }).forEach((target) -> {
            this.attracter.attract(target);
        });
    }
}
