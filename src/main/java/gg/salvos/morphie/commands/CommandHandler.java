package gg.salvos.morphie.commands;

import gg.salvos.morphie.MorphShops;
import gg.salvos.morphie.commands.admin.ReloadCommand;
import gg.salvos.morphie.commands.player.*;
import gg.salvos.morphie.menus.PlayerShops;
import gg.salvos.morphie.util.MessagesManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
            } else if (args[0].equalsIgnoreCase("lock")) {
                // Player Only
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    new LockCommand(plugin).lockShopToggle(player);
                } else {
                    sender.sendMessage(MessagesManager.addColor(MessagesManager.getMessage("ErrorPrefix") + MessagesManager.getMessage("NotAPlayer")));
                }
                return true;
            } else if (args[0].equalsIgnoreCase("lore")) {
                // Player Only
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    if (args.length > 1) {
                        String lore1 = "";
                        if (args[1].equalsIgnoreCase("remove")) {
                            new LoreCommand(plugin).handleShopLore(player, "remove", null, null);
                            return true;
                        } else if (args.length > 2) {
                            if (args[1].equalsIgnoreCase("add")) {
                                for (int i = 2; i < args.length; i++) {
                                    lore1 += args[i] += " ";
                                }
                                new LoreCommand(plugin).handleShopLore(player, "add", null, lore1);
                                return true;
                            } else if (args[1].equalsIgnoreCase("set")) {
                                for (int i = 3; i < args.length; i++) {
                                    lore1 += args[i] += " ";
                                }
                                try {
                                    int line = Integer.parseInt(args[2]);
                                    new LoreCommand(plugin).handleShopLore(player,"set", line, lore1);
                                    return true;
                                } catch (NumberFormatException e) {
                                    sender.sendMessage(MessagesManager.addColor(MessagesManager.getMessage("ErrorPrefix") + MessagesManager.getMessage("NoArgs")));
                                    return true;
                                }
                            }
                        } else {
                            sender.sendMessage(MessagesManager.addColor(MessagesManager.getMessage("ErrorPrefix") + MessagesManager.getMessage("NoArgs")));
                            return true;
                        }
                    } else {
                        sender.sendMessage(MessagesManager.addColor(MessagesManager.getMessage("ErrorPrefix") + MessagesManager.getMessage("NoArgs")));
                        return true;
                    }
                } else {
                    sender.sendMessage(MessagesManager.addColor(MessagesManager.getMessage("ErrorPrefix") + MessagesManager.getMessage("NotAPlayer")));
                    return true;
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