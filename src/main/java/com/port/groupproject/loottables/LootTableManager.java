package com.port.groupproject.loottables;

import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;


public class LootTableManager implements Listener {

    private String dir;

    public LootTableManager(URI uri) throws URISyntaxException {
        dir = uri.getRawPath();
        dir = dir.substring(1, dir.indexOf("plugins/groupproject-1.0.jar")) + "world/data/loot_tables";
        if (new File(dir).mkdir()) System.out.println("Created loot_tables folder.");
        dir += "/minecraft";
        if (new File(dir).mkdir()) System.out.println("Created minecraft folder.");
    }

    // creates new loot table for a chest
    public void addChests(ItemStack item, String lootTableName, int weight) {
        // Creates a new folder (if necessary)
        if (new File(dir + "/chests").mkdir()) System.out.println("Created chests folder.");
        // Mitigates try/catch when method is called
        PrintStream p = null;
        try {
            p = new PrintStream(dir + "/chests/" + lootTableName + ".json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert p != null;
        // write to file
        p.println("Hello, world!");
    }

    // Creates new loot table for an entity
    public void addEntities(ItemStack item, String lootTableName, int weight) {
        // Creates a new folder (if necessary)
        if (new File(dir + "/entities").mkdir()) System.out.println("Created entities folder.");
        // Mitigates try/catch when method is called
        PrintStream p = null;
        try {
            p = new PrintStream(dir + "/entities/" + lootTableName + ".json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert p != null;
        // write to file
        p.println("Hello, world!");
    }

    // Creates new loot table for a gameplay mechanic (fishing mostly)
    public void addGameplay(ItemStack item, String lootTableName, int weight) {
        // Creates a new folder (if necessary)
        if (new File(dir + "/gameplay").mkdir()) System.out.println("Created gameplay folder.");
        // Mitigates try/catch when method is called
        PrintStream p = null;
        try {
            p = new PrintStream(dir + "/gameplay/" + lootTableName + ".json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert p != null;
        // write to file
        p.println("Hello, world!");
    }

}
