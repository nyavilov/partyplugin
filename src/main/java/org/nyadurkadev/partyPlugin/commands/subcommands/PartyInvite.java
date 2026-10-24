package org.nyadurkadev.partyPlugin.commands.subcommands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.nyadurkadev.partyPlugin.PartyPlugin;
import org.nyadurkadev.partyPlugin.api.entities.PartyEntity;
import org.nyadurkadev.partyPlugin.commands.SubCommand;

public class PartyInvite implements SubCommand {
    @Override
    public void execute(Player player, String[] args) {
        PartyEntity party = PartyPlugin.getManager()
                .getPartyByMember(player.getUniqueId());

        if (party == null) {
            player.sendMessage(Component.text(
                            "Вы не состоите в группе!")
                    .color(TextColor.fromHexString("#D93B1C")));
            return;
        }

        if (party.getLeader() != player.getUniqueId()) {
            player.sendMessage(Component.text(
                            "Вы не являетесь владельцем группы!")
                    .color(TextColor.fromHexString("#D93B1C")));
            return;
        }

        if (args.length < 2) {
            player.sendMessage(Component.text(
                            "Укажите никнейм игрока")
                    .color(TextColor.fromHexString("#D93B1C")));
            return;
        }

        String target = args[1];

        if (target.equals(player.getName())) return;

        if (Bukkit.getPlayer(target) == null) return;

        PartyPlugin.getManager()
                .inviteToParty(Bukkit.getPlayer(party.getLeader()),
                        Bukkit.getPlayer(target));
    }

    @Override
    public String getName() {
        return "invite";
    }
}
