package com.narxoz.rpg.engine;

import com.narxoz.rpg.boss.DungeonBoss;
import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.observer.*;

import java.util.*;

public class DungeonEngine {

    private final EventManager bus;

    public DungeonEngine(EventManager bus) {
        this.bus = bus;
    }

    public EncounterResult run(List<Hero> heroes, DungeonBoss boss) {

        int rounds = 0;

        Set<Hero> lowHpTriggered = new HashSet<>();

        while (boss.isAlive() && heroes.stream().anyMatch(Hero::isAlive) && rounds < 50) {
            rounds++;


            for (Hero h : heroes) {
                if (!h.isAlive()) continue;

                int dmg = h.getStrategy().calculateDamage(h.getAttackPower())
                        - boss.getStrategy().calculateDefense(boss.getDefense());

                dmg = Math.max(1, dmg);

                boss.takeDamage(dmg, bus);

                bus.notify(new GameEvent(GameEventType.ATTACK_LANDED, h.getName(), dmg));
            }


            for (Hero h : heroes) {
                if (!h.isAlive()) continue;

                int dmg = boss.getStrategy().calculateDamage(boss.getAttack())
                        - h.getStrategy().calculateDefense(h.getDefense());

                dmg = Math.max(1, dmg);

                h.takeDamage(dmg);

                bus.notify(new GameEvent(GameEventType.ATTACK_LANDED, "Boss", dmg));


                if (h.getHp() <= h.getMaxHp() * 0.3 && !lowHpTriggered.contains(h)) {
                    lowHpTriggered.add(h);
                    bus.notify(new GameEvent(GameEventType.HERO_LOW_HP, h.getName(), h.getHp()));
                }

                if (!h.isAlive()) {
                    bus.notify(new GameEvent(GameEventType.HERO_DIED, h.getName(), 0));
                }
            }
        }

        int alive = (int) heroes.stream().filter(Hero::isAlive).count();

        return new EncounterResult(!boss.isAlive(), rounds, alive);
    }
}