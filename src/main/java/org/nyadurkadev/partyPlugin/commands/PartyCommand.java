package org.nyadurkadev.partyPlugin.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.nyadurkadev.partyPlugin.commands.subcommands.PartyAccept;
import org.nyadurkadev.partyPlugin.commands.subcommands.PartyCreate;
import org.nyadurkadev.partyPlugin.commands.subcommands.PartyInfo;
import org.nyadurkadev.partyPlugin.commands.subcommands.PartyInvite;

import java.util.HashMap;
import java.util.Map;

public class PartyCommand implements CommandExecutor {
    private final Map<String, SubCommand> subcommands = new HashMap<>();

    public PartyCommand() {
        subcommands.put("create", new PartyCreate());
        subcommands.put("info", new PartyInfo());
        subcommands.put("invite", new PartyInvite());
        subcommands.put("accept", new PartyAccept());
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Команда для игроков");
            return true;
        }

        if (args.length == 0) {
            player.sendMessage(Component.text(
                            "Укажите параметры. Доступные: info, create, invite, create")
                    .color(TextColor.fromHexString("#D93B1C")));
            return true;
        }

        SubCommand sub = subcommands.get(args[0].toLowerCase());

        if (sub == null) {
            player.sendMessage(Component.text(
                    "Неизвестная команда!")
                    .color(TextColor.fromHexString("#D93B1C")));
            return true;
        }

        sub.execute(player, args);
        return true;
    }
}
