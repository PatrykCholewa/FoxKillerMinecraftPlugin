package com.github.PatrykCholewa.FoxKiller.utils;

import com.github.PatrykCholewa.FoxKiller.enchantments.EnchantmentAssignment;
import io.papermc.paper.registry.RegistryAccess;
import io.papermc.paper.registry.RegistryKey;
import org.bukkit.enchantments.Enchantment;

public class EnchantmentUtils {

    private EnchantmentUtils() {

    }

    public static Enchantment getEnchantmentRef(EnchantmentAssignment enchantmentAssignment) {
        return RegistryAccess.registryAccess()
                .getRegistry(RegistryKey.ENCHANTMENT)
                .get(enchantmentAssignment.getKey());
    }

}
