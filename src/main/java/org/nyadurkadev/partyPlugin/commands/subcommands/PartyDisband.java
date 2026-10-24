package org.nyadurkadev.partyPlugin.commands.subcommands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;
import org.nyadurkadev.partyPlugin.PartyPlugin;
import org.nyadurkadev.partyPlugin.api.entities.PartyEntity;
import org.nyadurkadev.partyPlugin.commands.SubCommand;
import org.nyadurkadev.partyPlugin.utils.PartyManager;

public class PartyDisband implements SubCommand {
    @Override
    public void execute(Player player, String[] args) {
        PartyEntity party = PartyPlugin.getManager()
                .getPartyByMember(player.getUniqueId());

        if (!party.getLeader().equals(player.getUniqueId())) {
            player.sendMessage(Component.text(
                            "Вы не являетесь владельцем группы!")
                    .color(TextColor.fromHexString("#D93B1C")));
            return;
        }

        PartyPlugin.getManager().broadcast(party, Component.text(
                        "Лидер расформировал группу.")
                .color(TextColor.fromHexString("#FFD43B")));

        PartyPlugin.getManager().disbandParty(party.getLeader());
    }

    @Override
    public String getName() {
        return "disband";
    }
}
