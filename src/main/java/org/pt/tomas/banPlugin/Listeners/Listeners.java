package org.pt.tomas.banPlugin.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.pt.tomas.banPlugin.Main;

import java.util.ArrayList;

public class Listeners implements Listener {

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        String uuid = player.getUniqueId().toString();

        Main.instance.reloadConfig();
        ArrayList<String> uuidList = (ArrayList<String>) Main.instance.getConfig().getStringList("uuids");

        String uuidName = uuid + " - " + player.getName();
        if (uuidList.contains(uuidName)) {
            event.disallow(PlayerLoginEvent.Result.KICK_BANNED, "Você está banido do servidor!");
        }
    }
}
