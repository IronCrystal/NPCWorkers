package com.volumetricpixels.supported.NPCWorkers.entities;

import com.volumetricpixels.supported.NPCWorkers.entities.protocol.NPCProtocol;
import com.volumetricpixels.supported.NPCWorkers.entities.controller.NPC;
import com.volumetricpixels.supported.NPCWorkers.entities.controller.Builder;
import com.volumetricpixels.supported.NPCWorkers.entities.controller.DeliveryMan;
import com.volumetricpixels.supported.NPCWorkers.entities.controller.Farmer;
import com.volumetricpixels.supported.NPCWorkers.entities.controller.Guard;
import com.volumetricpixels.supported.NPCWorkers.entities.controller.Hunter;
import com.volumetricpixels.supported.NPCWorkers.entities.controller.Lumberjack;
import com.volumetricpixels.supported.NPCWorkers.entities.controller.Miner;
import com.volumetricpixels.supported.NPCWorkers.entities.controller.Thief;
import com.volumetricpixels.supported.NPCWorkers.entities.controller.Warrior;

public class ControllerTypes {
	public static final ControllerType NPC = new ControllerType(NPC.class, "NPC", new NPCProtocol());
	
	public static final ControllerType BUILDER = new ControllerType(Builder.class, "Builder");
	public static final ControllerType DELIVERY_MAN = new ControllerType(DeliveryMan.class, "Delivery Man");
	public static final ControllerType FARMER = new ControllerType(Farmer.class, "Farmer");
	public static final ControllerType GUARD = new ControllerType(Guard.class, "Guard");
	public static final ControllerType HUNTER = new ControllerType(Hunter.class, "Hunter");
	public static final ControllerType LUMBERJACK = new ControllerType(Lumberjack.class, "Lumberjack");
	public static final ControllerType MINER = new ControllerType(Miner.class, "Miner");
	public static final ControllerType THIEF = new ControllerType(Thief.class, "Thief");
	public static final ControllerType WARRIOR = new ControllerType(Warrior.class, "Warrior");
}