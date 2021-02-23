package com.port.groupproject;

import com.port.groupproject.commands.give.FellingCommand;
import com.port.groupproject.commands.give.HammerCommand;
import com.port.groupproject.commands.give.VeinMinerCommand;
import com.port.groupproject.enchantments.tools.Felling;
import com.port.groupproject.enchantments.tools.Hammer;
import com.port.groupproject.enchantments.tools.VeinMiner;
import com.port.groupproject.events.StartupEventListener;
import com.port.groupproject.recipes.RecipeManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class GroupProject extends JavaPlugin {

    @Override
    public void onEnable() {
        // register enchantment events
        getServer().getPluginManager().registerEvents(new Hammer(), this);
        getServer().getPluginManager().registerEvents(new Felling(), this);
        getServer().getPluginManager().registerEvents(new VeinMiner(), this);

        //register events
        getServer().getPluginManager().registerEvents(new StartupEventListener(), this);

        // register commands
        this.getCommand("hammer").setExecutor(new HammerCommand());
        this.getCommand("felling").setExecutor(new FellingCommand());
        this.getCommand("veinminer").setExecutor(new VeinMinerCommand());

        // Register recipes
        getServer().addRecipe(RecipeManager.woodHammer());
        getServer().addRecipe(RecipeManager.stoneHammer());
        getServer().addRecipe(RecipeManager.ironHammer());
        getServer().addRecipe(RecipeManager.goldHammer());
        getServer().addRecipe(RecipeManager.diamondHammer());
        getServer().addRecipe(RecipeManager.ironFellingAxe());
        getServer().addRecipe(RecipeManager.goldFellingAxe());
        getServer().addRecipe(RecipeManager.diamondFellingAxe());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
