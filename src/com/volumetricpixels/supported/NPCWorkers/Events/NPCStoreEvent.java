package com.volumetricpixels.supported.NPCWorkers.Events;

import org.spout.api.event.Cancellable;
import org.spout.api.event.HandlerList;

import com.volumetricpixels.supported.NPCWorkers.CustomBlocks.StorageChest;
import com.volumetricpixels.supported.NPCWorkers.NPCs.Worker;

/**
 * This event is called whenever a NPC stores an item in a Storage Chest
 * This event has yet to be called.
 */

public class NPCStoreEvent extends NPCEvent implements Cancellable {
    
	private static HandlerList handlers = new HandlerList();
	
	private StorageChest storagechest;

	public NPCStoreEvent(Worker worker, StorageChest storagechest) {
	    super(worker);
		this.storagechest = storagechest;
	}
	
	public StorageChest getStorageChest() {
		return storagechest;
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
