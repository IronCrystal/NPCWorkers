package me.IronCrystal.NPCWorkers;

import java.io.File;

import me.IronCrystal.NPCWorkers.Commands.Commands;

import org.spout.api.plugin.CommonPlugin;
import org.spout.api.scheduler.TaskPriority;
import org.spout.api.util.config.yaml.YamlConfiguration;

import me.IronCrystal.NPCWorkers.Timers.*;

public class NPCWorkers extends CommonPlugin {

	public static YamlConfiguration data;
	
	public final LumberjackTimer LumberjackTimer = new LumberjackTimer();

	@Override
	public void onDisable() {
		getLogger().info("Disabling.....");		
	}

	@Override
	public void onEnable() {
		data = new YamlConfiguration(new File(this.getDataFolder(), "data.yml"));
	    this.getEngine().getRootCommand().addSubCommand(this.getEngine(), "lumberjack").setHelp("Spawns a Lumberjack").setExecutor(new Commands());
	    getLogger().info("Enabled!");
	    this.getEngine().getScheduler().scheduleSyncRepeatingTask(this, new LumberjackTimer(), 0, 20L, TaskPriority.MEDIUM);
	}
}
