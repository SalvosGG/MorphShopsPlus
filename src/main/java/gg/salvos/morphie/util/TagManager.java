package gg.salvos.morphie.util;

import gg.salvos.morphie.MorphShops;
import gg.salvos.morphie.util.PlayerData.PlayerDataManager;

import java.util.List;
import java.util.UUID;

public class TagManager {

    private MorphShops plugin;

    public TagManager(MorphShops plugin) {
        this.plugin = plugin;
    }

    public String getDefaultTag() {
        return plugin.getConfig().getString("Tags.DefaultTag");
    }

    public String getPlayerTag(UUID uuid) {
        return new PlayerDataManager(plugin).getString(uuid, "PlayerData.CurrentTag");
    }

    public List<String> getCustomTags() {
        return plugin.getConfig().getStringList("Tags.CustomTags");
    }

    public String getPrevTag(UUID uuid) {
        int listSize = getCustomTags().size();
        if (new PlayerDataManager(plugin).getString(uuid, "PlayerData.CurrentTag").equalsIgnoreCase(getDefaultTag())) {
            List<String> customTags = getCustomTags();
            return customTags.get(listSize - 1);
        } else if (new PlayerDataManager(plugin).getString(uuid, "PlayerData.CurrentTag").equalsIgnoreCase(getCustomTags().get(0))) {
            return getDefaultTag();
        } else {
            int prevIndex = getCustomTags().indexOf(getPlayerTag(uuid));
            return getCustomTags().get(prevIndex-1);
        }
    }

    public String getNextTag(UUID uuid) {
        int listSize = getCustomTags().size();
        if (new PlayerDataManager(plugin).getString(uuid, "PlayerData.CurrentTag").equalsIgnoreCase(getDefaultTag())) {
            List<String> customTags = getCustomTags();
            return customTags.get(0);
        } else if (new PlayerDataManager(plugin).getString(uuid, "PlayerData.CurrentTag").equalsIgnoreCase(getCustomTags().get(listSize-1))) {
            return getDefaultTag();
        } else {
            int nextIndex = getCustomTags().indexOf(getPlayerTag(uuid));
            return getCustomTags().get(nextIndex+1);
        }
    }
}
