package pl.gry_skyware.SkyPunish.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import pl.gry_skyware.SkyPunish.Main;

import java.util.Date;

public class TempMute implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length < 2){
            sender.sendMessage("Brak wystarczającej liczby parametrów");
        }else{
            String nick = args[0];
            String uuid = Bukkit.getServer().getPlayer(nick).getUniqueId().toString();
            Date now = new Date();
            long timeNow = now.getTime();

            Main.getInstance().mutedPlayers.add(nick);
            Main.database.addRecord(nick,giveReasonForMute(args),uuid,4,timeNow, calculateTimeToPunish(args));
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

    private long calculateTimeToPunish(String[] args){
        Date now = new Date();
        long timeNow = now.getTime();
        long timeToCalculate = Integer.parseInt(args[1]) * 24 * 60 * 60;
        String convertingNumber = timeToCalculate + "000";
        long timeTo = timeNow + Integer.parseInt(convertingNumber);

        return timeTo;
    }
}