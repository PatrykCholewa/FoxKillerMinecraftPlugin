package com.github.PatrykCholewa.FoxKiller.enchantments;

import io.papermc.paper.registry.RegistryKey;
import io.papermc.paper.registry.TypedKey;
import io.papermc.paper.registry.data.EnchantmentRegistryEntry;
import io.papermc.paper.registry.event.RegistryFreezeEvent;
import io.papermc.paper.registry.event.WritableRegistry;
import org.bukkit.enchantments.Enchantment;

import java.util.Collection;

@SuppressWarnings({"UnstableApiUsage"})
public class EnchantmentUtils {

    private EnchantmentUtils() {

    }

    public static void registerAllEnchantments(RegistryFreezeEvent<Enchantment, EnchantmentRegistryEntry.Builder> event) {
        registerEnchantments(event, EnchantmentDefinitions.getDefinitions());
    }

    public static void registerEnchantments(RegistryFreezeEvent<Enchantment, EnchantmentRegistryEntry.Builder> event,
                                            Collection<EnchantmentAssignment> assignments) {
        WritableRegistry<Enchantment, EnchantmentRegistryEntry.Builder> registry = event.registry();
        assignments.forEach(assignment -> registerEnchantment(event, registry, assignment));
    }

    public static void registerEnchantment(RegistryFreezeEvent<?, ?> event,
                                           WritableRegistry<Enchantment, EnchantmentRegistryEntry.Builder> registry,
                                           EnchantmentAssignment assignment) {
        registry.register(
                TypedKey.create(RegistryKey.ENCHANTMENT, assignment.getKey()),
                b -> assignment.build(event, b)
        );
    }

}
