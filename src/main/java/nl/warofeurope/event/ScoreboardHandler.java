package nl.warofeurope.event;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.*;
import java.util.stream.Collectors;

import static nl.warofeurope.event.utils.Colors.color;
import static nl.warofeurope.event.utils.ScoreboardUtil.replaceScore;

public class ScoreboardHandler {
    public Scoreboard scoreboard;

    public ScoreboardHandler(){
        this.initialize();
    }

    public void updateScoreboard(){
        final int[] start = {17};
        Objective objective = this.scoreboard.getObjective("sidebar");

        List<Teams> teams = Arrays.stream(Teams.getValues()).sorted((o1, o2) -> o2.nexusLives - o1.nexusLives).collect(Collectors.toList());
        for (Teams team : teams) {
            replaceScore(objective, start[0], color(team.getDisplay() + "&f: " + (team.nexusLives <= 0 ? "&c×" : "&6" + team.nexusLives)));
            start[0]--;
        }
    }

    public void initialize(){
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

        Objective boardObject = this.scoreboard.registerNewObjective("sidebar", "dummy");
        boardObject.setDisplaySlot(DisplaySlot.SIDEBAR);
        boardObject.setDisplayName(color("&6&lNexus Levens"));

        for (Teams internalTeam : Teams.getValues()){
            Team team = this.scoreboard.registerNewTeam("t-" + internalTeam.toString());
            team.setPrefix(color(internalTeam.prefix + " "));
            team.setAllowFriendlyFire(false);
            team.setCanSeeFriendlyInvisibles(true);
        }

        this.updateScoreboard();
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }
}
