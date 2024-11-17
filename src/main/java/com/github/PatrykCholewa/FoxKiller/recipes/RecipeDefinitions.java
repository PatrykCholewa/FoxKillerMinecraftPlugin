package com.github.PatrykCholewa.FoxKiller.recipes;

import com.github.PatrykCholewa.FoxKiller.enchantments.EnchantmentDefinitions;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.List;

import static com.github.PatrykCholewa.FoxKiller.utils.EnchantmentUtils.getEnchantmentRef;


public class RecipeDefinitions {

    private Plugin plugin;

    public RecipeDefinitions(Plugin plugin) {
        this.plugin = plugin;
    }

    public List<Recipe> createDefinitions() {
        return List.of(createDeflectiveRetributionBook());
    }

    private Recipe createDeflectiveRetributionBook() {
        NamespacedKey key = new NamespacedKey(plugin, "WarriorSword");
        ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
        EnchantmentStorageMeta itemMeta = (EnchantmentStorageMeta) item.getItemMeta();
        itemMeta.addStoredEnchant(getEnchantmentRef(EnchantmentDefinitions.DEFLECTIVE_RETRIBUTION), 1, false);
        item.setItemMeta(itemMeta);

        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(" A ", "BCD", "AEA");
        recipe.setIngredient('A', Material.LAPIS_LAZULI);
        recipe.setIngredient('C', Material.BOOK);
        recipe.setIngredient('B', Material.SPECTRAL_ARROW);
        recipe.setIngredient('D', Material.TIPPED_ARROW);
        recipe.setIngredient('E', Material.ARROW);

        return recipe;
    }

}
