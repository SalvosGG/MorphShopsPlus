package gg.salvos.morphie.commands.player;

import gg.salvos.morphie.MorphShops;
import gg.salvos.morphie.util.CheckDangers;
import gg.salvos.morphie.util.MessagesManager;
import gg.salvos.morphie.util.playerdata.PlayerDataManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.UUID;

public class SetCommand {

    private MorphShops plugin;

    public SetCommand(MorphShops plugin) {
        this.plugin = plugin;
    }

    public void setShop(Player player) {
        UUID uuid = player.getUniqueId();
        if (player.hasPermission("morphshops.setshop")) {
            double bal = MorphShops.econ.getBalance(player);
		    Location loc = player.getLocation();
            if (!new CheckDangers(plugin).checkDangers(player, loc, 3)) {
                if (!plugin.getConfig().getBoolean("Settings.SetShopPrice.Enabled")) {
                    if (this.plugin.getConfig().getBoolean("Settings.CommandConfirmation.SetShop")) {
                        if (!(plugin.setshop.containsKey(player))) {
                            player.sendMessage(MessagesManager.addColor(new MessagesManager(plugin).getMessage("ErrorPrefix") + new MessagesManager(plugin).getMessage("SetShop.ConfirmMessage")));
                            plugin.setshop.put(player, true);
                            BukkitScheduler schedule = Bukkit.getScheduler();
                            schedule.scheduleSyncDelayedTask(plugin, new Runnable() {
                                public void run() {
                                    plugin.setshop.remove(player);
                                }
                            }, 1000L);
                        } else {
                            plugin.setshop.remove(player);
                            player.sendMessage(MessagesManager.addColor(new MessagesManager(plugin).getMessage("Prefix") + new MessagesManager(plugin).getMessage("SetShop.Message")));

                            new PlayerDataManager(plugin).setBoolean(player, uuid, "PlayerData.Shop", true);
                            new PlayerDataManager(plugin).setDouble(player, uuid, "PlayerData.location.x", player.getLocation().getX());
                            new PlayerDataManager(plugin).setDouble(player, uuid, "PlayerData.location.y", player.getLocation().getY());
                            new PlayerDataManager(plugin).setDouble(player, uuid, "PlayerData.location.z", player.getLocation().getZ());
                            new PlayerDataManager(plugin).setFloat(player, uuid, "PlayerData.location.yaw", player.getLocation().getYaw());
                            new PlayerDataManager(plugin).setFloat(player, uuid, "PlayerData.location.pitch", player.getLocation().getPitch());
                            new PlayerDataManager(plugin).setString(uuid, "PlayerData.location.world", player.getLocation().getWorld().getName());
                        }
                    } else {
                        player.sendMessage(MessagesManager.addColor(new MessagesManager(plugin).getMessage("Prefix") + new MessagesManager(plugin).getMessage("SetShop.Message")));

                        new PlayerDataManager(plugin).setBoolean(player, uuid, "PlayerData.Shop", true);
                        new PlayerDataManager(plugin).setDouble(player, player.getUniqueId(), "PlayerData.location.x", player.getLocation().getX());
                        new PlayerDataManager(plugin).setDouble(player, player.getUniqueId(), "PlayerData.location.y", player.getLocation().getY());
                        new PlayerDataManager(plugin).setDouble(player, player.getUniqueId(), "PlayerData.location.z", player.getLocation().getZ());
                        new PlayerDataManager(plugin).setFloat(player, player.getUniqueId(), "PlayerData.location.yaw", player.getLocation().getYaw());
                        new PlayerDataManager(plugin).setFloat(player, player.getUniqueId(), "PlayerData.location.pitch", player.getLocation().getPitch());
                        new PlayerDataManager(plugin).setString(player.getUniqueId(), "PlayerData.location.world", player.getLocation().getWorld().getName());
                    }
                } else {
                    if (bal >= plugin.getConfig().getDouble("Settings.SetShopPrice.Price")) {
                        if (!(plugin.setshop.containsKey(player))) {
                            player.sendMessage(MessagesManager.addColor(new MessagesManager(plugin).getMessage("ErrorPrefix") + new MessagesManager(plugin).getMessage("SetShop.ConfirmPriceMessage").replace("COST", this.plugin.getConfig().getString("Settings.SetShopPrice.Price"))));
                            plugin.setshop.put(player, true);
                            BukkitScheduler schedule = Bukkit.getScheduler();
                            schedule.scheduleSyncDelayedTask(plugin, new Runnable() {
                                public void run() {
                                    plugin.setshop.remove(player);
                                }
                            }, 1000L);
                        } else {
                            plugin.setshop.remove(player);
                            MorphShops.econ.withdrawPlayer(player, plugin.getConfig().getDouble("Settings.SetShopPrice.Price"));
                            player.sendMessage(MessagesManager.addColor(new MessagesManager(plugin).getMessage("Prefix") + new MessagesManager(plugin).getMessage("SetShop.Message")));
    
                            new PlayerDataManager(plugin).setBoolean(player, uuid, "PlayerData.Shop", true);
                            new PlayerDataManager(plugin).setDouble(player, player.getUniqueId(), "PlayerData.location.x", player.getLocation().getX());
                            new PlayerDataManager(plugin).setDouble(player, player.getUniqueId(), "PlayerData.location.y", player.getLocation().getY());
                            new PlayerDataManager(plugin).setDouble(player, player.getUniqueId(), "PlayerData.location.z", player.getLocation().getZ());
                            new PlayerDataManager(plugin).setFloat(player, player.getUniqueId(), "PlayerData.location.yaw", player.getLocation().getYaw());
                            new PlayerDataManager(plugin).setFloat(player, player.getUniqueId(), "PlayerData.location.pitch", player.getLocation().getPitch());
                            new PlayerDataManager(plugin).setString(player.getUniqueId(), "PlayerData.location.world", player.getLocation().getWorld().getName());
                        }
                    } else {
                        player.sendMessage(MessagesManager.addColor(new MessagesManager(plugin).getMessage("ErrorPrefix") + new MessagesManager(plugin).getMessage("NoMoney").replaceAll("COST", plugin.getConfig().getString("Settings.SetShopPrice.Price"))));
                    } 
                }
            }
        } else {
            player.sendMessage(MessagesManager.addColor(new MessagesManager(plugin).getMessage("ErrorPrefix") + new MessagesManager(plugin).getMessage("NoPermission")));
        }
    }
}
