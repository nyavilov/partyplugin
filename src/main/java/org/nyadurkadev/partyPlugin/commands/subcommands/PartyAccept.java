package org.nyadurkadev.partyPlugin.commands.subcommands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.nyadurkadev.partyPlugin.PartyPlugin;
import org.nyadurkadev.partyPlugin.api.entities.PartyEntity;
import org.nyadurkadev.partyPlugin.commands.SubCommand;
import org.nyadurkadev.partyPlugin.utils.PartyManager;

import java.util.UUID;

public class PartyAccept implements SubCommand {
    @Override
    public void execute(Player player, String[] args) {
        PartyManager manager = PartyPlugin.getManager();
        UUID playerUUID = player.getUniqueId();

        UUID leaderUUID = PartyPlugin.getManager()
                .getPendingInvites().get(playerUUID);

        if (leaderUUID == null) {
            player.sendMessage(Component.text(
                            "У вас нет активных приглашений!")
                    .color(TextColor.fromHexString("#D93B1C")));
            return;
        }

        if (manager.getPartyByMember(playerUUID) != null) {
            player.sendMessage(Component.text(
                            "Вы уже состоите в группе!")
                    .color(TextColor.fromHexString("#D93B1C")));
            return;
        }

        PartyEntity party = manager.getActiveParties().get(leaderUUID);

        if (party == null) {
            player.sendMessage(Component.text(
                            "Группы больше не существует")
                    .color(TextColor.fromHexString("#D93B1C")));
            manager.getPendingInvites().remove(playerUUID);
            return;
        }

        party.addMember(playerUUID);
        manager.getPendingInvites().remove(playerUUID);

        player.sendMessage((Component.text(
                "Вы успешно вступили в группу!")
                .color(TextColor.fromHexString("#96DE3E"))));

        Player leader = Bukkit.getPlayer(leaderUUID);
        if (leader != null) leader.sendMessage(player.getName() + " принял ваше приглашение!");
    }

    @Override
    public String getName() {
        return "accept";
    }
}
