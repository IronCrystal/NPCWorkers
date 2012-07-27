package me.IronCrystal.NPCWorkers.Events;

import me.IronCrystal.NPCWorkers.CustomBlocks.StorageChest;
import me.IronCrystal.NPCWorkers.NPCs.Worker;

import org.spout.api.event.Cancellable;
import org.spout.api.event.HandlerList;

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
