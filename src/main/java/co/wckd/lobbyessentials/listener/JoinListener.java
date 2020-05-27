package co.wckd.lobbyessentials.listener;

import co.wckd.lobbyessentials.LobbyEssentialsPlugin;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    private final LobbyEssentialsPlugin plugin;
    private final FileConfiguration config;

    public JoinListener(LobbyEssentialsPlugin plugin) {
        this.plugin = plugin;
        this.config = plugin.getFileLifecycle().getConfig();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();
        player.setMaxHealth(config.getDouble("config.on_join.set_life"));
        player.setHealth(config.getDouble("config.on_join.set_life"));

        if(config.getBoolean("config.on_join.cancel_message"))
            event.setJoinMessage(null);

        Location lobbyLocation = plugin.getInitLifecycle().getLobbyLocation();
        if(lobbyLocation == null) lobbyLocation = player.getWorld().getSpawnLocation();
        player.teleport(lobbyLocation);

    }

}
