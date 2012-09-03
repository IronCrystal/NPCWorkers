package com.volumetricpixels.supported.NPCWorkers.entities.protocol;

import java.util.ArrayList;
import java.util.List;

import org.spout.api.entity.Controller;
import org.spout.api.entity.Entity;
import org.spout.api.geo.discrete.Transform;
import org.spout.api.inventory.ItemStack;
import org.spout.api.protocol.EntityProtocol;
import org.spout.api.protocol.Message;
import org.spout.api.util.Parameter;
import org.spout.vanilla.protocol.msg.DestroyEntitiesMessage;
import org.spout.vanilla.protocol.msg.entity.EntityEquipmentMessage;
import org.spout.vanilla.protocol.msg.entity.EntityRelativePositionMessage;
import org.spout.vanilla.protocol.msg.entity.EntityRelativePositionRotationMessage;
import org.spout.vanilla.protocol.msg.entity.EntityRotationMessage;
import org.spout.vanilla.protocol.msg.entity.EntitySpawnPlayerMessage;
import org.spout.vanilla.protocol.msg.entity.EntityTeleportMessage;
import org.spout.vanilla.protocol.msg.entity.EntityVelocityMessage;

import com.volumetricpixels.supported.NPCWorkers.entities.controller.NPC;


import static org.spout.vanilla.protocol.ChannelBufferUtils.protocolifyPosition;
import static org.spout.vanilla.protocol.ChannelBufferUtils.protocolifyRotation;

/**
 * Provides compatibility with VanillaPlugin's protocol
 */
public class NPCProtocol implements EntityProtocol {
	public Message[] getSpawnMessage(Entity entity) {
		Controller c = entity.getController();
		if (c == null || !(c instanceof NPC)) {
			return new Message[0];
		}
		NPC npc = (NPC) c;
		int id = entity.getId();
		int x = (int) (entity.getPosition().getX() * 32);
		int y = (int) (entity.getPosition().getY() * 32);
		int z = (int) (entity.getPosition().getZ() * 32);
		int r = (int) (-entity.getYaw() * 32);
		int p = (int) (entity.getPitch() * 32);

		int item = 0;
		ItemStack heldItem = npc.getHeldItem();
		if (heldItem != null) {
			item = heldItem.getMaterial().getId();
		}

		List<Parameter<?>> parameters = new ArrayList<Parameter<?>>();
		parameters.add(new Parameter<Byte>(Parameter.TYPE_BYTE, 0, (byte) 0));
		return new Message[]{new EntitySpawnPlayerMessage(id, npc.getName(), x, y, z, r, p, item, parameters), new EntityEquipmentMessage(id, 0, heldItem)};
	}

	public Message[] getDestroyMessage(Entity entity) {
		Controller c = entity.getController();
		if (c == null || !(c instanceof NPC)) {
			return new Message[0];
		}
		return new Message[]{new DestroyEntitiesMessage(new int[]{entity.getId()})};
	}

	public Message[] getUpdateMessage(Entity entity) {
		Controller c = entity.getController();
		if (c == null || !(c instanceof NPC)) {
			return new Message[0];
		}
		NPC npc = (NPC) c;
		Transform prevTransform = npc.getTransformLive();
		Transform newTransform = entity.getTransform();

		int lastX = protocolifyPosition(prevTransform.getPosition().getX());
		int lastY = protocolifyPosition(prevTransform.getPosition().getY());
		int lastZ = protocolifyPosition(prevTransform.getPosition().getZ());

		int newX = protocolifyPosition(newTransform.getPosition().getX());
		int newY = protocolifyPosition(newTransform.getPosition().getY());
		int newZ = protocolifyPosition(newTransform.getPosition().getZ());
		int newYaw = protocolifyRotation(newTransform.getRotation().getYaw());
		int newPitch = protocolifyRotation(newTransform.getRotation().getPitch());

		int deltaX = newX - lastX;
		int deltaY = newY - lastY;
		int deltaZ = newZ - lastZ;

		List<Message> messages = new ArrayList<Message>(3);

		if (npc.needsPositionUpdate() || deltaX > 128 || deltaX < -128 || deltaY > 128 || deltaY < -128 || deltaZ > 128 || deltaZ < -128) {
			messages.add(new EntityTeleportMessage(entity.getId(), newX, newY, newZ, newYaw, newPitch));
			npc.setTransformLive(newTransform);
		} else {
			boolean moved = !prevTransform.getPosition().equals(newTransform.getPosition());
			boolean looked = !prevTransform.getRotation().equals(newTransform.getRotation());
			if (moved) {
				if (looked) {
					messages.add(new EntityRelativePositionRotationMessage(entity.getId(), deltaX, deltaY, deltaZ, newYaw, newPitch));
					npc.setTransformLive(newTransform);
				} else {
					messages.add(new EntityRelativePositionMessage(entity.getId(), deltaX, deltaY, deltaZ));
					npc.setTransformLive(newTransform);
				}
			} else if (looked) {
				messages.add(new EntityRotationMessage(entity.getId(), newYaw, newPitch));
				npc.setTransformLive(newTransform);
			}
		}

		if (npc.needsVelocityUpdate()) {
			messages.add(new EntityVelocityMessage(entity.getId(), npc.getVelocity()));
		}
		return new Message[0];
	}

	@Override
	public List<Message> getDestroyMessages(Entity arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> getSpawnMessages(Entity arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> getUpdateMessages(Entity arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}