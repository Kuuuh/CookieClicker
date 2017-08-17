package net.w6_.Cookie.Utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class CookieBank {
	
	public static void createPlayerFolder() {
		File file = new File("/plugins/CookieBank/");
		if (!file.exists()) {
			file.mkdir();
		}
	}
	
	public static void createPlayerFile(Player p) {
		File file = new File("/plugins/CookieBank/", p.getUniqueId() + "-CookieStats.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		if (!file.exists()) {
			try {
				file.createNewFile();
				cfg.set("Cookies", 0);
				cfg.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static int getCookies(Player p) {
		File file = new File("/plugins/CookieBank/",p.getUniqueId() + "-CookieStats.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		int cookie = cfg.getInt("Cookies");
		return cookie;
	}
	
	public static void setCookies(Player p, int amount) {
		File file = new File("/plugins/CookieBank/",p.getUniqueId() + "-CookieStats.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		cfg.set("Cookies", amount);
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void addCookies(Player p, int amount) {
		File file = new File("/plugins/CookieBank/",p.getUniqueId() + "-CookieStats.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		int cookiesbefore = cfg.getInt("Cookies");
		int after = cookiesbefore + amount;
		cfg.set("Cookies", after);
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void removeCookies(Player p, int amount) {
		File file = new File("/plugins/CookieBank/", p.getUniqueId() + "-CookieStats.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		int cookiesbefore = cfg.getInt("Cookies");
		int after = cookiesbefore - amount;
		cfg.set("Cookies", after);
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
