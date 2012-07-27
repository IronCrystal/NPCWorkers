package me.IronCrystal.NPCWorkers;

import me.IronCrystal.NPCWorkers.Commands.Commands;

import org.spout.api.plugin.CommonPlugin;
import org.spout.api.scheduler.TaskPriority;

import me.IronCrystal.NPCWorkers.Timers.*;

public class NPCWorkers extends CommonPlugin {

	//protected Logger logger = Logger.getLogger("NPCWorkers");
	
	public final LumberjackTimer LumberjackTimer = new LumberjackTimer();

	@Override
	public void onDisable() {
		getLogger().info("Disabling.....");		
	}

	@Override
	public void onEnable() {
	      this.getEngine().getRootCommand().addSubCommand(this.getEngine(), "lumberjack").setHelp("Spawns a Lumberjack").setExecutor(new Commands());
	      getLogger().info("Enabled!");
	      this.getEngine().getScheduler().scheduleSyncRepeatingTask(this, new LumberjackTimer(), 0, 20L, TaskPriority.MEDIUM);
	}

	/*public void example(CommandContext args, Command cmd, CommandSource source) throws CommandException {
		Player player = null;
		if (source instanceof Player) {
			player = (Player) source;
		}
		if (player != null) 
		{
			if (cmd.getPreferredName().equalsIgnoreCase("example"))
			{
				World world = player.getEntity().getWorld();
				player.getEntity().kill();
				//world.createAndSpawnEntity(player.getEntity().getPosition(), new Skeleton ());
			}
		}
	}*/
}
