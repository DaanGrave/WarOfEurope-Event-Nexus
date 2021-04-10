package nl.warofeurope.event.listeners;

import nl.warofeurope.event.EventPlugin;
import nl.warofeurope.event.Teams;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Optional;

import static nl.warofeurope.event.utils.Colors.color;

public class BlockBreakListener implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Location location = block.getLocation();

        Optional<Teams> fromLocation = Teams.getFromLocation(location);
        if (EventPlugin.getInstance().game.started && fromLocation.isPresent()){
            event.setCancelled(true);
            Teams teams = fromLocation.get();
            if (teams.nexusLives >= 0){
                teams.nexusLives--;
            }

            if (teams.nexusLives == 0){
                for (Player onlinePlayer : Bukkit.getOnlinePlayers()){
                    onlinePlayer.sendMessage(color("&4&l[Event] &e&l" + teams.getDisplay() + " &fzijn Nexus is kapot gegaan! Ze zullen niet meer respawnen vanaf nu."));
                }
            }

            EventPlugin.getInstance().scoreboardHandler.updateScoreboard();
        } else {
            if (!player.getGameMode().equals(GameMode.CREATIVE)){
                event.setCancelled(true);
            }
        }
    }
}
