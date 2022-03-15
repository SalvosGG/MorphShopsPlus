package gg.salvos.morphie.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import gg.salvos.morphie.MorphShops;
import gg.salvos.morphie.util.playerdata.PlayerDataManager;
import org.bukkit.ChatColor;

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
}
