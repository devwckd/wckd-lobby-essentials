package co.wckd.lobbyessentials.listener;

import co.wckd.lobbyessentials.LobbyEssentialsPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageListener implements Listener {

    private final LobbyEssentialsPlugin plugin;
    private final FileConfiguration config;

    public DamageListener(LobbyEssentialsPlugin plugin) {
        this.plugin = plugin;
        this.config = plugin.getFileLifecycle().getConfig();
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {

        if (!(event.getEntity() instanceof Player))
            return;

        event.setCancelled(true);
        event.setDamage(0);

    }

}
