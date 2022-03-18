package gg.salvos.morphie.util;

import java.util.ArrayList;

import gg.salvos.morphie.MorphShops;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.inventory.meta.SkullMeta;

public class GUIItemManager {
	
	private MorphShops plugin;
	  
	public GUIItemManager(MorphShops plugin) {
		this.plugin = plugin;
	}
	
    public ItemStack createInventoryItem(String paramString1, int paramInt, String paramString2, ArrayList<String> paramArrayList, boolean glow) {
    	ItemStack localItemStack = new ItemStack(Material.matchMaterial(paramString1), paramInt);
    	ItemMeta localItemMeta = localItemStack.getItemMeta();
    	localItemMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
    	localItemMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_POTION_EFFECTS });
    	localItemMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_DESTROYS });
    	if (glow) {
    		localItemMeta.addEnchant(Enchantment.DURABILITY, 1, true);
    		localItemMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
    	}
    	localItemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', paramString2));
    	localItemMeta.setLore(paramArrayList);
    	localItemStack.setItemMeta(localItemMeta);
    	return localItemStack;
    }

	public ItemStack createSkullItem(Player player, OfflinePlayer oplayer, String material, int amount, String title, ArrayList<String> lore, boolean glow) {
		ItemStack localItemStack = new ItemStack(Material.matchMaterial(material), amount);
		SkullMeta skull = (SkullMeta) localItemStack.getItemMeta();
		if (player != null) {
			skull.setOwningPlayer(player);
		} else {
			skull.setOwningPlayer(oplayer);
		}
		skull.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
		skull.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_POTION_EFFECTS });
		skull.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_DESTROYS });
		if (glow) {
			skull.addEnchant(Enchantment.DURABILITY, 1, true);
			skull.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		}
		skull.setDisplayName(ChatColor.translateAlternateColorCodes('&', title));
		skull.setLore(lore);
		localItemStack.setItemMeta(skull);
		return localItemStack;
	}
    
    public ItemStack createInventoryGlassItem(String paramString1, int paramInt, String paramString2, ArrayList<String> paramArrayList, boolean paramBoolean) {
    	ItemStack localItemStack = new ItemStack(Material.matchMaterial(paramString1), paramInt);
    	ItemMeta localItemMeta = localItemStack.getItemMeta();
    	if (paramBoolean) {
    		localItemMeta.addEnchant(Enchantment.DURABILITY, 1, true);
    		localItemMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
    	}
    	localItemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', paramString2));
    	localItemMeta.setLore(paramArrayList);
    	localItemStack.setItemMeta(localItemMeta);
    	return localItemStack;
    }
}
