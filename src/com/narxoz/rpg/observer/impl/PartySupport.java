package com.narxoz.rpg.observer.impl;
import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.observer.*;

import java.util.List;
import java.util.Random;

public class PartySupport implements GameObserver{
    private final List<Hero> heroes;
    private final Random random = new Random();

    public PartySupport(List<Hero> heroes) {
        this.heroes = heroes;
    }
    public void onEvent(GameEvent event) {
        if (event.getType() == GameEventType.HERO_LOW_HP) {

            List<Hero> alive = heroes.stream()
                    .filter(Hero::isAlive)
                    .toList();

            if (alive.isEmpty()) return;

            Hero h = alive.get(random.nextInt(alive.size()));

            h.heal(20);

            System.out.println("Support healed " + h.getName());
        }
    }
}
