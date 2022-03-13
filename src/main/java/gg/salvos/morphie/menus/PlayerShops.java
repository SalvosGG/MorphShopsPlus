package gg.salvos.morphie.menus;

import gg.salvos.morphie.MorphShops;
import gg.salvos.morphie.util.GUIItemManager;
import gg.salvos.morphie.util.MessagesManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class PlayerShops {
	
	private MorphShops plugin;
	  
	public PlayerShops(MorphShops plugin) {
		this.plugin = plugin;
	}
	
	public void openGUIPlayerShop(Player player) {
		Inventory Menu = Bukkit.createInventory(null, 54, ChatColor.translateAlternateColorCodes('&', new MessagesManager(plugin).getMessage("Menu.PlayerShops.Title")));
		int PageNumber = 1;
		// Menu Items >> 
		
		if (plugin.getConfig().getBoolean("Tags.Enabled")) {
			Menu.setItem(3, new GUIItemManager(plugin).createInventoryItem("KNOWLEDGE_BOOK", 1, new MessagesManager(plugin).getMessage("Menu.PlayerShops.InfoBook"), new MessagesManager(plugin).getLoreList("Menu.PlayerShops.InfoBookLore"), false));
			Menu.setItem(4, new GUIItemManager(plugin).createInventoryGlassItem("WHITE_STAINED_GLASS_PANE", 1, " ", null, false));
			Menu.setItem(5, new GUIItemManager(plugin).createInventoryItem("NAME_TAG", 1, new MessagesManager(plugin).getMessage("Menu.PlayerShops.TagFilterTitle"), new MessagesManager(plugin).replaceTagFilterLore(player.getUniqueId()), false));
		} else {
			Menu.setItem(3, new GUIItemManager(plugin).createInventoryGlassItem("WHITE_STAINED_GLASS_PANE", 1, " ", null, false));
			Menu.setItem(4, new GUIItemManager(plugin).createInventoryItem("KNOWLEDGE_BOOK", 1, new MessagesManager(plugin).getMessage("Menu.PlayerShops.InfoBook"), new MessagesManager(plugin).getLoreList("Menu.PlayerShops.InfoBookLore"), false));
			Menu.setItem(5, new GUIItemManager(plugin).createInventoryGlassItem("WHITE_STAINED_GLASS_PANE", 1, " ", null, false));
		}
		
		Menu.setItem(47, new GUIItemManager(plugin).createInventoryItem(plugin.getConfig().getString("PlayerShopMenu.NavigationItems.BackItem"), 1, new MessagesManager(plugin).getMessage("Menu.PlayerShops.PageBackTitle"), new MessagesManager(plugin).getLoreList("Menu.PlayerShops.PageBackLore"), false));
		Menu.setItem(49, new GUIItemManager(plugin).createInventoryItem(plugin.getConfig().getString("PlayerShopMenu.NavigationItems.PageItem"), 1, new MessagesManager(plugin).getMessage("Menu.PlayerShops.PageTitle").replace("PAGE", "" + PageNumber), null, false));
		Menu.setItem(51, new GUIItemManager(plugin).createInventoryItem(plugin.getConfig().getString("PlayerShopMenu.NavigationItems.NextItem"), 1, new MessagesManager(plugin).getMessage("Menu.PlayerShops.PageNextTitle"), new MessagesManager(plugin).getLoreList("Menu.PlayerShops.PageNextLore"), false));
		
		// Advert Slots >> (6 slots all with barriers when not purchased. Otherwise replaced with the players icon. Ordered first come first served.)
		
		if (plugin.getConfig().getBoolean("PlayerShopMenu.AdvertSlots.Enabled")) {
			Menu.setItem(19, new GUIItemManager(plugin).createInventoryItem(plugin.getConfig().getString("PlayerShopMenu.AdvertSlots.DefaultItem"), 1, new MessagesManager(plugin).getMessage("Menu.PlayerShops.Title"), new MessagesManager(plugin).getLoreList("Menu.PlayerShops.Warp1Lore"), false));	
			Menu.setItem(20, new GUIItemManager(plugin).createInventoryItem(plugin.getConfig().getString("PlayerShopMenu.AdvertSlots.DefaultItem"), 1, new MessagesManager(plugin).getMessage("Menu.PlayerShops.Title"), new MessagesManager(plugin).getLoreList("Menu.PlayerShops.Warp1Lore"), false));	
			Menu.setItem(21, new GUIItemManager(plugin).createInventoryItem(plugin.getConfig().getString("PlayerShopMenu.AdvertSlots.DefaultItem"), 1, new MessagesManager(plugin).getMessage("Menu.PlayerShops.Title"), new MessagesManager(plugin).getLoreList("Menu.PlayerShops.Warp1Lore"), false));	
			Menu.setItem(23, new GUIItemManager(plugin).createInventoryItem(plugin.getConfig().getString("PlayerShopMenu.AdvertSlots.DefaultItem"), 1, new MessagesManager(plugin).getMessage("Menu.PlayerShops.Title"), new MessagesManager(plugin).getLoreList("Menu.PlayerShops.Warp1Lore"), false));	
			Menu.setItem(24, new GUIItemManager(plugin).createInventoryItem(plugin.getConfig().getString("PlayerShopMenu.AdvertSlots.DefaultItem"), 1, new MessagesManager(plugin).getMessage("Menu.PlayerShops.Title"), new MessagesManager(plugin).getLoreList("Menu.PlayerShops.Warp1Lore"), false));	
			Menu.setItem(25, new GUIItemManager(plugin).createInventoryItem(plugin.getConfig().getString("PlayerShopMenu.AdvertSlots.DefaultItem"), 1, new MessagesManager(plugin).getMessage("Menu.PlayerShops.Title"), new MessagesManager(plugin).getLoreList("Menu.PlayerShops.Warp1Lore"), false));	
		} else {
			Menu.setItem(19, new GUIItemManager(plugin).createInventoryGlassItem("GRAY_STAINED_GLASS_PANE", 1, " ", null, false));
			Menu.setItem(20, new GUIItemManager(plugin).createInventoryGlassItem("GRAY_STAINED_GLASS_PANE", 1, " ", null, false));
			Menu.setItem(21, new GUIItemManager(plugin).createInventoryGlassItem("GRAY_STAINED_GLASS_PANE", 1, " ", null, false));
			Menu.setItem(23, new GUIItemManager(plugin).createInventoryGlassItem("GRAY_STAINED_GLASS_PANE", 1, " ", null, false));
			Menu.setItem(24, new GUIItemManager(plugin).createInventoryGlassItem("GRAY_STAINED_GLASS_PANE", 1, " ", null, false));
			Menu.setItem(25, new GUIItemManager(plugin).createInventoryGlassItem("GRAY_STAINED_GLASS_PANE", 1, " ", null, false));
		}
		
		// Player Shop Slots >> (9 slots filled in by time of creation? Easiest way is to make it by uuid or random each time.)
		
		Menu.setItem(27, new GUIItemManager(plugin).createInventoryItem("PLAYER_HEAD", 1, "TEST HEAD", new MessagesManager(plugin).getLoreList("Menu.CreditsLore"), false));
		Menu.setItem(28, new GUIItemManager(plugin).createInventoryItem("PLAYER_HEAD", 1, "TEST HEAD", new MessagesManager(plugin).getLoreList("Menu.CreditsLore"), false));
		Menu.setItem(29, new GUIItemManager(plugin).createInventoryItem("PLAYER_HEAD", 1, "TEST HEAD", new MessagesManager(plugin).getLoreList("Menu.CreditsLore"), false));
		Menu.setItem(30, new GUIItemManager(plugin).createInventoryItem("PLAYER_HEAD", 1, "TEST HEAD", new MessagesManager(plugin).getLoreList("Menu.CreditsLore"), false));
		Menu.setItem(31, new GUIItemManager(plugin).createInventoryItem("PLAYER_HEAD", 1, "TEST HEAD", new MessagesManager(plugin).getLoreList("Menu.CreditsLore"), false));
		Menu.setItem(32, new GUIItemManager(plugin).createInventoryItem("PLAYER_HEAD", 1, "TEST HEAD", new MessagesManager(plugin).getLoreList("Menu.CreditsLore"), false));
		Menu.setItem(33, new GUIItemManager(plugin).createInventoryItem("PLAYER_HEAD", 1, "TEST HEAD", new MessagesManager(plugin).getLoreList("Menu.CreditsLore"), false));
		Menu.setItem(34, new GUIItemManager(plugin).createInventoryItem("PLAYER_HEAD", 1, "TEST HEAD", new MessagesManager(plugin).getLoreList("Menu.CreditsLore"), false));
		Menu.setItem(35, new GUIItemManager(plugin).createInventoryItem("PLAYER_HEAD", 1, "TEST HEAD", new MessagesManager(plugin).getLoreList("Menu.CreditsLore"), false));
		
		// Glass Items >>
		
		Menu.setItem(0, new GUIItemManager(plugin).createInventoryGlassItem("CYAN_STAINED_GLASS_PANE", 1, " ", null, false));
		Menu.setItem(1, new GUIItemManager(plugin).createInventoryGlassItem("WHITE_STAINED_GLASS_PANE", 1, " ", null, false));
		Menu.setItem(2, new GUIItemManager(plugin).createInventoryGlassItem("WHITE_STAINED_GLASS_PANE", 1, " ", null, false));
		Menu.setItem(6, new GUIItemManager(plugin).createInventoryGlassItem("WHITE_STAINED_GLASS_PANE", 1, " ", null, false));
		Menu.setItem(7, new GUIItemManager(plugin).createInventoryGlassItem("WHITE_STAINED_GLASS_PANE", 1, " ", null, false));
		Menu.setItem(8, new GUIItemManager(plugin).createInventoryGlassItem("CYAN_STAINED_GLASS_PANE", 1, " ", null, false));
		Menu.setItem(9, new GUIItemManager(plugin).createInventoryGlassItem("GRAY_STAINED_GLASS_PANE", 1, " ", null, false));
		Menu.setItem(10, new GUIItemManager(plugin).createInventoryGlassItem("GRAY_STAINED_GLASS_PANE", 1, " ", null, false));
		Menu.setItem(11, new GUIItemManager(plugin).createInventoryGlassItem("GRAY_STAINED_GLASS_PANE", 1, " ", null, false));
		Menu.setItem(12, new GUIItemManager(plugin).createInventoryGlassItem("GRAY_STAINED_GLASS_PANE", 1, " ", null, false));
		Menu.setItem(13, new GUIItemManager(plugin).createInventoryGlassItem("GRAY_STAINED_GLASS_PANE", 1, " ", null, false));
		Menu.setItem(14, new GUIItemManager(plugin).createInventoryGlassItem("GRAY_STAINED_GLASS_PANE", 1, " ", null, false));
		Menu.setItem(15, new GUIItemManager(plugin).createInventoryGlassItem("GRAY_STAINED_GLASS_PANE", 1, " ", null, false));
		Menu.setItem(16, new GUIItemManager(plugin).createInventoryGlassItem("GRAY_STAINED_GLASS_PANE", 1, " ", null, false));
		Menu.setItem(17, new GUIItemManager(plugin).createInventoryGlassItem("GRAY_STAINED_GLASS_PANE", 1, " ", null, false));
		Menu.setItem(18, new GUIItemManager(plugin).createInventoryGlassItem("GRAY_STAINED_GLASS_PANE", 1, " ", null, false));
		Menu.setItem(22, new GUIItemManager(plugin).createInventoryGlassItem("GRAY_STAINED_GLASS_PANE", 1, " ", null, false));
		Menu.setItem(26, new GUIItemManager(plugin).createInventoryGlassItem("GRAY_STAINED_GLASS_PANE", 1, " ", null, false));
		Menu.setItem(36, new GUIItemManager(plugin).createInventoryGlassItem("GRAY_STAINED_GLASS_PANE", 1, " ", null, false));
		Menu.setItem(37, new GUIItemManager(plugin).createInventoryGlassItem("GRAY_STAINED_GLASS_PANE", 1, " ", null, false));
		Menu.setItem(38, new GUIItemManager(plugin).createInventoryGlassItem("GRAY_STAINED_GLASS_PANE", 1, " ", null, false));
		Menu.setItem(39, new GUIItemManager(plugin).createInventoryGlassItem("GRAY_STAINED_GLASS_PANE", 1, " ", null, false));
		Menu.setItem(40, new GUIItemManager(plugin).createInventoryGlassItem("GRAY_STAINED_GLASS_PANE", 1, " ", null, false));
		Menu.setItem(41, new GUIItemManager(plugin).createInventoryGlassItem("GRAY_STAINED_GLASS_PANE", 1, " ", null, false));
		Menu.setItem(42, new GUIItemManager(plugin).createInventoryGlassItem("GRAY_STAINED_GLASS_PANE", 1, " ", null, false));
		Menu.setItem(43, new GUIItemManager(plugin).createInventoryGlassItem("GRAY_STAINED_GLASS_PANE", 1, " ", null, false));
		Menu.setItem(44, new GUIItemManager(plugin).createInventoryGlassItem("GRAY_STAINED_GLASS_PANE", 1, " ", null, false));
		Menu.setItem(45, new GUIItemManager(plugin).createInventoryGlassItem("CYAN_STAINED_GLASS_PANE", 1, " ", null, false));
		Menu.setItem(46, new GUIItemManager(plugin).createInventoryGlassItem("WHITE_STAINED_GLASS_PANE", 1, " ", null, false));
		Menu.setItem(48, new GUIItemManager(plugin).createInventoryGlassItem("WHITE_STAINED_GLASS_PANE", 1, " ", null, false));
		Menu.setItem(50, new GUIItemManager(plugin).createInventoryGlassItem("WHITE_STAINED_GLASS_PANE", 1, " ", null, false));
		Menu.setItem(52, new GUIItemManager(plugin).createInventoryGlassItem("WHITE_STAINED_GLASS_PANE", 1, " ", null, false));
		Menu.setItem(53, new GUIItemManager(plugin).createInventoryGlassItem("CYAN_STAINED_GLASS_PANE", 1, " ", null, false));
		player.openInventory(Menu);
	}

}
