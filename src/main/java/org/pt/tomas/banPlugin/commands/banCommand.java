package org.pt.tomas.banPlugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.pt.tomas.banPlugin.Main;

import java.util.ArrayList;

public class banCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (command.getName().equalsIgnoreCase("banir")) {
            if (!(commandSender instanceof Player)) {
                commandSender.sendMessage("Este comando só pode ser executado por jogadores.");
                return true;
            }
            Player player = (Player) commandSender;


            if (args.length < 1) {
                player.sendMessage("/banir [nickname]");
            } else {
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    player.sendMessage("Jogador inexistente.");
                    return true;
                }

                String uuid = (target.getUniqueId().toString() + " - " + target.getName());

                ArrayList<String> uuidList = (ArrayList<String>) Main.instance.getConfig().getStringList("uuids");
                uuidList.add(uuid);
                Main.instance.getConfig().set("uuids", uuidList);
                Main.instance.saveConfig();

                player.sendMessage("Jogador banido com sucesso!");
            }
        }
        return true;
    }
}
