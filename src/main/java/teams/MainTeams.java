package teams;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.plugin.java.JavaPlugin;
import teams.commands.KonecHry;
import teams.commands.VytvorTeleportera;
import teams.listenery.*;
import teams.lobby.Lobby;
import teams.lobby.LobbyCreator;
import teams.teleporter.TeleportDoAreny;
import teams.teleporter.TeleportDoLobby;
import teams.tymy.SkoreTymu;
import teams.tymy.Tymy;

public class MainTeams extends JavaPlugin {

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        Bukkit.setDefaultGameMode(GameMode.ADVENTURE);
        //data
        LobbyCreator lobbyCreator = new LobbyCreator(this);
        Lobby lobby = lobbyCreator.createLobby();
        Tymy tymy = new Tymy();
        TeleportDoAreny teleportDoAreny = new TeleportDoAreny(this, lobby, tymy);
        TeleportDoLobby teleportDoLoby = new TeleportDoLobby(lobby, tymy);
        SkoreTymu skoreTymu = new SkoreTymu(Bukkit.getScoreboardManager().getNewScoreboard(), tymy);

        //listeners
        getServer().getPluginManager().registerEvents(new PripojeniDoLobby(lobby), this);
        getServer().getPluginManager().registerEvents(new StartHry(teleportDoAreny, skoreTymu), this);
        getServer().getPluginManager().registerEvents(new ZabitiNepritele(tymy, teleportDoLoby, teleportDoAreny, skoreTymu), this);
        getServer().getPluginManager().registerEvents(new OdpocetZakazPohybu(tymy, teleportDoAreny), this);
        getServer().getPluginManager().registerEvents(new RespawnHrace(tymy), this);
        //commandy
        getCommand("+vytvorTeleportera").setExecutor(new VytvorTeleportera());
        getCommand("+konec").setExecutor(new KonecHry(teleportDoLoby, tymy));
    }

}
