package gg.salvos.morphie.util;

import java.util.ArrayList;
import java.util.List;

import gg.salvos.morphie.MorphShops;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import gg.salvos.morphie.util.playerdata.PlayerDataManager;

public class CheckDangers {
	
	private MorphShops plugin;
	
	public CheckDangers(MorphShops plugin) {
		this.plugin = plugin;
	}
	
	public static List<Material> getNearbyBlocks(Location location, int radius) {
		List<Material> blocks = new ArrayList<Material>();
		for (int x = location.getBlockX() - radius; x <= location.getBlockX() + radius; x++) {
			for (int y = location.getBlockY() - radius; y <= location.getBlockY() + radius; y++) {
				for (int z = location.getBlockZ() - radius; z <= location.getBlockZ() + radius; z++) {
					blocks.add(location.getWorld().getBlockAt(x, y, z).getType());
				}
			}
		}
		return blocks;
	}

	public boolean checkDangers(Player p, Location loc, int radius) {
		boolean bool = true;
		List<Material> blocks = getNearbyBlocks(loc, radius);
		if (blocks.contains(Material.LAVA)) {
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', new MessagesManager(plugin).getMessage("ErrorPrefix") + new MessagesManager(plugin).getMessage("UnsafeShop.LavaNearMessage")));
		} else if (loc.getBlock().getRelative(BlockFace.DOWN).getType().equals(Material.AIR)) {
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', new MessagesManager(plugin).getMessage("ErrorPrefix") + new MessagesManager(plugin).getMessage("UnsafeShop.FloatingBlockMessage")));
		} else {
			bool = false;
		}
		return bool;
	}
	
	public Location getPlayerShopLoc(Player p) {
		double x = new PlayerDataManager(plugin).getDouble(p.getUniqueId(), "PlayerData.location.x");
		double y = new PlayerDataManager(plugin).getDouble(p.getUniqueId(), "PlayerData.location.y");
		double z = new PlayerDataManager(plugin).getDouble(p.getUniqueId(), "PlayerData.location.z");
		String w = new PlayerDataManager(plugin).getString(p.getUniqueId(), "PlayerData.location.world");
		float yaw = new PlayerDataManager(plugin).getFloat(p.getUniqueId(), "PlayerData.location.yaw");
		float pitch = new PlayerDataManager(plugin).getFloat(p.getUniqueId(), "PlayerData.location.pitch");

		Location loc = new Location(Bukkit.getServer().getWorld(w), x, y, z, yaw, pitch);
		
		return loc;
	}
}
