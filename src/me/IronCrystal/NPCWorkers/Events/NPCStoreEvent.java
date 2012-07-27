package me.IronCrystal.NPCWorkers.Events;

import me.IronCrystal.NPCWorkers.CustomBlocks.StorageChest;
import me.IronCrystal.NPCWorkers.NPCs.Worker;

import org.spout.api.event.Cancellable;
import org.spout.api.event.Event;
import org.spout.api.event.HandlerList;

public class NPCStoreEvent extends Event implements Cancellable {
	private static HandlerList handlers = new HandlerList();
	
	private Worker worker;
	private StorageChest storagechest;

	public NPCStoreEvent(Worker worker, StorageChest storagechest) {
		this.worker = worker;
		this.storagechest = storagechest;
	}
	
	public Worker getNPC() {
		return worker;
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
