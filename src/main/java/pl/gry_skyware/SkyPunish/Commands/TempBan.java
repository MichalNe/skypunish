package pl.gry_skyware.SkyPunish.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import pl.gry_skyware.SkyPunish.BanUnit;
import pl.gry_skyware.SkyPunish.DataBase;
import pl.gry_skyware.SkyPunish.Main;
import pl.gry_skyware.SkyPunish.Settings;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TempBan implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length < 3){
            sender.sendMessage("Za mało argumentów");
            return false;
        }else {

            String nick = args[0];
            String uuid = Bukkit.getServer().getPlayer(nick).getUniqueId().toString();

            long now = System.currentTimeMillis();

                if(Main.database.isInDb(nick) == false) {
                    Bukkit.getServer().getPlayer(nick).kickPlayer(giveReasonForBan(args));
                    Main.database.addRecord(nick, giveReasonForBan(args), uuid, 0, now, calculateTimeToPunish(args));
                    System.out.println("DODANO TEMP DO BAZY");
                }else{
                    Bukkit.getServer().getPlayer(nick).kickPlayer(giveReasonForBan(args));
                    Main.database.updateRecord(nick, giveReasonForBan(args), uuid, 0, now, calculateTimeToPunish(args));
                    System.out.println("POSZEDL UPDATE");
                }
            }



        return true;
    }


    private String giveReasonForBan(String[] args){
        String reason = "";

        for(int i = 3; i<=args.length - 1; i++){
            String arg = args[i] + " ";

            reason = reason + arg;
        }

        return reason;
    }

    private long calculateTimeToPunish(String[] args){

        /*Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
        String dateNow = day + "-" + month + "-" + year;
        String dateTo = day+args[1] + "-" + month + "-" + year;
        */

        long endOfBan = System.currentTimeMillis() + BanUnit.getTicks(args[2], Integer.parseInt(args[1]));

        return endOfBan;
    }
}