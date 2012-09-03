package com.volumetricpixels.supported.NPCWorkers.events;

import org.spout.api.event.Cancellable;
import org.spout.api.event.HandlerList;

import com.volumetricpixels.supported.NPCWorkers.entities.controller.NPC;

/**
 * This event is called whenever an NPC attacks another NPC
 * This event is called in EventListeners.class
 */
public class NPCAttackNPCEvent extends NPCEvent implements Cancellable {
	private static HandlerList handlers = new HandlerList();

	private NPC attacker;
	private NPC victim;
	private int damage;

	public NPCAttackNPCEvent(NPC attacker, NPC victim, int damage) {
		super(attacker);
		this.attacker = attacker;
		this.victim = victim;
		this.damage = damage;
	}

	public NPC getAttacker() {
		return attacker;		
	}

	public NPC getVictim() {
		return victim;
	}

	public int getDamage() {
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
