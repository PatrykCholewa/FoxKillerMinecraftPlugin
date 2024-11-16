package com.github.PatrykCholewa.FoxKiller.enchantments;

import io.papermc.paper.registry.RegistryKey;
import io.papermc.paper.registry.data.EnchantmentRegistryEntry;
import io.papermc.paper.registry.event.RegistryFreezeEvent;
import net.kyori.adventure.key.Key;
import org.bukkit.enchantments.Enchantment;

public interface EnchantmentAssignment {

    RegistryKey<Enchantment> REGISTRY_KEY = RegistryKey.ENCHANTMENT;
    String KEY_PREFIX = "foxkiller:";

    String getKeyName();

    default Key getKey() {
        return Key.key(KEY_PREFIX + getKeyName());
    }

    EnchantmentRegistryEntry.Builder build(RegistryFreezeEvent<?, ?> event, EnchantmentRegistryEntry.Builder builder);

}
