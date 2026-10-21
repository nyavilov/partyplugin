package org.nyadurkadev.partyPlugin.utils;

import org.bukkit.entity.Player;
import org.nyadurkadev.partyPlugin.api.entities.PartyEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PartyManager {
    private final Map<UUID, PartyEntity> activeParties = new HashMap<>();

    public boolean createParty(Player player) {
        if (activeParties.containsKey(player.getUniqueId())) {
            player.sendMessage("Пати нельзя создать");
            return false;
        }

        PartyEntity leader = new PartyEntity(player.getUniqueId());
        activeParties.put(player.getUniqueId(), leader);
        return true;
    }

    public void inviteToParty(Player leader, Player target) {
        PartyEntity party = activeParties.get(leader.getUniqueId());

        if (party == null) {
            leader.sendMessage("Вы не являетесь лидером пати!");
            return;
        }

        party.addMember(target.getUniqueId());
        leader.sendMessage("Игрок " + target.getName() + " добавлен в вашу группу!");
    }

    public PartyEntity getPartyByMember(UUID uuid) {
        for(PartyEntity party : activeParties.values()) {
            if (party.getMembers().contains(uuid)) {
                return party;
            }
        }
        return null;
    }
}
