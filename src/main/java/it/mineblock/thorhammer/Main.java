package it.mineblock.thorhammer;

import it.mineblock.mbcore.spigot.Chat;
import it.mineblock.mbcore.spigot.MBConfig;
import it.mineblock.mbcore.spigot.config.Configuration;
import it.mineblock.thorhammer.commands.GetThor;
import it.mineblock.thorhammer.listeners.SpawnThor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main extends JavaPlugin {

    private final String CONFIG = "config.yml";

    private static MBConfig configuration = new MBConfig();

    public static Configuration config;
    public static Random random;

    @Override
    public void onEnable() {

        config = configuration.autoloadConfig(this, this.getName(), getResource(CONFIG), new File(getDataFolder(), CONFIG), CONFIG);

        random = new Random();

        registerCommands();
        registerListeners();
    }

    @Override
    public void onDisable() {

    }

    private void registerCommands(){
        getCommand("gethammer").setExecutor(new GetThor());
    }

    private void registerListeners(){
        PluginManager pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(new SpawnThor(), this);
    }

    public static List<String> getTranslated(List<String> msgs){
        List<String> newmsgs = new ArrayList<>();
        for(String msg : msgs){
            newmsgs.add(Chat.getTranslated(msg));
        }
        return newmsgs;
    }
}
