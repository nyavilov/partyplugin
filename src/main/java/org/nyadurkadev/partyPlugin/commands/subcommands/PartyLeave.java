package org.nyadurkadev.partyPlugin.commands.subcommands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;
import org.nyadurkadev.partyPlugin.PartyPlugin;
import org.nyadurkadev.partyPlugin.api.entities.PartyEntity;
import org.nyadurkadev.partyPlugin.commands.SubCommand;
import org.nyadurkadev.partyPlugin.utils.PartyManager;

public class PartyLeave implements SubCommand {
    @Override
    public void execute(Player player, String[] args) {
        PartyManager manager = PartyPlugin.getManager();
        PartyEntity party = manager.getPartyByMember(player.getUniqueId());

        if (party == null) {
            player.sendMessage(Component.text(
                            "Вы не состоите в группе!")
                    .color(TextColor.fromHexString("#D93B1C")));
            return;
        }

        if (!party.getLeader().equals(player.getUniqueId())) {
            party.removeMember(player.getUniqueId());

            player.sendMessage(Component.text("Вы покинули группу.")
                    .color(TextColor.fromHexString("#5DE23C")));

            manager.broadcast(party, Component.text(
                    "Игрок " + player.getName() + " покинул группу.")
                    .color(TextColor.fromHexString("#FFD43B")));
        } else {
            manager.broadcast(party, Component.text(
                    "Лидер покинул группу. Группа расформирована.")
                    .color(TextColor.fromHexString("#FFD43B")));

            manager.disbandParty(party.getLeader());
        }
    }

    @Override
    public String getName() {
        return "leave";
    }
}
