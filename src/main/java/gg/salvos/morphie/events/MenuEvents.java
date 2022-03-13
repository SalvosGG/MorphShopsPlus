package gg.salvos.morphie.events;

import gg.salvos.morphie.MorphShops;
import gg.salvos.morphie.menus.PlayerShops;
import gg.salvos.morphie.util.TagManager;
import gg.salvos.morphie.util.MessagesManager;
import gg.salvos.morphie.util.PlayerData.PlayerDataManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.UUID;

public class MenuEvents implements Listener {

    private MorphShops plugin;

    public MenuEvents(MorphShops plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', new MessagesManager(plugin).getMessage("Menu.PlayerShops.Title")))) {
            Player player = (Player) event.getWhoClicked();
            UUID uuid = player.getUniqueId();
            if ((event.getCurrentItem() == null) || (!event.getCurrentItem().hasItemMeta())) {
                return;
            }

            ItemStack item = event.getCurrentItem();
            ItemMeta itemmeta = item.getItemMeta();
            ArrayList<String> itemlore = (ArrayList<String>) itemmeta.getLore();
            String DisplayName = itemmeta.getDisplayName();

            boolean normalItem = false;
            if (DisplayName.equals(ChatColor.translateAlternateColorCodes('&', new MessagesManager(plugin).getMessage("Menu.PlayerShops.TagFilterTitle")))) {
                event.setCancelled(true);
                String playerCurrentTag = new PlayerDataManager(plugin).getString(uuid, "PlayerData.CurrentTag");
                if (playerCurrentTag.equalsIgnoreCase(new TagManager(plugin).getDefaultTag())) {
                    new PlayerDataManager(plugin).setString(uuid, "PlayerData.CurrentTag", new TagManager(plugin).getCustomTags().get(0));
                    new PlayerShops(plugin).openGUIPlayerShop(player);
                } else if (new TagManager(plugin).getCustomTags().indexOf(playerCurrentTag) == new TagManager(plugin).getCustomTags().size()-1) {
                    new PlayerDataManager(plugin).setString(uuid, "PlayerData.CurrentTag", new TagManager(plugin).getDefaultTag());
                    new PlayerShops(plugin).openGUIPlayerShop(player);
                } else {
                    int playerTagIndex = new TagManager(plugin).getCustomTags().indexOf(playerCurrentTag);
                    new PlayerDataManager(plugin).setString(uuid, "PlayerData.CurrentTag", new TagManager(plugin).getCustomTags().get(playerTagIndex+1));
                    new PlayerShops(plugin).openGUIPlayerShop(player);
                }
            }
        }
        event.setCancelled(true);
    }
}