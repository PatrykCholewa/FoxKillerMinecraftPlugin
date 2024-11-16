package com.github.PatrykCholewa.FoxKiller;

import com.github.PatrykCholewa.FoxKiller.enchantments.EnchantmentUtils;
import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.bootstrap.PluginBootstrap;
import io.papermc.paper.registry.event.RegistryEvents;

public class FoxKillerPluginBootstrap implements PluginBootstrap {

    @Override
    public void bootstrap(BootstrapContext context) {
        context.getLifecycleManager().registerEventHandler(RegistryEvents.ENCHANTMENT.freeze().newHandler(event -> {
            EnchantmentUtils.registerAllEnchantments(event);
        }));
    }

}
