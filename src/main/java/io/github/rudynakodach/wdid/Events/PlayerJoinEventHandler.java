package io.github.rudynakodach.wdid.Events;

import io.github.rudynakodach.wdid.WDID;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerJoinEventHandler implements Listener {

    private final JavaPlugin plugin;

    public PlayerJoinEventHandler(JavaPlugin plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        WDID.playerNotifToggleMap.put(e.getPlayer().getName(), plugin.getConfig().getBoolean("default-value"));
    }
}
