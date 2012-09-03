package com.volumetricpixels.supported.NPCWorkers;

import org.spout.api.command.CommandRegistrationsFactory;
import org.spout.api.command.annotated.AnnotatedCommandRegistrationFactory;
import org.spout.api.command.annotated.SimpleAnnotatedCommandExecutorFactory;
import org.spout.api.command.annotated.SimpleInjector;
import org.spout.api.plugin.CommonPlugin;
import org.spout.api.util.config.yaml.YamlConfiguration;

import com.volumetricpixels.supported.NPCWorkers.commands.Command;
import com.volumetricpixels.supported.NPCWorkers.entities.ControllerTypes;

public class NPCWorkers extends CommonPlugin {

	public static YamlConfiguration data;

	//public final LumberjackTimer LumberjackTimer = new LumberjackTimer();

	@Override
	public void onDisable() {
		getLogger().info("Disabling.....");		
	}

	@Override
	public void onEnable() {
		//data = new YamlConfiguration(new File(this.getDataFolder(), "data.yml"));

		CommandRegistrationsFactory<Class<?>> commandRegFactory = new AnnotatedCommandRegistrationFactory(new SimpleInjector(this), new SimpleAnnotatedCommandExecutorFactory());
		getEngine().getRootCommand().addSubCommands(this, Command.class, commandRegFactory);
		//Fixes lazy jvm not loading the class
		ControllerTypes.NPC.toString();	    
		getLogger().info("Enabled!");
	}
}
