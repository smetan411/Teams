package teams.tymy;


import org.bukkit.Location;
import org.bukkit.entity.Player;
import teams.factory.ArmorFactory;
import teams.factory.Weapons;

import java.util.HashSet;
import java.util.Set;

public final class Tym {

    private final Set<Player> hraci = new HashSet<>();
    private final JmenoTymu jmenoTymu;
    public Integer pocetKillu = 0;
    private final ArmorFactory armor;
    private final Weapons weapons;
    private final Location spawnPoint;

    public Tym(JmenoTymu jmenoTymu, Location mistoVArene) {
        this.jmenoTymu = jmenoTymu;
        this.spawnPoint = mistoVArene;
        armor = new ArmorFactory(jmenoTymu.getColor());
        weapons = new Weapons();
    }

    private void vybavHrace(Player hrac) {
        var inventory = hrac.getInventory();
        inventory.clear();
        inventory.setChestplate(armor.chestplate());
        inventory.setBoots(armor.boots());
        inventory.setLeggings(armor.leggins());
        inventory.setHelmet(armor.helmet());
        inventory.setItemInMainHand(weapons.sword());
        inventory.setItemInOffHand(weapons.shield());
        inventory.addItem(weapons.bow());
        inventory.addItem(weapons.arrow());
    }

    public void pridej(Player player) {
        vybavHrace(player);
        hraci.add(player);
    }

    public Location getSpawnPoint() {
        return spawnPoint;
    }

    public boolean patriDoTymu(Player player) {
        return hraci.contains(player);
    }

    public Set<Player> vratHrace() {
        return new HashSet<>(hraci);
    }

    public JmenoTymu getJmenoTymu() {
        return jmenoTymu;
    }

    private static int convertSecToTicks(int sec) {
        return sec * 20;
    }

    public void zprava(String text, String subtext) {
        hraci.forEach(hrac -> {
            hrac.sendTitle(text, subtext, 0, convertSecToTicks(10), 0);
        });
    }
}
