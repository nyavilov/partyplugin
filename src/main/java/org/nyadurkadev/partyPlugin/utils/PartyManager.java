package org.nyadurkadev.partyPlugin.utils;

import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.nyadurkadev.partyPlugin.api.entities.PartyEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PartyManager {
    @Getter
    private final Map<UUID, PartyEntity> activeParties = new HashMap<>();

    @Getter
    @Setter
    private Map<UUID, UUID> pendingInvites = new HashMap<>();

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
        PartyEntity party = getPartyByMember(leader.getUniqueId());

        if (party == null) {
            leader.sendMessage("Вы не являетесь лидером пати!");
            return;
        }

        pendingInvites.put(target.getUniqueId(), leader.getUniqueId());

        leader.sendMessage("Игроку " + target.getName() + " отправлено приглашение!");
        target.sendMessage("Игрок" + leader.getName() + "пригласил вас. Введите /party accept");
    }

    public void broadcast(PartyEntity party, Component message) {
        for (UUID memberUUID : party.getMembers()) {
            Player member = Bukkit.getPlayer(memberUUID);
            if (member != null && member.isOnline()) {
                member.sendMessage(message);
            }
        }
    }

    public void disbandParty(UUID uuid) {
        activeParties.remove(uuid);
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
