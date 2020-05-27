package co.wckd.lobbyessentials.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        if(!event.getPlayer().hasPermission("lobbyessentials.bypass"))
            event.setCancelled(true);

    }

}
