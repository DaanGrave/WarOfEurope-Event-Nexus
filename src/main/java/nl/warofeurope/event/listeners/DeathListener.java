package nl.warofeurope.event.listeners;

import nl.warofeurope.event.EventPlugin;
import nl.warofeurope.event.ScoreboardHandler;
import nl.warofeurope.event.Teams;
import nl.warofeurope.event.utils.runnables.SyncDelayedTask;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import static nl.warofeurope.event.utils.Colors.color;

public class DeathListener implements Listener {
    private final EventPlugin eventPlugin;

    public DeathListener(EventPlugin eventPlugin) {
        this.eventPlugin = eventPlugin;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        Player entity = event.getEntity();
        entity.setGameMode(GameMode.SPECTATOR);

        event.setKeepInventory(true);
        event.setDeathMessage(null);

        Optional<Teams> fromPlayer = Teams.getFromPlayer(entity);
        if (fromPlayer.isPresent()){
            Teams teams = fromPlayer.get();
            if (teams.nexusLives <= 0){
                teams.getPlayers().remove(entity);
                new SyncDelayedTask(1, () -> entity.setGameMode(GameMode.SPECTATOR));
            } else {
                new SyncDelayedTask(1, () -> {
                    entity.teleport(teams.spawnLocations);
                    entity.setGameMode(GameMode.SURVIVAL);
                });
            }

            if (entity.getKiller() != null){
                for (Player onlinePlayer : Bukkit.getOnlinePlayers()){
                    onlinePlayer.sendMessage(color("&c" + entity.getName() + " &7is dood gegaan door &c" + entity.getKiller().getName() + "&7."));
                }

                Optional<Teams> optionalTeams = Teams.getFromPlayer(entity.getKiller());
                optionalTeams.ifPresent(Teams::addKill);
                this.eventPlugin.scoreboardHandler.updateScoreboard();
            }
        }
    }
}
