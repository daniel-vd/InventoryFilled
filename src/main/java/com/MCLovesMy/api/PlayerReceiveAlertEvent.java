package com.MCLovesMy.api;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

 /**
  * Will be called when a player's inventory is full and they receive an alert
  * 
  * @author Github/daniel-vd
  *
  */

public class PlayerReceiveAlertEvent extends Event implements Cancellable{

	private boolean cancelled;
	private Player player;
	private static final HandlerList handlers = new HandlerList();
	
	public PlayerReceiveAlertEvent(Player player) {
		super();
		this.cancelled = false;
		this.player = player;
	}
	
	/**
	 * {@link} org.bukkit.event.Cancellable#isCancelled()
	 */
	@Override
	public boolean isCancelled() {
		return cancelled;
	}
	
	
	/**
	 * {@link} org.bukkit.event.Cancellable#setCancelled(boolean)
	 */
	@Override
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
		
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList() {
		return handlers;
	}
	
	/**
	 * Gets the Player that is receiving the alert.
	 * @return the Player that is receiving the alert.
	 */
	public Player getPlayer(){
		return player;
	}

}
