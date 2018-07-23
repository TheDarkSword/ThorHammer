package it.mineblock.thorhammer.listeners;

import it.mineblock.mbcore.spigot.Chat;
import it.mineblock.thorhammer.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class SpawnThor implements Listener {

    @EventHandler
    public void onInteract(EntityDamageByEntityEvent event){
        if(!(event.getDamager() instanceof Player)) return;
        Player damager = (Player) event.getDamager();
        ItemStack thor = damager.getInventory().getItemInMainHand();
        if(!thor.getType().name().equals(Main.config.get("hammer.material"))) return;
        if(!thor.getItemMeta().getDisplayName().equals(Chat.getTranslated(Main.config.getString("hammer.name")))) return;
        if(!thor.getItemMeta().getLore().equals(Main.getTranslated(Main.config.getStringList("hammer.lore")))) return;
        if(!damager.hasPermission("th.use")) return;

        if(Main.random.nextInt(100) < Main.config.getInt("lightning-percent")){
            event.getEntity().getWorld().strikeLightning(event.getEntity().getLocation());
        }
    }
}
