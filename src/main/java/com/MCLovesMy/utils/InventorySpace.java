package com.MCLovesMy.utils;

import java.util.Collection;
import java.util.List;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class InventorySpace {
	
	//Check if item fits in inventory
	public static boolean itemFits(Player p, Collection<ItemStack> drops) {
	    if (p.getInventory().firstEmpty() == -1){
	        for(ItemStack item : drops){
	            for (int i=0; i<35; i++) {
	                if (p.getInventory().getItem(i).getAmount()+item.getAmount()<=64) {
	                    if (p.getInventory().getItem(i).getType().equals(item.getType())) {
	                   
	                        break;
	                    }
	                }
	                if (i==34) {
	                	return false;
	                }
	            }
	        }
	    }
		return true;
	}
}
