package me.IronCrystal.NPCWorkers.Commands;

import me.IronCrystal.NPCWorkers.NPCs.Lumberjack;

import org.spout.api.command.Command;
import org.spout.api.command.CommandContext;
import org.spout.api.command.CommandExecutor;
import org.spout.api.command.CommandSource;
import org.spout.api.entity.Entity;
import org.spout.api.exception.CommandException;
import org.spout.api.geo.World;
import org.spout.api.player.Player;

public class Commands implements CommandExecutor {

	/*	private NPCWorkers plugin;
	public Commands(NPCWorkers instance){
		plugin = instance;
	}*/

	@Override
	public boolean processCommand(CommandSource source, Command cmd, CommandContext args) throws CommandException {
		Player player = null;
		if (source instanceof Player) {
			player = (Player) source;
		}
		if (player != null) 
		{
			if(cmd.getPreferredName().compareToIgnoreCase("lumberjack") == 0) {
				World world = player.getEntity().getWorld();
				player.sendMessage("You did a command!");
				Lumberjack lumberjack = null;
				lumberjack.spawn(player.getEntity().getPosition(), player);
				//Entity lumberjack = world.createAndSpawnEntity(player.getEntity().getPosition(), new Lumberjack ("Lumberjack"));
				//player.sendMessage("" + lumberjack.getClass());
				//lumberjack.setRenderedItemInHand(new ItemStack(Material.get((short) 279), 1));
			}
		}else{
			source.sendMessage("You must be a player to access this command.");
		}
		return false;
	}
}
