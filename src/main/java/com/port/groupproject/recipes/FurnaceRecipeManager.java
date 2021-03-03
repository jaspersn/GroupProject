package com.port.groupproject.recipes;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Item;
import org.bukkit.event.Listener;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.material.MaterialData;

public class FurnaceRecipeManager implements Listener {

    public static Recipe rottenFleshToLeather() {
        return new FurnaceRecipe(new ItemStack(Material.LEATHER), new MaterialData(Material.ROTTEN_FLESH), .35f);
    }
}
