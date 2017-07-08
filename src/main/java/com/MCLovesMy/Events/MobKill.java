package com.MCLovesMy.Events;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import com.MCLovesMy.InventoryFilled;

public class MobKill implements Listener{
	
	private InventoryFilled plugin;
	public MobKill(InventoryFilled plugin) {
		this.plugin = plugin;
	}
	
	private static ArrayList<String> messageAlert1 = new ArrayList<String>();
	private static ArrayList<String> messageAlert2 = new ArrayList<String>();
	private static ArrayList<String> messageAlert3 = new ArrayList<String>();
	private static ArrayList<String> messageAlert4 = new ArrayList<String>();
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent e){
        LivingEntity entity = e.getEntity();
        
        if (!(entity.getKiller() instanceof Player)) {
        	return;
        }
    	Player killer = (Player) entity.getKiller();
        UUID uuid = killer.getUniqueId();
        
        ItemStack[] contents = killer.getInventory().getContents();
		
		int count = 0;
		for (int i = 0; i < contents.length; i++) {
		    if (contents[i] == null)
		        count++;
		}
		
		double invPercent = (100. / 36);
		int invSlotsFull = 36 - count;
		int invSlotsPercentFull = (int) (invPercent * invSlotsFull);
		
		String invSlotsFullPercent = invSlotsPercentFull + "%";
		
		//alert1
        if (invSlotsFull == (plugin.config.getInt("Inventory-Part-Alert.Alert1.Slots-Filled"))) {
        	if (!messageAlert1.contains(killer.getName())) {
             killer.sendMessage(ChatColor.BLUE + "Your inventory is filled for " + invSlotsFullPercent + "!");
             messageAlert1.add(killer.getName());
        	}
        }
        if (invSlotsFull >= (plugin.config.getInt("Inventory-Part-Alert.Alert1.Slots-Filled")) + 1){
        	messageAlert1.remove(killer.getName());
        }
        
        if (invSlotsFull <= (plugin.config.getInt("Inventory-Part-Alert.Alert1.Slots-Filled")) - 1){
        	messageAlert1.remove(killer.getName());
        }
        
        //alert2
        if (invSlotsFull == (plugin.config.getInt("Inventory-Part-Alert.Alert2.Slots-Filled"))) {
        	if (!messageAlert2.contains(killer.getName())) {
             killer.sendMessage(ChatColor.BLUE + "Your inventory is filled for " + invSlotsFullPercent + "!");
             messageAlert2.add(killer.getName());
        	}
        }
        if (invSlotsFull >= (plugin.config.getInt("Inventory-Part-Alert.Alert2.Slots-Filled")) + 1){
        	messageAlert2.remove(killer.getName());
        }
        
        if (invSlotsFull <= (plugin.config.getInt("Inventory-Part-Alert.Alert1.Slots-Filled")) - 1){
        	messageAlert2.remove(killer.getName());
        }
        
        //alert3
        if (invSlotsFull == (plugin.config.getInt("Inventory-Part-Alert.Alert3.Slots-Filled"))) {
        	if (!messageAlert3.contains(killer.getName())) {
             killer.sendMessage(ChatColor.BLUE + "Your inventory is filled for " + invSlotsFullPercent + "!");
             messageAlert3.add(killer.getName());
        	}
        }
        if (invSlotsFull >= (plugin.config.getInt("Inventory-Part-Alert.Alert3.Slots-Filled")) + 1){
        	messageAlert3.remove(killer.getName());
        }
        
        if (invSlotsFull <= (plugin.config.getInt("Inventory-Part-Alert.Alert3.Slots-Filled")) - 1){
        	messageAlert3.remove(killer.getName());
        }
        
        //alert4
        if (invSlotsFull == (plugin.config.getInt("Inventory-Part-Alert.Alert4.Slots-Filled"))) {
        	if (!messageAlert4.contains(killer.getName())) {
             killer.sendMessage(ChatColor.BLUE + "Your inventory is filled for " + invSlotsFullPercent + "!");
             messageAlert4.add(killer.getName());
        	}
        }
        if (invSlotsFull >= (plugin.config.getInt("Inventory-Part-Alert.Alert4.Slots-Filled")) + 1){
        	messageAlert4.remove(killer.getName());
        }
        
        if (invSlotsFull <= (plugin.config.getInt("Inventory-Part-Alert.Alert4.Slots-Filled")) - 1){
        	messageAlert4.remove(killer.getName());
        }
        
        
        
        
		if(entity.getLastDamageCause() instanceof EntityDamageByEntityEvent){ //the dead thing was killed by an entity
            if(entity.getKiller() instanceof Player){ //the killer was a player
                if (killer.hasPermission("InventoryFilled.alert")) {
        		if (plugin.playerdata.getBoolean("Players." + uuid + ".Alerts") == true) {
        		if (!killer.getGameMode().equals(GameMode.CREATIVE)) {
                if (killer.getInventory().firstEmpty() == -1){
    	            for(ItemStack item : (e.getDrops())) {
    	                for (int i=0; i<35; i++) {
    	                    if (killer.getInventory().getItem(i).getAmount()+item.getAmount()<=64) {
    	                        if (killer.getInventory().getItem(i).getType().equals(item.getType())) {
    	                       
    	                            break;
    	                        }
    	                    }
    	                    if (i==34) {
    	                    	if(plugin.config.getBoolean("Chat-Alert.Enabled")) {
    	                        killer.sendMessage(ChatColor.RED + plugin.messages.getString("Actions.MobKill.Chat-Alert-Message"));
    	                        return;
    		                   	} else {
    		                   		return;
    		                   	}
    	                    }
    	                }
    		                    }
    	                    }
    	                }
    	            }
                }
            }
		}
	}
	
	@EventHandler
	public void onEntityDeath1(EntityDeathEvent e){
        Entity entity = e.getEntity();
		if(entity.getLastDamageCause() instanceof EntityDamageByEntityEvent){ //the dead thing was killed by an entity
			EntityDamageByEntityEvent entityDamageByEntityEvent = (EntityDamageByEntityEvent) entity.getLastDamageCause();
            if(entityDamageByEntityEvent.getDamager() instanceof Player){ //the killer was a player
            	Player killer = (Player)entityDamageByEntityEvent.getDamager();
                UUID uuid = killer.getUniqueId();
                if (killer.hasPermission("InventoryFilled.alert")) {
        		if (plugin.playerdata.getBoolean("Players." + uuid + ".Alerts") == true) {
            	if (!killer.getGameMode().equals(GameMode.CREATIVE)) {
                if (killer.getInventory().firstEmpty() == -1){
    	            for(ItemStack item : e.getDrops()){
    	                for (int i=0; i<35; i++) {
    	                    if (killer.getInventory().getItem(i).getAmount()+item.getAmount()<=64) {
    	                        if (killer.getInventory().getItem(i).getType().equals(item.getType())) {
    	                       
    	                            break;
    	                        }
    	                    }
    	                    if (i==34) {
    	                    	if (plugin.config.getBoolean("Title-Alert.Enabled")) {
    	                    		/*TitleManager.sendTimings(killer, 5, 30, 15);
    	                    		String raw2 = TellrawConverterLite.convertToJSON(ChatColor.RED + plugin.messages.getString("Actions.MobKill.Title-Alert-Message"));
    	                    		TitleManager.sendTitle(killer, raw2);
    	                    		String raw1 = TellrawConverterLite.convertToJSON(ChatColor.BLUE + plugin.messages.getString("Actions.MobKill.SubTitle-Alert-Message"));
    	                    		TitleManager.sendSubTitle(killer, raw1);*/
    	                    		
    	                    		killer.sendTitle(ChatColor.RED + plugin.messages.getString("Actions.MobKill.Title-Alert-Message"), ChatColor.BLUE + plugin.messages.getString("Actions.MobKill.SubTitle-Alert-Message"), 5, 30, 15);
    	                    		
    		                   	} else {
    		                   		return;
    		                   	}
    		                   	}
    		                    }
    	            }
    	                    }
    	                }
    	            }
                }
            }
		}
	}
	
	@EventHandler
	public void onEntityDeath2(EntityDeathEvent e){
        Entity entity = e.getEntity();
		if(entity.getLastDamageCause() instanceof EntityDamageByEntityEvent){ //the dead thing was killed by an entity
			EntityDamageByEntityEvent entityDamageByEntityEvent = (EntityDamageByEntityEvent) entity.getLastDamageCause();
            if(entityDamageByEntityEvent.getDamager() instanceof Player){ //the killer was a player
            	Player killer = (Player)entityDamageByEntityEvent.getDamager();
                UUID uuid = killer.getUniqueId();
                if (killer.hasPermission("InventoryFilled.alert")) {
        		if (plugin.playerdata.getBoolean("Players." + uuid + ".Alerts") == true) {
            	if (!killer.getGameMode().equals(GameMode.CREATIVE)) {
                if (killer.getInventory().firstEmpty() == -1){
    	            for(ItemStack item : e.getDrops()){
    	                for (int i=0; i<35; i++) {
    	                    if (killer.getInventory().getItem(i).getAmount()+item.getAmount()<=64) {
    	                        if (killer.getInventory().getItem(i).getType().equals(item.getType())) {
    	                       
    	                            break;
    	                        }
    	                    }
    	                    if (i==34) {
    	                    	if (plugin.config.getBoolean("Sound-Alert.Enabled")) {
    	                    		String sound = plugin.config.getString("Sound-Alert.Sound");
    	                    		Location loc = killer.getLocation();
    	                    		killer.getWorld().playSound(loc,Sound.valueOf(sound),1, 0);  
    	                    		return;
    	                    	} else {
    	                    		return;
    	                    	}
    	                    	}
    	                    	}
    	            }
    	                    }
    	                }
    	            }
                }
            }
		}
	}
}
