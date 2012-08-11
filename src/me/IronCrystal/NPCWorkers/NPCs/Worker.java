package me.IronCrystal.NPCWorkers.NPCs;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import me.IronCrystal.NPCWorkers.Events.NPCSpawnEvent;

import org.spout.api.Spout;
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
	 * @param Point
	 * @param Worker
	 * @return Worker
	 */
	public static Worker spawn(Point point, Worker worker) {
		worker = (Worker) point.getWorld().createAndSpawnEntity(point, worker);
		Spout.getEventManager().callEvent(new NPCSpawnEvent(worker));
		return worker;
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
