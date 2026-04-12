package com.narxoz.rpg.strategy.impl;

import com.narxoz.rpg.strategy.CombatStrategy;

public class AggressiveStrategy implements CombatStrategy {
    public int calculateDamage(int basePower) {
        return (int)(basePower * 1.5);
    }

    public int calculateDefense(int baseDefense) {
        return (int)(baseDefense * 0.7);
    }

    public String getName() {
        return "Aggressive";
    }
}
