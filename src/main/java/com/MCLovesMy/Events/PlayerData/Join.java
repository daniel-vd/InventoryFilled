package com.MCLovesMy.Events.PlayerData;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import com.MCLovesMy.InventoryFilled;
import net.md_5.bungee.api.ChatColor;

public class Join implements Listener{
	
	private InventoryFilled plugin;
	public Join(InventoryFilled plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onPayerJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		String p = player.getName();
		UUID uuid = e.getPlayer().getUniqueId();
		if(!plugin.playerdata.contains("Players." + uuid)) {
			plugin.playerdata.set("Players." + uuid + ".Name", p);
			plugin.playerdata.set("Players." + uuid + ".Alerts", plugin.config.getBoolean("Default-Alert-State"));
			plugin.savePlayerDataFile();
			return;
		} 
	}
}
