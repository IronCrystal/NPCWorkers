package me.IronCrystal.NPCWorkers.NPCs;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import me.IronCrystal.NPCWorkers.Events.NPCSpawnEvent;

import org.spout.api.Spout;
import org.spout.api.entity.Entity;
import org.spout.api.geo.World;
import org.spout.api.geo.cuboid.Block;
import org.spout.api.geo.discrete.Point;
import org.spout.api.player.Player;
import org.spout.vanilla.controller.living.Human;

public class Worker extends Human {

	private UUID id;
	public Worker(String name) {
		id = UUID.randomUUID();
	}
	
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
	 * Gets the Worker's owner
	 * Not ready.  Needs a way to get owner.
	 * @return Player
	 */
	public Player getOwner() {
		return null;
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
	 * Gets the Worker's home chest
	 * Not ready.  Need a way to get block.
	 * @return Block
	 */
	public Block getHomeChest() {
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
	 * Gets the nearest storage chest for the worker.
	 * Not ready.  Need a way to get block.
	 * @return Block
	 */
	public Block getStorageChest() {
		return null;
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
}
