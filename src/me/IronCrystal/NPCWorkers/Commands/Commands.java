package me.IronCrystal.NPCWorkers.Commands;

import me.IronCrystal.NPCWorkers.NPCs.Lumberjack;

import org.spout.api.chat.style.ChatStyle;
import org.spout.api.command.Command;
import org.spout.api.command.CommandContext;
import org.spout.api.command.CommandExecutor;
import org.spout.api.command.CommandSource;
import org.spout.api.exception.CommandException;
import org.spout.api.player.Player;

public class Commands implements CommandExecutor {

	@SuppressWarnings("static-access")
	@Override
	public boolean processCommand(CommandSource source, Command cmd, CommandContext args) throws CommandException {
		Player player = null;
		if (source instanceof Player) {
			player = (Player) source;
		}
		if (player != null) 
		{
			if(cmd.getPreferredName().compareToIgnoreCase("lumberjack") == 0) {
				Lumberjack lumberjack = null;
				lumberjack.spawn("Lumberjack", player.getEntity().getPosition(), player);
				player.sendMessage(ChatStyle.YELLOW, "[NPCWorkers] ", ChatStyle.CYAN, "Succesfully spawned a lumberjack");
				return true;
			}
		}else{
			source.sendMessage("[NPCWorkers] You must be a player to access this command.");
			return true;
		}
		return false;
	}
}
 