package co.wckd.lobbyessentials;

import co.wckd.boilerplate.plugin.BoilerplatePlugin;
import co.wckd.lobbyessentials.lifecycle.FileLifecycle;
import lombok.Getter;

@Getter
public class LobbyEssentialsPlugin extends BoilerplatePlugin {

    private final FileLifecycle fileLifecycle = lifecycle(new FileLifecycle(this), 0);

    @Override
    public void enable() {
        
    }

}
