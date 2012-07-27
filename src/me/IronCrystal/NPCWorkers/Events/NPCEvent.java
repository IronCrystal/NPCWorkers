package me.IronCrystal.NPCWorkers.Events;

import me.IronCrystal.NPCWorkers.NPCs.Worker;

import org.spout.api.event.Event;

public abstract class NPCEvent extends Event {
    
    private Worker worker;
    
    public NPCEvent(Worker w) {
        this.worker = w;
    }
    
    public Worker getWorker() {
        return worker;
    }
    
}
