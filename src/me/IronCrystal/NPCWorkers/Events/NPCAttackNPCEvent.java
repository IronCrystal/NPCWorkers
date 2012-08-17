package me.IronCrystal.NPCWorkers.Events;

import org.spout.api.event.Cancellable;
import org.spout.api.event.Event;
import org.spout.api.event.HandlerList;

import me.IronCrystal.NPCWorkers.NPCs.Worker;

public class NPCAttackNPCEvent extends Event implements Cancellable {
	private static HandlerList handlers = new HandlerList();

	private Worker attacker;
	private Worker victim;
	private int damage;

	public NPCAttackNPCEvent(Worker attacker, Worker victim, int damage) {
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
