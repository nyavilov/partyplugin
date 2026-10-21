package org.nyadurkadev.partyPlugin.commands.subcommands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;
import org.nyadurkadev.partyPlugin.PartyPlugin;
import org.nyadurkadev.partyPlugin.commands.SubCommand;

public class PartyCreate implements SubCommand {
    @Override
    public void execute(Player player, String[] args){
        if (PartyPlugin.getManager().createParty(player)) {
            player.sendMessage(Component.text(
                    "Вы успешно создали пати!"
            ).color(TextColor.fromHexString("#96DE3E")));
        } else {
            player.sendMessage(Component.text(
                    "Вы уже состоите в пати!")
                    .color(TextColor.fromHexString("#D93B1C")));
        }

    }

    @Override
    public String getName() {
        return "create";
    }
}
