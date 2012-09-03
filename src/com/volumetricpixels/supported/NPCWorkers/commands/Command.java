package com.volumetricpixels.supported.NPCWorkers.commands;

import org.spout.api.command.CommandContext;
import org.spout.api.command.CommandSource;
import org.spout.api.command.annotated.NestedCommand;
import org.spout.api.exception.CommandException;

public class Command {

	//@Command(aliases = {"droplet"}, usage = "", desc = "Access droplet commands", min = 1, max = 1)
	@NestedCommand(Commands.class)
	public void droplet(CommandContext args, CommandSource source) throws CommandException {
	}
}