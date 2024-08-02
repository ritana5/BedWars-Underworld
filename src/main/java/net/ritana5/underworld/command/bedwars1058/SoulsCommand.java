package net.ritana5.underworld.command.bedwars1058;

import com.andrei1058.bedwars.api.arena.GameState;
import com.andrei1058.bedwars.api.language.Language;
import com.andrei1058.bedwars.arena.Arena;
import net.ritana5.underworld.Underworld;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class SoulsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {// Check if the sender is a player
        if (!(sender instanceof Player) && !sender.hasPermission("bw.admin")) {
            sender.sendMessage("This command can only be run by a player or an admin.");
            return false;
        }

        assert sender instanceof Player;
        if (!Arena.getArenaByPlayer((Player) sender).getStatus().equals(GameState.playing)) {
            return false;
        }

        // Check if the correct number of arguments are provided
        if (args.length != 3) {
            sender.sendMessage("Usage: /bw <givesouls/removesouls> <player> <amount>");
            return false;
        }

        // Get the command type and player
        String subCommand = args[0];
        Player targetPlayer = Bukkit.getPlayer(args[1]);

        // Check if the target player is online
        if (targetPlayer == null) {
            assert sender instanceof Player;
            sender.sendMessage(Language.getMsg((Player) sender, "addons.underworld.invalid-player"));
            return false;
        }

        // Parse the amount
        int amount;
        try {
            amount = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            assert sender instanceof Player;
            sender.sendMessage(Language.getMsg((Player) sender, "addons.underworld.invalid-amount"));
            return false;
        }

        // Perform the action based on the sub-command
        if (subCommand.equalsIgnoreCase("givesouls")) {
            giveSouls(targetPlayer, amount);
            sender.sendMessage("Gave " + amount + " souls " + targetPlayer.getName());
        } else if (subCommand.equalsIgnoreCase("removesouls")) {
            removeSouls(targetPlayer, amount);
            sender.sendMessage("Removed " + amount + " souls " + targetPlayer.getName());
        } else {
            assert sender instanceof Player;
            sender.sendMessage(Language.getMsg((Player) sender, "addons.underworld.unknown-command"));
            return false;
        }

        return true;
    }

    private void giveSouls(Player player, int amount) {
        JavaPlugin.getPlugin(Underworld.class).getSouls().put(player, JavaPlugin.getPlugin(Underworld.class).getSouls().get(player) + amount);
    }

    private void removeSouls(Player player, int amount) {
        JavaPlugin.getPlugin(Underworld.class).getSouls().put(player, Math.max((JavaPlugin.getPlugin(Underworld.class).getSouls().get(player) - amount), 0));
    }
}
