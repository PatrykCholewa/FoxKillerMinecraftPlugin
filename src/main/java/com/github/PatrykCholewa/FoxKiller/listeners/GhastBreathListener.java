package com.github.PatrykCholewa.FoxKiller.listeners;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.BrewEvent;
import org.bukkit.inventory.BrewerInventory;
import org.bukkit.inventory.ItemRarity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.potion.PotionType;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.List;

import static org.bukkit.Sound.ITEM_BOTTLE_FILL_DRAGONBREATH;

public class GhastBreathListener implements Listener {

    private static final Component GHAST_BREATH_DISPLAY_NAME = Component.text("Ghast's Breath");
    private static final Component GHAST_BREATH_LORE = Component.text("Can be condensed using alchemy with a foul ingredient");

    private PluginManager pluginManager;

    @EventHandler
    public void onGhastFireballHit(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player player)) {
            return;
        }

        if (!(event.getEntity() instanceof Fireball fireball)) {
            return;
        }

        if (!(fireball.getShooter() instanceof Ghast)) {
            return;
        }

        ItemStack itemStack = player.getInventory().getItemInMainHand();
        if (itemStack.getType() != Material.GLASS_BOTTLE) {
            return;
        }

        itemStack.subtract();
        fireball.setYield(0);
        fireball.setIsIncendiary(false);
        fireball.setVelocity(new Vector());
        fireball.setAcceleration(new Vector());

        ItemStack ghastBreath = new ItemStack(Material.POTION);
        PotionMeta ghastBreathMeta = (PotionMeta) ghastBreath.getItemMeta();
        ghastBreathMeta.displayName(GHAST_BREATH_DISPLAY_NAME);
        ghastBreathMeta.lore(List.of(GHAST_BREATH_LORE));
        ghastBreathMeta.setRarity(ItemRarity.UNCOMMON);
        ghastBreathMeta.setColor(Color.FUCHSIA);
        ghastBreathMeta.setBasePotionType(PotionType.WATER);
        ghastBreath.setItemMeta(ghastBreathMeta);

        player.getInventory().addItem(ghastBreath);
        player.playSound(player, ITEM_BOTTLE_FILL_DRAGONBREATH, 3, 2);
    }

    @EventHandler
    public void onCauldronFill(BrewEvent event) {
        BrewerInventory brewerInventory = event.getContents();
        if (brewerInventory.getIngredient() != null && brewerInventory.getIngredient().getType() != Material.FERMENTED_SPIDER_EYE) {
            return;
        }

        ItemStack potion1 = brewerInventory.getItem(0);
        ItemStack potion2 = brewerInventory.getItem(1);
        ItemStack potion3 = brewerInventory.getItem(2);

        if (!isGhastBreath(potion1) || !isGhastBreath(potion2) || !isGhastBreath(potion3)) {
            Bukkit.getLogger().warning(potion1.toString());
            Bukkit.getLogger().info(potion2.toString());
            Bukkit.getLogger().info(potion3.toString());
            return;
        }

        ItemStack dragonBreathSubstitute = new ItemStack(Material.DRAGON_BREATH);
        ItemMeta dragonBreathSubstituteMeta = dragonBreathSubstitute.getItemMeta();
        dragonBreathSubstituteMeta.displayName(Component.text("Dragon's Breath Substitute"));
        dragonBreathSubstitute.setItemMeta(dragonBreathSubstituteMeta);

        event.getResults().set(0, null);
        event.getResults().set(1, dragonBreathSubstitute);
        event.getResults().set(2, null);
    }

    private boolean isGhastBreath(ItemStack itemStack) {
        return itemStack != null
                && itemStack.getType() == Material.POTION
                && itemStack.getItemMeta().hasDisplayName()
                && itemStack.getItemMeta().displayName().equals(GHAST_BREATH_DISPLAY_NAME)
                && itemStack.getItemMeta().hasLore()
                && itemStack.getItemMeta().lore().getFirst().equals(GHAST_BREATH_LORE);
    }

}
