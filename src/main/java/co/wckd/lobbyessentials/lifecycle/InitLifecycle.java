package co.wckd.lobbyessentials.lifecycle;

import co.wckd.boilerplate.lifecycle.Lifecycle;
import co.wckd.lobbyessentials.LobbyEssentialsPlugin;
import co.wckd.lobbyessentials.listener.JoinListener;
import co.wckd.lobbyessentials.listener.QuitListener;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;

@Getter
public class InitLifecycle extends Lifecycle {

    private final LobbyEssentialsPlugin plugin;
    private FileConfiguration config;
    @Setter
    private Location lobbyLocation;

    public InitLifecycle(LobbyEssentialsPlugin plugin) {
        this.plugin = plugin;
        this.lobbyLocation = null;
    }

    @Override
    public void enable() {

        this.config = plugin.getFileLifecycle().getConfig();
        init();

    }

    private void init() {

        for (World world : Bukkit.getWorlds()) {
            world.setDifficulty(Difficulty.valueOf(config.getString("config.difficulty").toUpperCase()));
            world.setTime(config.getLong("time.value"));
            world.setGameRuleValue("doDaylightCycle", config.getBoolean("time.lock") ? "false" : "true");
        }

        String locationString = config.getString("on_join.location");
        if(config.getBoolean("on_join.enable") && locationString != null)
            lobbyLocation = plugin.getAdapter().adapt(locationString, String.class, Location.class);

        registerListeners();

    }

    private void registerListeners() {

        PluginManager pluginManager = plugin.getServer().getPluginManager();

        if(config.getBoolean("on_join.enable"))
            pluginManager.registerEvents(new JoinListener(plugin), plugin);

        if(config.getBoolean("on_quit.enable"))
            pluginManager.registerEvents(new QuitListener(plugin), plugin);

        if(config.getBoolean("on_damage.cancel"))
            pluginManager.registerEvents(new QuitListener(plugin), plugin);

    }

}
