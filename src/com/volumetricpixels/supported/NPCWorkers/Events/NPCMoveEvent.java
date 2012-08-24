package com.volumetricpixels.supported.NPCWorkers.Events;

import org.spout.api.event.Cancellable;
import org.spout.api.event.HandlerList;
import org.spout.api.geo.discrete.Point;

import com.volumetricpixels.supported.NPCWorkers.NPCs.Worker;

/**
 * This event is called any time an NPC moves.
 * It is called in EventListeners.class
 */
public class NPCMoveEvent extends NPCEvent implements Cancellable {
	
	private static HandlerList handlers = new HandlerList();
	private Point to;
	private Point from;

	public NPCMoveEvent(Worker w, Point from, Point to) {
		super(w);
		this.to = to;
		this.from = from;
	}
	
	public Point getFrom() {
		return from;
	}
	
	public Point getTo() {
		return to;
	}
	
	public void setTo(Point to) {
		this.to = to;
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
