package com.volumetricpixels.supported.NPCWorkers.events;

import org.spout.api.entity.Player;
import org.spout.api.event.Cancellable;
import org.spout.api.event.HandlerList;

import com.volumetricpixels.supported.NPCWorkers.entities.controller.NPC;

/**
 * This event is called every time an NPC is spawned
 * It is called with the spawn method in worker
 */

public class NPCSpawnEvent extends NPCEvent implements Cancellable {

	private static HandlerList handlers = new HandlerList();
	
	private Player player;
	
	public NPCSpawnEvent(NPC npc, Player p) {
		super(npc);
//		if(NPCWorkers.data.getNode("workers." + p.getName()) == null) {
//			String[] string = {"workers." + p.getName()};
//			NPCWorkers.data.setNode(new ConfigurationNode(NPCWorkers.data, string, new ArrayList<String>()));
//		}
//		@SuppressWarnings("unchecked")
//		List<String> list = (List<String>) NPCWorkers.data.getNode("workers." + p.getName()).getList();
//		list.add(npc.getUUID().toString());
//		player = p;
	}

	@Override
	public void setCancelled(boolean cancel) {
		this.cancelled = cancel;
	}
	
	public Player getOwner() {
		return player;
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public HandlerList getHandlerList() {
		return handlers;
	}

}
