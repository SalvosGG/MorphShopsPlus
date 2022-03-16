package gg.salvos.morphie.commands.player;

import gg.salvos.morphie.MorphShops;
import gg.salvos.morphie.util.MessagesManager;
import gg.salvos.morphie.util.playerdata.PlayerDataManager;
import org.bukkit.entity.Player;

import java.util.UUID;

public class LockCommand {

    private final MorphShops plugin;

    public LockCommand(MorphShops plugin) {
        this.plugin = plugin;
    }

    public void lockShopToggle(Player player) {
        UUID uuid = player.getUniqueId();
        if (player.hasPermission("mshops.lock")) {
            if (new PlayerDataManager(plugin).getString(uuid, "PlayerData.location") != null) {
                if (!new PlayerDataManager(plugin).getBoolean(uuid, "PlayerData.Locked")) {
                    new PlayerDataManager(plugin).setBoolean(player, uuid, "PlayerData.Locked", true);
                    player.sendMessage(MessagesManager.addColor(new MessagesManager(plugin).getMessage("Prefix") + new MessagesManager(plugin).getMessage("LockedMessage")));
                } else {
                    new PlayerDataManager(plugin).setBoolean(player, uuid, "PlayerData.Locked", false);
                    player.sendMessage(MessagesManager.addColor(new MessagesManager(plugin).getMessage("Prefix") + new MessagesManager(plugin).getMessage("UnLockedMessage")));
                }
            } else {
                player.sendMessage(MessagesManager.addColor(new MessagesManager(plugin).getMessage("ErrorPrefix") + new MessagesManager(plugin).getMessage("NoSetShop")));
            }
        } else {
            player.sendMessage(MessagesManager.addColor(new MessagesManager(plugin).getMessage("ErrorPrefix") + new MessagesManager(plugin).getMessage("NoPermission")));
        }
    }
}
