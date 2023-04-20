package teams.listenery;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import teams.teleporter.TeleportDoAreny;
import teams.tymy.Tymy;

public class OdpocetZakazPohybu implements Listener {

    private final Tymy tymy;
    private final TeleportDoAreny teleportDoHry;

    public OdpocetZakazPohybu(Tymy tymy, TeleportDoAreny teleportDoHry) {
        this.tymy = tymy;
        this.teleportDoHry = teleportDoHry;
    }

    @EventHandler
    public void zakazPohybu(PlayerMoveEvent event) {
        if (teleportDoHry.jeOdpocet()) {
            Player player = event.getPlayer();
            boolean jeVTymu = tymy.vratTymy()
                    .stream()
                    .anyMatch(tym -> tym.patriDoTymu(player));
            if (jeVTymu) {
                event.setCancelled(true);
            }
        }
    }
}
