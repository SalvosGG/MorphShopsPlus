package gg.salvos.morphie.commands;

import gg.salvos.morphie.MorphShops;
import gg.salvos.morphie.commands.admin.ReloadCommand;
import gg.salvos.morphie.commands.player.HelpCommand;
import gg.salvos.morphie.commands.player.RemoveCommand;
import gg.salvos.morphie.commands.player.SetCommand;
import gg.salvos.morphie.menus.PlayerShops;
import gg.salvos.morphie.util.MessagesManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class CommandHandler implements CommandExecutor {

    private MorphShops plugin;

    public CommandHandler(MorphShops plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("ms") || cmd.getName().equalsIgnoreCase("ps") || cmd.getName().equalsIgnoreCase("pshop") || cmd.getName().equalsIgnoreCase("mshop")) {
            if (args.length == 0) {
                // Player Only
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    new HelpCommand(plugin).sendHelp(player);
                } else {
                    sender.sendMessage(MessagesManager.addColor(MessagesManager.getMessage("ErrorPrefix") + MessagesManager.getMessage("NotAPlayer")));
                }
                return true;
            } else if (args[0].equalsIgnoreCase("menu")) {
                // Player Only
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    new PlayerShops(plugin).openGUIPlayerShop(player);
                } else {
                    sender.sendMessage(MessagesManager.addColor(MessagesManager.getMessage("ErrorPrefix") + MessagesManager.getMessage("NotAPlayer")));
                }
                return true;
            } else if (args[0].equalsIgnoreCase("set")) {
                // Player Only
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    new SetCommand(plugin).setShop(player);
                } else {
                    sender.sendMessage(MessagesManager.addColor(MessagesManager.getMessage("ErrorPrefix") + MessagesManager.getMessage("NotAPlayer")));
                }
                return true;
            } else if (args[0].equalsIgnoreCase("remove")) {
                // Player Only
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    new RemoveCommand(plugin).removeShop(player);
                } else {
                    sender.sendMessage(MessagesManager.addColor(MessagesManager.getMessage("ErrorPrefix") + MessagesManager.getMessage("NotAPlayer")));
                }
                return true;
            } else if (args[0].equalsIgnoreCase("reload")) {
                new ReloadCommand(plugin).reloadPlugin(sender);
                return true;
            } else {
                sender.sendMessage(MessagesManager.addColor(MessagesManager.getMessage("ErrorPrefix") + MessagesManager.getMessage("NoArgs")));
                return true;
            }
        }
        return false;
    }
}