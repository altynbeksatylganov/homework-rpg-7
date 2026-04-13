package com.narxoz.rpg;
import com.narxoz.rpg.boss.DungeonBoss;
import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.engine.*;
import com.narxoz.rpg.observer.EventManager;
import com.narxoz.rpg.observer.GameObserver;
import com.narxoz.rpg.observer.impl.*;
import com.narxoz.rpg.strategy.impl.*;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        EventManager bus = new EventManager();

        Hero h1 = new Hero("Warrior", 100, 20, 10);
        h1.setStrategy(new AggressiveStrategy());

        Hero h2 = new Hero("Tank", 120, 15, 15);
        h2.setStrategy(new DefensiveStrategy());

        Hero h3 = new Hero("Mage", 80, 25, 5);

        List<Hero> heroes = List.of(h1, h2, h3);

        DungeonBoss boss = new DungeonBoss(300, 25, 10);

        bus.subscribe((GameObserver) new BattleLogger());
        bus.subscribe(new AchievementTracker());
        bus.subscribe(new PartySupport(heroes));
        bus.subscribe(new HeroStatusMonitor(heroes));
        bus.subscribe(new LootDropper());
        bus.subscribe(boss);

        DungeonEngine engine = new DungeonEngine(bus);

        EncounterResult result = engine.run(heroes, boss);

        System.out.println("=== RESULT ===");
        System.out.println("Heroes won: " + result.isHeroesWon());
        System.out.println("Rounds: " + result.getRoundsPlayed());
        System.out.println("Survivors: " + result.getSurvivingHeroes());





    }
}
