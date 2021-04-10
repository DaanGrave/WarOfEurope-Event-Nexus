package nl.warofeurope.event.runnables;

import nl.warofeurope.event.utils.runnables.SyncTask;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Set;
import java.util.stream.Collectors;

public class PlayerLocationCheckRunnable extends BukkitRunnable {
    private final Location centerLocation;

    public PlayerLocationCheckRunnable(){
        this.centerLocation = new Location(Bukkit.getWorld("world"), 1, 64, 1);
    }

    @Override
    public void run() {
        Set<? extends Player> players = Bukkit.getOnlinePlayers().stream().filter(i -> i.getLocation().distance(this.centerLocation) >= 300).collect(Collectors.toSet());
        for (Player player : players){
            new SyncTask(() -> player.teleport(this.centerLocation));
        }
    }
}
