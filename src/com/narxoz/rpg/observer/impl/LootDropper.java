package com.narxoz.rpg.observer.impl;
import com.narxoz.rpg.observer.*;

public class LootDropper implements GameObserver {
    public void onEvent(GameEvent event) {
        if (event.getType() == GameEventType.BOSS_PHASE_CHANGED) {
            System.out.println("Loot dropped: Phase reward!");
        }

        if (event.getType() == GameEventType.BOSS_DEFEATED) {
            System.out.println("Loot dropped: LEGENDARY ITEM!");
        }
    }
}
