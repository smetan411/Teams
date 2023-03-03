package teams.teleporter;


import teams.lobby.Lobby;
import teams.tymy.Tymy;

public class TeleportDoLobby {
    private final Lobby lobby;
    private final Tymy tymy;

    public TeleportDoLobby(Lobby lobby, Tymy tymy) {
        this.lobby = lobby;
        this.tymy = tymy;
    }

    public void teleport() {
        tymy.vratTymy().forEach( tym -> {
            tym.vratHrace().forEach(hrac -> {
                var mistovLoby = lobby.nahodneMistoVLobby();
                hrac.teleport(mistovLoby);
            });
        });
    }
}
