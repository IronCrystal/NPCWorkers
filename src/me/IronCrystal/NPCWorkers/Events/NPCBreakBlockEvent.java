package me.IronCrystal.NPCWorkers.Events;

import me.IronCrystal.NPCWorkers.NPCs.Worker;

import org.spout.api.event.Cancellable;
import org.spout.api.event.Event;
import org.spout.api.event.HandlerList;
import org.spout.api.geo.cuboid.Block;

public class NPCBreakBlockEvent extends Event implements Cancellable{

	private static HandlerList handlers = new HandlerList();

	private Worker worker;
	private Block block;

	public NPCBreakBlockEvent(Worker worker, Block block) {
		this.worker = worker;
		this.block = block;
	}

	public Block getBlock() {
		return block;
	}

	public Worker getNPC() {
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
