package org.nyadurkadev.partyPlugin.commands.subcommands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.nyadurkadev.partyPlugin.PartyPlugin;
import org.nyadurkadev.partyPlugin.api.entities.PartyEntity;
import org.nyadurkadev.partyPlugin.commands.SubCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PartyInfo implements SubCommand {
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

        List<String> memberNames = new ArrayList<>();
        for (UUID uuid : party.getMembers()) {
            String name = Bukkit.getOfflinePlayer(uuid).getName();
            memberNames.add(name);
        }

        String allMembers = String.join(", ", memberNames);

        player.sendMessage("§fЛидер: §e" + Bukkit.getOfflinePlayer(party.getLeader()).getName());
        player.sendMessage("§fУчастники пати: " + allMembers);
    }

    @Override
    public String getName() {
        return "info";
    }
}
