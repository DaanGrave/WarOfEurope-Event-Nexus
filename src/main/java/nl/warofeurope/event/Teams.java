package nl.warofeurope.event;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public enum Teams {
    NEDERLAND(
            "&6[Ned]&r",
            "&6Nederland",
            new Location(Bukkit.getWorld("world"), -17.0, 68.0, 104.0, 180.0f, 0.0f),
            new Location(Bukkit.getWorld("world"), -17.0, 68.0, 102.0, 359.0f, 0.0f)
    ),
    BELGIE(
            "&e[Bel]&r",
            "&eBelgi\u00eb",
            new Location(Bukkit.getWorld("world"), 26.0, 69.0, 103.0, 180.0f, 0.0f),
            new Location(Bukkit.getWorld("world"), 26.0, 69.0, 101.0, 359.0f, 0.0f)
    ),
    CYPRUS(
            "&f[Cyp]&r",
            "&fCyprus",
            new Location(Bukkit.getWorld("world"), 68.0, 68.0, 103.0, 180.0f, 0.0f),
            new Location(Bukkit.getWorld("world"), 68.0, 67.0, 101.0, 359.0f, 0.0f)
    ),
    KOSOVO(
            "&1[Kos]&r",
            "&1Kosovo",
            new Location(Bukkit.getWorld("world"), 97.0, 68.0, 58.0, 90.0f, 0.0f),
            new Location(Bukkit.getWorld("world"), 95.0, 68.0, 58.0, 269.0f, 0.0f)
    ),
    IJSLAND(
            "&b[IJs]&r",
            "&bIJsland",
            new Location(Bukkit.getWorld("world"), 100.0, 69.0, 28.0, 90.0f, 0.0f),
            new Location(Bukkit.getWorld("world"), 98.0, 69.0, 28.0, 269.0f, 0.0f)
    ),
    ZWEDEN(
            "&9[Zwe]&r",
            "&9Zweden",
            new Location(Bukkit.getWorld("world"), 99.0, 68.0, -23.0, 90.0f, 0.0f),
            new Location(Bukkit.getWorld("world"), 97.0, 68.0, -23.0, 269.0f, 0.0f)
    ),
    MONACO(
            "&d[Mon]&r",
            "&dMonaco",
            new Location(Bukkit.getWorld("world"), 99.0, 68.0, -55.0, 90.0f, 0.0f),
            new Location(Bukkit.getWorld("world"), 97.0, 68.0, -55.0, 89.0f, 0.0f)
    ),
    TURKIJE(
            "&5[Tur]&r",
            "&5Turkije",
            new Location(Bukkit.getWorld("world"), 66.0, 68.0, -100.0, 360.0f, 0.0f),
            new Location(Bukkit.getWorld("world"), 66.0, 68.0, -98.0, 269.0f, 0.0f)
    ),
    ITALIE(
            "&2[Ita]&r",
            "&2Itali\u00eb",
            new Location(Bukkit.getWorld("world"), 26.0, 67.0, -104.0, 360.0f, 0.0f),
            new Location(Bukkit.getWorld("world"), 26.0, 69.0, -102.0, 89.0f, 0.0f)
    ),
    RUSLAND(
            "&4[Rus]&r",
            "&4Rusland",
            new Location(Bukkit.getWorld("world"), -28.0, 69.0, -105.0, 360.0f, 0.0f),
            new Location(Bukkit.getWorld("world"), -29.0, 69.0, -103.0, 89.0f, 0.0f)
    ),
    SPANJE(
            "&0[Spa]&r",
            "&0Spanje",
            new Location(Bukkit.getWorld("world"), -70.0, 69.0, -106.0, 360.0f, 0.0f),
            new Location(Bukkit.getWorld("world"), -70.0, 69.0, -104.0, 359.0f, 0.0f)
    ),
    ZWITSERLAND(
            "&c[Zwi]",
            "&cZwitserland",
            new Location(Bukkit.getWorld("world"), -106.0, 71.0, -62.0, 270.0f, 0.0f),
            new Location(Bukkit.getWorld("world"), -104.0, 71.0, -62.0, 180.0f, 0.0f)
    ),
    ENGELAND(
            "&7[Eng]&r",
            "&7Engeland",
            new Location(Bukkit.getWorld("world"), -104.0, 72.0, -28.0, 270.0f, 0.0f),
            new Location(Bukkit.getWorld("world"), -102.0, 72.0, -28.0, 180.0f, 0.0f)
    ),
    FRANKRIJK(
            "&8[Fra]&r",
            "&8Frankrijk",
            new Location(Bukkit.getWorld("world"), -101.0, 73.0, 27.0, 270.0f, 0.0f),
            new Location(Bukkit.getWorld("world"), -99.0, 73.0, 27.0, 180.0f, 0.0f)
    ),
    FINLAND(
            "&3[Fin]&r",
            "&3Finland",
            new Location(Bukkit.getWorld("world"), -98.0, 71.0, 65.0, 270.0f, 0.0f),
            new Location(Bukkit.getWorld("world"), -96.0, 71.0, 65.0, 89.0f, 0.0f)
    );

    public final String prefix;
    public final String display;
    public int kills;
    public final Set<Player> players;
    public final Location spawnLocations;
    public int nexusLives;
    public final Location nexusLocation;

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

    public static Optional<Teams> getFromLocation(Location location){
        return Arrays.stream(getValues()).filter(i -> i.nexusLocation.distance(location) <= 1).findFirst();
    }

    public static Optional<Teams> getFromPlayer(Player player){
        return Arrays.stream(getValues()).filter(i -> i.getPlayers().stream().anyMatch(y -> y.getUniqueId().equals(player.getUniqueId()))).findFirst();
    }
}
