package me.IronCrystal.NPCWorkers.Events;

import me.IronCrystal.NPCWorkers.NPCs.Worker;

import org.spout.api.event.Cancellable;
import org.spout.api.event.HandlerList;

public class NPCSpawnEvent extends NPCEvent implements Cancellable {

	private static HandlerList handlers = new HandlerList();
	
	public NPCSpawnEvent(Worker w) {
		super(w);
	}

	@Override
	public void setCancelled(boolean cancel) {
		this.cancelled = cancel;
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public HandlerList getHandlerList() {
		return handlers;
	}

}
