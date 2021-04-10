package nl.warofeurope.event.models;

import nl.warofeurope.event.EventPlugin;
import nl.warofeurope.event.ScoreboardHandler;
import nl.warofeurope.event.Teams;

import java.util.HashMap;
import java.util.Map;

public class Game {
    public boolean started;
    public Map<Teams, Integer> playersLeft;

    public Game(EventPlugin eventPlugin){
        this.started = false;
        eventPlugin.scoreboardHandler.initialize();

        this.playersLeft = new HashMap<>();
        for (Teams teams : Teams.getValues()) {
            this.playersLeft.putIfAbsent(teams, 0);
        }
    }
}
