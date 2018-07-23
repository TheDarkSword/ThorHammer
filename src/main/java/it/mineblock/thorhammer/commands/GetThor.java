package it.mineblock.thorhammer.commands;

import it.mineblock.mbcore.spigot.Chat;
import it.mineblock.thorhammer.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.UUID;

public class GetThor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            return true;
        }
        Player player = (Player) sender;

        if(!player.hasPermission("th.hammer")) return true;

        if(args.length > 1){
            Chat.send(Main.config.getString("messages.command"), player, true);
            return true;
        }

        ItemStack thor = new ItemStack(Material.getMaterial(Main.config.getString("hammer.material")), 1);
        ItemMeta meta = thor.getItemMeta();
        meta.setDisplayName(Chat.getTranslated(Main.config.getString("hammer.name")));
        List<String> lore = Main.getTranslated(Main.config.getStringList("hammer.lore"));
        meta.setLore(lore);
        thor.setItemMeta(meta);

        if(args.length == 1){
            Player target = Bukkit.getPlayer(UUID.fromString(args[0]));
            if(target == null){
                Chat.send(Main.config.getString("offline-player"), player, true);
                return true;
            }
            target.getInventory().addItem(thor);
            Chat.send(Main.config.getString("messages.get-hammer-other").replace("{player}", player.getName()), target, true);
            Chat.send(Main.config.getString("messages.get-hammer").replace("{player}", target.getName()), player, true);
        } else {

            player.getInventory().addItem(thor);

            Chat.send(Main.config.getString("messages.get-hammer").replace("{player}", player.getName()), player, true);
        }

        return true;
    }
}
