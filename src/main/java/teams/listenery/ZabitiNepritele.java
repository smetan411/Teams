package teams.listenery;


import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import teams.teleporter.TeleportDoAreny;
import teams.teleporter.TeleportDoLobby;
import teams.tymy.SkoreTymu;
import teams.tymy.Tym;
import teams.tymy.Tymy;

public class ZabitiNepritele extends PlayerDamageByPlayerListener {

    public static final int limit = 10;
    private final Tymy tymy;
    private final TeleportDoLobby teleportDoLoby;
    private final TeleportDoAreny teleportDoAreny;
    private final SkoreTymu skoreTymu;

    public ZabitiNepritele(Tymy tymy, TeleportDoLobby teleportDoLoby, TeleportDoAreny teleportDoAreny, SkoreTymu skoreTymu) {
        this.tymy = tymy;
        this.teleportDoLoby = teleportDoLoby;
        this.skoreTymu = skoreTymu;
        this.teleportDoAreny = teleportDoAreny;
    }

    @Override
    public void playerDamaged(Player utocnik, Player zraneny, double damage, EntityDamageByEntityEvent event) {
        if (tymy.spoluhraci(utocnik, zraneny)) event.setCancelled(true);
        if (zraneny.getHealth() < damage) {
            for (Tym tym: tymy.vratTymy()) {
                if (tym.vratHrace().contains(utocnik)) {
                    tym.pocetKillu++;
                    skoreTymu.update();
                    if (tym.pocetKillu >= limit) {
                        konecHry(tym);
                    }
                }
            }
        }
    }

    private void konecHry(Tym viteznyTym) {
        teleportDoLoby.teleport();
        tymy.vratTymy().forEach(tymZ -> {
            if (tymZ.getJmenoTymu() == viteznyTym.getJmenoTymu()) {
                tymZ.zprava("Game Over", "You Win");
            } else {
                tymZ.zprava("Game Over", "You Lost");
            }
        });
        tymy.clear();
    }
}
