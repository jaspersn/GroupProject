package com.port.groupproject.commands.give;

import com.port.groupproject.enchantments.tools.Hammer;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HammerCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (command.getName().equalsIgnoreCase("hammer")) {
                if (args.length >= 1) {
                    if (args[0].equalsIgnoreCase("Wood")) {
                        giveHammer(p, Material.WOOD_PICKAXE);
                        return true;
                    } else if (args[0].equalsIgnoreCase("Stone")) {
                        giveHammer(p, Material.STONE_PICKAXE);
                        return true;
                    } else if (args[0].equalsIgnoreCase("Iron")) {
                        giveHammer(p, Material.IRON_PICKAXE);
                        return true;
                    } else if (args[0].equalsIgnoreCase("Gold")) {
                        giveHammer(p, Material.GOLD_PICKAXE);
                        return true;
                    } else if (args[0].equalsIgnoreCase("Diamond")) {
                        giveHammer(p, Material.DIAMOND_PICKAXE);
                        return true;
                    }
                } else {
                    p.sendMessage("/hammer <material>");
                }
            }
        }
        return false;
    }

    private void giveHammer(Player p, Material material) {
        p.getInventory().addItem(Hammer.getHammer(material));
        p.sendMessage("Given *1 Hammer to " + p.getName());
    }
}
