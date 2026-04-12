package com.narxoz.rpg.strategy.impl;

import com.narxoz.rpg.strategy.CombatStrategy;

public class BossPhase2Strategy implements CombatStrategy{
    public int calculateDamage(int basePower) {
        return (int)(basePower * 1.3);
    }

    public int calculateDefense(int baseDefense) {
        return (int)(baseDefense * 0.8);
    }

    public String getName() {
        return "Boss Phase 2";
    }
}
