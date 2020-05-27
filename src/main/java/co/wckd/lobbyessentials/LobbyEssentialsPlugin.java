package co.wckd.lobbyessentials;

import co.wckd.boilerplate.adapter.Adapter;
import co.wckd.boilerplate.adapter.AdapterImpl;
import co.wckd.boilerplate.adapter.LocToStringAdapter;
import co.wckd.boilerplate.adapter.StringToLocAdapter;
import co.wckd.boilerplate.plugin.BoilerplatePlugin;
import co.wckd.lobbyessentials.command.LobbyEssentialsCommand;
import co.wckd.lobbyessentials.lifecycle.FileLifecycle;
import co.wckd.lobbyessentials.lifecycle.InitLifecycle;
import lombok.Getter;
import me.saiintbrisson.commands.CommandFrame;
import org.bukkit.Location;

@Getter
public class LobbyEssentialsPlugin extends BoilerplatePlugin {

    private final FileLifecycle fileLifecycle = lifecycle(new FileLifecycle(this), 0);
    private final InitLifecycle initLifecycle = lifecycle(new InitLifecycle(this), 1);

    private Adapter adapter;

    @Override
    public void load() {
        adapter = new AdapterImpl();
        adapter.registerAdapter(String.class, Location.class, new StringToLocAdapter());
        adapter.registerAdapter(Location.class, String.class, new LocToStringAdapter());
    }

    @Override
    public void enable() {
        registerCommands();
    }

    private void registerCommands() {
        CommandFrame commandFrame = new CommandFrame(this);
        commandFrame.setLackPermMessage("§cNo permission.");
        commandFrame.registerCommands(new LobbyEssentialsCommand(this));
    }

}
