package io.github.rudynakodach.wdid.Commands;

import io.github.rudynakodach.wdid.WDID;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class WdidCommandHandler implements CommandExecutor {

    private final JavaPlugin plugin;

    public WdidCommandHandler(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        if(!(commandSender instanceof Player)) {
            commandSender.sendMessage("This command is intended to be used by a Player!");
            return true;
        }

        Player player = (Player) commandSender;

        if(args.length < 1) {
            player.sendMessage("Available actions: toggle, enable, disable\nUsage: /wdid <action> <target?>");
            return true;
        }

        String action = args[0].trim().toLowerCase();

        // WDID actions for the current the player
        if(args.length == 1) {
            switch (action) {
                case "status":
                    if (!player.hasPermission("wdid.actions.status")) {
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
                        player.sendMessage(Component.text("You don't have permission to do that.").color(NamedTextColor.RED));
                        break;
                    }
                    boolean isEnabled = WDID.playerNotifToggleMap.get(player.getName());

                    Component message = Component.text("Death notification status: ");

                    if(isEnabled) {
                        player.sendMessage(message.append(Component.text(" enabled").color(NamedTextColor.GREEN)));
                    } else {
                        player.sendMessage(message.append(Component.text(" disabled").color(NamedTextColor.RED)));
                    }
                    break;

                case "toggle":
                    if (!player.hasPermission("wdid.actions.toggle")) {
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
                        player.sendMessage(Component.text("You don't have permission to do that.").color(NamedTextColor.RED));
                        break;
                    }

                    boolean b = !(WDID.playerNotifToggleMap.get(player.getName()));
                    WDID.playerNotifToggleMap.put(player.getName(), b);
                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 1, 20);
                    if (b) {
                        player.sendMessage(Component.text("Death notifications enabled.").color(NamedTextColor.GREEN));
                    } else {
                        player.sendMessage(Component.text("Death notifications disabled.").color(NamedTextColor.RED));
                    }
                    break;

                case "enable":
                    if (!player.hasPermission("wdid.actions.enable")) {
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
                        player.sendMessage(Component.text("You don't have permission to do that.").color(NamedTextColor.RED));
                        break;
                    }
                    WDID.playerNotifToggleMap.put(player.getName(), true);
                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 1, 20);
                    player.sendMessage(Component.text("Death notifications enabled.").color(NamedTextColor.GREEN));
                    break;

                case "disable":
                    if (!player.hasPermission("wdid.actions.disable")) {
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
                        player.sendMessage(Component.text("You don't have permission to do that.").color(NamedTextColor.RED));
                        break;
                    }
                    WDID.playerNotifToggleMap.put(player.getName(), false);
                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 1, 20);
                    player.sendMessage(Component.text("Death notifications disabled.").color(NamedTextColor.RED));
                    break;

                default:
                    player.sendMessage(Component.text("Action not found.").color(NamedTextColor.RED));
            }
        } else if(args.length == 2) { //manage other players
            Player target = plugin.getServer().getPlayer(args[1]);

            if(target == null) {
                player.sendMessage(Component.text("Player \"" + args[1] + "\" not found!").color(NamedTextColor.RED));
                return true;
            }

            switch (action) {
                case "status":
                    if (!player.hasPermission("wdid.actions.others.status")) {
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
                        player.sendMessage(Component.text("You don't have permission to do that.").color(NamedTextColor.RED));
                        break;
                    }
                    boolean isEnabled = WDID.playerNotifToggleMap.get(target.getName());

                    Component message = Component.text("Death notification status for " + target.getName() + ": ");

                    if(isEnabled) {
                        player.sendMessage(message.append(Component.text(" enabled").color(NamedTextColor.GREEN)));
                    } else {
                        player.sendMessage(message.append(Component.text(" disabled").color(NamedTextColor.RED)));
                    }
                    break;

                case "toggle":
                    if (!player.hasPermission("wdid.actions.others.toggle")) {
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
                        player.sendMessage(Component.text("You don't have permission to do that.").color(NamedTextColor.RED));
                        break;
                    }

                    boolean b = !(WDID.playerNotifToggleMap.get(player.getName()));
                    WDID.playerNotifToggleMap.put(player.getName(), b);
                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 1, 20);
                    if (b) {
                        player.sendMessage(Component.text("Death notifications enabled for " + target.getName() + ".").color(NamedTextColor.GREEN));
                    } else {
                        player.sendMessage(Component.text("Death notifications disabled for " + target.getName() + ".").color(NamedTextColor.RED));
                    }
                    break;

                case "enable":
                    if (!player.hasPermission("wdid.actions.others.enable")) {
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
                        player.sendMessage(Component.text("You don't have permission to do that.").color(NamedTextColor.RED));
                        break;
                    }
                    WDID.playerNotifToggleMap.put(target.getName(), true);
                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 1, 20);
                    player.sendMessage(Component.text("Death notifications enabled for " + target.getName() + ".").color(NamedTextColor.GREEN));
                    break;

                case "disable":
                    if (!player.hasPermission("wdid.actions.others.disable")) {
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
                        player.sendMessage(Component.text("You don't have permission to do that.").color(NamedTextColor.RED));
                        break;
                    }
                    WDID.playerNotifToggleMap.put(target.getName(), false);
                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 1, 20);
                    player.sendMessage(Component.text("Death notifications disabled for " + target.getName() + ".").color(NamedTextColor.RED));
                    break;

                default:
                    player.sendMessage(Component.text("Action not found.").color(NamedTextColor.RED));
            }
        }
        return true;
    }
}
