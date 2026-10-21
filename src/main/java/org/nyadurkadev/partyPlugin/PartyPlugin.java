package org.nyadurkadev.partyPlugin;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import org.nyadurkadev.partyPlugin.utils.PartyManager;

public final class PartyPlugin extends JavaPlugin {

    @Getter
    private static PartyPlugin instance;

    @Getter
    private static PartyManager manager;

    @Override
    public void onEnable() {

        instance = this;

        manager = new PartyManager();
        // Plugin startup logic
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
