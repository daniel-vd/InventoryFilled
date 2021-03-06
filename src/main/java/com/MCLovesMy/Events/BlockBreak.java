package com.MCLovesMy.Events;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import com.MCLovesMy.InventoryFilled;
import com.MCLovesMy.api.PlayerReceiveAlertEvent;
import com.MCLovesMy.utils.InventorySpace;

public class BlockBreak implements Listener{
	
	private InventoryFilled plugin;
	public BlockBreak(InventoryFilled plugin) {
		this.plugin = plugin;
	}
	
	private static ArrayList<String> messageAlert1 = new ArrayList<String>();
	private static ArrayList<String> messageAlert2 = new ArrayList<String>();
	private static ArrayList<String> messageAlert3 = new ArrayList<String>();
	private static ArrayList<String> messageAlert4 = new ArrayList<String>();
		
		//BlockBreak event
		@EventHandler
	    public void BlockBreakEvent(BlockBreakEvent e) {
			Player p = e.getPlayer();
			UUID uuid = p.getUniqueId();
			Location playerLoc = p.getLocation(); //For sound effect
			ItemStack[] contents = p.getInventory().getStorageContents();
			
			int count = 0;
			for (int i = 0; i < contents.length; i++) {
			    if (contents[i] == null)
			        count++; //Empty slot
			}
			
			double invPercent = (100. / 36);
			int invSlotsFull = 36 - count;
			int invSlotsPercentFull = (int) (invPercent * invSlotsFull);
			
			//Pretty unnecessary...
			String invSlotsFullPercent = invSlotsPercentFull + "";
			
			//alert1
	        if (invSlotsFull == (plugin.config.getInt("Inventory-Part-Alert.Alert1.Slots-Filled"))) {
	        	if (!messageAlert1.contains(p.getName())) {
                 String message = ChatColor.BLUE + plugin.messages.getString("Actions.BlockBreak.Partial-Filled-Alert");
                 p.sendMessage(message.replaceAll("\\*percentage\\*", invSlotsFullPercent));;
                 messageAlert1.add(p.getName());
	        	}
	        }
	        if (invSlotsFull >= (plugin.config.getInt("Inventory-Part-Alert.Alert1.Slots-Filled")) + 1){
	        	messageAlert1.remove(p.getName());
	        }
	        
	        if (invSlotsFull <= (plugin.config.getInt("Inventory-Part-Alert.Alert1.Slots-Filled")) - 1){
	        	messageAlert1.remove(p.getName());
	        }
	        
	        //alert2
	        if (invSlotsFull == (plugin.config.getInt("Inventory-Part-Alert.Alert2.Slots-Filled"))) {
	        	if (!messageAlert2.contains(p.getName())) {
	                 String message = ChatColor.BLUE + plugin.messages.getString("Actions.BlockBreak.Partial-Filled-Alert");
	                 p.sendMessage(message.replaceAll("\\*percentage\\*", invSlotsFullPercent));;
                 messageAlert2.add(p.getName());
	        	}
	        }
	        if (invSlotsFull >= (plugin.config.getInt("Inventory-Part-Alert.Alert2.Slots-Filled")) + 1){
	        	messageAlert2.remove(p.getName());
	        }
	        
	        if (invSlotsFull <= (plugin.config.getInt("Inventory-Part-Alert.Alert1.Slots-Filled")) - 1){
	        	messageAlert2.remove(p.getName());
	        }
	        
	        //alert3
	        if (invSlotsFull == (plugin.config.getInt("Inventory-Part-Alert.Alert3.Slots-Filled"))) {
	        	if (!messageAlert3.contains(p.getName())) {
	                 String message = ChatColor.BLUE + plugin.messages.getString("Actions.BlockBreak.Partial-Filled-Alert");
	                 p.sendMessage(message.replaceAll("\\*percentage\\*", invSlotsFullPercent));;
                 messageAlert3.add(p.getName());
	        	}
	        }
	        if (invSlotsFull >= (plugin.config.getInt("Inventory-Part-Alert.Alert3.Slots-Filled")) + 1){
	        	messageAlert3.remove(p.getName());
	        }
	        
	        if (invSlotsFull <= (plugin.config.getInt("Inventory-Part-Alert.Alert3.Slots-Filled")) - 1){
	        	messageAlert3.remove(p.getName());
	        }
	        
	        //alert4
	        if (invSlotsFull == (plugin.config.getInt("Inventory-Part-Alert.Alert4.Slots-Filled"))) {
	        	if (!messageAlert4.contains(p.getName())) {
	                 String message = ChatColor.BLUE + plugin.messages.getString("Actions.BlockBreak.Partial-Filled-Alert");
	                 p.sendMessage(message.replaceAll("\\*percentage\\*", invSlotsFullPercent));
                 messageAlert4.add(p.getName());
	        	}
	        }
	        if (invSlotsFull >= (plugin.config.getInt("Inventory-Part-Alert.Alert4.Slots-Filled")) + 1){
	        	messageAlert4.remove(p.getName());
	        }
	        
	        if (invSlotsFull <= (plugin.config.getInt("Inventory-Part-Alert.Alert4.Slots-Filled")) - 1){
	        	messageAlert4.remove(p.getName());
	        }
	        
	        if (p.hasPermission("InventoryFilled.alert")) {
	        	if (plugin.playerdata.getBoolean("Players." + uuid + ".Alerts") == true) {
	        		if (!p.getGameMode().equals(GameMode.CREATIVE)) {
	                    if (!InventorySpace.itemFits(p, e.getBlock().getDrops())) {
	            	        
	                    	PlayerReceiveAlertEvent event = new PlayerReceiveAlertEvent(p);
	                    	
	                    	Bukkit.getServer().getPluginManager().callEvent(event); //Run event
	                    	
	                    	if (event.isCancelled()) { //Make sure event is not called when cancelled
	                    		return;
	                    	}
	                    	
	            	        if (plugin.config.getBoolean("Disable-Block-Break-When-Full-Inv") == true) {
	            	        	e.setCancelled(true);
	            	        }
	                    	
	                    	if (plugin.config.getBoolean("Particle-Effect.Enabled")) {
		                    		
		                        Location loc = e.getBlock().getLocation();
		                        
		                        int radius = (int) 0.5;
		                        for(double y = 0; y <= 2; y+=0.05) {
		                            double x = radius * Math.cos(y);
		                            double z = radius * Math.sin(y);
		                            
		                            
		                            //Compatible with all versions
		                            loc.getWorld().spawnParticle(Particle.valueOf(plugin.config.getString("Particle-Effect.Effect")), (float) (loc.getX() + (x + 0.5)), (float) (loc.getY() + y), (float) (loc.getZ() + (z + 0.5)), 0, 0, 0, 0, 1, null);
	                            }
	                        }
	                    	
	                    	if(plugin.config.getBoolean("Chat-Alert.Enabled")) 
	                        p.sendMessage(ChatColor.RED + plugin.messages.getString("Actions.BlockBreak.Chat-Alert-Message"));
	                    	if (plugin.config.getBoolean("Title-Alert.Enabled")) {
	                    		
	                    		p.sendTitle(ChatColor.RED + plugin.messages.getString("Actions.BlockBreak.Title-Alert-Message"), ChatColor.BLUE + plugin.messages.getString("Actions.BlockBreak.SubTitle-Alert-Message"), 5, 30, 15);
	                    
	                    		}
	                    	if (plugin.config.getBoolean("Sound-Alert.Enabled")) {
	                    		String sound = plugin.config.getString("Sound-Alert.Sound");
	                    		p.getWorld().playSound(playerLoc,Sound.valueOf(sound),1, 0);  
	                    		
	                    	} else {
	                    		return;
	                    	}
	                    }
	        		}
	        	}
	        }
		}
}
