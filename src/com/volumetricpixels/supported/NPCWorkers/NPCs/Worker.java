package com.volumetricpixels.supported.NPCWorkers.NPCs;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.volumetricpixels.supported.NPCWorkers.Events.NPCSpawnEvent;

import org.spout.api.Spout;
import org.spout.api.entity.Entity;
import org.spout.api.entity.Player;
import org.spout.api.geo.World;
import org.spout.api.geo.cuboid.Block;
import org.spout.api.geo.discrete.Point;
import org.spout.api.inventory.InventoryBase;
import org.spout.api.inventory.ItemStack;
import org.spout.api.math.Vector3;
import org.spout.vanilla.entity.component.basic.HeadComponent;
import org.spout.vanilla.entity.creature.neutral.Human;

public class Worker extends Human {

	//private Inventory inventory;
	ItemStack renderedItemInHand;
	Boolean sprinting, isDigging;
	Point diggingPosition;
	long diggingStartTime;
	long previousDiggingTime = 0;

	private UUID id;
	public Worker(String name) {
		id = UUID.randomUUID();
	}
	
	/**
	 * Returns the block that the NPC is looking at.
	 * @return
	 */
	public Block getBlockLookingAt() {
		//		float x = this.getLookingAt().getX();
		//		float y = this.getLookingAt().getY();
		//		float z = this.getLookingAt().getZ();
		//
		//		World world = this.getHeadPosition().getWorld();
		//
		//		Point point = new Point(world, x, y, z);
		//
		//		Block block = world.getBlock(point, world);
		//		
		//		return block;
		return null;
	}

	/**
	 * Gets last time spent digging in real(client) ticks
	 * @return ticks spent digging
	 */
	public long getDiggingTicks() {
		return getDiggingTime() / 50;
	}
	
	/**
	 * Gets time spent digging
	 * @return time spent digging
	 */
	public long getDiggingTime() {
		if (!isDigging) {
			return previousDiggingTime;
		}

		// Is this correct?
		return System.currentTimeMillis() - diggingStartTime;
	}
	
	/**
	 * Returns the Point of the Human's feet.
	 * @return Point
	 */
	public Point getFeetPosition() {
		Point head = this.getHeadPosition();
		int x = head.getBlockX();
		int y = head.getBlockY();
		int z = head.getBlockZ();
		World world = head.getWorld();

		Point feet = new Point(world, y, z, x);
		return feet;
	}
	
	/**
	 * Returns the Point of the Human's head
	 * @return Point
	 */	
	public Point getHeadPosition() {
		return this.getComponent(HeadComponent.class).getPosition();
	}
	
	/**
	 * Gets the Worker's home chest
	 * Not ready.  Need a way to get block.
	 * @return Block
	 */
	public Block getHomeChest() {
		return null;
	}
	
	/**
	 * Gets the Human's inventory
	 * @return InventoryBase?
	 */
	public InventoryBase getInventory() {
		//Serializable inv = this.getDataMap().put("", inventory);
		return null;
	}
	
	/**
	 * Gets the vector3 that the human is looking at.
	 * @return Vector3
	 */
	public Vector3 getLookingAt() {
		return this.getComponent(HeadComponent.class).getLookingAt();
	}
	
	/**
	 * Returns all the nearby blocks of the worker
	 * @param Int
	 * @return List<Block>
	 */
	public List<Block> getNearbyBlocks(int radius) {

		List<Block> blocks = new ArrayList<Block>();

		Point headLoc = this.getHeadPosition();
		int locX = headLoc.getBlockX();
		int locY = headLoc.getBlockY();
		int locZ = headLoc.getBlockZ();
		World locWorld = headLoc.getWorld();

		Point loc = new Point(locWorld, locX, locY, locZ);

		for (int x = loc.getBlockX(); x < loc.getBlockX() + radius; x++) 
		{
			for (int y = loc.getBlockY(); y < loc.getBlockY() + 20; y++) 
			{
				for (int z = loc.getBlockZ(); z < loc.getBlockZ() + radius; z++) 
				{
					/**
					 * Not sure if this works....Just for now.  Haven't tested.
					 */
					Block block = loc.getWorld().getBlock(x, y, z, locWorld);

					blocks.add(block);

				}
			}

		}
		for (int x = loc.getBlockX(); x > loc.getBlockX() - radius; x--) {

			for (int y = loc.getBlockY(); y > loc.getBlockY() - 20; y--) {

				for (int z = loc.getBlockZ(); z > loc.getBlockZ() - radius; z--) {

					Block block = loc.getWorld().getBlock(x, y, z, locWorld);

					blocks.add(block);
				}
			}
		}
		return blocks;
	}
	
