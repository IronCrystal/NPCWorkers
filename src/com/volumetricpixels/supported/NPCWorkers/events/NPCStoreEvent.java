package com.volumetricpixels.supported.NPCWorkers.events;

import org.spout.api.event.Cancellable;
import org.spout.api.event.HandlerList;

import com.volumetricpixels.supported.NPCWorkers.customblocks.StorageChest;
import com.volumetricpixels.supported.NPCWorkers.entities.controller.NPC;

/**
 * This event is called whenever a NPC stores an item in a Storage Chest
 * This event has yet to be called.
 */

public class NPCStoreEvent extends NPCEvent implements Cancellable {
    
	private static HandlerList handlers = new HandlerList();
	
	private StorageChest storagechest;

	public NPCStoreEvent(NPC npc, StorageChest storagechest) {
	    super(npc);
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
