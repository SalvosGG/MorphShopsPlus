package gg.salvos.morphie.files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import gg.salvos.morphie.MorphShops;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Messages {
	
	private MorphShops plugin = (MorphShops)MorphShops.getPlugin(MorphShops.class);
	public FileConfiguration messagesCFG;
	public File messagesFile;
	  
	public void setup() {
		if (!this.plugin.getDataFolder().exists()) {
			this.plugin.getDataFolder().mkdir();
		}
		this.messagesFile = new File(this.plugin.getDataFolder(), "messages.yml");
		if (!this.messagesFile.exists()) {
			try {
				this.messagesFile.createNewFile();
				
				this.messagesCFG = YamlConfiguration.loadConfiguration(this.messagesFile);
	        
				this.messagesCFG.addDefault("Prefix", "&8[&a&l✔&8] &9&lMorphShops&a+ &8&l➙ ");
				this.messagesCFG.addDefault("ErrorPrefix", "&8[&c&l✕&8] &9&lMorphShops&a+ &8&l➙ ");
				
				this.messagesCFG.addDefault("NoPermission", "&7Invalid permissions!");
				this.messagesCFG.addDefault("NoPlayer", "&7Player does not exist!");
				this.messagesCFG.addDefault("NoSetShop", "&7You haven't set a shop yet! &8(&a/ps set&8)");
				this.messagesCFG.addDefault("NoPlayerSetShop", "&7This player hasn't set a shop yet!");
				this.messagesCFG.addDefault("NoMoney", "&7Invalid funds! &8(&9Cost&8: &aCOST&8)");
				this.messagesCFG.addDefault("NoArgs", "&7Invalid args. Use &a/ps &7for commands!");
				this.messagesCFG.addDefault("NotAPlayer", "&7only players can run this command!");
				this.messagesCFG.addDefault("PlayerNameChange", "&7Player shop cannot be found! Name change?");
				this.messagesCFG.addDefault("AddLore", "&7Successfully added LORE.");
				this.messagesCFG.addDefault("FullLore", "&7All lore lines have been filled. Try &a/ps lore set <num> <lore>");
				this.messagesCFG.addDefault("SetLore", "&7Successfully set LORE &7to line &aLINE.");
				this.messagesCFG.addDefault("RemoveLore", "&7Successfully removed all lore lines.");
				
				this.messagesCFG.addDefault("Commands.Header", "&7&m--&8&m-&7&m----------------------------------------------&8&m-&7&m--");
				this.messagesCFG.addDefault("Commands.Title", "&9&lMorphShops&a+ &8» &aVERSION");
				this.messagesCFG.addDefault("Commands.Alias", "&9&lAliases&8: &aps, pshop, ms, mshop");
				this.messagesCFG.addDefault("Commands.Ps", "&8» &a/ps &8- &7Shows this text menu.");
				this.messagesCFG.addDefault("Commands.PsSet", "&8» &a/ps set &8- &7Sets your player shop at your current location.");
				this.messagesCFG.addDefault("Commands.PsMenu", "&8» &a/ps menu &8 - &7Opens the shop advertisement menu.");
				this.messagesCFG.addDefault("Commands.PsTp", "&8» &a/ps tp &8- &7Teleports you to your shop. &8(&7If one is set.&8)");
				this.messagesCFG.addDefault("Commands.PsPlayer", "&8» &a/ps <player> &8- &7Teleports you to another player's shop. &8(&7If one is set.&8)");
				this.messagesCFG.addDefault("Commands.PsLock", "&8» &a/ps lock &8- &7Locks OR unlocks your shop. &8(&7Toggle&8)");
				this.messagesCFG.addDefault("Commands.PsRemove", "&8» &a/ps remove &8- &7Deletes your shop &bAND &7advertisement!");
				this.messagesCFG.addDefault("Commands.PsAdvertise", "&8» &a/ps advertise &8- &7Buy an advert spot for a set cost.");
				this.messagesCFG.addDefault("Commands.PsLoreAdd", "&8» &a/ps lore add <lore> &8- &7Adds a line of lore to your shop in numerical order. &8(&7Only 1-3!&8)");
				this.messagesCFG.addDefault("Commands.PsLoreSet", "&8» &a/ps lore set <linenumber> <lore> &8- &7Set a specific line of lore on your shop. &8(&7Only 1-3!&8)");
				this.messagesCFG.addDefault("Commands.PsLoreRemove", "&8» &a/ps lore remove &8- &7Remove all lines of lore on your shop.");
				this.messagesCFG.addDefault("Commands.PsReset", "&8» &9/ps reset &8- &7Resets the advert list!");
				this.messagesCFG.addDefault("Commands.PsReload", "&8» &9/ps reload &8- &7Reloads all plugin files!");
				this.messagesCFG.addDefault("Commands.Footer", "&7&m--&8&m-&7&m----------------------------------------------&8&m-&7&m--");
				
				this.messagesCFG.addDefault("LockedShopMessage", "&7This user's shop is currently locked!");
				this.messagesCFG.addDefault("LockedMessage", "&7Shop has been locked, use &a/ps lock &7to unlock!");
				this.messagesCFG.addDefault("UnLockedMessage", "&7Shop has been unlocked, use &a/ps lock &7to lock!");
				
				List<String> list = new ArrayList<String>();
				list.add("&9&lVersion&8: &7VERSION");
				list.add("&bCode Contributors&8:");
				list.add("&8- &7Morphie");
				list.add("&8- &7Teleports");
				list.add("&b&oClick for spigot link!");
				
				List<String> list2 = new ArrayList<String>();
				list2.add(" ");
				list2.add("&7Below you can click on a");
				list2.add("&7player shop to be teleported");
				list2.add("&7to the shops location. You can");
				list2.add("&7also use the arrows below to");
				list2.add("&7navigate the shops GUI.");
				list2.add(" ");
				
				List<String> list3 = new ArrayList<String>();
				list3.add(" ");
				list3.add("&8« &7PREV_TAG &8| &fCURRENT_TAG &8| &7NEXT_TAG &8»");
				list3.add(" ");
				list3.add("&7Click to cycle through available tags!");
				list3.add(" ");
				
				List<String> list4 = new ArrayList<String>();
				list4.add(" ");
				list4.add("&7Click to go back one page!");
				list4.add(" ");
				
				List<String> list5 = new ArrayList<String>();
				list5.add(" ");
				list5.add("&7Click to go to the next page!");
				list5.add(" ");

				List<String> list6 = new ArrayList<String>();
				list6.add(" ");
				list6.add("&7Locked&8: LOCK_STATUS");
				list6.add("&7Tags&8: PLAYER_TAGS");
				list6.add(" ");
				
				this.messagesCFG.addDefault("Menu.PlayerShops.Title", "&8» &9&lMorphShops&8: &aPlayer Shops &8«");
				this.messagesCFG.addDefault("Menu.PlayerShops.ShopTitle", "&9&lPLAYER's &ashop");
				this.messagesCFG.addDefault("Menu.PlayerShops.InfoBook", "&9&lInformation&8:");
				this.messagesCFG.addDefault("Menu.PlayerShops.InfoBookLore", list2);
				this.messagesCFG.addDefault("Menu.PlayerShops.TagFilterTitle", "&9&lTag Filter&8:");
				this.messagesCFG.addDefault("Menu.PlayerShops.TagFilterLore", list3);
				this.messagesCFG.addDefault("Menu.PlayerShops.PageBackTitle", "&9&lPrevious Page&8:");
				this.messagesCFG.addDefault("Menu.PlayerShops.PageBackLore", list4);
				this.messagesCFG.addDefault("Menu.PlayerShops.PageTitle", "&9&lCurrent Page&8: &aPAGE");
				this.messagesCFG.addDefault("Menu.PlayerShops.PageNextTitle", "&9&lNext Page&8:");
				this.messagesCFG.addDefault("Menu.PlayerShops.PageNextLore", list5);
				this.messagesCFG.addDefault("Menu.PlayerShops.DefaultShopLore", list6);
				this.messagesCFG.addDefault("Menu.PlayerShops.LockedShop", "&8[&c&l✕&8] &7Shop currently locked.");
				this.messagesCFG.addDefault("Menu.CreditsTitle", "&9&lCredits&8:");
				this.messagesCFG.addDefault("Menu.CreditsLore", list);
				
				this.messagesCFG.addDefault("ReloadMessage", "&7Plugin files successfully reloaded.");
				this.messagesCFG.addDefault("ReloadArgs", "&7Invalid arguments! &8(&a/ps reload&8)");
				
				this.messagesCFG.addDefault("RemoveConfirmMessage", "&7Type &a/ps remove &7again to confirm! This will &adelete &7your advertisement &aAND &7playershop.");
				this.messagesCFG.addDefault("RemoveMessage", "&7Your shop has been successfully removed!");
				
				this.messagesCFG.addDefault("SetShop.Message", "&7Your shop has been successfully created!");
				this.messagesCFG.addDefault("SetShop.ConfirmMessage", "&7Type &a/ps set &7again to confirm!");
				this.messagesCFG.addDefault("SetShop.ConfirmPriceMessage", "&7Type &a/ps set &7again to confirm! This will cost&8: &aCOST");
				
				this.messagesCFG.addDefault("ToShopMessage", "&7Teleporting to &aPLAYER's &7shop.");
				this.messagesCFG.addDefault("ToOwnShopMessage", "&7Teleporting to &ayour &7shop.");
				
				this.messagesCFG.addDefault("UnsafeShop.LavaNearMessage", "&7Unsafe shop location! &8(&aTo close to lava.&8)");
				this.messagesCFG.addDefault("UnsafeShop.FloatingBlockMessage", "&7Unsafe shop location! &8(&aOn a floating block.&8)");
	        
				this.messagesCFG.options().copyDefaults(true);
				saveMessages();
			}
			catch (IOException e) {
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Could not create the messages.yml file");
			}
		}
	    this.messagesCFG = YamlConfiguration.loadConfiguration(this.messagesFile);
	}
	  
	public void saveMessages() {
		try {
			this.messagesCFG.save(this.messagesFile);
	    }
	    catch (IOException e) {
	    	Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Could not save the messages.yml file");
	    }
	}
	  
	public void reloadMessages() {
		this.messagesCFG = YamlConfiguration.loadConfiguration(this.messagesFile);
	}

}
