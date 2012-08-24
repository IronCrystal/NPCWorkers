package com.volumetricpixels.supported.NPCWorkers.Commands;

import org.spout.api.chat.style.ChatStyle;
import org.spout.api.command.Command;
import org.spout.api.command.CommandContext;
import org.spout.api.command.CommandExecutor;
import org.spout.api.command.CommandSource;
import org.spout.api.entity.Player;
import org.spout.api.exception.CommandException;

import com.volumetricpixels.supported.NPCWorkers.NPCs.Lumberjack;

public class Commands implements CommandExecutor {

	@SuppressWarnings("static-access")
	@Override
	public void processCommand(CommandSource source, Command cmd, CommandContext args) throws CommandException {
		Player player = null;
		if (source instanceof Player) {
			player = (Player) source;
		}
		if (player != null) 
		{
			if(cmd.getPreferredName().compareToIgnoreCase("lumberjack") == 0) {
				Lumberjack lumberjack = null;
				lumberjack.spawn("Lumberjack", player.getPosition(), player);
				player.sendMessage(ChatStyle.YELLOW, "[NPCWorkers] ", ChatStyle.CYAN, "Succesfully spawned a lumberjack");
			}
		}else{
			source.sendMessage("[NPCWorkers] You must be a player to access this command.");
		}
	}
}
 