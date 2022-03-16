package gg.salvos.morphie.commands.player;

import gg.salvos.morphie.MorphShops;
import gg.salvos.morphie.util.MessagesManager;
import gg.salvos.morphie.util.playerdata.PlayerDataManager;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LoreCommand {

    private final MorphShops plugin;

    public LoreCommand(MorphShops plugin) {
        this.plugin = plugin;
    }

    public void handleShopLore(Player player, String type, Integer line, String args) {
        List<String> playersLore = new PlayerDataManager(plugin).getStringList(player.getUniqueId(), "PlayerData.Lore");
        Boolean hasShop = new PlayerDataManager(plugin).getBoolean(player.getUniqueId(), "PlayerData.Shop");
        if (hasShop) {
            if (type.equalsIgnoreCase("add")) {
                if (player.hasPermission("morphshops.lore.add")) {
                    if (playersLore.size() <= 2) {
                        new PlayerDataManager(plugin).addLore(player, player.getUniqueId(), "PlayerData.Lore", args);
                        player.sendMessage(MessagesManager.addColor(new MessagesManager(plugin).getMessage("Prefix") + new MessagesManager(plugin).getMessage("AddLore").replace("LORE", args)));
                    } else {
                        player.sendMessage(MessagesManager.addColor(new MessagesManager(plugin).getMessage("ErrorPrefix") + new MessagesManager(plugin).getMessage("FullLore")));
                    }
                } else {
                    player.sendMessage(MessagesManager.addColor(new MessagesManager(plugin).getMessage("ErrorPrefix") + new MessagesManager(plugin).getMessage("NoPermission")));
                }
            } else if (type.equalsIgnoreCase("set")) {
                List<Integer> list = new ArrayList<Integer>();
                Collections.addAll(list, 1, 2, 3);
                if (list.contains(line)) {
                    List<String> currentLore = new PlayerDataManager(plugin).getStringList(player.getUniqueId(), "PlayerData.Lore");
                    if (currentLore.isEmpty()) {
                        List<String> createLore = new ArrayList<>();
                        createLore.add("&7");
                        createLore.add("&7");
                        createLore.add("&7");
                        createLore.set(line-1, args);
                        new PlayerDataManager(plugin).setLore(player, player.getUniqueId(), "PlayerData.Lore", createLore);
                        player.sendMessage(MessagesManager.addColor(new MessagesManager(plugin).getMessage("Prefix") + new MessagesManager(plugin).getMessage("SetLore").replace("LORE", args).replace("LINE", line.toString())));
                    } else {
                        new PlayerDataManager(plugin).setLoreLine(player, player.getUniqueId(), "PlayerData.Lore", args, line);
                        player.sendMessage(MessagesManager.addColor(new MessagesManager(plugin).getMessage("Prefix") + new MessagesManager(plugin).getMessage("SetLore").replace("LORE", args).replace("LINE", line.toString())));
                    }
                } else {
                    player.sendMessage(MessagesManager.addColor(MessagesManager.getMessage("ErrorPrefix") + MessagesManager.getMessage("NoArgs")));
                }
            } else if (type.equalsIgnoreCase("remove")) {
                if (player.hasPermission("morphshops.lore.remove")) {
                    new PlayerDataManager(plugin).setLore(player, player.getUniqueId(),"PlayerData.Lore", null);
                    player.sendMessage(MessagesManager.addColor(new MessagesManager(plugin).getMessage("Prefix") + new MessagesManager(plugin).getMessage("RemoveLore")));
                } else {
                    player.sendMessage(MessagesManager.addColor(new MessagesManager(plugin).getMessage("ErrorPrefix") + new MessagesManager(plugin).getMessage("NoPermission")));
                }
            }
        } else {
            player.sendMessage(MessagesManager.addColor(new MessagesManager(plugin).getMessage("ErrorPrefix") + new MessagesManager(plugin).getMessage("NoSetShop")));
        }
    }
}
