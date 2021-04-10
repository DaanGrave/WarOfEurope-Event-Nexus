package nl.warofeurope.event;

import co.aikar.commands.*;
import nl.warofeurope.event.commands.EventCommand;
import nl.warofeurope.event.commands.TellLocationCommand;
import nl.warofeurope.event.listeners.*;
import nl.warofeurope.event.models.Game;
import nl.warofeurope.event.runnables.PlayerLocationCheckRunnable;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.stream.Collectors;

public class EventPlugin extends JavaPlugin {
    private PaperCommandManager commandManager;
    public ScoreboardHandler scoreboardHandler;
    public Game game;

    @Override
    public void onEnable() {
        this.scoreboardHandler = new ScoreboardHandler();

        this.registerListeners(
                new JoinListener(this),
                new DeathListener(this),
                new ChatListener(this),
                new BlockBreakListener(),
                new BlockPlaceListener()
        );
        this.registerCommands(
                new EventCommand(this),
                new TellLocationCommand(this)
        );

        this.game = new Game(this);

        new PlayerLocationCheckRunnable().runTaskTimerAsynchronously(this, 0L, 100L);
    }

    private void registerListeners(Listener... listeners){
        Arrays.stream(listeners).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, this));
    }

    private void registerCommands(BaseCommand... baseCommands){
        this.commandManager = new PaperCommandManager(this);

        this.commandManager.getCommandContexts().registerContext(Player.class, c -> {
            String name = c.popFirstArg();
            Player player = Bukkit.getPlayer(name);
            if (player != null){
                return player;
            } else {
                throw new InvalidCommandArgument(MessageKeys.COULD_NOT_FIND_PLAYER, "{search}", name);
            }
        });
        this.commandManager.getCommandContexts().registerContext(Material.class, c -> {
            String name = c.popFirstArg();
            try {
                return Material.valueOf(name);
            } catch (Exception e){
                throw new InvalidCommandArgument(MessageKeys.PLEASE_SPECIFY_ONE_OF, "{search}", ACFUtil.join(Arrays.stream(Material.values()).map(Material::toString).collect(Collectors.toSet())));
            }
        });

        Arrays.stream(baseCommands).forEach(baseCommand -> {
            this.commandManager.registerCommand(baseCommand);
        });
    }

    public static EventPlugin getInstance(){
        return EventPlugin.getPlugin(EventPlugin.class);
    }
}
