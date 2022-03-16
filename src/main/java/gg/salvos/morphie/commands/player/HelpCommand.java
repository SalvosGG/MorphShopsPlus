package gg.salvos.morphie.commands.player;

import gg.salvos.morphie.MorphShops;
import gg.salvos.morphie.util.MessagesManager;
import org.bukkit.entity.Player;

public class HelpCommand {

    private final MorphShops plugin;

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
        p.sendMessage(MessagesManager.addColor(new MessagesManager(plugin).getMessage("Commands.PsLoreAdd")));
        p.sendMessage(MessagesManager.addColor(new MessagesManager(plugin).getMessage("Commands.PsLoreSet")));
        p.sendMessage(MessagesManager.addColor(new MessagesManager(plugin).getMessage("Commands.PsLoreRemove")));
        p.sendMessage(" ");
        p.sendMessage(MessagesManager.addColor(new MessagesManager(plugin).getMessage("Commands.PsReset")));
        p.sendMessage(MessagesManager.addColor(new MessagesManager(plugin).getMessage("Commands.PsReload")));
        p.sendMessage(" ");
        p.sendMessage(MessagesManager.addColor(new MessagesManager(plugin).getMessage("Commands.Footer")));
        p.sendMessage(" ");
    }
}
