package com.github.PatrykCholewa.FoxKiller.listeners;

import com.github.PatrykCholewa.FoxKiller.enchantments.EnchantmentDefinitions;
import io.papermc.paper.registry.RegistryAccess;
import io.papermc.paper.registry.RegistryKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.PrepareGrindstoneEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Optional;

import static com.github.PatrykCholewa.FoxKiller.utils.EnchantmentUtils.getEnchantmentRef;

public class CurseOfAnchoringEnchantListener implements Listener {

    private static final Enchantment ENCHANTMENT = getEnchantmentRef(EnchantmentDefinitions.CURSE_OF_ANCHORING);

    @EventHandler
    public static void onDeath(PlayerDeathEvent event) {
        List<ItemStack> enchantedDrops = event.getDrops()
                .stream()
                .filter(itemStack -> itemStack.containsEnchantment(ENCHANTMENT))
                .toList();

        for (ItemStack itemStack : enchantedDrops) {
            event.getDrops().remove(itemStack);
            event.getItemsToKeep().add(itemStack);
        }
    }

    @EventHandler
    public static void onGrindstoneUse(PrepareGrindstoneEvent event) {
        var grindstoneInventory = event.getInventory();
        var upperItem = grindstoneInventory.getUpperItem();
        var lowerItem = grindstoneInventory.getLowerItem();
        boolean upperItemHasEnchantment = Optional.ofNullable(upperItem)
                .map(item -> item.containsEnchantment(ENCHANTMENT))
                .orElse(false);
        boolean lowerItemHasEnchantment = Optional.ofNullable(lowerItem)
                .map(item -> item.containsEnchantment(ENCHANTMENT))
                .orElse(false);

        if (upperItemHasEnchantment || lowerItemHasEnchantment) {
            event.setResult(null);
        }
    }

}
