package co.wckd.lobbyessentials.command;

import co.wckd.lobbyessentials.LobbyEssentialsPlugin;
import co.wckd.lobbyessentials.lifecycle.FileLifecycle;
import me.saiintbrisson.commands.Execution;
import me.saiintbrisson.commands.annotations.Command;
import org.bukkit.Location;

import java.io.IOException;

public class LobbyEssentialsCommand {

    private final LobbyEssentialsPlugin plugin;
    private final FileLifecycle fileLifecycle;

    public LobbyEssentialsCommand(LobbyEssentialsPlugin plugin) {
        this.plugin = plugin;
        this.fileLifecycle = plugin.getFileLifecycle();
    }

    @Command(
            name = "wicekdlobbyessentials",
            aliases = "wle",
            permission = "lobbyessentials.admin",
            inGameOnly = true
    )
    public void onLobbyEssentialsCommand(Execution execution) {

        execution.sendMessage(new String[]{
                " ",
                " §3§lWICKEDLOBBYESSENTIALS §8- §fGeneral Help.",
                " ",
                " §8? §b/wle setlobby §8- §fSets the lobby.",
                " "
        });

    }

    @Command(
            name = "wicekdlobbyessentials.setlobby"
    )
    public void onSetLobbyCommand(Execution execution) throws IOException {

        Location location = execution.getPlayer().getLocation();
        plugin.getInitLifecycle().setLobbyLocation(location);

        fileLifecycle.getConfig().set("on_join.location", plugin.getAdapter().adapt(location, Location.class, String.class));
        fileLifecycle.getConfig().save(fileLifecycle.getConfigFile());

        execution.sendMessage("§aLobby successfully set.");
    }

}
