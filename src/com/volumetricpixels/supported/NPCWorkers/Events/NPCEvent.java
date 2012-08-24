package com.volumetricpixels.supported.NPCWorkers.Events;

import org.spout.api.event.Event;

import com.volumetricpixels.supported.NPCWorkers.NPCs.Worker;

/**
 * This is called any time an event is related to an NPC
 */
public abstract class NPCEvent extends Event {
    
    private Worker worker;
    
    public NPCEvent(Worker w) {
        this.worker = w;
    }
    
    public Worker getWorker() {
        return worker;
    }
    
}
