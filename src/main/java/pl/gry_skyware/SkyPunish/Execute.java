package pl.gry_skyware.SkyPunish;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Execute {
    public static void executeKick(Player player, Type type) {
        player.kickPlayer(
                ChatColor.translateAlternateColorCodes('&',
                        "\n" +
                                "&2Zostałeś wyrzucony z serwera" +
                                "\n&4TYP: " + type +
                                "\nPowód" +
                                "\nKto:" +
                                "\nDługość" +
                                "\nDo:" +
                                "\n" +
                                "\nOdwołanie:"
                )
        );
    }
    public static void executeKick(Player player, Type type, String reason) {
        player.kickPlayer(
                ChatColor.translateAlternateColorCodes('&',
                        "\n" +
                                "&2Zostałeś wyrzucony z serwera" +
                                "\n&4TYP: " + type +
                                "\nPowód" + reason +
                                "\nKto:" +
                                "\nDługość" +
                                "\nDo:" +
                                "\n" +
                                "\nOdwołanie:"
                )
        );
    }
}
