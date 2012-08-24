package com.volumetricpixels.supported.NPCWorkers.Events;

/**
 * This event is called whenever the NPC is digging. (Moving arm up and down).
 * I have yet to figure out how to call this.
 */

import org.spout.api.event.HandlerList;

import com.volumetricpixels.supported.NPCWorkers.NPCs.Worker;

public class NPCDigEvent extends NPCEvent {

	private static HandlerList handlers = new HandlerList();

	public NPCDigEvent(Worker w) {
		super(w);
	}
	
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

}
