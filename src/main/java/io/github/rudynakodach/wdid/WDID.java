package io.github.rudynakodach.wdid;

import io.github.rudynakodach.wdid.Commands.WdidCommandHandler;
import io.github.rudynakodach.wdid.Events.PlayerDeathEventHandler;
import io.github.rudynakodach.wdid.Events.PlayerJoinEventHandler;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Objects;
import java.util.logging.Level;

public final class WDID extends JavaPlugin {

    public static HashMap<String, Boolean> playerNotifToggleMap = new HashMap<>();

    // Plugin startup logic
    @Override
    public void onEnable() {
        saveDefaultConfig();

        getLogger().log(Level.INFO, "So, where did I die again?");

        PlayerDeathEventHandler deathHandler = new PlayerDeathEventHandler();
        getServer().getPluginManager().registerEvents(deathHandler, this);

        PlayerJoinEventHandler joinEventHandler = new PlayerJoinEventHandler(this);
        getServer().getPluginManager().registerEvents(joinEventHandler, this);

        WdidCommandHandler commandHandler = new WdidCommandHandler(this);
        Objects.requireNonNull(getCommand("wdid")).setExecutor(commandHandler);
    }

    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, "I still don't remember where I died...");
    }
}
