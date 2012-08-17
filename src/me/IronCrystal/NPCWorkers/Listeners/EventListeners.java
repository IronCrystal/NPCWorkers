package me.IronCrystal.NPCWorkers.Listeners;

import me.IronCrystal.NPCWorkers.Events.NPCAttackNPCEvent;
import me.IronCrystal.NPCWorkers.Events.NPCDeathEvent;
import me.IronCrystal.NPCWorkers.Events.NPCMoveEvent;
import me.IronCrystal.NPCWorkers.NPCs.Worker;

import org.spout.api.Spout;
import org.spout.api.event.EventHandler;
import org.spout.api.event.Listener;
import org.spout.api.event.entity.EntityDeathEvent;
import org.spout.api.event.entity.EntityHealthChangeEvent;
import org.spout.api.event.entity.EntityMoveEvent;
import org.spout.api.geo.discrete.Point;

public class EventListeners implements Listener {

	@EventHandler
	void onNPCAttackNPC (EntityHealthChangeEvent event) {
		if (event.getSource() instanceof Worker) {
			if (event.getEntity() instanceof Worker) {
				Worker attacker = (Worker) event.getSource();
				Worker victim = (Worker) event.getEntity();
				int damage = event.getChange();
				NPCAttackNPCEvent NPCAttackNPC = new NPCAttackNPCEvent(attacker, victim, damage);
				Spout.getEventManager().callEvent(NPCAttackNPC);

				if (NPCAttackNPC.isCancelled()) {
					event.setCancelled(true);
				}else{
					event.setChange(NPCAttackNPC.getDamage());
				}
			}
		}
	}
	
	@EventHandler
	void onNPCMoveEvent (EntityMoveEvent event) {
		if (event.getEntity() instanceof Worker) {
			Worker worker = (Worker) event.getEntity();
			Point from = event.getFrom();
			Point to = event.getTo();
			NPCMoveEvent NPCMoveEvent = new NPCMoveEvent(worker, from, to);
			Spout.getEventManager().callEvent(NPCMoveEvent);
		}
	}
	
	@EventHandler
	void onNPCDeathEvent (EntityDeathEvent event) {
		if (event.getEntity() instanceof Worker) {
			Worker worker = (Worker) event.getEntity();
			NPCDeathEvent NPCDeathEvent = new NPCDeathEvent(worker);
			Spout.getEventManager().callEvent(NPCDeathEvent);
		}
	}
}
