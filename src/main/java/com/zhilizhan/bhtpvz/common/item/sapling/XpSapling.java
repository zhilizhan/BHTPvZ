package com.zhilizhan.bhtpvz.common.item.sapling;

public class XpSapling extends AbstractXpSapling {
    public XpSapling(Properties properties) {
        super(properties);
    }
    @Override
    protected int amount(){
        return 300;
    }
}
