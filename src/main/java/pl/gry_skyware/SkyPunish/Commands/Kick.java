package pl.gry_skyware.SkyPunish.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.gry_skyware.SkyPunish.*;

import java.util.Arrays;
import java.util.LinkedList;

public class Kick implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] userArgs) {

        LinkedList<String> args = new LinkedList<>(Arrays.asList(userArgs));

        if(args.size() < 1) {
            sender.sendMessage("za mało argumentów");
            return false;
        }

        String targetName = args.get(0);
        Player targetPlayer = Bukkit.getPlayer(targetName);

        if(targetPlayer == null) {
            sender.sendMessage("nie znaleziono gracza");
            return false;
        }


        Bukkit.getServer().getPlayer(targetPlayer.getName()).kickPlayer(giveReasonForKick(userArgs));
        //Execute.executeKick(targetPlayer, Type.KICK, giveReasonForKick(userArgs));
        Main.database.addRecord(targetPlayer.getName(), giveReasonForKick(userArgs), targetPlayer.getUniqueId().toString(), 2, 0, 0);


        return true;
    }

    private String giveReasonForKick(String[] userArgs){

        LinkedList<String> args = new LinkedList<>(Arrays.asList(userArgs));


        String reason = "";

        for(int i = 1; i<=args.size() - 1; i++){
            String arg = args.get(i) + " ";

            reason = reason + arg;
        }

        return reason;

    }
}