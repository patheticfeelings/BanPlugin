package org.pt.tomas.banPlugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.pt.tomas.banPlugin.Main;

import java.util.List;

public class unbanCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("desbanir")) {
            if (args.length < 1) {
                sender.sendMessage("/desbanir <nickname ou UUID>");
                return true;
            }

            String nickname = args[0];

            if (nickname == null) {
                sender.sendMessage("Jogador não encontrado ou informação inválida.");
                return true;
            }

            List<String> uuidList = Main.instance.getConfig().getStringList("uuids");

            boolean estaBanido = false;
            for (String banido : uuidList) {

                String[] partes = banido.split(" - ");
                if (partes.length == 2) {
                    String uuid = partes[0];
                    String nicknames = partes[1];
                    if (uuid.equalsIgnoreCase(nickname) || nicknames.equalsIgnoreCase(nickname)) {
                        uuidList.remove(banido);
                        Main.instance.getConfig().set("uuids", uuidList);
                        Main.instance.saveConfig();
                        sender.sendMessage("Jogador desbanido com sucesso!");
                        estaBanido = true;
                        break;
                    }
                }
            }

            if (!estaBanido) {
                sender.sendMessage("O jogador não está banido.");
            }
        }
        return true;
    }
}