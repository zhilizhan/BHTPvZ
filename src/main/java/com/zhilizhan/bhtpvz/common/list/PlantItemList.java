package com.zhilizhan.bhtpvz.common.list;

import com.hungteen.pvz.common.item.spawn.card.PlantCardItem;
import com.hungteen.pvz.utils.others.WeightList;
import com.zhilizhan.bhtpvz.common.item.BHTPvZItems;

public class PlantItemList {
    public static final WeightList<PlantCardItem> PLANT = new WeightList<>();
    static {
        PLANT.addItem(BHTPvZItems.BLAZE_WART_ENJOY_CARD.get(),100);
        PLANT.addItem(BHTPvZItems.ICE_CABBAGE_PULT_ENJOY_CARD.get(),90);
        PLANT.addItem(BHTPvZItems.BURST_KERNEL_PULT_ENJOY_CARD.get(),100);
        PLANT.addItem(BHTPvZItems.NUT_BOWLING_ENJOY_CARD.get(),90);
        PLANT.addItem(BHTPvZItems.GRASS_CARP_ENJOY_CARD.get(),100);
        PLANT.addItem(BHTPvZItems.CHORUS_FRUIT_PULT_ENJOY_CARD.get(),90);
    }
}
