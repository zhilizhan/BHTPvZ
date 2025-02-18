package com.zhilizhan.bhtpvz.common.impl;

import com.hungteen.pvz.api.types.ISkillType;
import com.hungteen.pvz.common.impl.SkillTypes;
import com.hungteen.pvz.utils.ArrayUtil;

import java.util.Arrays;

public class BHTPvZSkill extends SkillTypes {

    public static final ISkillType WART_HEAL_CD = new SkillTypes.SkillType("wart_heal_cd", ArrayUtil.getAverageArray(5, 15.0F, 9.0F), Arrays.asList(2, 3, 4,5));
    public static final ISkillType IMITATER_CHANCE = new SkillTypes.SkillType("imitater_chance", ArrayUtil.getAverageArray(3, 3.0F, 5.0F), Arrays.asList(2, 3, 5));

    public static final ISkillType LIGHT_BEAM_DAMAGE = new SkillTypes.SkillType("light_beam_damage", ArrayUtil.getAverageArray(5, 35.0F, 65.0F), Arrays.asList(2, 3, 4, 5, 6));
    public static final ISkillType MAGNIFYING_GRASS_DAMAGE = new SkillTypes.SkillType("magnifying_grass_damage", ArrayUtil.getAverageArray(3, 0.5F, 1.25F), Arrays.asList(2, 4));
    public static final ISkillType GRASS_CARP_DAMAGE = new SkillType("grass_carp_damage", ArrayUtil.getAverageArray(4, 1.0F, 2.0F), Arrays.asList(2, 3, 4, 5, 6));
    public static final ISkillType GRASS_CARP_HEAL_RANGE = new SkillType("grass_carp_heal_rage", ArrayUtil.getAverageArray(5, 9.0F, 15.0F), Arrays.asList(2, 4, 5, 6));

    public static final ISkillType MORE_CHORUS_FRUIT_DAMAGE = new SkillType("more_chorus_fruit_damage", ArrayUtil.getAverageArray(4, 1.5F, 3.5F), Arrays.asList(3, 4, 7));

    public BHTPvZSkill() {
    }

}