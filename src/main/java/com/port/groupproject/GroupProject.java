package com.port.groupproject;

import com.port.groupproject.commands.give.FellingCommand;
import com.port.groupproject.commands.give.HammerCommand;
import com.port.groupproject.enchantments.tools.Felling;
import com.port.groupproject.enchantments.tools.Hammer;
import com.port.groupproject.events.VeinMiner;
import com.port.groupproject.recipes.ShapedRecipeManager;
import com.port.groupproject.recipes.FurnaceRecipeManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.net.URISyntaxException;

public final class GroupProject extends JavaPlugin {

    public GroupProject() throws URISyntaxException {
    }

    @Override
    public void onEnable() {
        // register enchantment events
        getServer().getPluginManager().registerEvents(new Hammer(), this);
        getServer().getPluginManager().registerEvents(new Felling(), this);

        //register events
        getServer().getPluginManager().registerEvents(new VeinMiner(), this);

        // register commands
        this.getCommand("hammer").setExecutor(new HammerCommand());
        this.getCommand("felling").setExecutor(new FellingCommand());

        // Register recipes
        getServer().addRecipe(ShapedRecipeManager.ironHammer());
        getServer().addRecipe(ShapedRecipeManager.goldHammer());
        getServer().addRecipe(ShapedRecipeManager.diamondHammer());
        getServer().addRecipe(ShapedRecipeManager.ironFellingAxe());
        getServer().addRecipe(ShapedRecipeManager.goldFellingAxe());
        getServer().addRecipe(ShapedRecipeManager.diamondFellingAxe());
        getServer().addRecipe(FurnaceRecipeManager.rottenFleshToLeather());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
