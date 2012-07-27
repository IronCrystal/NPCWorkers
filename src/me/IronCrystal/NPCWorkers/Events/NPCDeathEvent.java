package me.IronCrystal.NPCWorkers.Events;

import me.IronCrystal.NPCWorkers.NPCs.Worker;

import org.spout.api.event.Cancellable;
import org.spout.api.event.Event;
import org.spout.api.event.HandlerList;

public class NPCDeathEvent extends Event implements Cancellable {
	private static HandlerList handlers = new HandlerList();

	private Worker worker;

	public NPCDeathEvent(Worker worker) {
		this.worker = worker;
	}

	public Worker getWorker() {
		return worker;
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
