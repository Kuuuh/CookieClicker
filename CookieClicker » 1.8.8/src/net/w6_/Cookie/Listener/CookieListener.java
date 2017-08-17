package net.w6_.Cookie.Listener;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Skull;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.w6_.Cookie.Utils.CookieBank;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;

public class CookieListener implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		CookieBank.createPlayerFolder();
		CookieBank.createPlayerFile(p);
	}

	public static void sendActionBar(Player p, String msg) {
		IChatBaseComponent cbc = ChatSerializer.a("{\"text\": \"" + msg + "\"}");
		PacketPlayOutChat ppoc = new PacketPlayOutChat(cbc,(byte) 2);
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(ppoc);
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if (e.getClickedBlock().getType() == Material.SKULL) {
			final Skull skull = (Skull)e.getClickedBlock().getState();
			if (skull.getOwner().equalsIgnoreCase("QuadratCookie")) {
				if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_BLOCK) {
					CookieBank.addCookies(e.getPlayer(), 1);
					e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.CLICK, 1, 1);
					sendActionBar(e.getPlayer(), "§8» §eDeine Cookies §8» §e" + CookieBank.getCookies(e.getPlayer()));
					e.getPlayer().playEffect(e.getClickedBlock().getLocation().add(0, 1, 0),	Effect.FLAME, 1);
				}
			}
		}
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		final Skull skull = (Skull) e.getBlock().getState();
		if (skull.getOwner().equalsIgnoreCase("QuadratCookie")) {
			e.setCancelled(true);
		}
	}
}
