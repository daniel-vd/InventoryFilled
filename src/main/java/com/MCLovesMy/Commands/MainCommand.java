package com.MCLovesMy.Commands;

import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.MCLovesMy.InventoryFilled;

public class MainCommand implements CommandExecutor{
	
	private InventoryFilled plugin;
	public MainCommand(InventoryFilled plugin) {
		this.plugin = plugin;
	}
	
	//Main Command
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		UUID uuid = p.getUniqueId();
		if(cmd.getName().equalsIgnoreCase("InventoryFilled")) {
			if(args.length == 0) {
				sender.sendMessage(ChatColor.BLUE + "InventoryFilled commands:");
				sender.sendMessage(ChatColor.BLUE + " - /if reload | Reload the config files");
				sender.sendMessage(ChatColor.BLUE + " - /if reload | Reload the config files");
				sender.sendMessage(ChatColor.BLUE + " - /if on/off | Turn the alerts on or off for yourself");
			}
			else if(args[0].equalsIgnoreCase("reload")) {
				if(sender.hasPermission("InventoryFilled.reload") || sender.isOp()){
					plugin.loadYamls();
					sender.sendMessage(ChatColor.GREEN + "InventoryFilled files succesfully reloaded!");
					return true;
				} else {
					sender.sendMessage(ChatColor.RED +  "You don't have permission for this!");
					return true;
				}
			}
			else if(args[0].equalsIgnoreCase("on")) {
				plugin.playerdata.set("Players." + uuid + ".Alerts", true);
				plugin.savePlayerDataFile();
				sender.sendMessage(ChatColor.GREEN + "You will get an alert when you have a full inventory now!");
				return true;
			}
			else if(args[0].equalsIgnoreCase("off")) {
				plugin.playerdata.set("Players." + uuid + ".Alerts", false);
				plugin.savePlayerDataFile();
				sender.sendMessage(ChatColor.GREEN + "You won't get an alert when you have a full inventory now!");
			} else {
				sender.sendMessage(ChatColor.RED + "That command does not exist!");
				sender.sendMessage(ChatColor.BLUE + "InventoryFilled commands:");
				sender.sendMessage(ChatColor.BLUE + " - /if reload | Reload the config files");
				sender.sendMessage(ChatColor.BLUE + " - /if reload | Reload the config files");
				sender.sendMessage(ChatColor.BLUE + " - /if on/off | Turn the alerts on or off for yourself");
				return true;
			} 
		}
		else if(cmd.getName().equalsIgnoreCase("if")) {
			if(args.length == 0) {
				sender.sendMessage(ChatColor.BLUE + "InventoryFilled commands:");
				sender.sendMessage(ChatColor.BLUE + " - /if reload | Reload the config files");
				sender.sendMessage(ChatColor.BLUE + " - /if reload | Reload the config files");
				sender.sendMessage(ChatColor.BLUE + " - /if on/off | Turn the alerts on or off for yourself");
			}
			else if(args[0].equalsIgnoreCase("reload")) {
				if(sender.hasPermission("InventoryFilled.reload") || sender.isOp()){
					plugin.loadYamls();
					sender.sendMessage(ChatColor.GREEN + "InventoryFilled files succesfully reloaded!");
					return true;
				} else {
					sender.sendMessage(ChatColor.RED + "You don't have permission for this!");
				}
			}
			else if(args[0].equalsIgnoreCase("on")) {
				plugin.playerdata.set("Players." + uuid + ".Alerts", true);
				plugin.savePlayerDataFile();
				sender.sendMessage(ChatColor.GREEN + "You will get an alert when you have a full inventory now!");
				return true;
			}
			else if(args[0].equalsIgnoreCase("off")) {
				plugin.playerdata.set("Players." + uuid + ".Alerts", false);
				plugin.savePlayerDataFile();
				sender.sendMessage(ChatColor.GREEN + "You won't get an alert when you have a full inventory now!");
			} else {
				sender.sendMessage(ChatColor.RED + "That command does not exist!");
				sender.sendMessage(ChatColor.BLUE + "InventoryFilled commands:");
				sender.sendMessage(ChatColor.BLUE + " - /if reload | Reload the config files");
				sender.sendMessage(ChatColor.BLUE + " - /if reload | Reload the config files");
				sender.sendMessage(ChatColor.BLUE + " - /if on/off | Turn the alerts on or off for yourself");
				return true;
			}
		}
		return true;
	}
}