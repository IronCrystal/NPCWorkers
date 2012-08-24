package com.volumetricpixels.supported.NPCWorkers.Listeners;

import org.spout.api.Spout;
import org.spout.api.event.EventHandler;
import org.spout.api.event.Listener;
import org.spout.api.geo.World;
import org.spout.api.geo.cuboid.Block;
import org.spout.api.geo.discrete.Point;
import org.spout.api.inventory.ItemStack;
import org.spout.api.material.Material;
import org.spout.api.math.Vector3;
import org.spout.vanilla.material.block.solid.Log;

import com.volumetricpixels.supported.NPCWorkers.NPCWorkers;
import com.volumetricpixels.supported.NPCWorkers.CustomBlocks.StorageChest;
import com.volumetricpixels.supported.NPCWorkers.Events.NPCBreakBlockEvent;
import com.volumetricpixels.supported.NPCWorkers.Events.NPCDigEvent;
import com.volumetricpixels.supported.NPCWorkers.Events.NPCMoveEvent;
import com.volumetricpixels.supported.NPCWorkers.Events.NPCSpawnEvent;
import com.volumetricpixels.supported.NPCWorkers.NPCs.Lumberjack;

public class LumberjackListener implements Listener {

	public static NPCWorkers plugin;
	public LumberjackListener(NPCWorkers instance)
	{
		plugin = instance;
	}

	@EventHandler
	void onLumberjackSpawn(NPCSpawnEvent event) {
		if (event.getWorker() instanceof Lumberjack) {
			Lumberjack lumberjack = (Lumberjack) event.getWorker();
			for (Block block : lumberjack.getNearbyLogs(20)) {
				if (block.getPosition().distance(lumberjack.getHeadPosition()) < 2) {
					lumberjack.move(block.getPosition().getX(), block.getPosition().getY(),block.getPosition().getZ());
					lumberjack.setLookingAtVector(block.getPosition());
				}
			}
		}
	}

	@EventHandler
	void onLumberjackMove (NPCMoveEvent event) {
		if (event.getWorker() instanceof Lumberjack) {
			Lumberjack lumberjack = (Lumberjack) event.getWorker();
			Point loc = lumberjack.getFeetPosition();
			if (lumberjack.getLookingAt().distance(loc) < 4) {
				lumberjack.setVelocity(Vector3.ZERO);
				if (lumberjack.getLookingAt().getClass().equals(Log.class)) { //Highly doubt that's correct
					lumberjack.startChopping();
				}
				else if (lumberjack.getLookingAt().getClass().equals(StorageChest.class)) {
					float x = lumberjack.getLookingAt().getX();
					float y = lumberjack.getLookingAt().getY();
					float z = lumberjack.getLookingAt().getZ();

					World world = lumberjack.getHeadPosition().getWorld();

					Point point = new Point(world, x, y, z);

					Block block = world.getBlock(point, world);

					StorageChest chest = (StorageChest) block;
					chest.getInventory().addItem(new ItemStack(Material.get("Log"), 1));
				}else{
					plugin.getLogger().info("Lumberjack had trouble finding a chest or tree!");
				}
			}
		}
	}

	@EventHandler
	void onLumberjackChopTree (NPCDigEvent event) {
		if (event.getWorker() instanceof Lumberjack) {
			Lumberjack lumberjack = (Lumberjack) event.getWorker();
			if (lumberjack.getDiggingTime() > 1200) { //Randomly picked a number
				lumberjack.getBlockLookingAt().setMaterial(Material.get("Air"));
				NPCBreakBlockEvent NPCBreakBlockEvent = new NPCBreakBlockEvent(lumberjack, lumberjack.getBlockLookingAt());
				Spout.getEventManager().callEvent(NPCBreakBlockEvent);
			}
		}
	}

	@EventHandler
	void onLumberjackBreakLog (NPCBreakBlockEvent event) {
		if (event.getWorker() instanceof Lumberjack) {
			Lumberjack lumberjack = (Lumberjack) event.getWorker();
			lumberjack.stopChopping();
			for (Block block : lumberjack.getNearbyBlocks(20)) {
				if (block instanceof StorageChest) {
					lumberjack.move(block.getX(), block.getY(), block.getZ());
					lumberjack.setLookingAtVector(block.getPosition());
				}
			}
		}
	}
}
