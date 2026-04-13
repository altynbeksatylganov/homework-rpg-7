package com.narxoz.rpg.boss;
import com.narxoz.rpg.observer.*;
import com.narxoz.rpg.strategy.CombatStrategy;
import com.narxoz.rpg.strategy.impl.*;

public class DungeonBoss implements GameObserver {
    private int hp;
    private final int maxHp;
    private final int attack;
    private final int defense;

    private int currentPhase = 1;
    private CombatStrategy strategy;
    public DungeonBoss(int hp, int attack, int defense) {
        this.hp = hp;
        this.maxHp = hp;
        this.attack = attack;
        this.defense = defense;
        this.strategy = new BossPhase1Strategy();
    }

    public int getHp() { return hp; }
    public boolean isAlive() { return hp > 0; }
    public int getAttack() { return attack; }
    public int getDefense() { return defense; }
    public CombatStrategy getStrategy() { return strategy; }

    public void takeDamage(int dmg, EventManager bus) {
        int oldHp = hp;
        hp = Math.max(0, hp - dmg);

        double oldPercent = (double) oldHp / maxHp;
        double newPercent = (double) hp / maxHp;


        if (oldPercent > 0.6 && newPercent <= 0.6) {
            bus.notify(new GameEvent(GameEventType.BOSS_PHASE_CHANGED, "Boss", 2));
        }


        if (oldPercent > 0.3 && newPercent <= 0.3) {
            bus.notify(new GameEvent(GameEventType.BOSS_PHASE_CHANGED, "Boss", 3));
        }

        if (hp == 0) {
            bus.notify(new GameEvent(GameEventType.BOSS_DEFEATED, "Boss", 0));
        }
    }

    @Override
    public void onEvent(GameEvent event) {
        if (event.getType() == GameEventType.BOSS_PHASE_CHANGED) {

            int newPhase = event.getValue();


            if (newPhase <= currentPhase) return;

            currentPhase = newPhase;

            if (currentPhase == 2) {
                strategy = new BossPhase2Strategy();
            } else if (currentPhase == 3) {
                strategy = new BossPhase3Strategy();
            }

            System.out.println("Boss switched to phase " + currentPhase +
                    " | Strategy: " + strategy.getName());
        }
    }
}

