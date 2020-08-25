package pl.gry_skyware.SkyPunish.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import pl.gry_skyware.SkyPunish.Main;

public class Mute implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length < 2){
            sender.sendMessage("Brak wystarczającej liczby parametrów");
        }else{
            String nick = args[0];
            String uuid = Bukkit.getServer().getPlayer(nick).getUniqueId().toString();

            Main.getInstance().mutedPlayers.add(nick);
            Main.database.addRecord(nick,giveReasonForMute(args),uuid,3,0,0);
        }

        return true;
    }

    private String giveReasonForMute(String[] args){
        String reason = "";

        for(int i = 1; i<=args.length - 1; i++){
            String arg = args[i] + " ";

            reason = reason + arg;
        }

        return reason;
    }
}