package com.MCLovesMy;

import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.MCLovesMy.Commands.MainCommand;
import com.MCLovesMy.Events.BlockBreak;
import com.MCLovesMy.Events.MobKill;
import com.MCLovesMy.Events.PlayerData.Join;
import com.MCLovesMy.Updaters.Updater;

public class InventoryFilled extends JavaPlugin implements CommandExecutor {

		public File configFile = new File(getDataFolder()+"/config.yml");
	    public FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
	    public File messagesFile = new File(getDataFolder()+"/messages.yml");
	    public FileConfiguration messages = YamlConfiguration.loadConfiguration(messagesFile);
	    public File playerdataFile = new File(getDataFolder()+"/playerdata.yml");
	    public FileConfiguration playerdata = YamlConfiguration.loadConfiguration(playerdataFile);
	    
	    public Server server = Bukkit.getServer();
	    public ConsoleCommandSender console = server.getConsoleSender();
	    
	    PluginDescriptionFile pdf = this.getDescription();
	    
	    
		public void onEnable() {
			getCommand("inventoryfilled").setExecutor(new MainCommand(this));
			getCommand("if").setExecutor(new MainCommand(this));
			registerEvents(this, new BlockBreak(this), new MobKill(this), new Join(this));
			
			loadConfiguration();
			saveConfigFile();
			saveMessagesFile();
			if (!playerdataFile.exists()) {
			savePlayerDataFile();
			}
			loadYamls();
			
			Metrics metrics = new Metrics(this);
		    
	        getServer().getScheduler().runTaskLaterAsynchronously(this, new Runnable() {
	            public void run() {
	            	console.sendMessage(ChatColor.BLUE + "============================================");
	            	console.sendMessage(ChatColor.GREEN + "[InventoryFilled]");
	            	console.sendMessage(ChatColor.GREEN + "InventoryFilled loaded succesfully!");
	                checkUpdate();
	        	    console.sendMessage(ChatColor.BLUE + "============================================");
	            }
	        }, 20L);
		}
		
		public void loadConfiguration() {
			//config.yml
			config.options().header(
					"InventoryFilled Config!\n Under this sentence you see the explanation about every option! \nUnder that you see all the options!\n"
					+ "PLEASE READ THE EXPLANATIONS BEFORE CHANGING OPTIONS! IT'S REALLY WORTH IT!"
					+ "DO NOT EDIT LINES WITH A # IN FRONT! THIS WILL NOT CHANGE SETTINGS! \n"
					+ "\n"
					+ "Turn the Chat-Alert on or off (true/false):\n"
					+ "Chat-Alert:\n"
					+ "  Enabled: true\n\n"
					+ "Turn the Title-Alert on or off (true/false):\n"
					+ "Title-Alert:\n"
					+ "  Enabled: true\n\n"
					+ "Turn the Sound-Alert on or off(true/false\n"
					+ "Change which sound will be played when a full inventory.\n"
					+ "For sounds see: http://jd.bukkit.org/org/bukkit/Sound.html\n"
					+ "Sound-Alert\n"
					+ "  Enabled: true\n"
					+ "  Sound: BLAZE_HIT\n\n"
					+ "Players can turn alerts on or off with /if on and /if off\n"
					+ "This Default-Alert-State option changes if players will get alerts by default, so when they did not turn it on/off yet.\n"
					+ "If true, players will get alerts by default. (So they have to do /if off if they don't want alerts)\n"
					+ "If false, players won't get alerts by default. (So they have to do /if on if the want alerts)\n"
					+ "Default-Alert-State: true"
					+ "\n\n"
					+ "Change which sound will be played when a full inventory.\n"
					+ "For a list of sounds please see: https://hub.spigotmc.org/javadocs/spigot/org/bukkit/Sound.html\n"
					+ "Sound-Alert-Sound: ENTITY_GHAST_HURT"
					+ "\n\n"
					+ "You can also send an message to the player when their inventory has a specific amount of full inventory slots.\n"
					+ "The message will be: 'Your inventory is filled for ...%!'"
					+ "You can change those amounts!\n"
					+ "If you don't understand this, look at this example:\n"
					+ "Let's say you set the amount of slots that must be filled to get a message, to 9.\n"
					+ "Now we will look at Drake, a player on your server.\n"
					+ "Drake has 9 filled slots, so Drake will get a message.\n"
					+ "You can set 4 amounts, but if you don't want 4, you can turn them off\n"
					+ "Inventory-Part-Alert:\n"
					+ "  Alert1:\n"
					+ "    Enabled: true\n"
					+ "    Slots-Filled: 9\n"
					+ "  Alert2:\n"
					+ "    Enabled: true\n"
					+ "    Slots-Filled: 18\n"
					+ "  Alert3:\n"
					+ "    Enabled: true\n"
					+ "    Slots-Filled: 27\n"
					+ "  Alert4:\n"
					+ "    Enabled: true\n"
					+ "    Slots-Filled: 32\n");
			if (!config.contains("Chat-Alert.Enabled")) {
				config.set("Chat-Alert.Enabled", true);
			}
			if (!config.contains("Title-Alert.Enabled")) {
				config.set("Title-Alert.Enabled", true);
			}
			if (!config.contains("Sound-Alert.Enabled")) {
				config.set("Sound-Alert.Enabled", true);
			}
			if (!config.contains("Sound-Alert.Sound")) {
				config.set("Sound-Alert.Sound", "ENTITY_GHAST_HURT");
			}
			config.set("Sound-Alert-Sound", null);
			if (!config.contains("Default-Alert-State")) {
				config.set("Default-Alert-State", true);
			}
			if (!config.contains("Inventory-Part-Alert.Alert1.Enabled")) {
				config.set("Inventory-Part-Alert.Alert1.Enabled", true);
			}
			if (!config.contains("Inventory-Part-Alert.Alert2.Enabled")) {
				config.set("Inventory-Part-Alert.Alert2.Enabled", true);
			}
			if (!config.contains("Inventory-Part-Alert.Alert3.Enabled")) {
				config.set("Inventory-Part-Alert.Alert3.Enabled", true);
			}
			if (!config.contains("Inventory-Part-Alert.Alert4.Enabled")) {
				config.set("Inventory-Part-Alert.Alert4.Enabled", true);
			}
			if (!config.contains("Inventory-Part-Alert.Alert1.Slots-Filled")) {
				config.set("Inventory-Part-Alert.Alert1.Slots-Filled", 9);
			}
			if (!config.contains("Inventory-Part-Alert.Alert2.Slots-Filled")) {
				config.set("Inventory-Part-Alert.Alert2.Slots-Filled", 18);
			}
			if (!config.contains("Inventory-Part-Alert.Alert3.Slots-filled")) {
				config.set("Inventory-Part-Alert.Alert3.Slots-Filled", 27);
			}
			if (!config.contains("Inventory-Part-Alert.Alert4.Slots-Filled")) {
				config.set("Inventory-Part-Alert.Alert4.Slots-Filled", 32);
			}
			config.options().copyDefaults(true).copyHeader(true);
			//messages.yml
			messages.addDefault("Actions.BlockBreak.Chat-Alert-Message", "You can't pick this block up, your inventory is full!");
			messages.addDefault("Actions.BlockBreak.Title-Alert-Message", "Inventory Full");
			messages.addDefault("Actions.BlockBreak.SubTitle-Alert-Message", "You can't pick this block up");
			messages.addDefault("Actions.MobKill.Chat-Alert-Message", "You can't pick the mob drops up, your inventory is full!");
			messages.addDefault("Actions.MobKill.Title-Alert-Message", "Inventory Full");
			messages.addDefault("Actions.MobKill.SubTitle-Alert-Message", "You can't pick the mob drops up!");
			messages.options().copyDefaults(true).copyHeader(true);
		}
		
