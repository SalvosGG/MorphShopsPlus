package gg.salvos.morphie.commands.player;

import gg.salvos.morphie.MorphShops;
import gg.salvos.morphie.util.MessagesManager;
import org.bukkit.entity.Player;

public class HelpCommand {

    private MorphShops plugin;

    public HelpCommand(MorphShops plugin) {
        this.plugin = plugin;
    }

    public void sendHelp(Player p) {
        p.sendMessage(" ");
        p.sendMessage(MessagesManager.addColor(new MessagesManager(plugin).getMessage("Commands.Header")));
        p.sendMessage(" ");
        p.sendMessage(MessagesManager.addColor(new MessagesManager(plugin).getMessage("Commands.Title").replace("VERSION", plugin.Version)));
        p.sendMessage(MessagesManager.addColor(new MessagesManager(plugin).getMessage("Commands.Alias")));
        p.sendMessage(" ");
        p.sendMessage(MessagesManager.addColor(new MessagesManager(plugin).getMessage("Commands.PsSet")));
        p.sendMessage(MessagesManager.addColor(new MessagesManager(plugin).getMessage("Commands.PsMenu")));
        p.sendMessage(MessagesManager.addColor(new MessagesManager(plugin).getMessage("Commands.PsTp")));
        p.sendMessage(MessagesManager.addColor(new MessagesManager(plugin).getMessage("Commands.PsPlayer")));
        p.sendMessage(MessagesManager.addColor(new MessagesManager(plugin).getMessage("Commands.PsLock")));
        p.sendMessage(MessagesManager.addColor(new MessagesManager(plugin).getMessage("Commands.PsRemove")));
        p.sendMessage(MessagesManager.addColor(new MessagesManager(plugin).getMessage("Commands.PsAdvertise")));
        p.sendMessage(MessagesManager.addColor(new MessagesManager(plugin).getMessage("Commands.PsLore")));
        p.sendMessage(MessagesManager.addColor(new MessagesManager(plugin).getMessage("Commands.PsLoreDelete")));
        p.sendMessage(" ");
        p.sendMessage(MessagesManager.addColor(new MessagesManager(plugin).getMessage("Commands.PsReset")));
        p.sendMessage(MessagesManager.addColor(new MessagesManager(plugin).getMessage("Commands.PsReload")));
        p.sendMessage(" ");
        p.sendMessage(MessagesManager.addColor(new MessagesManager(plugin).getMessage("Commands.Footer")));
        p.sendMessage(" ");
    }
}
