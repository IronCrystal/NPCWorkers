package com.volumetricpixels.supported.NPCWorkers.Events;

import org.spout.api.event.Cancellable;
import org.spout.api.event.HandlerList;
import org.spout.api.geo.cuboid.Block;

import com.volumetricpixels.supported.NPCWorkers.NPCs.Worker;

/**
 * This event is called every time a NPC destroys a block
 * This event is yet to be called
 */
public class NPCBreakBlockEvent extends NPCEvent implements Cancellable {

	private static HandlerList handlers = new HandlerList();
	
	private Block block;

	public NPCBreakBlockEvent(Worker worker, Block block) {
	    super(worker);
		this.block = block;
	}

	public Block getBlock() {
		return block;
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
