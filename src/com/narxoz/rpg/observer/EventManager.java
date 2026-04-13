package com.narxoz.rpg.observer;
import java.util.ArrayList;
import java.util.List;
public class EventManager {
    private final List<GameObserver> observers = new ArrayList<>();

    public void subscribe(GameObserver observer) {
        observers.add(observer);
    }

    public void notify(GameEvent event) {
        for (GameObserver o : observers) {
            o.onEvent(event);
        }
    }
}