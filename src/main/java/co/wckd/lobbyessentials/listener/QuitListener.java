package co.wckd.lobbyessentials.listener;

import co.wckd.lobbyessentials.LobbyEssentialsPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {

    private final LobbyEssentialsPlugin plugin;
    private final FileConfiguration config;

    public QuitListener(LobbyEssentialsPlugin plugin) {
        this.plugin = plugin;
        this.config = plugin.getFileLifecycle().getConfig();
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {

        if(config.getBoolean("config.on_quit.cancel_message"))
            event.setQuitMessage(null);

    }

}
