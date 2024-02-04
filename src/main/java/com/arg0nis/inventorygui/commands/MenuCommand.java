package com.arg0nis.inventorygui.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MenuCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)) {
            commandSender.sendMessage("Only players can use this command!");
            return false;
        }

        Player p = (Player) commandSender;

        Inventory inv = Bukkit.createInventory(p, 9*3, "Menu");

        ItemStack getItem = new ItemStack(Material.GUNPOWDER);
        ItemMeta getItemsMeta = getItem.getItemMeta();
        getItemsMeta.setDisplayName(ChatColor.BOLD+"Get Item");
        getItem.setItemMeta(getItemsMeta);

        ItemStack clearInvButton = new ItemStack(Material.BARRIER);
        ItemMeta clearInvMeta = clearInvButton.getItemMeta();
        clearInvMeta.setDisplayName(ChatColor.WHITE + "Clear Inventory");
        clearInvButton.setItemMeta(clearInvMeta);

        inv.setItem(11, getItem);
        inv.setItem(15, clearInvButton);

        p.openInventory(inv);


        return true;
    }
}
