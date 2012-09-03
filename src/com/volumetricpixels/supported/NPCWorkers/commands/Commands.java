package com.volumetricpixels.supported.NPCWorkers.commands;

import org.spout.api.chat.ChatArguments;
import org.spout.api.chat.style.ChatStyle;
import org.spout.api.command.CommandContext;
import org.spout.api.command.CommandSource;
import org.spout.api.command.annotated.CommandPermissions;
import org.spout.api.entity.Controller;
import org.spout.api.entity.Player;
import org.spout.api.entity.controller.type.ControllerRegistry;
import org.spout.api.entity.controller.type.ControllerType;
import org.spout.api.exception.CommandException;
import org.spout.api.inventory.ItemStack;
import org.spout.vanilla.material.VanillaMaterials;

import com.volumetricpixels.supported.NPCWorkers.NPCWorkers;
import com.volumetricpixels.supported.NPCWorkers.entities.controller.NPC;

//public class Commands {

//	@SuppressWarnings("static-access")
//	@Override
//	public void processCommand(CommandSource source, Command cmd, CommandContext args) throws CommandException {
//		Player player = null;
//		if (source instanceof Player) {
//			player = (Player) source;
//		}
//		if (player != null) 
//		{
//			if(cmd.getPreferredName().compareToIgnoreCase("lumberjack") == 0) {
//				Lumberjack lumberjack = null;
//				lumberjack.spawn("Lumberjack", player.getPosition(), player);
//				player.sendMessage(ChatStyle.YELLOW, "[NPCWorkers] ", ChatStyle.CYAN, "Succesfully spawned a lumberjack");
//			}
//		}else{
//			source.sendMessage("[NPCWorkers] You must be a player to access this command.");
//		}
//	}

public class Commands {
	
	private final NPCWorkers plugin;

	public Commands(NPCWorkers plugin) {
		this.plugin = plugin;
	}

	//@Command(aliases = {"spawn"}, usage = "<controllertype>", desc = "Spawn a controller!", min = 1, max = 1)
	@CommandPermissions("droplet.command.spawn")
	public void spawn(CommandContext args, CommandSource source) throws CommandException {
		if (!(source instanceof Player)) {
			throw new CommandException("Must be in-game to spawn a controller!");
		}
		Player spawner = (Player) source;
		ControllerType spawning = ControllerRegistry.get(args.getString(0));
		if (spawning == null) {
			throw new CommandException("The type " + args.getString(0) + " was not found in the Controller Registry!");
		}
		if (!spawning.canCreateController()) {
			throw new CommandException("Cannot create the controller!");
		}
		Controller controller = spawning.createController();
		if (controller instanceof NPC) {
			((NPC) controller).setName("Spouty");
			((NPC) controller).setHeldItem(new ItemStack(VanillaMaterials.DIAMOND_SWORD, 1));
		}
		spawner.getWorld().createAndSpawnEntity(spawner.getPosition(), controller);
		source.sendMessage(new ChatArguments("[", ChatStyle.BLUE, plugin.getName(), ChatStyle.WHITE, "] Spawned a " + spawning.getName() + " controller."));
	}
}