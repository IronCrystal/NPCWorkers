package com.volumetricpixels.supported.NPCWorkers.Events;

import org.spout.api.event.Cancellable;
import org.spout.api.event.HandlerList;

import com.volumetricpixels.supported.NPCWorkers.NPCs.Worker;

/**
 * This event is called whenever an NPC attacks another NPC
 * This event is called in EventListeners.class
 */
public class NPCAttackNPCEvent extends NPCEvent implements Cancellable {
	private static HandlerList handlers = new HandlerList();

	private Worker attacker;
	private Worker victim;
	private int damage;

	public NPCAttackNPCEvent(Worker attacker, Worker victim, int damage) {
		super(attacker);
		this.attacker = attacker;
		this.victim = victim;
		this.damage = damage;
	}

	public Worker getAttacker() {
		return attacker;		
	}

	public Worker getVictim() {
		return victim;
	}

	public Integer getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
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
