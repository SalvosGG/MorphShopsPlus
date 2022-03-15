package gg.salvos.morphie.util;

import gg.salvos.morphie.MorphShops;
import gg.salvos.morphie.util.playerdata.PlayerDataManager;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class GetPlayerShops {

    private MorphShops plugin;

    public GetPlayerShops(MorphShops plugin) {
        this.plugin = plugin;
    }

    public List<String> getAllShopsList(String currentTag) {
        File data = new File(plugin.getDataFolder() + File.separator + "PlayerData");
        File path = new File(data.getPath());
        File dir = new File(path.toString());
        File[] directoryListing = dir.listFiles();
        List<String> playerShops = new java.util.ArrayList<>(Collections.emptyList());
        if (directoryListing != null) {
            for (File child : directoryListing) {
                String[] childUUID = child.getName().split("[.]");
                UUID uuid1 = UUID.fromString(childUUID[0]);
                if (new PlayerDataManager(plugin).getBoolean(uuid1, "PlayerData.Shop")) {
                    if (currentTag.equalsIgnoreCase(plugin.getConfig().getString("Tags.DefaultTag"))) {
                        playerShops.add(uuid1.toString());
                    } else {
                        if (new PlayerDataManager(plugin).getStringList(uuid1, "Tags").contains(currentTag)) {
                            playerShops.add(uuid1.toString());
                        } else  {
                            return null;
                        }
                    }
                }
            }
            return playerShops;
        }
        return null;
    }
}
