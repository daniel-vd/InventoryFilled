package com.MCLovesMy.Events.utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PartiallyFilled {

	public PartiallyFilled (Player player) {
		
		ItemStack[] contents = player.getInventory().getStorageContents();
		
		int count = 0;
		
		for (int i = 0; i < contents.length; i++) {
		    if (contents[i] == null)
		        count++; //Empty slot
		}
		
		double invPercent = (100. / 36);
		int invSlotsFull = 36 - count;
		int invSlotsPercentFull = (int) (invPercent * invSlotsFull);
		
		if (invSlotsPercentFull == 25) {
			
			player.sendMessage("Your inventory is filled for: " + invSlotsPercentFull + "%");
		}

		
	}
	
}