		public void saveConfigFile() {
		    try {
		        config.save(configFile);
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
		
		public void saveMessagesFile() {
		    try {
		        messages.save(messagesFile);
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
		
		public void savePlayerDataFile() {
		    try {
		        playerdata.save(playerdataFile);
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
		
		public void loadYamls() {
		    try {
		        config.load(configFile);
		        messages.load(messagesFile);
		        playerdata.load(playerdataFile);;
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		
		public static void registerEvents(org.bukkit.plugin.Plugin plugin, Listener... listeners) {
			for (Listener listener : listeners) {
			Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
			}
		}
		
	    	public void checkUpdate() {
	    		console.sendMessage(ChatColor.DARK_AQUA + "Checking for InventoryFilled updates...");
	            final Updater updater = new Updater(this, 14072, false);
	            final Updater.UpdateResult result = updater.getResult();
	            switch (result) {
	                case FAIL_SPIGOT: {
	                	console.sendMessage(ChatColor.RED + "ERROR: The updater of InventoryFilled could not contact Spgitomc.org");
	                    break;
	                }
	                case NO_UPDATE: {
	                	console.sendMessage(ChatColor.DARK_AQUA + "The InventoryFilled updater works fine!");
	                	console.sendMessage(ChatColor.GREEN + "You have the latest InventoryFilled version!");
	                	console.sendMessage(ChatColor.DARK_AQUA + "Current version: " + pdf.getVersion());
	                    break;
	                }
	                case UPDATE_AVAILABLE: {
	                    String version = updater.getVersion();
	                	console.sendMessage(ChatColor.DARK_AQUA + "The InventoryFilled updater works fine!");
	                    console.sendMessage(ChatColor.GREEN + "An InventoryFilled update is found!");
	                    console.sendMessage(ChatColor.DARK_AQUA + "Your version: " + pdf.getVersion() + ". Newest Version: " + version);
	                    @SuppressWarnings("unused")
	    				Boolean updateAvailable = true;
	                    break;
	                }
	                default: {
	                    console.sendMessage(result.toString());
	                    break;
	                }
	            }
	    }
}
