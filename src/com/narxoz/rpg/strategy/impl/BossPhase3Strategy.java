package com.narxoz.rpg.strategy.impl;

import com.narxoz.rpg.strategy.CombatStrategy;


public class BossPhase3Strategy implements CombatStrategy{
    public int calculateDamage(int basePower) {
        return (int)(basePower * 1.8);
    }

    public int calculateDefense(int baseDefense) {
        return 0;
    }

    public String getName() {
        return "Boss Phase 3";
    }
}
