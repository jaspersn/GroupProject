package com.port.groupproject.recipes;

import com.port.groupproject.enchantments.tools.Felling;
import com.port.groupproject.enchantments.tools.Hammer;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;

public class RecipeManager implements Listener {

    private static ItemStack woodHammer = Hammer.getHammer(Material.WOOD_PICKAXE);
    private static ItemStack stoneHammer = Hammer.getHammer(Material.STONE_PICKAXE);
    private static ItemStack ironHammer = Hammer.getHammer(Material.IRON_PICKAXE);
    private static ItemStack goldHammer = Hammer.getHammer(Material.GOLD_PICKAXE);
    private static ItemStack diamondHammer = Hammer.getHammer(Material.DIAMOND_PICKAXE);
    private static ItemStack ironFellingAxe = Felling.getFellingAxe(Material.IRON_AXE);
    private static ItemStack goldFellingAxe = Felling.getFellingAxe(Material.GOLD_AXE);
    private static ItemStack diamondFellingAxe = Felling.getFellingAxe(Material.DIAMOND_AXE);


    public static Recipe woodHammer() {
        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("wood_hammer"), woodHammer);
        sr.shape("*#*", " / ", " / ");
        sr.setIngredient('*', Material.WOOD);
        sr.setIngredient('#', Material.LOG);
        sr.setIngredient('/', Material.STICK);
        return sr;
    }

    public static Recipe stoneHammer() {
        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("stone_hammer"), stoneHammer);
        sr.shape("*#*", " / ", " / ");
        sr.setIngredient('*', Material.STONE);
        sr.setIngredient('#', Material.SMOOTH_BRICK);
        sr.setIngredient('/', Material.STICK);
        return sr;
    }

    public static Recipe ironHammer() {
        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("iron_hammer"), ironHammer);
        sr.shape("*#*", " / ", " / ");
        sr.setIngredient('*', Material.IRON_INGOT);
        sr.setIngredient('#', Material.IRON_BLOCK);
        sr.setIngredient('/', Material.STICK);
        return sr;
    }

    public static Recipe goldHammer() {
        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("gold_hammer"), goldHammer);
        sr.shape("*#*", " / ", " / ");
        sr.setIngredient('*', Material.GOLD_INGOT);
        sr.setIngredient('#', Material.GOLD_BLOCK);
        sr.setIngredient('/', Material.STICK);
        return sr;
    }

    public static Recipe diamondHammer() {
        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("diamond_hammer"), diamondHammer);
        sr.shape("*#*", " / ", " / ");
        sr.setIngredient('*', Material.DIAMOND);
        sr.setIngredient('#', Material.DIAMOND_BLOCK);
        sr.setIngredient('/', Material.STICK);
        return sr;
    }

    public static Recipe ironFellingAxe() {
        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("iron_felling_axe"), ironFellingAxe);
        sr.shape("*# ", "*/ ", " / ");
        sr.setIngredient('*', Material.IRON_INGOT);
        sr.setIngredient('#', Material.IRON_BLOCK);
        sr.setIngredient('/', Material.STICK);
        return sr;
    }

    public static Recipe goldFellingAxe() {
        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("gold_felling_axe"), goldFellingAxe);
        sr.shape("*# ", "*/ ", " / ");
        sr.setIngredient('*', Material.GOLD_INGOT);
        sr.setIngredient('#', Material.GOLD_BLOCK);
        sr.setIngredient('/', Material.STICK);
        return sr;
    }

    public static Recipe diamondFellingAxe() {
        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("diamond_felling_axe"), diamondHammer);
        sr.shape("*# ", "*/ ", " / ");
        sr.setIngredient('*', Material.DIAMOND);
        sr.setIngredient('#', Material.DIAMOND_BLOCK);
        sr.setIngredient('/', Material.STICK);
        return sr;
    }
}
