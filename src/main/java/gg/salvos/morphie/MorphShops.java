package gg.salvos.morphie;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import gg.salvos.morphie.commands.PlayerShop;
import gg.salvos.morphie.events.MenuEvents;
import gg.salvos.morphie.events.PlayerFileEvent;
import gg.salvos.morphie.files.Messages;
import gg.salvos.morphie.util.PlayerData.PlayerDataCleaner;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;


import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;

public class MorphShops extends JavaPlugin implements Listener {
	
	public static Economy econ = null;
	private static Permission perms = null;
	private static Chat chat = null;
	public Messages messagescfg;
	public String Version = "APLHA 1.0.0";
	
	public HashMap<Player, Boolean> setshop = new HashMap<Player, Boolean>();
	public HashMap<Player, Boolean> removeshop = new HashMap<Player, Boolean>();
	
	MorphShops plugin;
	PluginManager pm = Bukkit.getServer().getPluginManager();
	
	public void onEnable() {
		this.getCommand("ps").setExecutor(new PlayerShop(this));
		pm.registerEvents(new PlayerFileEvent(this), this);
		pm.registerEvents(new MenuEvents(this), this);
		getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾"));
		getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&3MorphShops+ &8- &a" + this.Version));
		createConfig();
		loadConfigManager();
		if (!setupEconomy()) {
			getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Economy&8: &cNot Found!"));
			getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Permissions&8: &cNot Found!"));
			getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Chat&8: &cNot Found!"));
			getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cPlugin disabled due to no Vault dependecy found!"));
			getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', " "));
			getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾"));
			getServer().getPluginManager().disablePlugin(this);
			return;
		}
		setupPermissions();
		getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&3PlayerFiles&8: &aLoaded."));
		getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Economy&8: &aVault Found."));
		getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Permissions&8: &aVault Found."));
		getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Chat&8: &aVault Found."));
		getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Plugin Status&8: &aEnabled!"));
		if (this.getConfig().getBoolean("Settings.AutoDeletePlayerFiles.Enabled")) {
			getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', " "));
			getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&3PlayerData Cleaner&8: &aChecking for old files."));
			new PlayerDataCleaner(this).cleanPlayerData();	
		}
		getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', " "));
		getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾"));
	}
	
	public void onDisable() {
		getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾"));
		getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&3MorphShopsPlus &8- &a" + this.Version));
		getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Economy&8: &cDisabled."));
		getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Permissions&8: &cDisabled."));
		getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Chat&8: &cDisabled."));
		getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Plugin Status&8: &cDisabled!"));
		getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', " "));
		getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾"));
	}
	
	private boolean setupEconomy() {
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}
		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			return false;
		}
		econ = rsp.getProvider();
		return econ != null;
	}
	
	private boolean setupPermissions() {
		RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
		perms = rsp.getProvider();
		return perms != null;
	}

	public static Economy getEconomy() {
		return econ;
	}

	public static Permission getPermissions() {
		return perms;
	}

	public static Chat getChat() {
		return chat;
	}

	private void createConfig() {
		try {
			if (!getDataFolder().exists()) {
				getDataFolder().mkdirs();
			}
			File file = new File(getDataFolder(), "config.yml");
			if (!file.exists()) {
				getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Config&8: &aGenerating."));
				getConfig().options().copyDefaults(true);
				saveDefaultConfig();
			} else {
				getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Config&8: &aLoading."));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadConfigManager() {
		this.messagescfg = new Messages();
		this.messagescfg.setup();
	}
}
