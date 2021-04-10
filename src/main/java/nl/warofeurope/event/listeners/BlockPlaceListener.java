package nl.warofeurope.event.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.entity.PolarBear;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event){
        Player player = event.getPlayer();
        if (!player.getGameMode().equals(GameMode.CREATIVE)){
            event.setCancelled(true);
        }
    }
}
