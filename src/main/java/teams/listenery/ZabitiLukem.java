package teams.listenery;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import teams.tymy.Tym;
import teams.tymy.Tymy;


public class ZabitiLukem implements Listener {

    private final Tymy tymy;
    public ZabitiLukem(Tymy tymy) {
        this.tymy = tymy;
    }
    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if(!(event.getEntity() instanceof Player)) return;
        if(event.getDamager() instanceof Arrow){
            Arrow sip = (Arrow) event.getDamager();
            Player utocnik = (Player) sip.getShooter();
            Player obet = (Player) event.getEntity();
            if(tymy.spoluhraci(utocnik,obet)){
                event.setCancelled(true);
                sip.remove();
            }
        }
    }

}
