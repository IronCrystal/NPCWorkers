package me.IronCrystal.NPCWorkers.NPCs;

import me.IronCrystal.NPCWorkers.CustomBlocks.StorageChest;

import org.spout.api.geo.World;
import org.spout.api.geo.discrete.Point;
import org.spout.api.math.Vector3;
import org.spout.api.player.Player;
import org.spout.vanilla.material.block.solid.Log;

public class Lumberjack extends Worker {

	protected Lumberjack lumberjack;
	private LumberjackState state;

	public Lumberjack(String Name) {
		super(Name);
	}

	/**
	 * Just a little method to start digging
	 */
	public void startChopping() {
		float x = lumberjack.getLookingAt().getX();
		float y = lumberjack.getLookingAt().getY();
		float z = lumberjack.getLookingAt().getZ();

		World world = lumberjack.getHeadPosition().getWorld();

		Point point = new Point(world, x, y, z);

		lumberjack.startDigging(point);
	}
	
	public void stopChopping() {
		float x = lumberjack.getLookingAt().getX();
		float y = lumberjack.getLookingAt().getY();
		float z = lumberjack.getLookingAt().getZ();

		World world = lumberjack.getHeadPosition().getWorld();

		Point point = new Point(world, x, y, z);

		lumberjack.stopDigging(point);
	}

	/**
	 * Spawning a Lumberjack
	 * Needed?
	 * @param Point, Player
	 */
	public Lumberjack spawn(Point point, Player player) {
		Lumberjack lumberjack = (Lumberjack) point.getWorld().createAndSpawnEntity(point, new Lumberjack(player.getName() + "'s Lumberjack"));
		return lumberjack;
	}

	public LumberjackState getState() {

		if (lumberjack.getMovementSpeed() == Vector3.ZERO) {
			if (lumberjack.isDigging() == false) {
				this.state = LumberjackState.AT_CHEST;
			}else{
				if (lumberjack.getDiggingTime() > 5000) {
					this.state = LumberjackState.CHOPPING_AND_HAS_BEEN_CHOPPING_FOR_LONG_ENOUGH;
				}else{
					this.state = LumberjackState.CHOPPING;
				}
			}
		}else{
			if (lumberjack.getHeadPosition().distance(lumberjack.getLookingAt()) < 4) {
				if (lumberjack.getLookingAt().getClass().equals(Log.class)) {
					this.state = LumberjackState.WALKING_AND_IS_NEAR_TREE;
				}
				else if (lumberjack.getLookingAt().getClass().equals(StorageChest.class)){
					this.state = LumberjackState.RETURNING_TO_CHEST_AND_IS_NEAR_CHEST;
				}
			}else{
				if (lumberjack.getLookingAt().getClass().equals(Log.class)) {
					this.state = LumberjackState.WALKING_TO_TREE;
				}
				else if (lumberjack.getLookingAt().getClass().equals(StorageChest.class)){
					this.state = LumberjackState.RETURNING_TO_CHEST;
				}
			}
		}
		return state;
	}

	public enum LumberjackState {

		AT_CHEST,

		WALKING_TO_TREE,

		WALKING_AND_IS_NEAR_TREE,

		CHOPPING,

		CHOPPING_AND_HAS_BEEN_CHOPPING_FOR_LONG_ENOUGH,

		RETURNING_TO_CHEST,

		RETURNING_TO_CHEST_AND_IS_NEAR_CHEST;
	}
}