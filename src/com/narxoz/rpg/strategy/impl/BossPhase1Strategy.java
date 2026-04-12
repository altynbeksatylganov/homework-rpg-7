package com.narxoz.rpg.strategy.impl;

import com.narxoz.rpg.strategy.CombatStrategy;

public class BossPhase1Strategy implements CombatStrategy{
    public int calculateDamage(int basePower) {
        return basePower;
    }

    public int calculateDefense(int baseDefense) {
        return baseDefense;
    }

    public String getName() {
        return "Boss Phase 1";
    }
}
