package me.IronCrystal.NPCWorkers.Timers;

import me.IronCrystal.NPCWorkers.CustomBlocks.StorageChest;
import me.IronCrystal.NPCWorkers.NPCs.Lumberjack;
import me.IronCrystal.NPCWorkers.NPCs.Lumberjack.LumberjackState;

import org.spout.api.Spout;
import org.spout.api.entity.Entity;
import org.spout.api.geo.World;
import org.spout.api.geo.cuboid.Block;
import org.spout.api.geo.discrete.Point;
import org.spout.api.inventory.ItemStack;
import org.spout.api.material.Material;
import org.spout.api.math.Vector3;
import org.spout.vanilla.material.block.solid.Log;

public class LumberjackTimer implements Runnable {

	@Override
	public void run() {

		for (Entity entity : Spout.getEngine().getWorld("world").getAll()) {

			if (entity instanceof Lumberjack) {

				Lumberjack lumberjack = (Lumberjack) entity;


				// If the lumberjack is not walking or cutting, then walk to nearest tree

				if (lumberjack.getState() == LumberjackState.AT_CHEST){

					for (Block block : lumberjack.getNearbyBlocks(20)) {
						if (block.getClass() == Log.class) {
							Point point = block.getPosition();
							float x = point.getX();
							float y = point.getY();
							float z = point.getZ();

							lumberjack.move(x, y, z);
							lumberjack.setLookingAtVector(point);
							break;
						}
					}
				}
				// If the lumberjack is walking and is close to the tree, then chop it down.
				else if (lumberjack.getState() == LumberjackState.WALKING_AND_IS_NEAR_TREE) {
					lumberjack.setVelocity(Vector3.ZERO);
					lumberjack.startChopping();
				}

				//if the lumberjack has been cutting down the tree for a while.

				else if (lumberjack.getState() == LumberjackState.CHOPPING_AND_HAS_BEEN_CHOPPING_FOR_LONG_ENOUGH) {

					lumberjack.stopChopping();

					World world = lumberjack.getHeadPosition().getWorld();

					Block block = world.getBlock(lumberjack.getLookingAt());
					block.setMaterial(Material.get("Air"));

					for (Block blocks : lumberjack.getNearbyBlocks(50)) {
						if (blocks.getClass() == StorageChest.class) {
							Point point = blocks.getPosition();
							float x = point.getX();
							float y = point.getY();
							float z = point.getZ();

							lumberjack.move(x, y, z);
							lumberjack.setLookingAtVector(point);
							break;
						}
					}					
				}
				
				// If Lumberjack is returning to chest and is near chest.
				
				else if (lumberjack.getState() == LumberjackState.RETURNING_TO_CHEST_AND_IS_NEAR_CHEST) {
					lumberjack.setVelocity(Vector3.ZERO);
					//lumberjack.setRenderedItemInHand(null);
					
					float x = lumberjack.getLookingAt().getX();
					float y = lumberjack.getLookingAt().getY();
					float z = lumberjack.getLookingAt().getZ();
					
					World world = lumberjack.getHeadPosition().getWorld();

					Point point = new Point(world, x, y, z);
					
					Block block = world.getBlock(point);
					
					if (block instanceof StorageChest) {
						StorageChest chest = (StorageChest) block;
						chest.getInventory().addItem(new ItemStack(Material.get("Log"), 1));
					}
				}
			}
		}
	}
}