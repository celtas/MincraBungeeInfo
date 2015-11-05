package mincrabungeeinfo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class MincraBungeeInfo extends Plugin {
	public static Configuration config;
    @Override
    public void onEnable() {
    	if (!getDataFolder().exists())
            getDataFolder().mkdir();

        	File file = new File(getDataFolder(), "config.yml");

        if (!file.exists()) {
            try {
				Files.copy(getResourceAsStream("config.yml"), file.toPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        
    	try {
			config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(getDataFolder(), "config.yml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        this.getProxy().getPluginManager().registerListener(this, new EventListener(this));
    }
    public void save() throws IOException{
    	ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, new File(getDataFolder(), "config.yml"));
    }
}