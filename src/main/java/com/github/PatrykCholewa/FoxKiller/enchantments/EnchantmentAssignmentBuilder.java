package com.github.PatrykCholewa.FoxKiller.enchantments;

import com.google.common.collect.Sets;
import io.papermc.paper.registry.data.EnchantmentRegistryEntry;
import io.papermc.paper.registry.event.RegistryFreezeEvent;
import io.papermc.paper.registry.set.RegistryKeySet;
import io.papermc.paper.registry.tag.TagKey;
import net.kyori.adventure.text.Component;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemType;

import java.util.Set;
import java.util.function.BiFunction;

public class EnchantmentAssignmentBuilder {

    private final String keyName;
    private Component title;
    private TagKey<ItemType> supportedItems;
    private TagKey<ItemType> primaryItems;
    private int weight = 1;
    private int maxLevel = 1;
    private EnchantmentRegistryEntry.EnchantmentCost minimumCost = EnchantmentRegistryEntry.EnchantmentCost.of(30, 1);
    private EnchantmentRegistryEntry.EnchantmentCost maximumCost = EnchantmentRegistryEntry.EnchantmentCost.of(30, 1);
    private int anvilCost = 0;
    private final Set<EquipmentSlotGroup> equipmentSlotGroups = Sets.newHashSet(EquipmentSlotGroup.ANY);
    private RegistryKeySet<Enchantment> exclusiveWith;

    public EnchantmentAssignmentBuilder(String keyName) {
        this.keyName = keyName;
    }

    public EnchantmentAssignmentBuilder title(Component title) {
        this.title = title;
        return this;
    }

    public EnchantmentAssignmentBuilder supportedItems(TagKey<ItemType> supportedItems) {
        this.supportedItems = supportedItems;
        return this;
    }

    public EnchantmentAssignmentBuilder primaryItems(TagKey<ItemType> primaryItems) {
        this.primaryItems = primaryItems;
        return this;
    }

    public EnchantmentAssignmentBuilder  weight(int weight) {
        this.weight = weight;
        return this;
    }

    public EnchantmentAssignmentBuilder  maxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
        return this;
    }

    public EnchantmentAssignmentBuilder  minimumCost(int baseCost, int additionalCost) {
        this.minimumCost = EnchantmentRegistryEntry.EnchantmentCost.of(baseCost, additionalCost);
        return this;
    }

    public EnchantmentAssignmentBuilder  maximumCost(int baseCost, int additionalCost) {
        this.maximumCost = EnchantmentRegistryEntry.EnchantmentCost.of(baseCost, additionalCost);
        return this;
    }

    public EnchantmentAssignmentBuilder anvilCost(int anvilCost) {
        this.anvilCost = anvilCost;
        return this;
    }

    public EnchantmentAssignmentBuilder equipmentSlotGroups(EquipmentSlotGroup equipmentSlotGroup) {
        this.equipmentSlotGroups.add(equipmentSlotGroup);
        return this;
    }

    public EnchantmentAssignmentBuilder exclusiveWith(RegistryKeySet<Enchantment> exclusiveWith) {
        this.exclusiveWith = exclusiveWith;
        return this;
    }

    public EnchantmentAssignment build() {
        return new SimpleEnchantmentAssignment(keyName, this::apply);
    }

    private EnchantmentRegistryEntry.Builder apply(RegistryFreezeEvent<?, ?> event, EnchantmentRegistryEntry.Builder externalBuilder) {
        EnchantmentRegistryEntry.Builder builder = externalBuilder
                .description(title)
                .weight(weight)
                .maxLevel(maxLevel)
                .minimumCost(minimumCost)
                .maximumCost(maximumCost)
                .anvilCost(anvilCost)
                .activeSlots(equipmentSlotGroups);

        if (supportedItems != null) {
            builder = builder.supportedItems(event.getOrCreateTag(supportedItems));
        }
        if (primaryItems != null) {
            builder = builder.primaryItems(event.getOrCreateTag(primaryItems));
        }
        if (exclusiveWith != null) {
            builder = builder.exclusiveWith(exclusiveWith);
        }

        return builder;
    }

    private static class SimpleEnchantmentAssignment implements EnchantmentAssignment {

        private final String keyName;
        private final BiFunction<RegistryFreezeEvent<?, ?>, EnchantmentRegistryEntry.Builder, EnchantmentRegistryEntry.Builder> builder;

        public SimpleEnchantmentAssignment(String keyName, BiFunction<RegistryFreezeEvent<?, ?>, EnchantmentRegistryEntry.Builder, EnchantmentRegistryEntry.Builder> builder) {
            this.keyName = keyName;
            this.builder = builder;
        }

        @Override
        public String getKeyName() {
            return keyName;
        }

        @Override
        public EnchantmentRegistryEntry.Builder build(RegistryFreezeEvent<?, ?> event, EnchantmentRegistryEntry.Builder externalBuilder) {
            return builder.apply(event, externalBuilder);
        }
    }

}
