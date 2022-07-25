package teams.lobby;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.RandomUtils;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

public final class Lobby {
    private final Location roh1;
    private final Location roh2;

    public Lobby(Location roh1, Location roh2) {
        this.roh1 = roh1;
        this.roh2 = roh2;
    }

    public boolean jeVLobby(Location location) {
        //between x
        var minX = Math.min(roh1.getX(), roh2.getX());
        var maxX = Math.max(roh1.getX(), roh2.getX());
        if (!between(location.getX(), minX, maxX)) return false;

        //between Y
        var minY = Math.min(roh1.getY(), roh2.getY());
        var maxY = Math.max(roh1.getY(), roh2.getY());
        if (!between(location.getY(), minY, maxY)) return false;

        //between Z
        var minZ = Math.min(roh1.getZ(), roh2.getZ());
        var maxZ = Math.max(roh1.getZ(), roh2.getZ());
        if (!between(location.getZ(), minZ, maxZ)) return false;

        //vse v poradku
        return true;
    }

    private boolean between(double value, double min, double max) {

        return value >= min && value <= max;
    }

    public Location nahodneMistoVLobby() {
        double x = randomPoint(roh1.getX(), roh2.getX());
        //y random
        var minY = Math.min(roh1.getY(), roh2.getY());
        //between Z
        double z = randomPoint(roh1.getZ(), roh2.getZ());
        return new Location(roh1.getWorld(), x, minY, z);
    }

    private double randomPoint(double point1, double point2) {
        var min = Math.min(point1, point2);
        var max = Math.max(point1, point2);
        if (min < 0) {
            return RandomUtils.nextDouble(0, max + Math.abs(min)) + min;
        } else {
            return RandomUtils.nextDouble(min, max);
        }
    }

    public List<Player> hraciVLobby() {
        World world = roh1.getWorld();
        return world.getPlayers().stream()
                .filter(player -> jeVLobby(player.getLocation()))
                .collect(Collectors.toList());
    }
}