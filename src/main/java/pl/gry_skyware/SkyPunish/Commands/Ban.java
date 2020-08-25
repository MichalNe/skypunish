package pl.gry_skyware.SkyPunish.Commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.BanEntry;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.gry_skyware.SkyPunish.*;

public class Ban implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length < 2) {
            sender.sendMessage("Brak wystarczającej liczby parametrów");
            return false;
        }else{


            String nick = args[0];
            String uuid = Bukkit.getServer().getPlayer(nick).getUniqueId().toString();

            Bukkit.getServer().getPlayer(nick).kickPlayer(giveReasonForBan(args));
            Main.database.addRecord(nick, giveReasonForBan(args), uuid, 1, 0, 0);
            System.out.println("DODANO DO BAZY");
            broadcastMessageKick(nick, args);
        }



        return true;
    }

    private String giveReasonForBan(String[] args){
        String reason = "";

        for(int i = 1; i<=args.length - 1; i++){
            String arg = args[i] + " ";

            reason = reason + arg;
        }

        return reason;
    }

    private void broadcastMessageKick(String nick, String[] args){
        for (Player player:Bukkit.getOnlinePlayers()){
            player.sendMessage(ChatColor.RED + nick + " został zbanowany za " + ChatColor.BOLD + giveReasonForBan(args));
        }
    }
}
