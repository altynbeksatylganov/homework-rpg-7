package com.narxoz.rpg.observer.impl;
import com.narxoz.rpg.observer.*;
public class AchievementTracker implements GameObserver {
    private boolean firstBlood = false;
    private int attacks = 0;

    public void onEvent(GameEvent event) {

        if (event.getType() == GameEventType.ATTACK_LANDED) {
            attacks++;

            if (!firstBlood) {
                System.out.println("Achievement: First Blood!");
                firstBlood = true;
            }

            if (attacks >= 10) {
                System.out.println("Achievement: Relentless Attacker!");
            }
        }

        if (event.getType() == GameEventType.BOSS_DEFEATED) {
            System.out.println("Achievement: Boss Slayer!");
        }

        if (event.getType() == GameEventType.HERO_DIED) {
            System.out.println("Achievement: Fallen Hero...");
        }
    }
}
