package org.pt.tomas.banPlugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.pt.tomas.banPlugin.Listeners.Listeners;
import org.pt.tomas.banPlugin.commands.banCommand;
import org.pt.tomas.banPlugin.commands.unbanCommand;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;
import java.util.ArrayList;


public final class Main extends JavaPlugin {
    public static Main instance;
    public ArrayList<String> uuidList;

    @Override
    public void onEnable() {
        instance = this;
        System.out.println("KitPvP iniciado com sucesso!");

        uuidList = new ArrayList<>();

        this.getServer().getPluginManager().registerEvents(new Listeners(), this);
        this.getCommand("banir").setExecutor(new banCommand());
        this.getCommand("desbanir").setExecutor(new unbanCommand());


        ArrayList<String> uuidList = new ArrayList<String>();
        getConfig().addDefault("uuids", uuidList);
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        BukkitTask uuids = new BukkitRunnable() {
            @Override
            public void run() {
                ArrayList<String> uuidList = (ArrayList<String>) getConfig().getStringList("uuids");

                for (String banido : uuidList) {
                    String[] partes = banido.split(" - ");
                    if (partes.length == 2) {
                        String uuid = partes[0];
                        if (Bukkit.getPlayer(UUID.fromString(uuid)) != null) {
                            Bukkit.getPlayer(UUID.fromString(uuid)).kickPlayer("Você está banido do servidor!");
                        }
                    }
                }
            }
        }.runTaskTimer(this, 0L, 20L);
    }
}
