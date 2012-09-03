package com.volumetricpixels.supported.NPCWorkers.entities;

import org.spout.api.entity.Controller;
import org.spout.api.entity.controller.type.EmptyConstructorControllerType;
import org.spout.api.protocol.EntityProtocol;

import org.spout.vanilla.VanillaPlugin;

public class ControllerType extends EmptyConstructorControllerType {
	public ControllerType(Class<? extends Controller> controllerClass, String name) {
		this(controllerClass, name, null);
	}

	public ControllerType(Class<? extends Controller> controllerClass, String name, EntityProtocol protocol) {
		super(controllerClass, name);
		if (protocol != null) {
			//Vanilla compatibility
			setEntityProtocol(VanillaPlugin.VANILLA_PROTOCOL_ID, protocol);
		}
	}
}