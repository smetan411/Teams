package teams.commands;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import teams.teleporter.TeleportDoAreny;

public class VytvorTeleportera extends PlayerCommandExecutor {

    @Override
    boolean onCommand(Player operator, World world, Command command, String s, String[] args) {
        var teleporter = world.spawn(operator.getLocation(), Villager.class);
        teleporter.setAI(false);
        teleporter.setCustomName(TeleportDoAreny.JMENO_TELEPORTERA);
        return true;
    }


}
