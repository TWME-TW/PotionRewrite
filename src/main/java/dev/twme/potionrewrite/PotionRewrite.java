package dev.twme.potionrewrite;

import org.bukkit.Bukkit;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.entity.LingeringPotionSplashEvent;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;

import java.util.Collection;

public final class PotionRewrite extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
    }

    @EventHandler
    public void onEntityPotionEffectEvent(EntityPotionEffectEvent event) {
        if (event.getCause() == EntityPotionEffectEvent.Cause.PLUGIN) {
            return;
        }
        PotionEffect oldEffect = event.getOldEffect();
        if (oldEffect != null) {
            if (oldEffect.getAmplifier() > 124 || oldEffect.getAmplifier() < 0) {
                event.setCancelled(true);
            }
        }
        PotionEffect newEffect = event.getNewEffect();
        if (newEffect != null) {
            if (newEffect.getAmplifier() > 124 || newEffect.getAmplifier() < 0) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onLingeringPotionSplashEvent(LingeringPotionSplashEvent event) {
        ThrownPotion potion = event.getEntity();
        Collection<PotionEffect> effects = potion.getEffects();
        for (PotionEffect effect : effects) {
            if (effect.getAmplifier() > 124 || effect.getAmplifier() < 0) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPotionSplashEvent(PotionSplashEvent event) {
        ThrownPotion potion = event.getPotion();
        Collection<PotionEffect> effects = potion.getEffects();
        for (PotionEffect effect : effects) {
            if (effect.getAmplifier() > 124 || effect.getAmplifier() < 0) {
                event.setCancelled(true);
            }
        }
    }
}
