package com.MCLovesMy.api;

import java.util.Collection;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.MCLovesMy.utils.InventorySpace;

/**
 * InventoryFilled API
 * Allows to check for full inventories and InventoryFilled events.
 * 
 * @author Github/daniel-vd
 *
 */

public class InventoryFilledAPI {

	/**
	 * Checks if a list of items fits in the player's inventory.
	 * 
	 * @param player The player whose inventory will be used.
	 * @param drops The drops from the mined block or killed mob.
	 * @return true if item fits, false otherwise.
	 */
	public static boolean doesItemFit(Player player, Collection<ItemStack> drops) {
		return InventorySpace.itemFits(player, drops);
	}
	
}
