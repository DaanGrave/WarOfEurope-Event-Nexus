package nl.warofeurope.event.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import nl.warofeurope.event.EventPlugin;
import nl.warofeurope.event.ScoreboardHandler;
import nl.warofeurope.event.Teams;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Optional;

import static nl.warofeurope.event.utils.Colors.color;

@CommandAlias("tl|telllocation")
@Description("Vertel je locatie")
public class TellLocationCommand extends BaseCommand {
    private final EventPlugin eventPlugin;

    public TellLocationCommand(EventPlugin eventPlugin) {
        this.eventPlugin = eventPlugin;
    }

    @Default
    public void tl(CommandSender sender){
        if (!(sender instanceof Player))
            return;
        Player player = (Player) sender;
        Optional<Teams> fromPlayer = Teams.getFromPlayer(player);
        if (fromPlayer.isPresent()){
            Location location = player.getLocation();
            Teams teams = fromPlayer.get();

            int x = location.getBlockX();
            int y = location.getBlockY();
            int z = location.getBlockZ();

            String message = color("&e" + player.getName() + ": " + x + ", " + y + ", " + z);

            for (Player onlinePlayer : Bukkit.getOnlinePlayers()){
                if (teams.getPlayers().contains(onlinePlayer)){
                    onlinePlayer.sendMessage(message);
                }
            }
        }
    }
}
