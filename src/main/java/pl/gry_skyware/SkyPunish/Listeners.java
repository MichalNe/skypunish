package pl.gry_skyware.SkyPunish;

import org.bukkit.BanEntry;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Listeners implements Listener {

    @EventHandler
    public void onPlayerLogin(PlayerJoinEvent event) {
        String nick = event.getPlayer().getName();

        if(Main.database.getBanRecords(nick).get(0).getBanned().equals("1")){
            Bukkit.getServer().getPlayer(nick).kickPlayer(ChatColor.RED + Main.database.getBanRecords(nick).get(0).getReason());
            //Execute.executeKick(event.getPlayer(), Type.KICK, Main.database.getBanRecords(nick).get(0).getReason());
            System.out.println("WYRZUCONY BO BAN");
        }else if(Main.database.getBanRecords(nick).get(0).getBanned().equals("0")){
            Bukkit.getServer().getPlayer(nick).kickPlayer(ChatColor.RED + Main.database.getBanRecords(nick).get(0).getReason());
            //Execute.executeKick(event.getPlayer(), Type.KICK, Main.database.getBanRecords(nick).get(0).getReason());
            System.out.println("WYRZUCONY BO TEMPBAN");
        }else if(Main.database.getBanRecords(nick).get(0).getBanned().equals("3")){
            Main.getInstance().mutedPlayers.add(nick);
            System.out.println("DODANO DO LISTY MUTE");
        }else if(Main.database.getBanRecords(nick).get(0).getBanned() == null){
            return;
        }
    }

    @EventHandler
    public void onChatText(AsyncPlayerChatEvent event){
        String nick = event.getPlayer().getName();
        Player player = event.getPlayer();

        if(Main.getInstance().mutedPlayers.contains(nick)){
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED + "Jestes zmutowany.");
        }
    }

    @EventHandler
    public void onPlayerDisconnect(PlayerQuitEvent event) {
        String nick = event.getPlayer().getName();

        if(Main.getInstance().mutedPlayers.contains(nick)){
            Main.getInstance().mutedPlayers.remove(nick);
        }
    }
}
