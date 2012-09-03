package com.volumetricpixels.supported.NPCWorkers.events;

import org.spout.api.event.Event;

import com.volumetricpixels.supported.NPCWorkers.entities.controller.NPC;

/**
 * This is called any time an event is related to an NPC
 */
public abstract class NPCEvent extends Event {
    
    private NPC npc;
    
    public NPCEvent(NPC npc) {
        this.npc = npc;
    }
    
    public NPC getWorker() {
        return npc;
    }    
}
