package nl.warofeurope.event;

import nl.warofeurope.event.utils.runnables.AsyncTask;
import nl.warofeurope.event.utils.runnables.SyncTask;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
            replaceScore(objective, start[0], color(team.getDisplay() + "&f: &6" + team.nexusLives + " &7&o(Kills: " + team.getKills() + ")"));
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

    public static enum Teams {
        NEDERLAND(
                "&6[Ned]&r",
                "&6Nederland",
                new Location(Bukkit.getWorld("world"), 85.0, 67.0, -146.0, 359.0f, 0.0f),
                new Location(Bukkit.getWorld("world"), 85.0, 67.0, -146.0, 359.0f, 0.0f)
        ),
        BELGIE(
                "&0[B&ee&4l]&r",
                "&0Be&elg&4i\u00eb",
                new Location(Bukkit.getWorld("world"), -86.0, 67.0, -98.0, 359.0f, 0.0f),
                new Location(Bukkit.getWorld("world"), -86.0, 67.0, -98.0, 359.0f, 0.0f)
        ),
        CYPRUS(
                "&f[Cy&6p]&r",
                "&fCypr&6us",
                new Location(Bukkit.getWorld("world"), 171.0, 67.0, -97.0, 359.0f, 0.0f),
                new Location(Bukkit.getWorld("world"), 171.0, 67.0, -97.0, 359.0f, 0.0f)
        ),
        KOSOVO(
                "&1[Ko&6s]&r",
                "&1Koso&6vo",
                new Location(Bukkit.getWorld("world"), -163.0, 67.0, 94.0, 269.0f, 0.0f),
                new Location(Bukkit.getWorld("world"), -163.0, 67.0, 94.0, 269.0f, 0.0f)
        ),
        IJSLAND(
                "&b[IJ&fs]&r",
                "&bIJs&fland",
                new Location(Bukkit.getWorld("world"), -119.0, 67.0, 180.0, 269.0f, 0.0f),
                new Location(Bukkit.getWorld("world"), -119.0, 67.0, 180.0, 269.0f, 0.0f)
        ),
        ZWEDEN(
                "&9[Z&ewe]&r",
                "&9Zwe&eden",
                new Location(Bukkit.getWorld("world"), -167.0, 67.0, 38.0, 269.0f, 0.0f),
                new Location(Bukkit.getWorld("world"), -167.0, 67.0, 38.0, 269.0f, 0.0f)
        ),
        MONACO(
                "&d[Mo&fn]&r",
                "&dMon&faco",
                new Location(Bukkit.getWorld("world"), 254.0, 67.0, 35.0, 89.0f, 0.0f),
                new Location(Bukkit.getWorld("world"), 254.0, 67.0, 35.0, 89.0f, 0.0f)
        ),
        TURKIJE(
                "&5[Tu&fr]&r",
                "&5Tur&fkije",
                new Location(Bukkit.getWorld("world"), -130.0, 67.0, -46.0, 269.0f, 0.0f),
                new Location(Bukkit.getWorld("world"), -130.0, 67.0, -46.0, 269.0f, 0.0f)
        ),
        ITALIE(
                "&2[I&ft&4a]&r",
                "&2It&fal&4i\u00eb",
                new Location(Bukkit.getWorld("world"), 211.0, 67.0, -57.0, 89.0f, 0.0f),
                new Location(Bukkit.getWorld("world"), 211.0, 67.0, -57.0, 89.0f, 0.0f)
        ),
        RUSLAND(
                "&9[Ru&cs]&r",
                "&9Rus&cland",
                new Location(Bukkit.getWorld("world"), 208.0, 67.0, 190.0, 89.0f, 0.0f),
                new Location(Bukkit.getWorld("world"), 208.0, 67.0, 190.0, 89.0f, 0.0f)
        ),
        SPANJE(
                "&6[S&epa]&r",
                "&6Spa&enje",
                new Location(Bukkit.getWorld("world"), 4.0, 67.0, -148.0, 359.0f, 0.0f),
                new Location(Bukkit.getWorld("world"), 4.0, 67.0, -148.0, 359.0f, 0.0f)
        ),
        ZWITSERLAND(
                "&c[Z&fwi]",
                "&cZ&fwit&cserland",
                new Location(Bukkit.getWorld("world"), 30.0, 67.0, 267.0, 180.0f, 0.0f),
                new Location(Bukkit.getWorld("world"), 30.0, 67.0, 267.0, 180.0f, 0.0f)
        ),
        ENGELAND(
                "&f[E&4ng]&r",
                "&fEnge&4land",
                new Location(Bukkit.getWorld("world"), 176.0, 67.0, 217.0, 180.0f, 0.0f),
                new Location(Bukkit.getWorld("world"), 176.0, 67.0, 217.0, 180.0f, 0.0f)
        ),
        FRANKRIJK(
                "&9[F&fr&4a]&r",
                "&9Fra&fnk&4rijk",
                new Location(Bukkit.getWorld("world"), -80.0, 67.0, 216.0, 180.0f, 0.0f),
                new Location(Bukkit.getWorld("world"), -80.0, 67.0, 216.0, 180.0f, 0.0f)
        ),
        FINLAND(
                "&f[Fi&9n]&r",
                "&9Fin&fland",
                new Location(Bukkit.getWorld("world"), 251.0, 67.0, 109.0, 89.0f, 0.0f),
                new Location(Bukkit.getWorld("world"), 251.0, 67.0, 109.0, 89.0f, 0.0f)
        ),
        DUITSLAND(
                "&0[D&4u&6i]&r",
                "&0Du&4its&6land",
                new Location(Bukkit.getWorld("world"), 102.0, 67.0, 263.0, 180.0f, 0.0f),
                new Location(Bukkit.getWorld("world"), 102.0, 67.0, 263.0, 180.0f, 0.0f)
        );

        private final String prefix;
        private final String display;
        private int kills;
        private final Set<Player> players;
        private final Location spawnLocations;
        private int nexusLives;
        private final Location nexusLocation;

        Teams(String prefix, String display, Location spawnLocation, Location nexusLocation) {
            this.prefix = prefix;
            this.display = display;
            this.kills = 0;
            this.spawnLocations = spawnLocation;
            this.players = new HashSet<>();
            this.nexusLives = 100;
            this.nexusLocation = nexusLocation;
        }

        public void addKill(){
            this.kills++;
        }

        public int getKills() {
            return this.kills;
        }

        public Set<Player> getPlayers() {
            return players;
        }

        public Location getSpawnLocations() {
            return this.spawnLocations;
        }

        public void teleport(Player player){
            player.teleport(this.getSpawnLocations());
        }

        public void teleport(String permission){
            for (Player player : Bukkit.getOnlinePlayers()){
                if (!player.hasPermission("eventbypass")){
                    if (player.hasPermission(permission)){
                        this.teleport(player);
                    }
                }
            }
        }

        public String getPrefix() {
            return prefix;
        }

        public String getDisplay() {
            return display;
        }

        public static Teams[] getValues(){
            return Teams.values();
        }
    }
}
