package com.arg0nis.inventorygui.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class MenuListener implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if(e.getView().getTitle().equalsIgnoreCase("Menu")) {
            Player p = (Player) e.getWhoClicked();
            PlayerInventory pInv = p.getInventory();

            switch (e.getSlot()) {

                case 11:
                    e.setCancelled(true);

                    Inventory chooseItem = Bukkit.createInventory(p, 9*6, "Choose Item You Want");
                    ItemStack close = new ItemStack(Material.BARRIER);
                    ItemMeta closeMeta = close.getItemMeta();
                    closeMeta.setDisplayName(ChatColor.RED + "Close");
                    close.setItemMeta(closeMeta);
                    for(int i = 45; i < 54; i++) {
                        ItemStack glass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
                        ItemMeta glassMeta = glass.getItemMeta();
                        glassMeta.setDisplayName(" ");
                        glass.setItemMeta(glassMeta);
                        chooseItem.setItem(i, glass);
                    }

                    chooseItem.setItem(49, close);

                    ItemStack[] getItemItem = {new ItemStack(Material.GUNPOWDER)};

                    int index = 0;
                    for(int i = 0; i < 53; i++) {
                       if(chooseItem.getItem(i) != null)
                           continue;
                       if(getItemItem.length-1 < index)
                           break;
                       chooseItem.setItem(i, getItemItem[index]);
                       index++;
                    }

                    p.closeInventory();
                    p.openInventory(chooseItem);

                    break;
                case 15:
                    e.setCancelled(true);
                    Inventory askAgain = Bukkit.createInventory(p, 9*3, "Are you sure?");

                    ItemStack yes = new ItemStack(Material.GREEN_WOOL);
                    ItemStack no = new ItemStack(Material.RED_WOOL);
                    ItemMeta yesMeta = yes.getItemMeta();
                    ItemMeta noMeta = no.getItemMeta();
                    yesMeta.setDisplayName(ChatColor.GREEN + "YES");
                    noMeta.setDisplayName(ChatColor.RED + "NO");
                    yes.setItemMeta(yesMeta);
                    no.setItemMeta(noMeta);

                    p.closeInventory();
                    p.openInventory(askAgain);


                    askAgain.setItem(11, yes);
                    askAgain.setItem(15, no);
            }
        } else if (e.getView().getTitle().equalsIgnoreCase("Are you sure?")) {
            Player p = (Player) e.getWhoClicked();
            switch (e.getSlot()) {
                case 15:
                    e.setCancelled(true);
                    p.closeInventory();
                    break;
                case 11:
                    e.setCancelled(true);
                    p.closeInventory();
                    p.getInventory().clear();
                    p.sendMessage("Your inventory has been " + ChatColor.RED + "cleared" + ChatColor.WHITE + "!");
                    break;
            }

        } else if(e.getView().getTitle().equalsIgnoreCase("Choose Item You Want")) {
            Player p = (Player) e.getWhoClicked();
            if(e.getClickedInventory() == p.getInventory())
                return;
            if(e.getCurrentItem() == null) {
                e.setCancelled(true);
                return;
            }
            ItemStack clickedItem = e.getCurrentItem();
            if(clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED + "Close")) {
                e.setCancelled(true);
                p.closeInventory();
                return;
            }
            if (clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase(" ")) {
                e.setCancelled(true);
                return;
            }

            e.setCancelled(true);
            p.getInventory().addItem(e.getCurrentItem());
        }
    }
}
