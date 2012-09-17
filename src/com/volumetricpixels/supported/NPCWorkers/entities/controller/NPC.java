package com.volumetricpixels.supported.NPCWorkers.entities.controller;

import java.util.Collection;

import org.spout.api.chat.ChatArguments;
import org.spout.api.chat.style.ChatStyle;
import org.spout.api.data.Data;
import org.spout.api.entity.BasicController;
import org.spout.api.entity.Entity;
import org.spout.api.entity.Player;
import org.spout.api.event.player.PlayerInteractEvent.Action;
import org.spout.api.geo.cuboid.Block;
import org.spout.api.geo.discrete.Transform;
import org.spout.api.inventory.InventoryBase;
import org.spout.api.inventory.ItemStack;
import org.spout.api.math.MathHelper;
import org.spout.api.math.Vector3;
import org.spout.api.protocol.event.ProtocolEvent;
import org.spout.vanilla.entity.InventoryOwner;
import org.spout.vanilla.entity.VanillaPlayerController;
import org.spout.vanilla.entity.component.HealthOwner;
import org.spout.vanilla.entity.component.basic.HealthComponent;
import org.spout.vanilla.entity.object.moving.Item;
import org.spout.vanilla.window.Window;

import com.volumetricpixels.supported.NPCWorkers.entities.ControllerTypes;


public class NPC extends BasicController implements InventoryOwner, HealthOwner {
	private final Transform transformLive = new Transform();
	//Movement
	private Vector3 maxSpeed = new Vector3(0.4, 0.4, 0.4);
	private Vector3 velocity = Vector3.ZERO;
	private int positionTicks = 0, velocityTicks = 0;
	//
	private String name;
	//
	private ItemStack heldItem;

	public NPC() {
		super(ControllerTypes.NPC);
	}

	@Override
	public void onAttached() {
		if (name.isEmpty()) {
			name = getDataMap().get(Data.TITLE);
		}
		if (heldItem == null) {
			heldItem = getDataMap().get(Data.HELD_ITEM);
		}
	}

	@Override
	public void onTick(float dt) {
		super.onTick(dt);

		this.setVelocity(this.getVelocity().subtract(0, 0.04, 0));
		getParent().translate(MathHelper.min(getVelocity(), maxSpeed));
		this.setVelocity(this.getVelocity().multiply(0.98));

		//TODO Remove when collisions are in
		Block below = getParent().getWorld().getBlock(getParent().getPosition().subtract(0.0, 0.2, 0.0), getParent());
		if (below.getMaterial().isSolid()) {
			this.setVelocity(this.getVelocity().multiply(0.7, 0.0, 0.7).add(0.0, 0.06, 0.0));
		}
		positionTicks++;
		velocityTicks++;
	}

	@Override
	public void onInteract(Entity entity, Action action) {
		if (action == Action.RIGHT_CLICK) {
			if (entity instanceof Player) {
				Player player = (Player) entity;
				player.sendMessage(new ChatArguments("<", ChatStyle.BLUE, name, ChatStyle.WHITE, "> You touched me you dirty person!"));
			}
		} else if (action == Action.LEFT_CLICK) {
			//TODO Health
		}
	}

	@Override
	public void onDeath() {
		for (ItemStack drop : this.getInventory()) {//getDrops(getHealth().getLastDamageCause(), getHealth().getLastDamager())) {
			if(drop != null) {
				Item item = new Item(drop, Vector3.ZERO);
				getParent().getLastTransform().getPosition().getWorld().createAndSpawnEntity(getParent().getLastTransform().getPosition(), item);
			}
			// TODO: Drop experience
		}
	}

	@Override
	public boolean isSavable() {
		return true;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onSave() {
		super.onSave();
		getDataMap().put(Data.TITLE, name);
		getDataMap().put(Data.HELD_ITEM, heldItem);
	}

	public Transform getTransformLive() {
		return transformLive;
	}

	public void setTransformLive(Transform live) {
		if (live == null) {
			throw new NullPointerException("Live transform cannot be null!");
		}
	}

	public Vector3 getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector3 velocity) {
		this.velocity = velocity;
	}

	public boolean needsVelocityUpdate() {
		return velocityTicks % 5 == 0;
	}

	public boolean needsPositionUpdate() {
		return positionTicks % 30 == 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setHeldItem(ItemStack heldItem) {
		this.heldItem = heldItem;
	}

	public ItemStack getHeldItem() {
		return heldItem;
	}

	@Override
	public void addViewer(VanillaPlayerController arg0, Window arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean close(VanillaPlayerController arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void closeAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<VanillaPlayerController> getViewers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasViewers() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean open(VanillaPlayerController arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Window removeViewer(VanillaPlayerController arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void callProtocolEvent(ProtocolEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public HealthComponent getHealth() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InventoryBase getInventory() {
		// TODO Auto-generated method stub
		return null;
	}
}