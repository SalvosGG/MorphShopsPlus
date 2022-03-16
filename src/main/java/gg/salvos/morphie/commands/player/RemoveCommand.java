package gg.salvos.morphie.commands.player;

import gg.salvos.morphie.MorphShops;
import gg.salvos.morphie.util.MessagesManager;
import gg.salvos.morphie.util.playerdata.PlayerDataManager;
import org.bukkit.entity.Player;

import java.util.UUID;

public class RemoveCommand {

    private final MorphShops plugin;

    public RemoveCommand(MorphShops plugin) {
        this.plugin = plugin;
    }

    public void removeShop(Player player) {
        UUID uuid = player.getUniqueId();
        if (player.hasPermission("morphshops.remove")) {
            if (new PlayerDataManager(plugin).getString(uuid, "PlayerData.location") != null) {
                if (this.plugin.getConfig().getBoolean("Settings.CommandConfirmation.RemoveShop")) {
                    if (!(plugin.removeshop.containsKey(player))) {
                        player.sendMessage(MessagesManager.addColor(new MessagesManager(plugin).getMessage("ErrorPrefix") + new MessagesManager(plugin).getMessage("RemoveConfirmMessage")));
                        plugin.removeshop.put(player, true);
                    } else {
                        new PlayerDataManager(plugin).removeShop(player, uuid);
                        plugin.removeshop.remove(player);
                        new PlayerDataManager(plugin).setBoolean(player, uuid, "PlayerData.Shop", false);
                        player.sendMessage(MessagesManager.addColor(new MessagesManager(plugin).getMessage("Prefix") + new MessagesManager(plugin).getMessage("RemoveMessage")));
                    }
                } else {
                    new PlayerDataManager(plugin).removeShop(player, uuid);
                    plugin.removeshop.remove(player);
                    new PlayerDataManager(plugin).setBoolean(player, uuid, "PlayerData.Shop", false);
                    player.sendMessage(MessagesManager.addColor(new MessagesManager(plugin).getMessage("Prefix") + new MessagesManager(plugin).getMessage("RemoveMessage")));
                }
            } else {
                player.sendMessage(MessagesManager.addColor(new MessagesManager(plugin).getMessage("ErrorPrefix") + new MessagesManager(plugin).getMessage("NoSetShop")));
            }
        } else {
            player.sendMessage(MessagesManager.addColor(new MessagesManager(plugin).getMessage("ErrorPrefix") + new MessagesManager(plugin).getMessage("NoPermission")));
        }
    }
}
