package com.MCLovesMy.api;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerReceivesAlertEvent extends Event implements Cancellable{

	private boolean cancelled;
	private Player player;
	private static final HandlerList handlers = new HandlerList();
	
	public PlayerReceivesAlertEvent(Player player) {
		super();
		this.cancelled = false;
		this.player = player;
	}
	
	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
		
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public Player getPlayer(){
		return player;
	}

}
