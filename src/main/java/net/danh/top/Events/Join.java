package net.danh.top.Events;

import net.danh.top.Data.Files;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (Files.getInstance().getBlocks(p) == 0 && Files.getInstance().getKill(p) == 0) {
            Files.getInstance().setBlocks(p, 0);
            Files.getInstance().setKill(p, 0);
        }
    }
}
