package io.github.rudynakodach.wdid.Events;

import io.github.rudynakodach.wdid.WDID;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PlayerDeathEventHandler implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        if(WDID.playerNotifToggleMap.get(e.getPlayer().getName())) {
            Location playerLocation = e.getPlayer().getLocation();
            String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
            String msg = "You died at X:" + (int)playerLocation.getX() + " Y: " + (int)playerLocation.getY() + " Z: " + (int)playerLocation.getZ();
            e.getPlayer().sendMessage(Component.text("[" + time + "] ").color(NamedTextColor.GRAY).decorate(TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false)
                    .append(Component.text(msg).color(NamedTextColor.WHITE)).decorate(TextDecoration.ITALIC).decoration(TextDecoration.BOLD, false));
        }
    }
}
