package com.port.groupproject;

import com.port.groupproject.commands.give.HammerCommand;
import com.port.groupproject.enchantments.tools.Hammer;
import com.port.groupproject.events.StartupEventListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class GroupProject extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new Hammer(), this);
        getServer().getPluginManager().registerEvents(new StartupEventListener(), this);
        // register commands
        this.getCommand("hammer").setExecutor(new HammerCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
