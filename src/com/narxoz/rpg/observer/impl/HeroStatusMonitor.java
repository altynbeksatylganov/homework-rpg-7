package com.narxoz.rpg.observer.impl;
import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.observer.*;

import java.util.List;

public class HeroStatusMonitor implements GameObserver {
    private final List<Hero> heroes;

    public HeroStatusMonitor(List<Hero> heroes) {
        this.heroes = heroes;
    }

    public void onEvent(GameEvent event) {
        if (event.getType() == GameEventType.HERO_LOW_HP ||
                event.getType() == GameEventType.HERO_DIED) {

            for (Hero h : heroes) {
                System.out.println(h.getName() + " HP: " + h.getHp());
            }
        }
    }
}