	/**
	 * Gets the Worker's owner
	 * Not ready.  Needs a way to get owner.
	 * @return Player
	 */
	public Player getOwner() {
		return null;
	}

	/**
	 * Gets the item rendered in the human's hand; not necessarily the actual item in the human's hand.
	 * @return rendered item in hand
	 */
	public ItemStack getRenderedItemInHand() {
		return renderedItemInHand;
	}
	
	/**
	 * Gets the nearest storage chest for the worker.
	 * Not ready.  Need a way to get block.
	 * @return Block
	 */	
	public Block getStorageChest() {
		return null;
	}
	
	/**
	 * Get the Worker's custom id.
	 * @return UUID
	 */
	public UUID getUUID() {
		return id;
	}
	
	/**
	 * Gets the Worker's worker chest
	 * Not ready.  Need a way to get block.
	 * @return Block
	 */
	public Block getWorkerChest() {
		return null;
	}
	
	/**
	 * Returns the digging state of the controller
	 * @return true if player is digging
	 */
	public boolean isDigging() {
		return isDigging;
	}
	
	/**
	 * Whether or not the worker is sprinting.
	 * @return true if sprinting
	 */
	public boolean isSprinting() {
		return sprinting;
	}
	
	/**
	 * Sets the Vector3 that the human is looking at.
	 * @param Vector3
	 */
	public void setLookingAtVector(Vector3 vector3) {
		this.getComponent(HeadComponent.class).setLooking(vector3);
	}
	
	/**
	 * Sets the item rendered in the human's hand; not necessarily the actual item in the human's hand.
	 * @param renderedItemInHand
	 */
	public void setRenderedItemInHand(ItemStack item) {
		this.renderedItemInHand = item;
	}
	
	/**
	 * Sets the visible skin of the Human
	 */
	public void setSkin() {

	}
	
	/**
	* Sets whether or not the worker is sprinting
	* @param sprinting
	*/
	public void setSprinting(boolean sprinting) {
		this.sprinting = sprinting;
	}

	/**
	 * Spawns the worker and calls NPCSpawnEvent.
	 * @param String
	 * @param Point
	 * @return Player
	 */
	@SuppressWarnings("unused")
	public static Worker spawn(String name, Point loc, Player p) {
		Worker worker = new Worker(name);
		World world = loc.getWorld();
		Entity entity = (Entity) world.createAndSpawnEntity(loc, worker);  //Hey this is untested so I have no clue how it should be.
		//Maybe what you put was right but I have no clue.
		NPCSpawnEvent NPCSpawnEvent = new NPCSpawnEvent(worker, p);
		Spout.getEventManager().callEvent(NPCSpawnEvent);
		if (NPCSpawnEvent.isCancelled()) {
			return null;
		}else{
			return worker;
		}
	}
	
	public boolean startDigging(Point position) {
		if (getParent().getPosition().getDistance(position) > 6) { // TODO: Actually get block reach from somewhere instead of just using 6
			return false;
		}
		isDigging = true;
		diggingPosition = position;
		diggingStartTime = System.currentTimeMillis();
		return true;
	}
	
	/**
	 * Sets isDigging false and records total time, unless the dig was invalid/never started.
	 * @return true if successful
	 */
	public boolean stopDigging(Point position) {
//		if (!isDigging) {
//			return false;
//		}
//		previousDiggingTime = getDiggingTime();
//		isDigging = false;
//		VanillaNetworkUtil.sendPacketsToNearbyPlayers(getParent(), getParent().getViewDistance(), new AnimationMessage(getParent().getId(), AnimationMessage.ANIMATION_NONE));
//		if (!position.equals(diggingPosition)) {
//			return false;
//		}
		return true;
	}
}
