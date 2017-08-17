package net.w6_.Cookie;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.w6_.Cookie.Listener.CookieListener;
import net.w6_.Cookie.Utils.CookieBank;

public class CookieClicker extends JavaPlugin {
	
	private static CookieClicker plugin;
	
	@SuppressWarnings("deprecation")
	@Override
	public void onEnable() {
		plugin = this;
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new CookieListener(), this);
	}
	
	public static CookieClicker getPlugin() {
		return plugin;
	}
}
