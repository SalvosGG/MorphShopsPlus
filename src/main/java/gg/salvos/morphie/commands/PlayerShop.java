package gg.salvos.morphie.commands;

import java.io.File;
import java.util.Arrays;
import java.util.UUID;

import gg.salvos.morphie.MorphShops;
import gg.salvos.morphie.menus.PlayerShops;
import gg.salvos.morphie.util.CheckDangers;
import gg.salvos.morphie.util.MessagesManager;
import gg.salvos.morphie.util.playerdata.PlayerDataManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

import net.md_5.bungee.api.ChatColor;

public class PlayerShop {
	
	private MorphShops plugin;
	
	public PlayerShop(MorphShops plugin) {
		this.plugin = plugin;
	}
	
//	@Override
//	public boolean onCommand(CommandSender s, Command c, String l, String[] args) {
//		if (l.equalsIgnoreCase("ps") || l.equalsIgnoreCase("pshop") || l.equalsIgnoreCase("ms") || l.equalsIgnoreCase("mshop")) {
//			if (s.hasPermission("mshops.ps")) {
//				if (args.length == 0) {
//					final Player p = (Player) s;
//					UUID uuid = p.getUniqueId();
//					p.sendMessage(" ");
//					p.sendMessage(ChatColor.translateAlternateColorCodes('&', new MessagesManager(plugin).getMessage("Commands.Header")));
//					p.sendMessage(" ");
//					p.sendMessage(ChatColor.translateAlternateColorCodes('&', new MessagesManager(plugin).getMessage("Commands.Title").replace("VERSION", plugin.Version)));
//					p.sendMessage(ChatColor.translateAlternateColorCodes('&', new MessagesManager(plugin).getMessage("Commands.Alias")));
//					p.sendMessage(" ");
//					p.sendMessage(ChatColor.translateAlternateColorCodes('&', new MessagesManager(plugin).getMessage("Commands.PsSet")));
//					p.sendMessage(ChatColor.translateAlternateColorCodes('&', new MessagesManager(plugin).getMessage("Commands.PsMenu")));
//					p.sendMessage(ChatColor.translateAlternateColorCodes('&', new MessagesManager(plugin).getMessage("Commands.PsTp")));
//					p.sendMessage(ChatColor.translateAlternateColorCodes('&', new MessagesManager(plugin).getMessage("Commands.PsPlayer")));
//					p.sendMessage(ChatColor.translateAlternateColorCodes('&', new MessagesManager(plugin).getMessage("Commands.PsLock")));
//					p.sendMessage(ChatColor.translateAlternateColorCodes('&', new MessagesManager(plugin).getMessage("Commands.PsRemove")));
//					p.sendMessage(ChatColor.translateAlternateColorCodes('&', new MessagesManager(plugin).getMessage("Commands.PsAdvertise")));
//					p.sendMessage(ChatColor.translateAlternateColorCodes('&', new MessagesManager(plugin).getMessage("Commands.PsLore")));
//					p.sendMessage(ChatColor.translateAlternateColorCodes('&', new MessagesManager(plugin).getMessage("Commands.PsLoreDelete")));
//					p.sendMessage(" ");
//					p.sendMessage(ChatColor.translateAlternateColorCodes('&', new MessagesManager(plugin).getMessage("Commands.PsReset")));
//					p.sendMessage(ChatColor.translateAlternateColorCodes('&', new MessagesManager(plugin).getMessage("Commands.PsReload")));
//					p.sendMessage(" ");
//					p.sendMessage(ChatColor.translateAlternateColorCodes('&', new MessagesManager(plugin).getMessage("Commands.Footer")));
//					p.sendMessage(" ");
//					return true;
//				} else if (args.length == 1) {
//					if (args[0].equalsIgnoreCase("set")) {
//						final Player p = (Player) s;
//						UUID uuid = p.getUniqueId();
//						if (s.hasPermission("mshops.setshop")) {
//							double bal = MorphShops.econ.getBalance(p);
//							Location loc = p.getLocation();
//							if (!new CheckDangers(plugin).checkDangers(p, loc, 3)) {
//								if (!plugin.getConfig().getBoolean("Settings.SetShopPrice.Enabled")) {
//									if (this.plugin.getConfig().getBoolean("Settings.CommandConfirmation.SetShop")) {
//										if (!(plugin.setshop.containsKey(p))) {
//											p.sendMessage(ChatColor.translateAlternateColorCodes('&', new MessagesManager(plugin).getMessage("ErrorPrefix") + new MessagesManager(plugin).getMessage("SetShop.ConfirmMessage")));
//											plugin.setshop.put(p, true);
//											BukkitScheduler schedule = Bukkit.getScheduler();
//											schedule.scheduleSyncDelayedTask(plugin, new Runnable() {
//												public void run() {
//													plugin.setshop.remove(p);
//												}
//											}, 1000L);
//											return true;
//										} else {
//											plugin.setshop.remove(p);
//											p.sendMessage(ChatColor.translateAlternateColorCodes('&', new MessagesManager(plugin).getMessage("Prefix") + new MessagesManager(plugin).getMessage("SetShop.Message")));
//
//											new PlayerDataManager(plugin).setBoolean(p, uuid, "PlayerData.Shop", true);
//											new PlayerDataManager(plugin).setDouble(p, p.getUniqueId(), "PlayerData.location.x", p.getLocation().getX());
//											new PlayerDataManager(plugin).setDouble(p, p.getUniqueId(), "PlayerData.location.y", p.getLocation().getY());
//											new PlayerDataManager(plugin).setDouble(p, p.getUniqueId(), "PlayerData.location.z", p.getLocation().getZ());
//											new PlayerDataManager(plugin).setFloat(p, p.getUniqueId(), "PlayerData.location.yaw", p.getLocation().getYaw());
//											new PlayerDataManager(plugin).setFloat(p, p.getUniqueId(), "PlayerData.location.pitch", p.getLocation().getPitch());
//											new PlayerDataManager(plugin).setString(p.getUniqueId(), "PlayerData.location.world", p.getLocation().getWorld().getName());
//											return true;
//										}
//									} else {
//										p.sendMessage(ChatColor.translateAlternateColorCodes('&', new MessagesManager(plugin).getMessage("Prefix") + new MessagesManager(plugin).getMessage("SetShop.Message")));
//
//										new PlayerDataManager(plugin).setBoolean(p, uuid, "PlayerData.Shop", true);
//										new PlayerDataManager(plugin).setDouble(p, p.getUniqueId(), "PlayerData.location.x", p.getLocation().getX());
//										new PlayerDataManager(plugin).setDouble(p, p.getUniqueId(), "PlayerData.location.y", p.getLocation().getY());
//										new PlayerDataManager(plugin).setDouble(p, p.getUniqueId(), "PlayerData.location.z", p.getLocation().getZ());
//										new PlayerDataManager(plugin).setFloat(p, p.getUniqueId(), "PlayerData.location.yaw", p.getLocation().getYaw());
//										new PlayerDataManager(plugin).setFloat(p, p.getUniqueId(), "PlayerData.location.pitch", p.getLocation().getPitch());
//										new PlayerDataManager(plugin).setString(p.getUniqueId(), "PlayerData.location.world", p.getLocation().getWorld().getName());
//										return true;
//									}
//								} else {
//									if (bal >= plugin.getConfig().getDouble("Settings.SetShopPrice.Price")) {
//										if (!(plugin.setshop.containsKey(p))) {
//											p.sendMessage(ChatColor.translateAlternateColorCodes('&', new MessagesManager(plugin).getMessage("ErrorPrefix") + new MessagesManager(plugin).getMessage("SetShop.ConfirmPriceMessage").replace("COST", this.plugin.getConfig().getString("Settings.SetShopPrice.Price"))));
//											plugin.setshop.put(p, true);
//											BukkitScheduler schedule = Bukkit.getScheduler();
//											schedule.scheduleSyncDelayedTask(plugin, new Runnable() {
//												public void run() {
//													plugin.setshop.remove(p);
//												}
//											}, 1000L);
//											return true;
//										} else {
//											plugin.setshop.remove(p);
//											MorphShops.econ.withdrawPlayer(p, plugin.getConfig().getDouble("Settings.SetShopPrice.Price"));
//											p.sendMessage(ChatColor.translateAlternateColorCodes('&', new MessagesManager(plugin).getMessage("Prefix") + new MessagesManager(plugin).getMessage("SetShop.Message")));
//
//											new PlayerDataManager(plugin).setBoolean(p, uuid, "PlayerData.Shop", true);
//											new PlayerDataManager(plugin).setDouble(p, p.getUniqueId(), "PlayerData.location.x", p.getLocation().getX());
//											new PlayerDataManager(plugin).setDouble(p, p.getUniqueId(), "PlayerData.location.y", p.getLocation().getY());
//											new PlayerDataManager(plugin).setDouble(p, p.getUniqueId(), "PlayerData.location.z", p.getLocation().getZ());
//											new PlayerDataManager(plugin).setFloat(p, p.getUniqueId(), "PlayerData.location.yaw", p.getLocation().getYaw());
//											new PlayerDataManager(plugin).setFloat(p, p.getUniqueId(), "PlayerData.location.pitch", p.getLocation().getPitch());
//											new PlayerDataManager(plugin).setString(p.getUniqueId(), "PlayerData.location.world", p.getLocation().getWorld().getName());
//											return true;
//										}
//									} else {
//										s.sendMessage(ChatColor.translateAlternateColorCodes('&', new MessagesManager(plugin).getMessage("ErrorPrefix") + new MessagesManager(plugin).getMessage("NoMoney").replaceAll("COST", plugin.getConfig().getString("Settings.SetShopPrice.Price"))));
//										return true;
//									}
//								}
//							}
//						} else {
//							s.sendMessage(ChatColor.translateAlternateColorCodes('&', new MessagesManager(plugin).getMessage("ErrorPrefix") + new MessagesManager(plugin).getMessage("NoPermission")));
//							return true;
//						}
//					} else if (args[0].equalsIgnoreCase("remove")) {
//						final Player p = (Player) s;
//						UUID uuid = p.getUniqueId();
//						if (s.hasPermission("mshops.remove")) {
//							if (new PlayerDataManager(plugin).getString(p.getUniqueId(), "PlayerData.location") != null) {
//								if (this.plugin.getConfig().getBoolean("Settings.CommandConfirmation.RemoveShop")) {
//									if (!(plugin.removeshop.containsKey(p))) {
//										p.sendMessage(ChatColor.translateAlternateColorCodes('&', new MessagesManager(plugin).getMessage("ErrorPrefix") + new MessagesManager(plugin).getMessage("RemoveConfirmMessage")));
//										plugin.removeshop.put(p, true);
//									} else {
//										new PlayerDataManager(plugin).removeShop(p, p.getUniqueId());
//										plugin.removeshop.remove(p);
//										new PlayerDataManager(plugin).setBoolean(p, uuid, "PlayerData.Shop", false);
//										p.sendMessage(ChatColor.translateAlternateColorCodes('&', new MessagesManager(plugin).getMessage("Prefix") + new MessagesManager(plugin).getMessage("RemoveMessage")));
//									}
//								} else {
//									new PlayerDataManager(plugin).removeShop(p, p.getUniqueId());
//									plugin.removeshop.remove(p);
//									new PlayerDataManager(plugin).setBoolean(p, uuid, "PlayerData.Shop", false);
//									p.sendMessage(ChatColor.translateAlternateColorCodes('&', new MessagesManager(plugin).getMessage("Prefix") + new MessagesManager(plugin).getMessage("RemoveMessage")));
//								}
//							} else {
//								p.sendMessage(ChatColor.translateAlternateColorCodes('&', new MessagesManager(plugin).getMessage("ErrorPrefix") + new MessagesManager(plugin).getMessage("NoSetShop")));
//							}
//						} else {
//							s.sendMessage(ChatColor.translateAlternateColorCodes('&', new MessagesManager(plugin).getMessage("ErrorPrefix") + new MessagesManager(plugin).getMessage("NoPermission")));
//						}
//						return true;
//					} else if (args[0].equalsIgnoreCase("lock")) {
//						final Player p = (Player) s;
//						UUID uuid = p.getUniqueId();
//						if (s.hasPermission("mshops.lock")) {
//							if (new PlayerDataManager(plugin).getString(p.getUniqueId(), "PlayerData.location") != null) {
//								if (!new PlayerDataManager(plugin).getBoolean(p.getUniqueId(), "PlayerData.Locked")) {
//									new PlayerDataManager(plugin).setBoolean(p, p.getUniqueId(), "PlayerData.Locked", true);
//									p.sendMessage(ChatColor.translateAlternateColorCodes('&', new MessagesManager(plugin).getMessage("Prefix") + new MessagesManager(plugin).getMessage("LockedMessage")));
//									return true;
//								} else {
//									new PlayerDataManager(plugin).setBoolean(p, p.getUniqueId(), "PlayerData.Locked", false);
//									p.sendMessage(ChatColor.translateAlternateColorCodes('&', new MessagesManager(plugin).getMessage("Prefix") + new MessagesManager(plugin).getMessage("UnLockedMessage")));
//									return true;
//								}
//							} else {
//								p.sendMessage(ChatColor.translateAlternateColorCodes('&', new MessagesManager(plugin).getMessage("ErrorPrefix") + new MessagesManager(plugin).getMessage("NoSetShop")));
//								return true;
//							}
//						} else {
//							s.sendMessage(ChatColor.translateAlternateColorCodes('&', new MessagesManager(plugin).getMessage("ErrorPrefix") + new MessagesManager(plugin).getMessage("NoPermission")));
//							return true;
//						}
//					} else if (args[0].equalsIgnoreCase("tp")) {
//						final Player p = (Player) s;
//						UUID uuid = p.getUniqueId();
//						if (s.hasPermission("mshops.tp")) {
//						    File file1 = new PlayerDataManager(this.plugin).getPlayerFile(p.getUniqueId());
//						    FileConfiguration playerCFG1 = YamlConfiguration.loadConfiguration(file1);
//							if (playerCFG1.contains("PlayerData.location")) {
//								Location loc = new CheckDangers(plugin).getPlayerShopLoc(p);
//								if (!new CheckDangers(plugin).checkDangers(p, loc, 2)) {
//									s.sendMessage(ChatColor.translateAlternateColorCodes('&', new MessagesManager(plugin).getMessage("Prefix") + new MessagesManager(plugin).getMessage("ToOwnShopMessage")));
//									p.teleport(loc);
//									return true;
//								} else {
//									p.sendMessage(ChatColor.translateAlternateColorCodes('&', new MessagesManager(plugin).getMessage("ErrorPrefix") + new MessagesManager(plugin).getMessage("UnsafeShop.Message")));
//									return true;
//								}
//							} else {
//								p.sendMessage(ChatColor.translateAlternateColorCodes('&', new MessagesManager(plugin).getMessage("ErrorPrefix") + new MessagesManager(plugin).getMessage("NoSetShop")));
//								return true;
//							}
//						} else {
//							s.sendMessage(ChatColor.translateAlternateColorCodes('&', new MessagesManager(plugin).getMessage("ErrorPrefix") + new MessagesManager(plugin).getMessage("NoPermission")));
//							return true;
//						}
//					} else if (args[0].equalsIgnoreCase("menu")) {
//						final Player p = (Player) s;
//						UUID uuid = p.getUniqueId();
//						if (s.hasPermission("mshops.menu")) {
//							new PlayerShops(plugin).openGUIPlayerShop(p);
//							return true;
//						} else {
//							s.sendMessage(ChatColor.translateAlternateColorCodes('&', new MessagesManager(plugin).getMessage("ErrorPrefix") + new MessagesManager(plugin).getMessage("NoPermission")));
//							return true;
//						}
//					} else if (args[0].equalsIgnoreCase("reload")) {
//						if (s.hasPermission("morphshops.admin") || s.hasPermission("morphshops.reload")) {
//							Plugin pl = Bukkit.getServer().getPluginManager().getPlugin("MorphShopsPlus");
//							if (pl != null) {
//								this.plugin.reloadConfig();
//								this.plugin.getServer().getPluginManager().disablePlugin(pl);
//								this.plugin.getServer().getPluginManager().enablePlugin(pl);
//								s.sendMessage("THIS IS A TEST");
//								return true;
//							}
//						} else {
//							s.sendMessage(ChatColor.translateAlternateColorCodes('&', new MessagesManager(plugin).getMessage("ErrorPrefix") + new MessagesManager(plugin).getMessage("NoPermission")));
//							return true;
//						}
//					} else {
//						s.sendMessage(ChatColor.translateAlternateColorCodes('&', new MessagesManager(plugin).getMessage("ErrorPrefix") + new MessagesManager(plugin).getMessage("NoArgs")));
//						return true;
//					}
//				} else {
//					s.sendMessage(ChatColor.translateAlternateColorCodes('&', new MessagesManager(plugin).getMessage("ErrorPrefix") + new MessagesManager(plugin).getMessage("NoArgs")));
//					return true;
//				}
//			} else {
//				s.sendMessage(ChatColor.translateAlternateColorCodes('&', new MessagesManager(plugin).getMessage("ErrorPrefix") + new MessagesManager(plugin).getMessage("NoArgs")));
//				return true;
//			}
//		} else {
//			s.sendMessage(ChatColor.translateAlternateColorCodes('&', new MessagesManager(plugin).getMessage("ErrorPrefix") + new MessagesManager(plugin).getMessage("NoArgs")));
//			return true;
//		}
//		return false;
//	}
}
