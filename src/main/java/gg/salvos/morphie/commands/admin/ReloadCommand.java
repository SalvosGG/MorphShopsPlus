package gg.salvos.morphie.commands.admin;

import gg.salvos.morphie.MorphShops;
import gg.salvos.morphie.util.MessagesManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class ReloadCommand {

    private MorphShops plugin;

    public ReloadCommand(MorphShops plugin) {
        this.plugin = plugin;
    }

    public void reloadPlugin(CommandSender s) {
        if (s.hasPermission("morphshops.admin") || s.hasPermission("morphshops.reload")) {
            Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("MorphShopsPlus");
            if (this.plugin != null) {
                s.sendMessage(MessagesManager.addColor(new MessagesManager(this.plugin).getMessage("Prefix") + new MessagesManager(this.plugin).getMessage("ReloadMessage")));
                this.plugin.reloadConfig();
                this.plugin.getServer().getPluginManager().disablePlugin(plugin);
                this.plugin.getServer().getPluginManager().enablePlugin(plugin);
            }
        } else {
            s.sendMessage(MessagesManager.addColor(new MessagesManager(plugin).getMessage("ErrorPrefix") + new MessagesManager(plugin).getMessage("NoPermission")));
        }
    }
}
