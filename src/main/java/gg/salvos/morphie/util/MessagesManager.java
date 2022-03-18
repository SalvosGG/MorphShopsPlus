package gg.salvos.morphie.util;

import gg.salvos.morphie.MorphShops;
import gg.salvos.morphie.util.playerdata.PlayerDataManager;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MessagesManager {
	
	private static MorphShops plugin;
	
	public MessagesManager(MorphShops plugin) {
		MessagesManager.plugin = plugin;
	}

	// Return a string from the messages.yml
	public static String getMessage(String string) {
		return plugin.messagescfg.messagesCFG.getString(string);
	}

	// Return a String List from the messages.yml
	public List<String> getMessageList(String string) {
		return plugin.messagescfg.messagesCFG.getStringList(string);
	}

	// Return Arraylist from messages.yml
	public ArrayList<String> getLoreList(String string) {
		ArrayList<String> Lore = new ArrayList();
	    for (String s : this.getMessageList(string)) {
	    	Lore.add(ChatColor.translateAlternateColorCodes('&', s));
	    }
		return Lore;
	}

	// TagManager Lore : /ps menu
	public ArrayList<String> replaceTagFilterLore(UUID uuid) {
		ArrayList<String> newLore = new ArrayList<>();
		List<String> customTags = new TagManager(plugin).getCustomTags();
		String defaultTag = new TagManager(plugin).getDefaultTag();
		if (!customTags.contains(new PlayerDataManager(plugin).getString(uuid, "PlayerData.CurrentTag")) && !defaultTag.equalsIgnoreCase(new PlayerDataManager(plugin).getString(uuid, "PlayerData.CurrentTag"))) {
			new PlayerDataManager(plugin).setString(uuid, "PlayerData.CurrentTag", defaultTag);
		}
		return this.getStrings(uuid, newLore);
	}

	// TagManager Lore : /ps menu
	private ArrayList<String> getStrings(UUID uuid, ArrayList<String> newLore) {
		for (String s : this.getMessageList("Menu.PlayerShops.TagFilterLore")) {
			newLore.add(ChatColor.translateAlternateColorCodes('&',s.replace("PREV_TAG","&o" + new TagManager(plugin).getPrevTag(uuid)).replace("CURRENT_TAG", "&l" + new PlayerDataManager(plugin).getString(uuid, "PlayerData.CurrentTag")).replace("NEXT_TAG","&o" +new TagManager(plugin).getNextTag(uuid))));
		}
		return newLore;
	}

	// Colorize strings containing minecraft color codes
	public static String addColor(String message) {
		if (message == null) {
			return null;
		}
		return ChatColor.translateAlternateColorCodes('&', message);
	}

	// Get the default lore of player shops + add any lore the player adds.
	public ArrayList<String> getLore(Player player, OfflinePlayer oplayer) {
		List<String> lore = new MessagesManager(plugin).getLoreList("Menu.PlayerShops.DefaultShopLore");
		if (player != null) {
			List<String> playerLore = new PlayerDataManager(plugin).getStringList(player.getUniqueId(), "PlayerData.Lore");
			List<String> tags = new PlayerDataManager(plugin).getStringList(player.getUniqueId(), "Tags");
			Boolean locked = new PlayerDataManager(plugin).getBoolean(player.getUniqueId(), "PlayerData.Locked");
			if (locked) {
				return getPlayerLore(player, null,"&aTrue", lore, tags);
			} else {
				return getPlayerLore(player, null,"&cFalse", lore, tags);
			}
		} else {
			List<String> playerLore = new PlayerDataManager(plugin).getStringList(oplayer.getUniqueId(), "PlayerData.Lore");
			List<String> tags = new PlayerDataManager(plugin).getStringList(oplayer.getUniqueId(), "Tags");
			Boolean locked = new PlayerDataManager(plugin).getBoolean(oplayer.getUniqueId(), "PlayerData.Locked");
			if (locked) {
				return getPlayerLore(null, oplayer, "&aTrue", lore, tags);
			} else {
				return getPlayerLore(null, oplayer, "&cFalse", lore, tags);
			}
		}
	}

	// Replace and add player lore if applicable.
	private ArrayList<String> getPlayerLore(Player player, OfflinePlayer oplayer, String lock, List<String> lore, List<String> tags) {
		ArrayList<String> newLore = new ArrayList<>();
		for(String s : lore) {
			if (!tags.isEmpty()) {
				newLore.add(MessagesManager.addColor(s.replace("LOCK_STATUS", lock).replace("PLAYER_TAGS", tags.toString())));
			} else {
				newLore.add(MessagesManager.addColor(s.replace("LOCK_STATUS", lock).replace("PLAYER_TAGS", plugin.getConfig().getString("Tags.DefaultTag"))));
			}
		}
		if (player != null) {
			if (new PlayerDataManager(plugin).getStringList(player.getUniqueId(), "PlayerData.Lore")!=null) {
				List<String> playerLore = new PlayerDataManager(plugin).getStringList(player.getUniqueId(), "PlayerData.Lore");
				for (String s : playerLore) {
					newLore.add(MessagesManager.addColor(s));
				}
			}
		} else {
			if (new PlayerDataManager(plugin).getStringList(oplayer.getUniqueId(), "PlayerData.Lore")!=null) {
				List<String> playerLore = new PlayerDataManager(plugin).getStringList(oplayer.getUniqueId(), "PlayerData.Lore");
				for (String s : playerLore) {
					newLore.add(MessagesManager.addColor(s));
				}
			}
		}
		return newLore;
	}
}
