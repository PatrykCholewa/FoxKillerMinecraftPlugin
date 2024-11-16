package com.github.PatrykCholewa.FoxKiller.enchantments;

import com.google.common.collect.Lists;
import io.papermc.paper.registry.RegistryKey;
import io.papermc.paper.registry.keys.tags.ItemTypeTagKeys;
import io.papermc.paper.registry.set.RegistrySet;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

import java.util.Collection;
import java.util.List;

import static io.papermc.paper.registry.keys.EnchantmentKeys.VANISHING_CURSE;

public class EnchantmentDefinitions {

    public static EnchantmentAssignment CURSE_OF_ANCHORING = new EnchantmentAssignmentBuilder("anchoring_curse")
            .title(Component.text("Curse of Anchoring", NamedTextColor.RED))
            .supportedItems(ItemTypeTagKeys.ENCHANTABLE_DURABILITY)
            .exclusiveWith(RegistrySet.keySet(RegistryKey.ENCHANTMENT, List.of(VANISHING_CURSE)))
            .build();

    public static Collection<EnchantmentAssignment> getDefinitions() {
        return Lists.newArrayList(CURSE_OF_ANCHORING);
    }
}
