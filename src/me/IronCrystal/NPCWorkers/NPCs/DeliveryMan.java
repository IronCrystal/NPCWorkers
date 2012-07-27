package me.IronCrystal.NPCWorkers.NPCs;

public class DeliveryMan extends Worker {
	protected DeliveryMan deliveryman;

	public DeliveryMan(String clientName) {
		super(clientName);
	}

	/**
	 * Gets the nearest Storage Chest
	 * Return Block
	 */
	/*public Block getNearbyStorageBlock(int radius) {

		Block blocks = null;

		Boolean isLog = false;

		Point headLoc = deliveryman.getHeadPosition();
		int locX = headLoc.getBlockX();
		int locY = headLoc.getBlockY();
		int locZ = headLoc.getBlockZ();
		World locWorld = headLoc.getWorld();

		Point loc = new Point(locWorld, locX, locY, locZ);

		OUTERMOST : for (int x = loc.getBlockX(); x < loc.getBlockX() + radius; x++) 
		{
			for (int y = loc.getBlockY(); y < loc.getBlockY() + 20; y++) 
			{
				for (int z = loc.getBlockZ(); z < loc.getBlockZ() + radius; z++) 
				{
					Point nl = new Point(loc.getWorld(), x, y, z);

					Block block = loc.getWorld().getBlock(nl);
					//If block is a storage block
						blocks = block;
						isLog = true;
						break OUTERMOST;
					//}
				}
			}

		}
		if (isLog == false) {

			OUTERMOST: for (int x = loc.getBlockX(); x > loc.getBlockX() - radius; x--) {

				for (int y = loc.getBlockY(); y > loc.getBlockY() - 20; y--) {

					for (int z = loc.getBlockZ(); z > loc.getBlockZ() - radius; z--) {

						Point nl = new Point(loc.getWorld(), x, y, z);

						Block block = loc.getWorld().getBlock(nl);

						//If block is a storage block
							blocks = block;
							break OUTERMOST;
						//}
					}
				}
			}
		}
		return blocks;
	}*/
}
