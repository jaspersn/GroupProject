package com.port.groupproject.commands.give;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class HammerCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (command.getName().equalsIgnoreCase("hammer")) {
                if (args.length >= 1) {
                    if (args[0].equalsIgnoreCase("wood")) {
                        giveHammer(p, Material.WOOD_PICKAXE, "Wood Hammer");
                        return true;
                    } else if (args[0].equalsIgnoreCase("stone")) {
                        giveHammer(p, Material.STONE_PICKAXE, "Stone Hammer");
                        return true;
                    } else if (args[0].equalsIgnoreCase("iron")) {
                        giveHammer(p, Material.IRON_PICKAXE, "Iron Hammer");
                        return true;
                    } else if (args[0].equalsIgnoreCase("gold")) {
                        giveHammer(p, Material.GOLD_PICKAXE, "Gold Hammer");
                        return true;
                    } else if (args[0].equalsIgnoreCase("diamond")) {
                        giveHammer(p, Material.DIAMOND_PICKAXE, "Diamond Hammer");
                        return true;
                    }
                } else {
                    p.sendMessage("/hammer <material>");
                }
            }
        }
        return false;
    }

    private void giveHammer(Player p, Material material, String itemName) {
        ItemStack hammer = new ItemStack(material);
        ItemMeta meta = hammer.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Hammer");
        meta.setDisplayName(itemName);
        meta.setLore(lore);
        hammer.setItemMeta(meta);
        p.getInventory().addItem(hammer);
        p.sendMessage("Given *1 hammer to " + p.getName());
    }
}
