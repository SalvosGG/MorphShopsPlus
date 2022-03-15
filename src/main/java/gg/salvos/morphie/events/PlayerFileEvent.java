package gg.salvos.morphie.events;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

import gg.salvos.morphie.MorphShops;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerFileEvent implements Listener {

	private MorphShops plugin;
	
	public PlayerFileEvent(MorphShops plugin) {
		this.plugin = plugin;
	}
	
  	@EventHandler
  	public void onJoin(PlayerJoinEvent e) {
  		final Player player = e.getPlayer();
  		final UUID uuid = player.getUniqueId();
  		
  		new BukkitRunnable() {
  			public void run() {
  				File file = getData(uuid);
  		        FileConfiguration pd = YamlConfiguration.loadConfiguration(file);
  		        if (!pd.contains("PlayerData.Shop")) {
					pd.set("PlayerData.Shop", Boolean.valueOf(false));
					pd.set("PlayerData.Locked", Boolean.valueOf(false));
					pd.set("PlayerData.CurrentTag", String.valueOf(plugin.getConfig().getString("Tags.DefaultTag")));
					pd.set("PlayerData.Lore", null);
					pd.set("Stats.ShopVisits", Integer.valueOf(0));
					pd.set("Tags", null);
  		            try {
  		              pd.save(file);
  		              plugin.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&3MorphShops+&8] &aPlayer file succesfully created for &3" + player.getName() + "&a."));
  		            }
  		            catch (IOException e) {
  		              Bukkit.getServer().getLogger().log(Level.SEVERE, "Could not save " + uuid + "'s player file!");
  		              e.printStackTrace();
  		            }
  		        }
  			}
  		}.runTaskLater(this.plugin, 60L);
   	}
  	
  	public File getData(UUID uuid) {
  		File data = new File(this.plugin.getDataFolder() + File.separator + "PlayerData", uuid + ".yml");
  	    FileConfiguration dataFile = YamlConfiguration.loadConfiguration(data);
  	    if (!data.exists()) {
  	    	try {
  	    		dataFile.save(data);
  	    	}	
  	    	catch (IOException e1) {
  	    		e1.printStackTrace();
  	    	}
  	    }
  		return data;  
  	}
}
