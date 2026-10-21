package org.nyadurkadev.partyPlugin.api.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PartyEntity {
    @Getter
    private final UUID leader;

    @Getter
    private final List<UUID> members;

    public void addMember(UUID uuid) {
        if (this.members.contains(uuid)) return;

        this.members.add(uuid);
    }

    public void removeMember(UUID uuid) {
        this.members.remove(uuid);
    }

    public PartyEntity(UUID leader) {
        this.leader = leader;
        this.members = new ArrayList<>(); // Инициализация! Теперь список существует.
        this.members.add(leader);
    }
}
